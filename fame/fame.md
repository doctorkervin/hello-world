### 验证数据库并发更新

 ```
 发现@Version数据库记录的更新还是不是全部都更新成功，但是出现异常了
 现在看到Article里的comment_count和Comment的数量都不是100了，但是这两个的值必定是一样的了。因为刚才我们处理的时候假如Article表的数据发生了冲突，
 那么就不会更新到数据库里，这时抛出异常使其事务回滚，这样就能保证没有更新Article的时候Comment也不会插入，就解决了数据不统一的问题。
 
 这种直接回滚的处理方式用户体验比较差，通常来说如果判断Article更新条数为0时，会尝试重新从数据库里查询信息并重新修改，
 再次尝试更新数据，如果不行就再查询，直到能够更新为止。当然也不会是无线的循环这样的操作，会设置一个上线，比如循环3次查询修改更新都不行，这时候才会抛出异常
 
 如果每次访问冲突概率小于 20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次 数不得小于 3 次

```
#### 乐观锁
```
乐观锁适合写少读多的场景
版本号机制就是在数据库中加一个字段当作版本号，比如我们加个字段version。
那么这时候拿到Article的时候就会带一个版本号，
比如拿到的版本是1，然后你对这个Article一通操作，操作完之后要插入到数据库了
。发现哎呀，怎么数据库里的Article版本是2，和我手里的版本不一样啊，说明我手里的Article不是最新的了，
那么就不能放到数据库了。这样就避免了并发时数据冲突的问题。
```

#### 悲观锁
```
悲观锁顾名思义就是悲观的认为自己操作的数据都会被其他线程操作，所以就必须自己独占这个数据，可以理解为”独占锁“。
在java中synchronized和ReentrantLock等锁就是悲观锁，数据库中表锁、行锁、读写锁等也是悲观锁。
行锁就是操作数据的时候把这一行数据锁住，其他线程想要读写必须等待，但同一个表的其他数据还是能被其他线程操作的。
只要在需要查询的sql后面加上for update，就能锁住查询的行，
特别要注意查询条件必须要是索引列，如果不是索引就会变成表锁，把整个表都锁住。

```