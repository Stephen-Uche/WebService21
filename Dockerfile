FROM eclipse-temurin:19-jre-jammy
COPY utils/target/classes /app
COPY core/target/classes /app
ENTRYPOINT ["java", "-cp", "/app", "Main"]