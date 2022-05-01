# ZIO Sample Project

This is just a toy project to explore ZIO's potential. 

### Goals

Set up the project with the following features:

* [X] HTTP (zio-http)
* [X] Configuration (zio-config)
* [X] Logs (zio-logging)
* [X] JSON parser (zio-json)
* [ ] Database (Quill) https://www.youtube.com/watch?v=SmBpGkIsJIU
* [ ] GraphQL (Caliban) https://www.youtube.com/watch?v=mzqsXklbmfM
* [ ] Authentication (OAuth???, Okta???)

_Bonus_

* [X] Scala 3
* [X] ZIO 2.0
* [X] Dockerize
* [X] Kubernetes (basic)
* [ ] Setup GitHub Actions
* [ ] Metrics (zio-metrics, zio-zmx) https://www.youtube.com/watch?v=oMJ1RMdR7wg


### Initialization

To install the dependencies run the following command:

```
sbt update
```

**Development**

To initialize the project, the first step is running sbt shell with the command `sbt`. 

Once in sbt shell, run the command `~reStart`. This will make the project to be initialized in watch mode. If a change is detected SBT recompiles the required classes and sbt-revolver automatically 
restarts your application.

**Kubernetes** (for learning purposes only)

1. [Install Helm](https://helm/docs/intro/install/)

2. Create a docker image:
    ```sh
    chmod +x bin/create-docker-image && \
    ./bin/create-docker-image -v develop-SNAPSHOT
    ```

    > **NOTE**: The output will show a lot of `[error]`. Just ignore it if there's no apparent error at the end of the command.

3. Install the chart running the following command:
   
   ```sh
   chmod +x ./helm/scripts/* && ./helm/scripts/install
   ```
   
   Run `./helm/scripts/uninstall` to uninstall the chart.
   
4. To access the app, run the following command:
   
   ```sh
   chmod +x ./bin/port-forward && ./bin/port-forward <LOCALHOST-PORT>
   ```
