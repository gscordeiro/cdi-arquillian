/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadocodigo.livrocdi.folhapagamento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gilliard
 */
@Stateless
public class FuncionarioBean {
    
    @PersistenceContext(unitName = "livroCdiPU")
    private EntityManager em;
    
    public void salvar(Funcionario funcionario){
        em.persist(funcionario);
    }
}
