/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.entidade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tobias
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.obterLogin", 
                query = "select u from Usuario u where u.email = :email and u.senha = :senha"),
        @NamedQuery(name = "Usuario.obterUsuarios", 
                query = "select u from Usuario u")
})
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @Column(name = "senha", length = 20, nullable = false)
    @Size(min = 4, max = 20)
    private String senha;
    
    @Email
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", length = 20, nullable = false)
    private NivelUsuario nivel;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;
    
    public Usuario() {
    }
    
    public Usuario(String nome, String senha, String email, NivelUsuario nivel) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.nivel = nivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NivelUsuario getNivel() {
        return nivel;
    }

    public void setNivel(NivelUsuario nivel) {
        this.nivel = nivel;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }
    
    @Override
    public String toString() {
        return this.nome;
    }
}
