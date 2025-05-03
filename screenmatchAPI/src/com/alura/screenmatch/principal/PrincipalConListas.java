package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula encanto = new Pelicula("Encanto", 2021);
        encanto.evalua(9);
        Pelicula avatar = new Pelicula("Avatar", 2023);
        avatar.evalua(6);
        var matrix = new Pelicula("Matrix", 1999);
        matrix.evalua(10);
        Serie lost = new Serie("Lost", 2000);
        lost.evalua(4);
        // Generic con superTipo
        List<Titulo> lista = new ArrayList<>();
        lista.add(encanto);
        lista.add(avatar);
        lista.add(matrix);
        lista.add(lost);

        for(Titulo item: lista){
            System.out.println(item.getNombre());
            if(item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2){
                //Pelicula pelicula = (Pelicula) item;
                System.out.println(pelicula.getClasificacion());
            }
        }

        ArrayList<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Keanu Reves");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo Darin");
        System.out.println("Lista Artistas sin ordenar: " + listaDeArtistas);

        Collections.sort(listaDeArtistas);
        System.out.println("Lista Artistas Ordenada: " + listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista de titulos ordenados (Por nombre): " + lista);

        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Ordenado de titulos ordenados (Por fecha lanzamiento): " + lista);
    }
}
