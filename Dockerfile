FROM mozilla/sbt

ARG SBT_VERSION="1.5.5"
ARG OPENJDK_TAG="11"

WORKDIR /app
ADD . /app

CMD ["sbt", "run"]
