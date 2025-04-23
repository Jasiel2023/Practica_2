package com.estructura.clase.controller.data_structure;

import com.estructura.clase.controller.data_structure.LeerArchivo;
import com.estructura.clase.controller.data_structure.Generador;
import com.estructura.clase.controller.data_structure.LinkendList;

import java.util.*;
import java.time.*;

public class Main {
    
    public static void main(String[] args) {
        List<Long> tiemposArray = new ArrayList<>();
        List<Long> tiemposLista = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            System.out.println("------ EJECUCIÓN #" + i + " ------");

            // Usamos la nueva clase ArchivoReader para leer los datos desde el archivo
            List<Generador> data = LeerArchivo.leerDesdeArchivo("data.txt");

            if (data.isEmpty()) {
                System.out.println("No se pudo leer el archivo o está vacío.");
                return;
            }

            // --- ARREGLO ---
            long inicioA = System.currentTimeMillis();
            Generador[] arreglo = new Generador[data.size()];
            Set<Generador> duplicadosA = new HashSet<>();
            for (int j = 0; j < data.size(); j++) {
                Generador g = data.get(j);
                for (int k = 0; k < j; k++) {
                    if (g.equals(arreglo[k])) {
                        duplicadosA.add(g);
                        break;
                    }
                }
                arreglo[j] = g;
            }
            long finA = System.currentTimeMillis();
            tiemposArray.add(finA - inicioA);

            // --- LISTA ENLAZADA ---
            long inicioL = System.currentTimeMillis();
            LinkendList<Generador> lista = new LinkendList<>();
            Set<Generador> duplicadosL = new HashSet<>();
            for (Generador g : data) {
                boolean esDup = false;
                for (int j = 0; j < lista.getLength(); j++) {
                    if (g.equals(lista.get(j))) {
                        duplicadosL.add(g);
                        esDup = true;
                        break;
                    }
                }
                lista.add(g);
            }
            long finL = System.currentTimeMillis();
            tiemposLista.add(finL - inicioL);

            System.out.println("Duplicados arreglo: " + duplicadosA.size());
            System.out.println("Duplicados lista: " + duplicadosL.size());
            System.out.println("Tiempo arreglo: " + (finA - inicioA) + " ms");
            System.out.println("Tiempo lista: " + (finL - inicioL) + " ms");
        }

        // --- TABLA COMPARATIVA ---
        System.out.println("\n======= TABLA COMPARATIVA =======");
        System.out.println("| Iteración | Arreglo (ms) | Lista (ms) |");
        System.out.println("|-----------|--------------|------------|");
        for (int i = 0; i < 3; i++) {
            System.out.printf("|     %d     |     %5d     |   %5d    |\n",
                    i + 1, tiemposArray.get(i), tiemposLista.get(i));
        }
    }
}