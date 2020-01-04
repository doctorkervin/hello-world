if i set the spring redisConfig use follow code

--------------------------
 private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    	//return new Jackson2JsonRedisSerializer(Object.class);
    }

    private RedisSerializer<Object> valueSerializer() {
        //return new GenericJackson2JsonRedisSerializer();
        return new JdkSerializationRedisSerializer();
    }

--------------------------
then i got cache objedct from redis, i cast the object to pojo then thows  Java.lang.ClassCastException

As explained in the documentation that I linked to above, DevTools uses two separate ClassLoaders: the app class loader and a restart class loader. The classes in your application (rediscache module) are loaded by the restart class loader so that they can be quickly reloaded as you make changes.

In your RedisCache class, you're creating a JdkSerializationRedisSerializer without specifying a ClassLoader. As a result, it uses the app class loader. This means that you end up with a User loaded by the app class loader being cast to the User type loaded by the restart class loader.

To fix the problem you need to specify a class loader when you create the serializer. For example:
------------------------------

RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());

-----------------------------------
then fix this problem

-------------------》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》
spring-data-redis的序列化类有下面这几个:

GenericToStringSerializer: 可以将任何对象泛化为字符串并序列化
Jackson2JsonRedisSerializer: 跟JacksonJsonRedisSerializer实际上是一样的
JacksonJsonRedisSerializer: 序列化object对象为json字符串
JdkSerializationRedisSerializer: 序列化java对象
StringRedisSerializer: 简单的字符串序列化

1，使用JdkSerializationRedisSerializer序列化


用JdkSerializationRedisSerializer序列化的话，被序列化的对象必须实现Serializable接口。

在存储内容时，除了属性的内容外还存了其它内容在里面，总长度长，且不容易阅读。

对于最下面的代码，存到redis里的内容如下：

redis 127.0.0.1:6379> get users/user1
"\xac\xed\x00\x05sr\x00!com.oreilly.springdata.redis.User\xb1\x1c \n\xcd\xed%\xd8\x02\x00\x02I\x00\x03ageL\x00\buserNamet\x00\x12Ljava/lang/String;xp\x00\x00\x00\x14t\x00\x05user1"



2，使用JacksonJsonRedisSerializer序列化

如果需要保存对象为json的话推荐使用JacksonJsonRedisSerializer，它不仅可以将对象序列化，

还可以将对象转换为json字符串并保存到redis中，但需要和jackson配合一起使用。

用JacksonJsonRedisSerializer序列化的话，被序列化的对象不用实现Serializable接口。

Jackson是利用反射和getter和setter方法进行读取的，如果不想因为getter和setter方法来影响存储，就要使用注解来定义被序列化的对象。

Jackson序列化的结果清晰，容易阅读，而且存储字节少，速度快，推荐。

对于最下面的代码，存到redis里的内容如下：

redis 127.0.0.1:6379> get json/user1
"{\"userName\":\"user1\",\"age\":20}"
redis 127.0.0.1:6379> strlen json/user1
(integer) 29



3，使用StringRedisSerializer序列化

一般如果key-value都是string的话，使用StringRedisSerializer就可以了



代码：

@Test
public void testJacksonSerialiable()
{    
    RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();    
    redis.setConnectionFactory(connectionFactory);    
    redis.setKeySerializer(ApplicationConfig.StringSerializer.INSTANCE);    
    redis.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));    
    redis.afterPropertiesSet();    
    
    
    ValueOperations<String, Object> ops = redis.opsForValue();    
    User user1 = new User();    
    user1.setUserName("user1");    
    user1.setAge(20);    
    User user11 = null;    
    String key1 = "json/user1";    
    long begin = System.currentTimeMillis();    
    for (int i = 0; i < 100; i++) {        
        ops.set(key1,user1);        
        user11 = (User)ops.get(key1);    
    }    
    long time = System.currentTimeMillis() - begin;    System.out.println("json time:"+time);    
    assertThat(user11.getUserName(),is("user1"));
}
