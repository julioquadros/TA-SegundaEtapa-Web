package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AdministradorDAO;
import br.edu.ifsul.modelo.Administrador;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleAdministrador")
@SessionScoped
public class ControleAdministrador implements Serializable {
    
    @EJB
    private AdministradorDAO<Administrador> dao;
    private Administrador objeto;
    private Boolean editando;
    
    public ControleAdministrador() {
        editando = false;
    }
    
    public String listar() {
        editando = false;
        return "/privado/administrador/listar?faces-redirect=true";
    }
    
    public void novo() {
        objeto = new Administrador();
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

    public AdministradorDAO<Administrador> getDao() {
        return dao;
    }

    public void setDao(AdministradorDAO<Administrador> dao) {
        this.dao = dao;
    }

    public Administrador getObjeto() {
        return objeto;
    }

    public void setObjeto(Administrador objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    
}
