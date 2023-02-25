# User Api

 Api para registro de Usuarios

### Requerimientos 📋

Java 8

## Ejecución 🛠️

* Configurado con puerto: 9094
* Swagger: http://localhost:9094/swagger-ui/index.html
* Base de datos en memoria H2: http://localhost:9094/h2-console
  * Driver class: org.h2.Driver
  * JDBC url: jdbc:h2:mem:maintenancedb
  * User Name: h2
  * Password: h2
* Ruta de diagrama: src/main/resources/diagram/UserRegisterDiagram.png

## Seguridad

* Autenticación básica para todos los endpoints (Basic Auth)
  * Username: jcachi
  * Password: secreto

## Pruebas Unitarias

* Test para probar el endpoint de listar usuarios con Junit y Mockito
  
## Importante

Reglas colocadas para el campo password:

* Minimo 10, máximo 128 caracteres
* Al menos una minúscula
* Al menos una mayúscula
* Al menos un número
* Al menos un caracter especial

## Autor ✒️

* **Jhonatan Cachi Arroyo**  - [jcachi99](https://github.com/jcachi99)
