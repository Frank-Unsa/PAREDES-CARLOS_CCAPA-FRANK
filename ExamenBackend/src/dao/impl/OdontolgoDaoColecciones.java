package dao.impl;

import dao.IDAO;
import modelo.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OdontolgoDaoColecciones implements IDAO<Odontologo> {
    private static  final Logger LOGGER=Logger.getLogger(OdontolgoDaoColecciones.class);
    private Map <Integer, Odontologo> odontologoMap = new HashMap<>();
    private  static Integer idContador=1;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Comenzamos con la persistencia en una colleccion");
        odontologo.setId(idContador++);
        odontologoMap.put(odontologo.getId(), odontologo);
        LOGGER.info("Odontologo guardado: "+ odontologo.getNombre());
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listando odontologos de las colecciones");


        return new ArrayList<>(odontologoMap.values());
    }
}
