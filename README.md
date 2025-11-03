# PruebaCastores
• IDE utilizado -> IntelliJ

• Versión del lenguaje de programación utilizado -> Java 17

• DBMS utilizado y su versión -> MySQL Workbench 8.0 CE

• Lista de pasos para correr su aplicación
  * clonar la rama master para obtener toda la aplicación *
  * Para el motor de BD usé Xampp *
    1) debe de ejecutarse el script para poder generar el DDL de la BD.
    2) una vez con el servicio de MySQL corriendo en Xampp, nos vamos al archivo application.properties del directorio src/main/resources/
       en el lo unico que se debe de modificar es el puerto de la BD, el usuario y contraseña de conexión.
    3) ya con la base de datos lista y el servicio de MySQL corriendo, ejecutaremos la aplicacion que en este caso la hice con spring boot.
    4) cuando en la consola de ejecución aparezca que esta corriendo en el puerto 8080 nos dirigimos a cualquier navegador y pondremos la URL:
       localhost:8080/usuarios/login la cual permitira visualizar el inicio de sesion.
    5) anexe los dos usuarios con los que hice las pruebas, uno con rol de administrador y otro con rol almacenista, usaremos cualquiera de ellos para poder ingresar         a la aplicacion.
