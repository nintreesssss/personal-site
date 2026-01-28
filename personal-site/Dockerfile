# 阶段1: 构建 - 用 Java 25 + 手动安装 Maven（确保 mvn 存在）
FROM eclipse-temurin:25 AS builder

# 手动安装 Maven 3.9.9（Railway 环境有时没预装）
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip \
    && unzip apache-maven-3.9.9-bin.zip -d /opt \
    && rm apache-maven-3.9.9-bin.zip \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn \
    && mvn -version  # 日志里会显示 Maven 版本，方便检查

WORKDIR /app

# 缓存依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 打包
COPY src ./src
RUN mvn clean package -DskipTests

# 阶段2: 运行
FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE ${PORT:-8080}
ENTRYPOINT ["java", "-jar", "app.jar"]