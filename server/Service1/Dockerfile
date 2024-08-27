
FROM openjdk:17-alpine

ENV TZ=Asia/Shanghai
# 设置工作目录
WORKDIR /app

# 复制生成的jar文件到容器中
COPY target/Service1-0.0.1-SNAPSHOT.jar app.jar

# 暴露应用程序的端口
EXPOSE 8081

# 启动应用程序
ENTRYPOINT ["java", "-jar", "app.jar"]

