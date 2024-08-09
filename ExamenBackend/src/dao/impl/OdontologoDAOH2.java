package dao.impl;

import dao.IDAO;
import modelo.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDAO<Odontologo> {
    private static  final Logger LOGGER=Logger.getLogger(OdontologoDAOH2.class);

    private final static String DB_JDBC_DRIVER="org.h2.Driver";
    private final static String DB_URL ="jdbc:h2:~/dbestudiantes";
    private final static  String DB_USER="sa";
    private final static String DB_PASSWORD="";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Comenzamos a persistir un odontologo");
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        try{
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //2. Crear una sentencia

            preparedStatement=connection.prepareStatement("INSERT INTO ODONTOLOGOS_TABLE  VALUES(?,?,?,?)");
            preparedStatement.setLong(1,odontologo.getId());
            preparedStatement.setString(2, odontologo.getMatricula());
            preparedStatement.setString(3, odontologo.getNombre());
            preparedStatement.setString(4, odontologo.getApellido());


            //3 Ejecutar la sentencia
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologo.setId(resultSet.getInt(1));
                LOGGER.info("Odontólogo guardado: " + odontologo.getNombre());
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //LOGGER.info("Se a creado un odontologo" + odontologo.getNombre());
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listando a los odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        List<Odontologo> odontologoList=new ArrayList<>();
        // 1. Levantamos el driver y contactarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);

            //2. Crear una sentencia

            preparedStatement=connection.prepareStatement("SELECT * FROM ODONTOLOGOS_TABLE ");

            //3 Ejecutar la sentencia
            ResultSet result = preparedStatement.executeQuery();
            //4. evaluar los resultados
            while (result.next()){
                Integer idOdontologo= result.getInt("id");
                String matricula= result.getString("NUMERO_MATRICULA");
                String  nombreOdo= result.getString("NOMBRE");
                String apellido=result.getString("APELLIDO");
                Odontologo odontologo= new Odontologo();
                odontologo.setId(idOdontologo);
                odontologo.setMatricula(matricula);
                odontologo.setNombre(nombreOdo);
                odontologo.setApellido(apellido);

                odontologoList.add(odontologo);
            }

            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return odontologoList;
        }
}
