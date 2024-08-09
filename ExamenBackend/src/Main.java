import dao.impl.OdontolgoDaoColecciones;
import dao.impl.OdontologoDAOH2;
import modelo.Odontologo;
import servicio.OdontologoService;

public class Main {
    public static void main(String[] args) {
        Odontologo odontologo=new Odontologo();
        odontologo.setId(2);
        odontologo.setMatricula("12345");
        odontologo.setNombre("Fer");
        odontologo.setApellido("Ccapa");

        OdontologoService odontologoService= new OdontologoService(new OdontologoDAOH2());
        //Seteamos una estrategia de persistencia, es decir un DAO
        //odontologoService.setOdontologoIDAO(new OdontologoDAOH2());


        //odontologoService.guardarOdontologo(odontologo);

       // odontologoService.buscarTodos();
        OdontolgoDaoColecciones odontolgoDaoColecciones = new OdontolgoDaoColecciones();
        odontologoService.setOdontologoIDAO(new OdontolgoDaoColecciones());
        //odontolgoDaoColecciones.guardar()


    }
}
