package com.curso.challengeMoneda.modelos;

import java.util.Map;

public class Moneda {

    private Map<String, Double> valor;
    private String simbolo;

    public Moneda(MonedaExchange miMonedaExchange) {
        this.simbolo = miMonedaExchange.base_code();
        this.valor = miMonedaExchange.conversion_rates();
    }

}
