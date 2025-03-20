FROM gradle:7.6.0-jdk11 AS build

WORKDIR /Brute-Miner-Kotlin

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src /Brute-Miner-Kotlin/src
COPY gradle .
COPY gradlew .
COPY gradlew.bat .

RUN chmod +x gradlew

CMD ["gradle", "run"]