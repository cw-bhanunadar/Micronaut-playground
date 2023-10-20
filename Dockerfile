FROM ghcr.io/graalvm/jdk:ol7-java17 as graalvm
EXPOSE 8080
COPY build/libs/globalexception-0.1-all-optimized.jar app.jar
ENV JAVA_OPTS=""
CMD ["java", "-jar", "app.jar"]