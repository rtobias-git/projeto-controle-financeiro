/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author aluno
 */
@Entity
@Table(name = "lancamento")
@NamedQueries({
      @NamedQuery(name = "Lancamento.obterLancamentos",
              query = "select l from Lancamento l"),
      @NamedQuery(name = "Lancamento.obterPorUsuario", 
              query = "select l from Lancamento l where l.usuario.id = :id_usuario")
})
public class Lancamento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 20, nullable = false)
    private TipoLancamento tipo;
    
    @Column(name = "valor", precision = 8, scale = 2, nullable = false)
    @DecimalMin("0.00")
    private BigDecimal valor;
    
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate data_vencimento;
    
    @Column(name = "data_pagamento")
    private LocalDate data_pagamento;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Lancamento() {
    }

    public Lancamento(String descricao, TipoLancamento tipo, BigDecimal valor, LocalDate data_vencimento, LocalDate data_pagamento, Usuario usuario) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.usuario = usuario;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(LocalDate data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public LocalDate getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(LocalDate data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lancamento other = (Lancamento) obj;
        return Objects.equals(this.id, other.id);
    }
}
