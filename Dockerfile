FROM adoptopenjdk:16-jre
COPY core/target/modules /app/modules/
COPY core/target/classes /app/core/
COPY core/target/web /web
CMD [ "java", "-cp", "/app/core:/app/modules/utils-1.0-SNAPSHOT.jar:/app/modules/gson-2.8.7.jar", "x.snowroller.Main" ]

