import com.fame.LockAndTransactionApplication;
import com.fame.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
}
