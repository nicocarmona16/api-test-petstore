# api-test-petstore

Este proyecto contiene la automatización de pruebas API para la tienda de mascotas "PerfDog", basándose en la documentación de [Swagger Petstore](https://petstore.swagger.io/v2/).

El objetivo principal es validar las funcionalidades importantes del sistema garantizando que cada prueba sea independiente.

## Tecnologías Utilizadas

* **Lenguaje:** Java 8+
* **Gestor de dependencias:** Maven
* **Framework de Pruebas:** TestNG
* **Librería HTTP:** Rest Assured
* **Otras herramientas:** Lombok (para simplificar modelos) y Jackson (para serialización/deserialización de objetos)

## Estructura del Proyecto

* `config/`: Configuración base y lectura de variables de entorno (`TestRunner`).
* `model/`: Clases DTO para mapear las peticiones y respuestas JSON.
* `request/`: Clase (`RequestBuilder`) para construir y ejecutar las peticiones HTTP.
* `test/`: Clases de pruebas automatizadas.