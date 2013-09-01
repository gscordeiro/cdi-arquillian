/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadocodigo.livrocdi.tests;

import br.com.casadocodigo.livrocdi.folhapagamento.Escolaridade;
import br.com.casadocodigo.livrocdi.folhapagamento.Funcionario;
import java.io.File;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 *
 * @author gilliard
 */
@RunWith(Arquillian.class)
public class FuncionarioDBUnitTest {

    @Deployment
    public static JavaArchive createDeployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Funcionario.class)
                .addAsManifestResource(new File("src/test/resources/META-INF/persistence-teste.xml"), "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @PersistenceContext(unitName = "livroCdiPU")
    EntityManager em;

    @Test
    @UsingDataSet("funcionarios.xls")
    @ShouldMatchDataSet("funcionarios-expected.xls")
    public void deveConcederAumentoDeDezPorcentoAosFuncionariosNivelSuperior() {

        List<Funcionario> funcionarios = em.createQuery("select f from Funcionario f "
                + "where f.escolaridade = :escolaridade", Funcionario.class)
                .setParameter("escolaridade", Escolaridade.SUPERIOR)
                .getResultList();

        for(Funcionario funcionario : funcionarios){
            double salarioAtual = funcionario.getSalarioBase();
            funcionario.setSalarioBase(salarioAtual * 1.1);
        }


    }
}