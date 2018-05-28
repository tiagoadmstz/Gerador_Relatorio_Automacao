package br.com.cbi.test;


import br.com.cbi.entities.Atendimento;
import br.com.cbi.entities.Peca;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tiago
 */
public class ManipulaBeanTest {

    public ManipulaBeanTest() {
    }

    @Test
    public void limparDadosAtributosEntidade() {
        Peca peca = new Peca(1l, "teste", "Teste Unitário de Manipula Bean", new BigDecimal(12), new Atendimento());
        peca.clear();
        Assert.assertNull(peca.getId());
        Assert.assertNull(peca.getAtendimento());
        Assert.assertNull(peca.getCodigo());
        Assert.assertNull(peca.getDescricao());
        Assert.assertNull(peca.getQuantidade());
    }

    @Test
    public void copiarObjectoCompleto() {
        Peca peca = new Peca(1l, "teste", "Teste Unitário de Manipula Bean", new BigDecimal(12), new Atendimento());
        Peca peca2 = new Peca();
        peca2.copiar(peca);
        Assert.assertEquals(peca, peca2);
    }

    @Test
    public void clonarObjectCompleto() {
        Peca peca = new Peca(1l, "teste", "Teste Unitário de Manipula Bean", new BigDecimal(12), new Atendimento());
        Peca peca2 = peca.clonar();
        Assert.assertEquals(peca, peca2);
    }
}
