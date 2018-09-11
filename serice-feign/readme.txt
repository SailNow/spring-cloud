springcloud服务消费者使用feign，不能使用@EnableFeignClients 注解解决办法,使用的是springboot版本2.0.2，springcloud版本为Finchley.RC2
<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
      <version>2.0.0.RC1</version>
</dependency>

二、断路由器
Feign是自带断路器的，在D版本的Spring Cloud中，它没有默认打开。需要在配置文件中配置打开它，在配置文件加以下代码
feign.hystrix.enabled=true

