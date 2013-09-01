/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.casadocodigo.livrocdi.folhapagamento;

/**
 *
 * @author gilliard
 */
public class CalculadoraSalariosDezPorcento implements CalculadoraSalarios {
    
    @Override
    public double calculaSalario(Funcionario funcionario){
        return funcionario.getSalarioBase() * 1.1;
    }
}
