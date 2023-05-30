FROM eclipse-temurin:17-jdk-alpine

ADD ./app/build/distributions/app.tar /

ENTRYPOINT ["/app/bin/app"]
