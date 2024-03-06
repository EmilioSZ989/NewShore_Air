ACLARACIÓN: SE REALIZO ASÍ EL PROYECTO PORQUE LA API A CONSUMIR NO FUNCIONABA, POR TANTO SE ME DIO LA OPCION DE CONSUMIR MI PROPIA API.

# Documentación de Proyecto: Solución de Conexión de Viajes para NEWSHORE AIR

## Descripción del Proyecto
La solución de conexión de viajes para NEWSHORE AIR es una aplicación web que permite a los usuarios encontrar rutas de vuelo entre un origen y un destino específicos. La aplicación consta de un backend desarrollado en Spring Boot que proporciona una API REST para la consulta de vuelos y una interfaz de usuario frontend desarrollada en Angular que permite a los usuarios realizar búsquedas de rutas de vuelo.

## Tecnologías Utilizadas
- Backend:
  - Spring Boot v3.2.3 (Java)
  - MySQL (Base de Datos Relacional)
  - JPA (Java Persistence API)

- Frontend:
  - Angular CLI v17.2.2
  - Node.js v20.11.1
  - npm v10.4.0

## Instrucciones de Instalación
### Backend (Spring Boot)
1. Clona el repositorio del proyecto desde [GitHub](https://github.com/EmilioSZ989/NewShore_Air.git).
2. Abre el proyecto en tu IDE (Eclipse).
3. Configura la conexión a la base de datos MySQL en el archivo `application.properties`.
4. Ejecuta el proyecto Spring Boot.
5. El backend estará disponible en `http://localhost:8080`.

### Frontend (Angular)
1. Clona el repositorio del proyecto desde [GitHub](https://github.com/EmilioSZ989/NewShore_Air.git).
2. Abre una terminal en el directorio del proyecto y ejecuta `npm install` para instalar las dependencias.
3. Ejecuta `ng serve` para iniciar el servidor de desarrollo de Angular.
4. El frontend estará disponible en `http://localhost:4200`.

## Guía de Uso
1. Abre la aplicación en tu navegador.
2. Ingresa el origen y destino del viaje en los campos correspondientes.
3. Haz clic en el botón "Buscar Ruta de Vuelo".
4. La aplicación mostrará la ruta de vuelo si es posible, de lo contrario, mostrará un mensaje indicando que la ruta no puede ser calculada.

Estructura del Proyecto
La solución está dividida en tres capas

API: Contiene el backend construido con Spring Boot.
Business: Lógica de negocio de la aplicación.
DataAccess: Acceso a la base de datos.

Importante
Antes de comenzar, asegúrate de tener configurada una base de datos MySQL llamada "newshore_air".

Uso de la Colección de Postman
Para facilitar las pruebas de la API, se proporciona una colección de Postman que incluye todas las rutas y ejemplos de solicitudes. La colección está disponible en la carpeta "postman_collection" dentro de este repositorio.

Ejemplo de Datos para Transport y Flights
Para crear un transporte en la base de datos a través de la API, utiliza un formato similar a este:
{
  "flightNumber": "12345",
  "flightCarrier": "Avianca"
}

Para crear un vuelo en la base de datos a través de la API, utiliza un formato similar a este:
{
  "origin": "BOG",
  "destination": "MDE",
  "price": 150.50,
  "transport": {
    "flightNumber": "12345",
    "flightCarrier": "Avianca"
  }
}

Y Listo...espero disfrutes de este lindo proyecto.
