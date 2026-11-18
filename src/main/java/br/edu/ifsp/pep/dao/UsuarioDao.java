/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entidade.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author tobias
 */
@Stateless
public class UsuarioDao extends AbstractDAO<Usuario>{
    
    public Usuario obterLogin(String email, String senha){
        EntityManager em = getEntityManager();
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obterLogin", Usuario.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        return query.getSingleResult();
    }
    
    public List<Usuario> obterUsuarios(){
        EntityManager em = getEntityManager();
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.obterUsuarios", Usuario.class);
        return query.getResultList();
    }
    
    public Usuario obterPeloId(Integer id){
        EntityManager em = getEntityManager();
        return em.find(Usuario.class, id);
    }
    
}
