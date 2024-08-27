
FROM openjdk:17-alpine

ENV TZ=Asia/Shanghai
# 设置工作目录
WORKDIR /app

# 复制生成的jar文件到容器中
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar app.jar
#确保这行存在，否则无法读取配置文件
COPY src/main/resources/config-repo /config-repo


# 暴露应用程序的端口
EXPOSE 8888

# 启动应用程序
ENTRYPOINT ["java", "-jar", "app.jar"]

