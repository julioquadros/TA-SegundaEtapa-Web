package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Operacao;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class OperacaoDAO<T> extends DAOGenerico<Operacao> implements Serializable {

    public OperacaoDAO(){
        super();
        super.classePersistente = Operacao.class;
        super.setOrdem("id");
    }
}