package com.estructura.clase.controller.data_structure;


public class LinkendList<E> {
    private Node<E> head;
    private Node<E> last;
    private Integer length;

    // getter and setter
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer lenth) {
        this.length = lenth;
    }

    public LinkendList() {
        this.head = null;
        this.last = null;
        this.length = 0;
    }

    public Boolean isEmpty() {
        return head == null || length == 0;
    }

    private Node<E> getNode(Integer pos){
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Indice Fuera de rango");
            // System.out.println("lista vacia");
            // return null;
        } else if (pos < 0 || pos >= length) {
            throw new ArrayIndexOutOfBoundsException("Indice Fuera de rango");
            // System.out.println("Fuera de rango");
            // return null;
        } else if (pos == 0) {
            return head;
        } else if ((length.intValue()-1) == pos.intValue()) {
            return last;
        } else {
            Node<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                search = search.getNext();
                cont++;
            }
            return search;
        }
    }

    private E getDattaFirst(){
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("List empty");
        } else {
            return head.getData();
        }
    }

    private E getDattaLast(){
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("List empty");
        }else {
            return last.getData();
        }

    }


    public E get(Integer pos) {
        return getNode(pos).getData();
         /*if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("List empty");
            //System.out.println("Lista vacia");
            //return null;
        }else if (pos < 0 || pos >= length) {
           // System.out.println("Fuera de rango");
            //return null;
            throw new ArrayIndexOutOfBoundsException("Index out range");
        }else if (pos == 0) {
            return getDataFisrt();
            
        } else if (length.intValue()== pos.intValue()) {
            return getDataLast();
        }
        else {
            return getNode(pos).getData();
        }*/
    }

    private void addFirst(E data) {
        if (isEmpty()) {
            Node<E> aux = new Node<>(data);
            head = aux;
            last = aux;
            
        } else {
            Node<E> head_old = head;
            Node<E> aux = new Node<>(data, head_old);
            head = aux;

        }
        length++;
    }

    private void addLast(E data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            Node<E> aux = new Node<>(data);
            last.setNext(aux);
            last = aux;
            length++;
        }
    }

    public void add(E data, Integer pos) throws Exception {
        if (pos == 0) {
            addFirst(data);
        } else if (length.intValue() == pos.intValue()) {
            addLast(data);
        } else {
            // a b c d f
            // e post 4
            // search_preview = a b c d
            // search = f
            // aux = e f
            // search_preview a b c d e f
            Node<E> search_preview = getNode(pos - 1);
            Node<E> search = getNode(pos);
            Node<E> aux = new Node<>(data, search);
            search_preview.setNext(aux);
            length++;
        }
    }

    public void add(E data) {
        addLast(data);
    }

    public String print() {
        if (isEmpty()) {
            return "La lista esta vacia";

        } else {
            // head = null
            StringBuilder resp = new StringBuilder();
            Node<E> help = head;
            while (help != null) {
                // resp += help.getData() + " -> ;
                resp.append(help.getData()).append(" -> ");
                help = help.getNext();
            }
            resp.append("\n");
            return resp.toString();
        }
    }

    public void update(E data, Integer pos) {
        getNode(pos).setData(data);
    }

    public void clear() {
        head = null;
        last = null;
        length = 0;

    }

    public static void main(String[] args){
        LinkendList<Double> lista = new LinkendList<>();
        try {
            System.out.println("HOLA");
            // lista.update(10.00, 0);

            lista.add(4.7);
            System.out.println(lista.print());
            lista.add(9.0, 1);
            lista.add(12.0, 2);
            lista.add(-1.0, lista.getLength());
            System.out.println(lista.print());
            System.out.println(lista.getLength() + " size");
            System.out.println(lista.get(lista.getLength() - 1));
            System.out.println("Actualizar");
            lista.update(10.00, 0);
            System.out.println(lista.print());
            System.out.println("Borrar");
            lista.clear();
            System.out.println(lista.print());

        } catch (Exception e) {
            System.out.println("Error: " + e);
            // TODO: handle exception
        }
        System.out.println(lista.print());
        System.out.println("FINAl");
    }

}