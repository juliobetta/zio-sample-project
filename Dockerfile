FROM mozilla/sbt

ARG SBT_VERSION="1.5.5"
ARG OPENJDK_TAG="11"

WORKDIR /app
ADD . /app

RUN ["sbt", "compile"]

EXPOSE 8090

CMD ["sbt", "run"]
