# 阶段 1: 构建（用官方 Maven + Java 25 镜像）
FROM maven:3.9.9-eclipse-temurin-21-jammy AS builder

WORKDIR /app

# 先复制 pom.xml 缓存依赖（加速后续构建）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并打包
COPY src ./src
RUN mvn clean package -DskipTests

# 阶段 2: 运行（轻量 JRE 镜像）
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE ${PORT:-8080}

ENTRYPOINT ["java", "-jar", "app.jar"]