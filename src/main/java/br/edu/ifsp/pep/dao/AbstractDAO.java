/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author aluno
 * @param <T>
 */

public abstract class AbstractDAO <T>{
    @PersistenceContext(name = "conexaoPU")
    private EntityManager em;
    
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public void adicionar(T entity){
        em.persist(entity);
    }
    
    public void remover(T entity){
        em.remove(em.merge(entity));
    }
    
    public void editar(T entity){
        em.merge(entity);
    }
}
