package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Cheque;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class ChequeDAO<T> extends DAOGenerico<Cheque> implements Serializable {

    public ChequeDAO(){
        super();
        super.classePersistente = Cheque.class;
    }

}