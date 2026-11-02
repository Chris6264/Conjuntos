package mx.tecnm.culiacan;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Integer> listaPrimerConjunto = List.of(1,2,2,3,3);
        Conjunto primerConjunto = new ConjuntoSet(listaPrimerConjunto);

        List<Integer> listaSegundoConjunto = List.of(1,2,2,3);
        Conjunto segundoConjunto = new ConjuntoSet(listaSegundoConjunto);

        Set<Integer> conjuntoA = primerConjunto.getConjunto();
        Set<Integer> conjuntoB = segundoConjunto.getConjunto();

        FormateadorConjuntos formateadorConjuntos = new FormateadorConjuntos();

        System.out.println("Primer conjunto: " + primerConjunto + ", Segundo Conjunto: " + segundoConjunto);
        System.out.println("Esta vacio: " + primerConjunto.isEmpty());
        System.out.println("Contiene elemento : " + primerConjunto.contains(5));
        System.out.println("Es SubConjunto: " + primerConjunto.subSet(conjuntoB));
        System.out.println("Es SubConjunto Propio: " + primerConjunto.subSetP(conjuntoB));
        System.out.println("Union: " + formateadorConjuntos.formatearConjunto(primerConjunto.union(conjuntoB)));
        System.out.println("Interseccion: " + formateadorConjuntos.formatearConjunto(primerConjunto.intersection(conjuntoB)));
        System.out.println("Diferencia: " + formateadorConjuntos.formatearConjunto(primerConjunto.difference(conjuntoB)));
        System.out.println("Complemento: " + formateadorConjuntos.formatearConjunto(primerConjunto.complement()));
        System.out.println("Clonacion: " + formateadorConjuntos.formatearConjunto(primerConjunto.cloneConjunto()));
        System.out.println("Son iguales: " + primerConjunto.equals(segundoConjunto));
        System.out.println("Producto Cartesiano: " + formateadorConjuntos.formatearConjuntoProductoCartesiano(primerConjunto.productC(conjuntoB)));
        System.out.println("Conjunto Potencia: " + formateadorConjuntos.formatearConjuntoPotencia(primerConjunto.pow()));
    }
}