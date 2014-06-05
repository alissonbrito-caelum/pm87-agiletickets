package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculoParaEspetaculoSemAcrescimo implements CalculoDeAcrescimo {

	@Override
	public BigDecimal aplica(Sessao sessao) {
		return sessao.getPreco();
	}
	
}
