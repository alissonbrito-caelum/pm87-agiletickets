package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculoDeAcrescimoParaEspetaculoPremium implements CalculoDeAcrescimo {

	private static final double PORCENTAGEM_FALTANTES_PREMIUM = 0.50;
	private static final BigDecimal ACRESCIMO_FALTANTES_PREMIUM = BigDecimal.valueOf(0.20);
	private static final BigDecimal ACRESCIMO_DURACAO_PREMIUM = BigDecimal.valueOf(0.10);
	private static final int DURACAO_LIMITE_PREMIUM = 60;

	@Override
	public BigDecimal aplica(Sessao sessao) {
		BigDecimal preco = sessao.getPreco();

		if (sessao.porcentagemDeIngressosFaltando() <= PORCENTAGEM_FALTANTES_PREMIUM) {
			preco = preco.add(sessao.precoComAcrescimo(ACRESCIMO_FALTANTES_PREMIUM));
		}

		if (sessao.getDuracaoEmMinutos() > DURACAO_LIMITE_PREMIUM) {
			preco = preco.add(sessao.precoComAcrescimo(ACRESCIMO_DURACAO_PREMIUM));
		}
		
		return preco;
	}

}
