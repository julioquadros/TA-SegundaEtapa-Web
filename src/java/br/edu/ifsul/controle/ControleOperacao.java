package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ChequeDAO;
import br.edu.ifsul.dao.OperacaoDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.modelo.Cheque;
import br.edu.ifsul.modelo.Operacao;
import br.edu.ifsul.modelo.Cliente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "controleOperacao")
@SessionScoped
public class ControleOperacao implements Serializable {
    
    @EJB
    private OperacaoDAO<Operacao> dao;
    private Operacao objeto;
    private Boolean editando;
    @EJB
    private ChequeDAO<Cheque> daoCheque;
    private Cheque cheque;
    private Boolean editandoCheque;
    private Boolean novoCheque;
    
    @EJB
    private ClienteDAO<Cliente> daoCliente;
    private Cliente cliente;
    private Boolean editandoCliente;
    
    public ControleOperacao() {
        editando = false;
        editandoCheque = false;
        dao = new OperacaoDAO<>();
        daoCheque = new ChequeDAO<>();
        daoCliente = new ClienteDAO<>();
    }
    
    public String listar() {
        editando = false;
        return "/privado/operacao/listar?faces-redirect=true";
    }

    
    
    public void novo() {
        objeto = new Operacao();
        editando = true;
        editandoCheque = false;
    }
    
    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoCheque = false;
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
    
    public void novoCheque(){
        cheque = new Cheque();
        novoCheque = true;
    }
    
    public void alterarCheque(int index){
       cheque = objeto.getCheques().get(index);
       novoCheque = false;
       editandoCheque = true;
    }
    
    public void salvarCheque(){
        if (novoCheque){
            objeto.adicionarCheque(cheque);
        }
        Util.mensagemInformacao("Cheque adicionado com sucesso");
    }
    
    public void removerCheque(Cheque obj) {
        objeto.getCheques().remove(obj);
        Util.mensagemInformacao("Cheque removido com sucesso!");
    }
    
    public OperacaoDAO<Operacao> getDao() {
        return dao;
    }
    
    public void setDao(OperacaoDAO<Operacao> dao) {
        this.dao = dao;
    }
    
    public Operacao getObjeto() {
        return objeto;
    }
    
    public void setObjeto(Operacao objeto) {
        this.objeto = objeto;
    }
    
    public ChequeDAO<Cheque> getDaoCheque() {
        return daoCheque;
    }
    
    public void setDaoCheque(ChequeDAO<Cheque> daoCheque) {
        this.daoCheque = daoCheque;
    }
    
    public Boolean getEditando() {
        return editando;
    }
    
    public void setEditando(Boolean editando) {
        this.editando = editando;
    }
    
    public ClienteDAO<Cliente> getDaoCliente() {
        return daoCliente;
    }
    
    public void setDaoCliente(ClienteDAO<Cliente> daoCliente) {
        this.daoCliente = daoCliente;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Boolean getEditandoCliente() {
        return editandoCheque;
    }
    
    public void setEditandoCheque(Boolean editandoCheque) {
        this.editandoCheque = editandoCheque;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Boolean getNovoCheque() {
        return novoCheque;
    }

    public void setNovoCheque(Boolean novoCheque) {
        this.novoCheque = novoCheque;
    }
    
    public void setEditandoCliente(Boolean editandoCliente) {
        this.editandoCliente = editandoCliente;
    }
}
