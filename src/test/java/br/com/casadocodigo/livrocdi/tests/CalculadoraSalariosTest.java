/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadocodigo.livrocdi.tests;

import br.com.casadocodigo.livrocdi.folhapagamento.CalculadoraSalarios;
import br.com.casadocodigo.livrocdi.folhapagamento.CalculadoraSalariosDezPorcento;
import br.com.casadocodigo.livrocdi.folhapagamento.Escolaridade;
import br.com.casadocodigo.livrocdi.folhapagamento.Funcionario;
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
public class CalculadoraSalariosTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
                
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(Funcionario.class)
            .addClass(CalculadoraSalarios.class)
            .addClass(CalculadoraSalariosDezPorcento.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    private CalculadoraSalarios calculadoraSalarios;
    
    @Test
    public void deveAumentarSalarioBaseEmDezPorcento(){
        
        Funcionario funcionario = new Funcionario("Fulano", 4000.0, Escolaridade.MEDIO);
        double salario = calculadoraSalarios.calculaSalario(funcionario);
        
        assertEquals(4400.0, salario, 0.001);
    }
}