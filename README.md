# Proyecto BackComerIdeal

Este repositorio contiene el código fuente de la aplicación BackComerIdeal. Se trata de una aplicación de gestión de locales comerciales.

## Tecnologías Utilizadas

- 🚀 Java
- 🌐 Spring Boot
- 🗄️ Hibernate
- 🛢️ MySQL

## Estructura del Proyecto

- **src/main/java/com/backcomerideal**: Contiene el código fuente de la aplicación.
  - **model**: Clases de modelo que representan entidades como `Local`.
  - **repository**: Repositorios JPA para acceder a la base de datos, como `LocalRepository`.
  - **service**: Lógica de negocio y servicios, incluyendo `LocalService`.
  - **controller**: Controladores REST, como `LocalController`.
- **src/main/resources**: Contiene archivos de configuración de la aplicación.

## Funcionalidades Principales

- **LocalController**: Controlador REST para manejar las operaciones relacionadas con los locales, como obtener todos los locales, filtrarlos por distrito o tipo de actividad.
- **LocalService**: Implementa la lógica de negocio para operaciones relacionadas con locales.

## Configuración

- **application.properties**: Archivo de configuración de Spring Boot para la conexión a la base de datos MySQL y otras propiedades de la aplicación.

## Ejecución

1. Clonar el repositorio: `git clone https://github.com/tu-usuario/backcomer-ideal.git`
2. Importar el proyecto en tu IDE favorito.
3. Configurar la conexión a la base de datos MySQL en `application.properties`.
4. Ejecutar la aplicación.
