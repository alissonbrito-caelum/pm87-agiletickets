package br.com.caelum.agiletickets.models;

import br.com.caelum.agiletickets.domain.precos.CalculoDeAcrescimo;
import br.com.caelum.agiletickets.domain.precos.CalculoDeAcrescimoParaEspetaculoPremium;
import br.com.caelum.agiletickets.domain.precos.CalculoDeAcrescimoParaEspetaculoStandard;
import br.com.caelum.agiletickets.domain.precos.CalculoParaEspetaculoSemAcrescimo;

public enum TipoDeEspetaculo {

	CINEMA(new CalculoDeAcrescimoParaEspetaculoStandard()), 
	SHOW(new CalculoDeAcrescimoParaEspetaculoStandard()), 
	TEATRO(new CalculoParaEspetaculoSemAcrescimo()), 
	BALLET(new CalculoDeAcrescimoParaEspetaculoPremium()), 
	ORQUESTRA(new CalculoDeAcrescimoParaEspetaculoPremium());

	private CalculoDeAcrescimo calculoAcrescimo;

	private TipoDeEspetaculo(CalculoDeAcrescimo calculoAcrescimo) {
		this.calculoAcrescimo = calculoAcrescimo;
	}

	public CalculoDeAcrescimo getCalculoAcrescimo() {
		return calculoAcrescimo;
	}

}
