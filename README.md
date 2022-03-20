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
