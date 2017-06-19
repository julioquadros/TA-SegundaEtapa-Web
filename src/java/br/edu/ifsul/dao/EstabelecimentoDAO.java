package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Estabelecimento;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Query;

@Stateful
public class EstabelecimentoDAO<T> extends DAOGenerico<Estabelecimento> implements Serializable {

    public EstabelecimentoDAO(){
        super();
        super.classePersistente = Estabelecimento.class;
    }
    
    @Override
    public Estabelecimento getObjectById(Integer id) throws Exception {
        Estabelecimento obj = (Estabelecimento) em.find(classePersistente, id);
        obj.getAdministradores().size();
        return obj;
    }    
    
    public Estabelecimento localizaPorEstabelecimento(String estabelecimento){
        Query query = em.createQuery("from Estabelecimento where upper(estabelecimento) = :estabelecimento");
        query.setParameter("estabelecimento", estabelecimento.toUpperCase());
        Estabelecimento obj = (Estabelecimento) query.getSingleResult();
        obj.getAdministradores().size();
        return obj;
    }
    
}