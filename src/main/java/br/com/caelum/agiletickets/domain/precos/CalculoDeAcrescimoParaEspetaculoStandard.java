package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculoDeAcrescimoParaEspetaculoStandard implements CalculoDeAcrescimo {

	private static final double PORCENTAGEM_FALTANTES_STANDARD = 0.05;
	private static final BigDecimal ACRESCIMO_FALTANTES_STANDARD = BigDecimal.valueOf(0.10);
 
	@Override
	public BigDecimal aplica(Sessao sessao) {
		BigDecimal preco = sessao.getPreco();
		
		if (sessao.porcentagemDeIngressosFaltando() <= PORCENTAGEM_FALTANTES_STANDARD) {
			preco = preco.add(sessao.precoComAcrescimo(ACRESCIMO_FALTANTES_STANDARD));
		}
		
		return preco;
	}
	
}
