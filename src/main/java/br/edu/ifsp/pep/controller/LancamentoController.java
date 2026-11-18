/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.LancamentoDao;
import br.edu.ifsp.pep.dao.UsuarioDao;
import br.edu.ifsp.pep.entidade.Lancamento;
import br.edu.ifsp.pep.entidade.NivelUsuario;
import br.edu.ifsp.pep.entidade.TipoLancamento;
import br.edu.ifsp.pep.entidade.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
@Named
@SessionScoped
public class LancamentoController implements Serializable{

    private Lancamento lancamento = new Lancamento();
    private TipoLancamento tipoSelecionado;
    private Lancamento lancamentoSelecionado;
    private Usuario usuarioSelecionado;
    private List<Lancamento> lancamentos;
    private List<Usuario> usuarios;

    @Inject
    private LancamentoDao lancamentoDao;
    @Inject
    private UsuarioDao usuarioDao;

    public void cadastrar() {
        this.lancamento.setUsuario(usuarioSelecionado);
        this.lancamento.setTipo(this.tipoSelecionado);
        lancamentoDao.adicionar(this.lancamento);
        System.out.println("Cadastrado: " + this.lancamento);
    }

    public List<Lancamento> obterTodos() {
        Usuario usuario = getUsuarioLogado();

        if (usuario == null) {
            return new ArrayList<>();
        }

        if (usuario.getNivel() == NivelUsuario.ADMINISTRADOR) {
            lancamentos = lancamentoDao.obterLancamentos();
        } else {
            lancamentos = lancamentoDao.obterLancamentosPorUsuario(usuario.getId());
        }

        return lancamentos;
    }

    public void excluir() {
        lancamentoDao.remover(this.lancamentoSelecionado);
        System.out.println("Lancamento removido : "+ this.lancamentoSelecionado);
    }
    
    public void editar(){
        this.lancamentoSelecionado.setTipo(this.tipoSelecionado);
        lancamentoDao.editar(this.lancamentoSelecionado);
    }

    public List<Usuario> obterUsers() {
        if (this.usuarios == null) {
            this.usuarios = usuarioDao.obterUsuarios();
        }
        return this.usuarios;
    }

    public Usuario getUsuarioLogado() {
        return (Usuario) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("usuarioLogado");
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public TipoLancamento getTipoSelecionado() {
        return tipoSelecionado;
    }

    public void setTipoSelecionado(TipoLancamento tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Lancamento getLancamentoSelecionado() {
        return lancamentoSelecionado;
    }

    public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
        this.lancamentoSelecionado = lancamentoSelecionado;
    }
}
