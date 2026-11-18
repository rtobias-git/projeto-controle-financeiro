/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.entidade.Lancamento;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author aluno
 */
@Stateless
public class LancamentoDao extends AbstractDAO<Lancamento>{
 
    public List<Lancamento> obterLancamentos(){
        EntityManager em = getEntityManager();
        TypedQuery<Lancamento> query = em.createNamedQuery("Lancamento.obterLancamentos", Lancamento.class);
        return query.getResultList();
    }
   
    public List<Lancamento> obterLancamentosPorUsuario(Integer id_usuario){
        EntityManager em = getEntityManager();
        TypedQuery<Lancamento> query = em.createNamedQuery("Lancamento.obterPorUsuario", Lancamento.class);
        query.setParameter("id_usuario", id_usuario);
        return query.getResultList();
    }
}
