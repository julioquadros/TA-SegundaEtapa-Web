package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Administrador;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class AdministradorDAO<T> extends DAOGenerico<Administrador> implements Serializable {

    public AdministradorDAO(){
        super();
        super.classePersistente = Administrador.class;
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }
}