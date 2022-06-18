
FROM amazoncorretto:8
MAINTAINER weibocy weibocy@hotmail.com
# todo 环境配置应该由命令行参数传入 以配合maven-docker插件部署 同时还要兼容命令行运行方式
# todo 复制的包名称也应该由命令行参数传入 以配合maven-docker插件部署 同时还要兼容命令行运行方式

# 中文字体 centos镜像
# ADD simsun.ttc /usr/share/fonts/simsun.ttc
RUN yum install -y google-noto-sans-simplified-chinese-fonts \
    cjkuni-ukai-fonts cjkuni-uming-fonts man-pages-zh-CN \
    opencc.x86_64 texlive-arphic wqy-microhei-fonts \
    && yum clean all && rm -rf /var/cache/yum

# 时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 匿名卷
VOLUME /tmp /logs

# 添加服务jar包
ADD target/ruoyi-application-admin-22.6.0.jar /app.jar

# JVM参数（必须配合docker资源限制机制，比如最大给多少内存容器即可，JVM会自动分配）
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:InitialRAMPercentage=50.0 -XX:MinRAMPercentage=50.0 -XX:MaxRAMPercentage=50.0 -XX:-UseAdaptiveSizePolicy"

# 环境配置
ENV RUN_ENV="dev"

# 导出端口
EXPOSE 8080

# 启动命令
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/logs/ -jar /app.jar --spring.profiles.active=$RUN_ENV
