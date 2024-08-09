package servicio;

import dao.impl.OdontolgoDaoColecciones;
import dao.impl.OdontologoDAOH2;
import modelo.Odontologo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private  OdontologoService odontologoServiceh2 = new OdontologoService( new OdontologoDAOH2());
    private OdontologoService odontologoServiceCollecion = new OdontologoService( new OdontolgoDaoColecciones());

    @Test
    void testGuardarYListarOdontologosH2(){
        //arrange
        Odontologo odontologo=new Odontologo();
        odontologo.setId(3);
        odontologo.setMatricula("12345");
        odontologo.setNombre("Prueba3");
        odontologo.setApellido("ApellidoPrueba");
        //act

        odontologoServiceh2.guardarOdontologo(odontologo);
        List<Odontologo>odontologoList = odontologoServiceh2.buscarTodos();

        //Assert
        // en la base de datos
        /*
        * ID  	NUMERO_MATRICULA  	NOMBRE  	APELLIDO
            1	12345	            Frank	Ccapa
            2	12345	            Fer	    Ccap
        *
        * */
        assertFalse(odontologoList.isEmpty());
        assertEquals("Prueba3", odontologoList.get(2).getNombre());


    }
    @Test
    void testGuardarYListarOdontologoColleccion(){
        //Arrange
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1);
        odontologo.setMatricula("1234");
        odontologo.setNombre("frank1");
        odontologo.setApellido("ApellidoPrueba");

        //Act
        odontologoServiceCollecion.guardarOdontologo(odontologo);
        List<Odontologo> odontologoList = odontologoServiceCollecion.buscarTodos();
        //Assert
        assertFalse(odontologoList.isEmpty());
        assertEquals("frank1", odontologoList.get(0).getNombre());
    }

}