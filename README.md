# Gestión de Tareas Programadas
En este proyecto de Liferay se podrá encontrar un portlet que permitirá la gestión de las tareas programadas que tenga el sistema. Con el podremos realizar las siguientes funciones:

* Listar las tareas programadas del sistemas y para cada una de ellas el estado en el que se encuentra. Si esta parada o en ejecución, la próxima fecha de lanzamiento, si está almacenada o en memoria.
* Se podrá dar de alta una tarea programada especificando el portlet y la clase que contiene la lógica de negocio. A su vez se podrá indicar el tipo trigger si se ejecutará cada X milisegundo o bien por una expresión cron.
* Se podrá editar la información del trigger asociado a una tarea programada.
* Se podrá pausar o reanudar una tarea programada.
* Se podrá elimianr una tarea programada.

## Instalación y despliegue
El proyecto se podrá compilar con maven y se deberá desplegar en la carpeta /deploy del servidor liferay.
```
mvn clean package
cp target/gestion-tareas-programadas-1.0.0.war <servidor liferay>/deploy
```
## Acceso y pantallas
El portlet es una aplicacion de sistema que se podrá acceder desde el Panel de Control >> Configuración >> Tareas Programadas.

### Listado de tareas
![Listado Tareas Programadas](/img/listado-tareas-programadas.png)
### Nueva tarea programada
![Nueva Tarea Programada](/img/nueva-tarea-programada.png)
### Edición de tarea programada
![Edición Tarea Programada](/img/edicion-tarea-programada.png)
