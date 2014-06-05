package br.com.caelum.agiletickets.domain.precos;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public BigDecimal calcula(Sessao sessao, Integer quantidade) {
		TipoDeEspetaculo tipoDeEspetaculo = sessao.getEspetaculo().getTipo();
		
		CalculoDeAcrescimo calculoDeAcrescimo = tipoDeEspetaculo.getCalculoAcrescimo();
 
		BigDecimal precoUnitario = calculoDeAcrescimo.aplica(sessao);
		
		BigDecimal precoTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
		
		return precoTotal;
	}


}