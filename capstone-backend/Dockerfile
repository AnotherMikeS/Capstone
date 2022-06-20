# 1. Corretto.
FROM amazoncorretto:11
# 2. Create the directory and navigate to it.
WORKDIR /home/app
# 3. Copy our packaged jar to the working directory and rename.
COPY ./target/capstone-backend-1.0-SNAPSHOT.jar app.jar
# 4. Run the jar.
CMD ["java", "-jar", "./app.jar"]