import com.fame.LockAndTransactionApplication;
import com.fame.dao.CommentRepository;
import com.fame.entity.Comment;
import com.fame.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: hello-world
 * @desc: 测试并发评论
 * @author: kervin
 * @time: 2019-12-20 11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LockAndTransactionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentControllerTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

   /* @LocalServerPort
    int randomServerPort;*/
    @Test
    public void concurrentComment() {
        String url = "http://localhost:8080/comment";
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("articleId", "1");
                params.add("content", "测试内容" + finalI);
                System.out.println(params.toString());
                String result = testRestTemplate.postForObject(url, params, String.class);
            }).start();
        }

    }

    @Autowired
    private com.fame.dao.CommentRepository commentRepository;

    @Test
    public void test01(){
        /** Jpa 的deleteById 关联的数据库执行了两条sql，先通过id，将这条记录查出来，然后删除这条记录*/
        //commentRepository.deleteById(1L);
        /** 使用原生的sql，只执行一条sql语句 delet from*/
        commentRepository.deleteInBulkById(2L);
    }

    /**
     * 并发测试重试机制
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {

        int clientTotal = 100;
        // 同时并发执行的线程数
        int threadTotal = 20;
        int count = 0;
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    //执行此方法用于获取执行许可，当总计未释放的许可数不超过200时，
                    //允许通行，否则线程阻塞等待，直到获取到许可。
                    semaphore.acquire();

                    //并发业务处理

                    //释放许可
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();

                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();
    }
}
