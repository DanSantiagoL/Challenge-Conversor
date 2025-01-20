package com.curso.challengeMoneda.modelos;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public record MonedaExchange(Map<String, Double> conversion_rates, String base_code) {

    public Double obtenerTasa(String moneda) {
        // Verifica si la moneda existe en el mapa y retorna su valor

        return conversion_rates.getOrDefault(moneda.toUpperCase(), null);
        // Retorna null si no existe


    }

    public Double obtenerTasa2(String moneda) {
        // Verifica si la moneda existe en el mapa y retorna su valor

        return conversion_rates.getOrDefault(moneda.toUpperCase(), null);
        // Retorna null si no existe


    }

    public String mostrarDatos() {

        // Y quieres mostrar solo los primeros 3 caracteres de base_code
        String parteBaseCode = base_code.substring(0, Math.min(3, base_code.length()));

        return parteBaseCode;
    }
}
