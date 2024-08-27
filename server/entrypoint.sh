#!/bin/bash

# 启动服务1
nohup java -jar /jars/ConfigServer.jar &

# 等待服务1启动完成（可选，如果服务启动很快，可能不需要）
sleep 5
# 启动服务1
nohup java -jar /jars/Gateway.jar &

# 启动服务1
nohup java -jar /jars/Service1.jar &

# 等待服务1启动完成（可选，如果服务启动很快，可能不需要）
# sleep 10

# 启动服务2
nohup java -jar /jars/Service2.jar &

# （可选）添加更多服务的启动命令

# 等待所有服务启动完成（如果需要的话）
# wait

# 脚本保持运行，防止容器退出（通常不需要，因为后台运行的服务会保持容器活跃）
# tail -f /dev/null