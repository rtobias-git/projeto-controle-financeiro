/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.converter;

import br.edu.ifsp.pep.dao.UsuarioDao;
import br.edu.ifsp.pep.entidade.Usuario;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author tobias
 */
@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario>{

    @Override
    public Usuario getAsObject(FacesContext fc, UIComponent uic, String valor) {
        UsuarioDao usuarioDao = CDI.current()
                .select(UsuarioDao.class).get();
        
        return usuarioDao.obterPeloId(Integer.valueOf(valor));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return usuario.getId().toString();
    }
    
}
