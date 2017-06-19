package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AdministradorDAO;
import br.edu.ifsul.dao.EstabelecimentoDAO;
import br.edu.ifsul.modelo.Administrador;
import br.edu.ifsul.modelo.Estabelecimento;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleEstabelecimento")
@SessionScoped
public class ControleEstabelecimento implements Serializable {
    
    @EJB
    private EstabelecimentoDAO<Estabelecimento> dao;
    private Estabelecimento objeto;
    private Boolean editando;
    
    @EJB
    private AdministradorDAO<Administrador> daoAdministrador;
    private Administrador administrador;
    private Boolean editandoAdministrador;
    private Boolean novoAdministrador;
    
    
    public ControleEstabelecimento() {
        dao = new EstabelecimentoDAO<>();
        daoAdministrador = new AdministradorDAO<>();
        editando = false;
    }
    
    public String listar() {
        editando = false;
        return "/privado/estabelecimento/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Estabelecimento();
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
    
    public void novoAdministrador(){
        editandoAdministrador = true;
    }
    
    public void salvarAdministrador(){
        if (!objeto.getAdministradores().contains(administrador)) {
            objeto.getAdministradores().add(administrador);
            Util.mensagemInformacao("Administrador adicionado com sucesso!");
        } else {
            Util.mensagemErro("Estabelecimento já possui este administrador!");
        }
        editandoAdministrador = false;
    }
    
    public void removerAdministrador(Administrador obj) {
        objeto.getAdministradores().remove(obj);
        Util.mensagemInformacao("Administrador removido com sucesso!");
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public EstabelecimentoDAO<Estabelecimento> getDao() {
        return dao;
    }

    public void setDao(EstabelecimentoDAO<Estabelecimento> dao) {
        this.dao = dao;
    }

    public Estabelecimento getObjeto() {
        return objeto;
    }

    public void setObjeto(Estabelecimento objeto) {
        this.objeto = objeto;
    }

    public AdministradorDAO<Administrador> getDaoAdministrador() {
        return daoAdministrador;
    }

    public void setDaoAdministrador(AdministradorDAO<Administrador> daoAdministrador) {
        this.daoAdministrador = daoAdministrador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Boolean getEditandoAdministrador() {
        return editandoAdministrador;
    }

    public void setEditandoAdministrador(Boolean editandoAdministrador) {
        this.editandoAdministrador = editandoAdministrador;
    }

    public Boolean getNovoAdministrador() {
        return novoAdministrador;
    }

    public void setNovoAdministrador(Boolean novoAdministrador) {
        this.novoAdministrador = novoAdministrador;
    }
    
    
}
