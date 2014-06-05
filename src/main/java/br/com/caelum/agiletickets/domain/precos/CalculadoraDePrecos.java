package br.com.caelum.agiletickets.domain.precos;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		if (sessaoEspetaculoTipo(sessao).equals(tipoDeEspetaculoCinema())
				|| sessaoEspetaculoTipo(sessao)
						.equals(tipoDeEspetaculoShow())) {
			// quando estiver acabando os ingressos...
			preco = calculaPreco(sessao, 1.10);
		} else if (sessaoEspetaculoTipo(sessao)
				.equals(tipoDeEspetaculoBallet())) {
			preco = calculaPrecoComAumentoDeDuracao(sessao);
		} else if (sessaoEspetaculoTipo(sessao)
				.equals(tipoDeEspetaculoOrquestra())) {
			preco = calculaPrecoComAumentoDeDuracao(sessao);
		} else {
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static TipoDeEspetaculo tipoDeEspetaculoOrquestra() {
		return TipoDeEspetaculo.ORQUESTRA;
	}

	private static TipoDeEspetaculo tipoDeEspetaculoBallet() {
		return TipoDeEspetaculo.BALLET;
	}

	private static TipoDeEspetaculo tipoDeEspetaculoShow() {
		return TipoDeEspetaculo.SHOW;
	}

	private static TipoDeEspetaculo tipoDeEspetaculoCinema() {
		return TipoDeEspetaculo.CINEMA;
	}

	private static TipoDeEspetaculo sessaoEspetaculoTipo(Sessao sessao) {
		return sessao.getEspetaculo().getTipo();
	}

	private static BigDecimal calculaPrecoComAumentoDeDuracao(Sessao sessao) {
		BigDecimal preco;
		preco = calculaPreco(sessao, 1.20);
		preco = calculaAumentoDuracaoMinutos(sessao, preco);
		return preco;
	}

	private static BigDecimal calculaAumentoDuracaoMinutos(Sessao sessao,
			BigDecimal preco) {
		if (sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal calculaPreco(Sessao sessao, double fatorAcrescimo) {
		BigDecimal preco;
		if (calculaPercentualDeIngressosRestantes(sessao) <= 0.50) {
			preco = sessao.getPreco().multiply(
					BigDecimal.valueOf(fatorAcrescimo));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

	private static double calculaPercentualDeIngressosRestantes(Sessao sessao) {
		return (sessao.getTotalIngressos() - sessao.getIngressosReservados())
				/ sessao.getTotalIngressos().doubleValue();
	}

}