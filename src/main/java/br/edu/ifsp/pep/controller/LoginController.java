/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.UsuarioDao;
import br.edu.ifsp.pep.entidade.Usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author tobias
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private Usuario usuario = new Usuario();
    private Usuario usuarioAutenticado;

    @Inject
    private UsuarioDao usuarioDao;

    public void acessar() {
        usuarioAutenticado = usuarioDao.obterLogin(
                usuario.getEmail(), usuario.getSenha());

        if (usuarioAutenticado == null) {
            System.out.println("Usuario não encontrado");
            return;
        }

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put("usuarioLogado", usuarioAutenticado);

        System.out.println("Acesso valido");
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
