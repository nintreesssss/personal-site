FROM eclipse-temurin:25 AS builder
RUN apt-get update && apt-get install -y maven wget unzip \
    && wget https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip \
    && unzip apache-maven-3.9.9-bin.zip -d /opt \
    && rm apache-maven-3.9.9-bin.zip \
    && ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE ${PORT:-8080}
ENTRYPOINT ["java", "-jar", "app.jar"]