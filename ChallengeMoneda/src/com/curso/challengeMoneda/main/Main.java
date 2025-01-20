package com.curso.challengeMoneda.main;

import com.curso.challengeMoneda.modelos.Moneda;
import com.curso.challengeMoneda.modelos.MonedaExchange;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        int busqueda = 0;
        Scanner sc = new Scanner(System.in);
        List<Moneda> monedas = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while(busqueda != 7){
            System.out.println("*********************************\n" +
                    "Sea bienvenido/a al conversor de Moneda \n\n1) Dólar =>> Peso argentino\n" +
                    "2) Peso argentino =>> Dólar \n3) Dólar =>> Real brasileño \n4) Real brasileño" +
                    " =>> Dólar \n5) Dólar =>> Peso colombiano \n6) Peso colombiano =>> Dólar \n" +
                    "7) Salir \n Elija una opcion válida:\n*********************************");
            busqueda = sc.nextInt();

            var simboloMoneda = "";

            switch (busqueda){
                case 1: simboloMoneda = "USD";
                    break;
                case 2: simboloMoneda = "ARS";
                    break;
                case 3: simboloMoneda = "USD";
                    break;
                case 4: simboloMoneda = "BRL";
                    break;
                case 5: simboloMoneda = "USD";
                    break;
                case 6: simboloMoneda = "COP";
                    break;
                case 7:{
                    System.out.println("Saliste del sistema");
                }
                default:{
                    System.out.println("Opcion no valida");
                    return;
                }
            }

            String direccion = "https://v6.exchangerate-api.com/v6/f19c59556b275703265e2453/latest/"+
                    simboloMoneda;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            MonedaExchange miMonedaExchange = gson.fromJson(json, MonedaExchange.class);

            double cantidad;
            System.out.println("Ingrese el valor que desea convertir");
            cantidad = sc.nextDouble();

            var divisaConvertir = "";
            double totalYaConvertido = 0;
            switch (busqueda){
                case 1:
                    divisaConvertir = "ARS";
                    totalYaConvertido= miMonedaExchange.obtenerTasa(divisaConvertir)*cantidad;
                    break;
                case 2:
                    divisaConvertir = "USD";
                    totalYaConvertido= miMonedaExchange.obtenerTasa(divisaConvertir)*cantidad;
                    break;
                case 3:
                    divisaConvertir = "BRL";
                    totalYaConvertido= miMonedaExchange.obtenerTasa(divisaConvertir)*cantidad;
                    break;
                case 4:
                    divisaConvertir = "USD";
                    totalYaConvertido= miMonedaExchange.obtenerTasa(divisaConvertir)*cantidad;
                    break;
                case 5:
                    divisaConvertir = "COP";
                    totalYaConvertido= miMonedaExchange.obtenerTasa(divisaConvertir)*cantidad;
                    break;
                case 6:
                    divisaConvertir = "USD";
                    totalYaConvertido= miMonedaExchange.obtenerTasa(divisaConvertir)*cantidad;
                    break;
            }

            System.out.println("El valor "+ cantidad + " " + miMonedaExchange.mostrarDatos()+ " corresponde" +
                    " al valor final de =>> "+ totalYaConvertido + " " + divisaConvertir);
        }
    }
}