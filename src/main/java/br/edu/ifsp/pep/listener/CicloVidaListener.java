/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.LoginController;
import br.edu.ifsp.pep.entidade.NivelUsuario;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;

/**
 *
 * @author tobias
 */
public class CicloVidaListener implements PhaseListener{
    
    @Inject
    private LoginController loginController;
    
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("After phase: " + event.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext ctx;
        String pagina;
        
        System.out.println("Before phase: " + event.getPhaseId());
        
        ctx = event.getFacesContext();
        pagina = ctx.getViewRoot().getViewId();
        if(pagina.startsWith("/lancamento/cadastro")){
            if (loginController.getUsuarioAutenticado() == null || loginController.getUsuarioAutenticado().getNivel() != NivelUsuario.ADMINISTRADOR) {
                redirecionar(ctx, "/usuario/loginErrado.xhtml");
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
    
    private void redirecionar(FacesContext ctx, String pagina) {
        try {
            String projeto = ctx.getExternalContext().getApplicationContextPath();
            ctx.getExternalContext().redirect(projeto + pagina);
        } catch (IOException ex) {
            System.out.println("Erro ao redirecionar.");
        }
    }
}
