package com.estructura.clase.controller.data_structure;

import java.io.InputStream;
import java.util.*;

public class LeerArchivo {
    public static List<Generador> leerDesdeArchivo(String nombreArchivo) {
        List<Generador> lista = new ArrayList<>();
        try (InputStream is = LeerArchivo.class.getClassLoader().getResourceAsStream(nombreArchivo);
             Scanner scanner = new Scanner(is)) {

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String id = partes[0];
                    String tipo = partes[1];
                    double consumo = Double.parseDouble(partes[2]);
                    lista.add(new Generador(id, tipo, consumo));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return lista;
    }
}
