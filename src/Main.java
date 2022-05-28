import java.sql.*;

import org.apache.log4j.Logger;

public class Main {

private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            //cargar el driver
            Class.forName("org.h2.Driver");
            logger.info("Driver h2 cargado");

            // Establecer la conexión
            String url = "jdbc:h2:~/db_usuarios";
            Connection connection = DriverManager.getConnection(url, "sa", "sa");
            logger.info("conexion establecida");

            // sentencia para crear tabla
            String creacionTabla = "DROP TABLE IF EXISTS usuarios; CREATE TABLE usuarios (id INT PRIMARY KEY, primer_nombre VARCHAR2, apellido VARCHAR2, edad INT);";
            Statement st = connection.createStatement();
            st.execute(creacionTabla);
            logger.info("Tabla creada");

            //se insertan datos
            String insercion1 = "INSERT INTO usuarios(id, primer_nombre, apellido, edad) VALUES (1,'carlos', 'artunduaga', 40); ";
            String insercion2 = "INSERT INTO usuarios(id, primer_nombre, apellido, edad) VALUES (2,'andres', 'runrun', 20); ";
            String insercion3 = "INSERT INTO usuarios(id, primer_nombre, apellido, edad) VALUES(3,'fico', 'quemado', 15); ";
            st.execute(insercion1);
            st.execute(insercion2);
            st.execute(insercion3);
            logger.info("se insertaron todas las filas");

            //consultar los datos
            String consulta = "select *  from usuarios";
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next() ) {
             System.out.println(rs.getInt(1)+ " " + rs.getString(2));
            }
            logger.info("consulta realizada con exito");




            //eliminar registros
            String borrado = "DELETE FROM usuarios WHERE id=2";


            //consultar los datos nuevamente


            // sentencia para actualizar un registro
            String update =  "UPDATE usuarios SET primer_nombre =  'Carlos Artunduaga'  WHERE id=1";
            st.execute(update);
            logger.info("update a usuarios");

            //consultar los datos nuevamente
            System.out.println("---------------despues de la actualziación");
            //while

        } catch (ClassNotFoundException e) {
            logger.error("driver no encontrado");
            e.printStackTrace();
        } catch (SQLException errorSQL){
            logger.error("error de SQL: " + errorSQL);
        }

    }
}