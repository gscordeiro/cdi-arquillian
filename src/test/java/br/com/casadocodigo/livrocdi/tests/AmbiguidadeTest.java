/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadocodigo.livrocdi.tests;

import br.com.casadocodigo.livrocdi.folhapagamento.CalculadoraSalarios;
import br.com.casadocodigo.livrocdi.folhapagamento.CalculadoraSalariosDezPorcento;
import br.com.casadocodigo.livrocdi.folhapagamento.CalculadoraSalariosVintePorcento;
import br.com.casadocodigo.livrocdi.folhapagamento.Escolaridade;
import br.com.casadocodigo.livrocdi.folhapagamento.Funcionario;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author gilliard
 */
@RunWith(Arquillian.class)
public class AmbiguidadeTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
                
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(CalculadoraSalarios.class)
            .addClass(CalculadoraSalariosDezPorcento.class)
            .addClass(CalculadoraSalariosVintePorcento.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    private Instance<CalculadoraSalarios> calculadoraSalarios;
    
    @Test
    public void deveApresentarAmbiguidade(){
        
        assertTrue(calculadoraSalarios.isAmbiguous());
    }
    
    @Test
    public void naoDeveApresentarAmbiguidade(){
        
        Instance<? extends CalculadoraSalarios> calculadoraEspecifica = 
                calculadoraSalarios.select(CalculadoraSalariosVintePorcento.class);
        
        assertFalse(calculadoraEspecifica.isAmbiguous());
    }
}