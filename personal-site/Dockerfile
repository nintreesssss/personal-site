# 阶段 1: 构建（用 Maven + Java 25）
FROM maven:3.9.9-eclipse-temurin-25 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 阶段 2: 运行（轻量 JRE 镜像）
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE ${PORT:-8080}
ENTRYPOINT ["java", "-jar", "app.jar"]