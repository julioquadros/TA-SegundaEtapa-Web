package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.dao.EstabelecimentoDAO;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.modelo.Estabelecimento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleCliente")
@SessionScoped
public class ControleCliente implements Serializable {
    
    @EJB
    private ClienteDAO<Cliente> dao;
    private Cliente objeto;
    private Boolean editando;
    @EJB
    private EstabelecimentoDAO<Estabelecimento> daoEstabelecimento;
    private Estabelecimento estabelecimento;
    
    public ControleCliente() {
        editando = false;
        daoEstabelecimento = new EstabelecimentoDAO<>();
    }
    
    public String listar() {
        editando = false;
        return "/privado/cliente/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Cliente();
        editando = true;
    }
    
    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }
    
    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public ClienteDAO<Cliente> getDao() {
        return dao;
    }

    public void setDao(ClienteDAO<Cliente> dao) {
        this.dao = dao;
    }

    public Cliente getObjeto() {
        return objeto;
    }

    public void setObjeto(Cliente objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public EstabelecimentoDAO<Estabelecimento> getDaoEstabelecimento() {
        return daoEstabelecimento;
    }

    public void setDaoEstabelecimento(EstabelecimentoDAO<Estabelecimento> daoEstabelecimento) {
        this.daoEstabelecimento = daoEstabelecimento;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
    
}
