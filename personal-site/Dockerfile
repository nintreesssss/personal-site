# 阶段 1: 构建 - 用 Java 25 基础镜像 + 手动安装 Maven（最稳，避免镜像问题）
FROM eclipse-temurin:25 AS builder

# 安装 Maven（因为基础镜像只有 JDK，没有 Maven）
RUN apt-get update && apt-get install -y maven wget unzip \
    && wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip \
    && unzip apache-maven-3.9.9-bin.zip -d /opt \
    && rm apache-maven-3.9.9-bin.zip \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn

WORKDIR /app

# 先复制 pom.xml 缓存依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并打包
COPY src ./src
RUN mvn clean package -DskipTests

# 阶段 2: 运行 - 轻量 JRE
FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE ${PORT:-8080}
ENTRYPOINT ["java", "-jar", "app.jar"]