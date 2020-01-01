##### 方法1 在pom.xml文件中添加
```
<build>
  <plugins>
<!-- maven 打包时跳过测试 -->
			<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <skip>true</skip>
                </configuration>
            </plugin>
       </plugins>
   <build>  

```

##### 方法2 使用mvn命令 ：使用mvn命令打包项目时 可以使用下列语句 
```
mvn clean package -DskipTests
``` 