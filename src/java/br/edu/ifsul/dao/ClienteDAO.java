package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cliente;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class ClienteDAO<T> extends DAOGenerico<Cliente> implements Serializable {

    public ClienteDAO(){
        super();
        super.classePersistente = Cliente.class;
    }
}