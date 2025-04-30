package com.estructura.clase.controller;

import com.estructura.clase.controller.data_structure.LinkedList;
import com.estructura.clase.controller.data_structure.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Practica {
    private LinkedList<Integer> linkedList;
    private LinkedList<Integer> duplicatesLinkedList;
    private Integer[] array;
    private Integer[] duplicatesArray;
    private int size;

    public Practica() {
        linkedList = new LinkedList<>();
        duplicatesLinkedList = new LinkedList<>();
    }

    public void readFile(String path) throws IOException {
        int lines = contarLineas(path);

        array = new Integer[lines];
        duplicatesArray = new Integer[lines];
        size = lines;

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        int index = 0;

        while ((line = reader.readLine()) != null) {
            int value = Integer.parseInt(line.trim());
            array[index++] = value;
            linkedList.add(value);
        }
        reader.close();
    }

    private int contarLineas(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int count = 0;
        while (reader.readLine() != null) count++;
        reader.close();
        return count;
    }

    public long findDuplicatesArray() {
        long start = System.nanoTime();
        int pos = 0;

        for (int i = 0; i < size; i++) {
            Integer current = array[i];
            for (int j = i + 1; j < size; j++) {
                if (current.equals(array[j])) {
                    duplicatesArray[pos++] = current;
                    break;
                }
            }
        }

        return System.nanoTime() - start;
    }

    public long findDuplicatesLinkedList() throws Exception {
        long start = System.nanoTime();

        Node<Integer> pointer = linkedList.getHead();
        while (pointer != null) {
            Node<Integer> scanner = pointer.getNext();
            while (scanner != null) {
                if (pointer.getData().equals(scanner.getData())) {
                    duplicatesLinkedList.add(pointer.getData());
                    break;
                }
                scanner = scanner.getNext();
            }
            pointer = pointer.getNext();
        }

        return System.nanoTime() - start;
    }

    public void runPerformanceComparison() throws Exception {
        System.out.println("\n>>> Comparación de Tiempo de Detección <<<");
        System.out.printf("%-10s %-18s %-18s%n", "Iteración", "Tiempo ArregloS (ms)", "Tiempo Lista (ms)");
        System.out.println("---------   ---------------    ---------------");

        for (int i = 1; i <= 3; i++) {
            duplicatesArray = new Integer[size];
            duplicatesLinkedList.clear();

            long timeArray = findDuplicatesArray();
            long timeList = findDuplicatesLinkedList();

           


            System.out.printf("%-10d %-18.3f %-18.3f%n", i, timeArray/1_000_000.0 , timeList/ 1_000_000.0);
        }

        mostrarResumen();
    }

    private void mostrarResumen() {
        int arrayCount = 0;
        for (Integer num : duplicatesArray) {
            if (num != null) arrayCount++;
        }

        int listCount = duplicatesLinkedList.getLength();

        System.out.println("\n>>> Resumen Final <<<");
        System.out.println("Duplicados encontrados (Array): " + arrayCount);
        System.out.println("Duplicados encontrados (Lista): " + listCount);
    }

    public static void main(String[] args) {
        try {
            Practica p = new Practica();
            String ruta = "data.txt";

            p.readFile(ruta);
            p.runPerformanceComparison();

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error de ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
