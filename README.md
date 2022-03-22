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

Create a docker image:

```
chmod +x bin/create-docker-image && \
./bin/create-docker-image -v <current-version>
```

> **NOTE**: The output will show a lot of `[error]`. Just ignore it if there's no apparent error at the end of the command.

To deploy the into a k8s cluster, run the following command:

```
kubctl apply -f k8s/templates/app
```

In order to access the app, run the following commands:

_Check the pod name_  
```
kubectl get pods
```

_Map the service port to the local machine_  
```
kubectl port-forward --address 0.0.0.0 zio-app/zio-app-service <localhost-port>:9000
```
