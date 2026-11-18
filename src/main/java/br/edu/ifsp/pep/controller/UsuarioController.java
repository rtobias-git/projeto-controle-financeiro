/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.UsuarioDao;
import br.edu.ifsp.pep.entidade.NivelUsuario;
import br.edu.ifsp.pep.entidade.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 *
 * @author tobias
 */
@Named
@RequestScoped
public class UsuarioController {
    private Usuario usuario = new Usuario();
    private NivelUsuario nivelSelecionado;
    
    @Inject
    private UsuarioDao usuarioDao;
    
    public void adicionar(){
        this.usuario.setNivel(nivelSelecionado);
        usuarioDao.adicionar(this.usuario);
        System.out.println("Usuario Adicionado: " + this.usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NivelUsuario getNivelSelecionado() {
        return nivelSelecionado;
    }

    public void setNivelSelecionado(NivelUsuario nivelSelecionado) {
        this.nivelSelecionado = nivelSelecionado;
    }    
    
}
