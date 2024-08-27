<p align="center">
    <div style ="background-color: white">
        <img alt="logo" src="https://gitee.com/hycerlance/PicGo/raw/main/common/logo3.png">
	</div>
</p>
<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">FangChe</h1>
<h4 align="center">基于spring+Vue3前后端分离式的在线教育平台</h4>

<div align="center">

![Static Badge](https://img.shields.io/badge/Licence-MIT-blue)
![Static Badge](https://img.shields.io/badge/Frontend-vue-orange)
![Static Badge](https://img.shields.io/badge/Backend-Spring-green)
![Static Badge](https://img.shields.io/badge/Database-MySQL-red)
![Static Badge](https://img.shields.io/badge/File-MongoDB-yellow)
![Static Badge](https://img.shields.io/badge/Microservice-SpringCloud-purple)

</div>




## 技术栈介绍

* 前端技术栈 [Vue3](https://v3.cn.vuejs.org) + [Element-plus](https://element-plus.org/zh-CN/)+ [Vite](https://cn.vitejs.dev) 。
* 后端[Spring Boot](https://docs.spring.io/spring-boot/index.html)+[Spring Cloud](https://docs.springcloud.cc/)+[MyBatis-Plus](https://baomidou.com/)
* 数据库[Mysql]([MySQL](https://www.mysql.com/cn/))+[MongoDB](https://www.mongodb.com/zh-cn)。

## 项目结构

```
FangChe
├─web 									# 前端文件夹
|  ├─.gitignore
|  ├─index.html
|  ├─jsconfig.json
|  ├─package.json
|  ├─README.md
|  ├─vite.config.js
|  ├─src
|  |  ├─App.vue
|  |  ├─main.js
|  |  ├─components
|  |  ├─assets
|  ├─public
|  |   └favicon.ico
├─server								# 后端文件夹
|   ├─static							# 静态资源文件夹 包括用户头像,课程图片
|   ├─Service2							# Service2SpringBoot微服务,涉及视频,测评接口
|   |    ├─.gitignore
|   |    ├─Dockerfile
|   |    ├─HELP.md
|   |    ├─mvnw
|   |    ├─mvnw.cmd
|   |    ├─pom.xml
|   |    ├─src
|   |    |  ├─main
|   |    |  |  ├─resources
|   |    |  |  |     ├─application.yml
|   |    |  |  ├─java
|   |    |  |  |  ├─com
|   |    |  |  |  |  ├─fangche
|   |    |  |  |  |  |    ├─service2
|   |    |  |  |  |  |    |    ├─Service2Application.java
|   ├─Service1							# Service1 SpringBoot微服务,涉及用户,课程接口
|   |    ├─.gitignore
|   |    ├─Dockerfile
|   |    ├─HELP.md
|   |    ├─mvnw
|   |    ├─mvnw.cmd
|   |    ├─pom.xml
|   |    ├─src
|   |    |  ├─main
|   |    |  |  ├─resources
|   |    |  |  |     ├─application.yml
|   |    |  |  ├─java
|   |    |  |  |  ├─com
|   |    |  |  |  |  ├─fangche
|   |    |  |  |  |  |    ├─service1
|   |    |  |  |  |  |    |    ├─Service1Application.java
|   ├─Gateway							# API网关微服务
|   |    ├─.gitignore
|   |    ├─Dockerfile
|   |    ├─HELP.md
|   |    ├─mvnw
|   |    ├─mvnw.cmd
|   |    ├─pom.xml
|   |    ├─src
|   |    |  ├─main
|   |    |  |  ├─resources
|   |    |  |  |     └application.yml
|   |    |  |  ├─java
|   |    |  |  |  ├─com
|   |    |  |  |  |  ├─fangche
|   |    |  |  |  |  |    ├─gateway
|   |    |  |  |  |  |    |    └GatewayApplication.java
|   ├─EurekaServer						# 服务注册与发现中心
|   |      ├─.gitignore
|   |      ├─HELP.md
|   |      ├─mvnw
|   |      ├─mvnw.cmd
|   |      ├─pom.xml
|   |      ├─src
|   |      |  ├─main
|   |      |  |  ├─resources
|   |      |  |  |     └application.yml
|   |      |  |  ├─java
|   |      |  |  |  ├─com
|   |      |  |  |  |  ├─fangche
|   |      |  |  |  |  |    ├─eurekaserver
|   |      |  |  |  |  |    |      └EurekaServerApplication.java
|   ├─ConfigServer						# 配置中心
|   |      ├─.gitignore
|   |      ├─Dockerfile
|   |      ├─HELP.md
|   |      ├─mvnw
|   |      ├─mvnw.cmd
|   |      ├─pom.xml
|   |      ├─src
|   |      |  ├─main
|   |      |  |  ├─resources
|   |      |  |  |     ├─application.yml
|   |      |  |  |     ├─config-repo	# 配置文件仓库,包含除配置中心外所有微服务的配置文件
|   |      |  |  |     |      ├─EurekaServer-dev.yml
|   |      |  |  |     |      ├─Gateway-dev.yml
|   |      |  |  |     |      ├─Service1-dev.yml
|   |      |  |  |     |      └Service2-dev.yml
|   |      |  |  ├─java
|   |      |  |  |  ├─com
|   |      |  |  |  |  ├─fangche
|   |      |  |  |  |  |    ├─configserver
|   |      |  |  |  |  |    |      └ConfigServerApplication.java
```



## 前端运行

```bash
# 克隆项目
git clone https://github.com/LanceHE6/FangChe.git

# 进入前端目录
cd web

# 安装依赖
npm install

# 启动服务
npm run dev

# 前端访问地址 http://localhost:5173
```

## 后端运行

```bash
# 用IDEA打开后端文件夹并等待加载Maven项目

# 在server/ConfigServer/src/main/resources/config-repo文件夹下修改各个微服务的配置文件
# 主要为Service1的数据库及邮件服务配置
spring:
  application:
    name: Service1
  # 静态资源访问
  mvc:
    static-path-pattern: /static/**
  web:
    resources:
      static-locations: file:./static/
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/fangche?createDatabaseIfNotExist=true&serverTimezone=GMT%2B8&useSSL=true
    username: root
    password:root
  # 邮件服务
  mail:
    host: smtp.qq.com
    username: fangche@qq.com
    password: bjg**********die
    port: 587
    default-encoding: UTF-8
    properties:
      mail:
        smtp:
          ssl:
            enable: false
            required: false
# Service2的数据库配置
spring:
  application:
    name: Service2
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3307/fangche?createDatabaseIfNotExist=true&serverTimezone=GMT%2B8&useSSL=true
    username: root
    password: root
  servlet:
    multipart:
      max-file-size: 1204MB
      max-request-size: 1024MB
  data:
    mongodb:
      host: localhost
      port: 27017
      database: fangche

# 微服务启动顺序
# 1.ConfigServer
# 2.EurekaServer	
# 3.Gateway
# 4.Service1
# 5.Service2
```

## 项目架构

![](https://gitee.com/hycerlance/PicGo/raw/main/common/20240709091227.png)

## 内置功能

### 用户管理

* 基于邮箱的用户注册与登录
* 基于邮箱的重置密码功能
* 用户基础信息管理(个人资料,头像,密码)
* 基于AOP的用户鉴权

### 课程

* 课程增删
* 课程分页查询
* 课程收藏

### 课程视频

* 视频增删改
* 视频分页查询
* 视频点击量统计

### 测评试题

* 测评试题增删改查

## 权限组

#### 普通用户

* 浏览课程,观看视频

#### 讲师

* 发布课程,视频
* 发布测评
* 管理*自己*发布的内容

#### 管理员

* 管理网站所有内容



## 技术点介绍

### 用户密码加密存储

```java
public static String generateSaltPassword(String password) {
        Random random = new Random();
        //生成一个16位的随机数
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = stringBuilder.length();
        if (len < 16) {
            stringBuilder.append("0".repeat(Math.max(0, 16 - len)));
        }
        // 生成盐
        String salt = stringBuilder.toString();
        //将盐加到明文中，并生成新的MD5码
        password = md5Hex(password + salt);
        //将盐混到新生成的MD5码中，之所以这样做是为了后期更方便的校验明文和秘文
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }
```

这个方法的作用是通过以下步骤生成一个带盐的密码哈希：

1. 生成一个16位的随机盐值。
2. 将盐值和密码组合，生成MD5哈希值。
3. 将生成的盐值和MD5哈希值混合，形成一个新的48位长的字符串。

这个方法增强了密码存储的安全性，使得即使两个用户使用相同的密码，由于使用了不同的盐值，其存储的哈希值也会不同，从而增加了攻击者进行字典攻击和暴力破解的难度。

**密码校验:**

1. **提取盐和MD5哈希值**：从存储的48位字符串中提取出16位盐和32位MD5哈希值。
2. **重新生成哈希值**：使用提取出的盐和输入的密码重新计算MD5哈希值。
3. **比较哈希值**：将重新计算的MD5哈希值与提取出的MD5哈希值进行比较，以验证密码是否正确。

### 基于AOP的JWT权限控制

```java
public static String generateJwtToken(User user){

        // 头部 map / Jwt的头部承载，第一部分
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        //载荷
        Map<String,Object> claims = new HashMap<String,Object>();

        //私有声明 
        claims.put("id", user.getId());
        claims.put("account", user.getAccount());
        claims.put("password", user.getPassword());
        claims.put("nickname", user.getNickname());
        claims.put("role", user.getRole());
        claims.put("created_at", user.getCreatedAt());
        claims.put("session_id", user.getSessionId());

        //标准中注册的声明
        claims.put("iss", jwt_iss);
        return Jwts.builder() // 设置jwt的body
                .setHeader(map)         // 头部信息
                .setClaims(claims)      // 载荷信息
                .setId(UUID.randomUUID().toString()) // 设置jti(JWT ID)：是JWT的唯一标识，从而回避重放攻击。
                .setIssuedAt(new Date())       // 设置iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + access_token_expiration * 1000)) // 设置exp 3天
                .setSubject(subject)    //设置sub：代表这个jwt所面向的用户，所有人
                .signWith(SIGNATURE_ALGORITHM, secret)//设置签名
                .compact(); // 压缩
    }
```

在JWT载荷部分中包含`session_id`主要是为了增强安全性、便于管理用户会话:

尽管JWT本身是无状态的，但通过包含一个`session_id`，可以实现更细粒度的会话控制。

- **会话跟踪**：通过在载荷中包含`session_id`，服务器可以更容易地跟踪用户的会话状态.当用户登录时，服务器生成当前时间戳用作唯一的会话ID，并将其包含在JWT中。这样，即使JWT在多个请求中被使用，服务器也可以通过会话ID识别和管理用户的会话。
- **会话过期控制**：虽然JWT本身有过期时间（exp），但有时需要更灵活的会话过期控制。通过`session_id`，服务器可以在会话表中设置会话的过期时间和状态。如果会话被标记为无效或已过期，服务器可以拒绝处理该JWT，即使JWT本身尚未过期。

* **单点登录**: 通过唯一`session_id`, 无需在数据库中存储token,只需比对会话id即可判断是否为有效登录.在JWT中包含`session_id`可以增加一层安全保障。即使JWT被盗用，攻击者也无法轻易地伪造一个有效的`session_id`。

```java
//注解需作用在方法上
@Target(ElementType.METHOD)
//注解会保留到程序运行期
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {
    byte value() default Permission.NORMAL;

}
```

基于AOP进行权限检查:

1. **代码整洁**：权限检查逻辑集中管理，不会分散在各个业务方法中。
2. **灵活性高**：可以通过修改注解值快速调整权限要求，无需修改业务方法。
3. **易于维护**：当权限逻辑发生变化时，只需修改切面或拦截器的实现，而无需修改业务方法。





### 基于Mongodb的测试题文档存储

```java
    public Result addFile(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请上传文件");
        }
        try {
            String str = new String(file.getBytes(), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            QuestionDto[] questions = gson.fromJson(str, QuestionDto[].class);
            ArrayList<QuestionDto> list = new ArrayList<>(Arrays.asList(questions));
            mongoTemplate.insertAll(list);
        } catch (Exception e) {
            return Result.error("传入的不是有效的json文件");
        }
        return Result.success();
    
```

传入的文件格式不限，但是内容必须是一个json格式

举例：

```json


[
    {
        "text": "Java编程题3：以下哪个Java关键字用于声明一个接口？",  
        "options": [
            {  
                "text": "A. class",  
                "isCorrect": false  
            },  
            {  
                "text": "B. interface",  
                "isCorrect": true  
            },  
            {  
                "text": "C. enum",  
                "isCorrect": false  
            },  
            {  
                "text": "D. void",  
                "isCorrect": false  
            }  
        ],  
        "correctAnswer": "B",  
        "type": "编程"  
    }
] 
//必须是以列表的形式将json对象封装进去，同时json对象的格式严格要按照例子的要求,同一个文档中的测试题，type可以不同。
```

