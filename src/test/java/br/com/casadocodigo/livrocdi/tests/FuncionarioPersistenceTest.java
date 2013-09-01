/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadocodigo.livrocdi.tests;

import br.com.casadocodigo.livrocdi.folhapagamento.Escolaridade;
import br.com.casadocodigo.livrocdi.folhapagamento.Funcionario;
import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.TransactionMode;
import org.jboss.arquillian.persistence.Transactional;
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
public class FuncionarioPersistenceTest {

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
    @Transactional(TransactionMode.ROLLBACK)
    public void deveInserir2Funcionarios() {

        TypedQuery<Funcionario> query = em.createQuery("select f from Funcionario f", Funcionario.class);

        assertEquals(0, query.getResultList().size());


        Funcionario funcionario1 = new Funcionario("Xico", 2000.0, Escolaridade.FUNDAMENTAL);
        Funcionario funcionario2 = new Funcionario("Maria", 4000.0, Escolaridade.MEDIO);

        assertEquals(null, funcionario1.getId());
        assertEquals(null, funcionario2.getId());

        em.persist(funcionario1);
        em.persist(funcionario2);

        assertEquals(Integer.valueOf(1), funcionario1.getId());
        assertEquals(Integer.valueOf(2), funcionario2.getId());

        assertEquals(2, query.getResultList().size());
    }

}