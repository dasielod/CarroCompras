Hello World sample shows how to deploy [Carrito-Compra](https://github.com/dasielod/CarroCompras) RESTful web service application with [Docker](https://www.docker.com/)

#### Prerequisite

Installed:   
[Java 1.8](https://www.oracle.com/technetwork/java/javase/overview/index.html)   
[Maven 3.x](https://maven.apache.org/install.html)
[Docker](https://www.docker.com/)   
[Docker-Compose](https://docs.docker.com/compose/install/)   

#### Steps

##### Clone source code from git
```
$  git clone https://github.com/dasielod/CarroCompras.git .
```

##### Build Maven project
```
Con Docker cambiar la conexion a base de datos que se encuentra en application.properties por spring.datasource.url=jdbc:postgresql://demo-postgres:5432/carrocompra
Si no usa Docker cambiar la conexion a base de datos que se encuentra en application.properties por spring.datasource.url=jdbc:postgresql://localhost:5432/carrocompra    

$ mvn clean install .
```

##### Run SpringBoot project with Maven
```
$ mvn sprintboot:run .
```

##### Test application

```
$ curl http://localhost:8080/actuator/health
```

Debe recibir como respuesta:
```
{"status":"UP","components":{"db":{"status":"UP","details":{"database":"PostgreSQL","validationQuery":"isValid()"}},"diskSpace":{"status":"UP","details":{"total":244549410816,"free":18844389376,"threshold":10485760,"exists":true}},"ping":{"status":"UP"}}}
```

## Run with docker-compose 

Build and start the container by running 

```
$ docker-compose up
```

##### Test application with ***curl*** command
```
$ curl http://localhost:8080/actuator/health
```

Debe recibir como respuesta:
```
{"status":"UP","components":{"db":{"status":"UP","details":{"database":"PostgreSQL","validationQuery":"isValid()"}},"diskSpace":{"status":"UP","details":{"total":244549410816,"free":18844389376,"threshold":10485760,"exists":true}},"ping":{"status":"UP"}}}
```

##### Stop Docker Container:
```
docker-compose down
```


