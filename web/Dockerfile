# 设置基础镜像
FROM nginx
# 定义作者
MAINTAINER hycer
# 将dist文件中的内容复制到 /usr/share/nginx/html/ 这个目录下面
# /nginx/html是Nginx.conf中index对应目录，不同版本Nginx可能有区别
COPY dist/  /usr/share/nginx/html/
