# camel-soap-demo

This project uses Quarkus.

It's throw exception use camelk deploy in OpenShift

This exception occurs at startup

### Deploy command
```agsl
mvn clean compiler:compiler jar:jar
kamel run --name camel-soap-demo DemoRoute.java
```