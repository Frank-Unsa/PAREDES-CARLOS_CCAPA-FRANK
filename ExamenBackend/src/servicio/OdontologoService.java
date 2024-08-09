package servicio;

import dao.IDAO;
import dao.impl.OdontolgoDaoColecciones;
import dao.impl.OdontologoDAOH2;
import modelo.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDAO<Odontologo> odontologoIDAO;

    public OdontologoService(OdontologoDAOH2 odontologoDAOH2) {
        this.odontologoIDAO=  odontologoDAOH2;
    }
    public OdontologoService(OdontolgoDaoColecciones odontolgoDaoColecciones) {
        this.odontologoIDAO= odontolgoDaoColecciones;
    }

    public Odontologo guardarOdontologo(Odontologo o){
        return odontologoIDAO.guardar(o);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoIDAO.listarTodos();
    }
    public IDAO<Odontologo> getOdontologoIDAO() {
        return odontologoIDAO;
    }

    public void setOdontologoIDAO(IDAO<Odontologo> odontologoIDAO) {
        this.odontologoIDAO = odontologoIDAO;
    }


}
