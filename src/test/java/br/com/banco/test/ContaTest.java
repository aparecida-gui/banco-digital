package br.com.banco.test;

import br.com.banco.Agencia;
import br.com.banco.Banco;
import br.com.banco.Conta;
import br.com.banco.ContaCorrente;
import br.com.banco.exeptions.ValidaContaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ContaTest {
	@Test
	void deposito_na_propria_conta_realizado_com_sucesso() {
		Conta conta = new ContaCorrente(111, new BigDecimal(1000),
				new Agencia(123,
						new Banco("Banco do Brasil", "00000000000")));

		conta.depositar(new BigDecimal(100));
		Assertions.assertEquals(new BigDecimal(1100), conta.getSaldo());
	}

	@Test
	void depositar_valor_zerado_na_conta() {
		Conta conta = new ContaCorrente(111, new BigDecimal(1000),
				new Agencia(123,
						new Banco("Banco do Brasil", "00000000000")));

		Assertions.assertThrows(ValidaContaException.class,
				() -> conta.depositar(new BigDecimal(0)),
				"Valor do deposito não pode ser zerado.");
	}

	@Test
	void depositar_valor_negativo_na_conta() {
		Conta conta = new ContaCorrente(111, new BigDecimal(1000),
				new Agencia(123,
						new Banco("Banco do Brasil", "00000000000")));

		Assertions.assertThrows(ValidaContaException.class,
				() -> conta.depositar(new BigDecimal(-100)),
				"Valor do deposito não pode ser negativo.");
	}
}