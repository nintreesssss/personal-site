# 阶段1: 构建 - 用 Java 25 + 手动安装 Maven
FROM eclipse-temurin:25 AS builder

# 手动安装 Maven（因为基础镜像只有 JDK，没有 Maven）
RUN apt-get update && apt-get install -y wget unzip \
    && wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip \
    && unzip apache-maven-3.9.9-bin.zip -d /opt \
    && rm apache-maven-3.9.9-bin.zip \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn \
    && mvn -version  # 验证安装成功

WORKDIR /app

# 先复制 pom.xml 缓存依赖（加速）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并打包
COPY src ./src
RUN mvn clean package -DskipTests

# 阶段2: 运行 - 只用 JRE（镜像更小）
FROM eclipse-temurin:25-jre

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE ${PORT:-8080}
ENTRYPOINT ["java", "-jar", "app.jar"]