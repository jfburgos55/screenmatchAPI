package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.ErrorConversionDuracion;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("---- Iniciando API Java Peliculas ----");
        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        // JSON to Java Class
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        Titulo miTitulo = null;
        while (true) {
            System.out.println("Digite nombre de la pelicula: ");
            var busqueda = lectura.nextLine();

            if (busqueda.equalsIgnoreCase("Salir")) {
                break;
            }
            // Aca ajustar la api key
            String direccion = "http://www.omdbapi.com/?t="
                    + busqueda.replace(" ", "+")
                    + "&apikey=COLOCAR_API_KEY";
            miTitulo = null;
            try {
                // HTTP Request
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                //HTTP Response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(response.body());

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmdb);

                miTitulo = new Titulo(miTituloOmdb);
                System.out.println("Titulo convertido: " + miTitulo);

                titulos.add(miTitulo);
            } catch (NumberFormatException e) {
                System.out.println("Ocurrio un error: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error en la URI, verificar direccion: ");
                System.out.println(e.getMessage());
            } catch (ErrorConversionDuracion e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);
        FileWriter escrituraPeliculas = new FileWriter("titulos.json");
        escrituraPeliculas.write(gson.toJson(titulos));
        escrituraPeliculas.close();
        System.out.println("Finaliza la ejecución del programa!");
    }
}
