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

        System.out.println("Primer conjunto: " + primerConjunto + ", Segundo Conjunto: " + segundoConjunto);
        System.out.println("Esta vacio: " + primerConjunto.isEmpty());
        System.out.println("Contiene elemento : " + primerConjunto.contains(5));
        System.out.println("Es SubConjunto: " + primerConjunto.subSet(conjuntoB));
        System.out.println("Es SubConjunto Propio: " + primerConjunto.subSetP(conjuntoB));
        System.out.println("Union: " + FormateadorConjuntos.formatearConjunto(primerConjunto.union(conjuntoB)));
        System.out.println("Interseccion: " + FormateadorConjuntos.formatearConjunto(primerConjunto.intersection(conjuntoB)));
        System.out.println("Diferencia: " + FormateadorConjuntos.formatearConjunto(primerConjunto.difference(conjuntoB)));
        System.out.println("Complemento: " + FormateadorConjuntos.formatearConjunto(primerConjunto.complement()));
        System.out.println("Clonacion: " + FormateadorConjuntos.formatearConjunto(primerConjunto.cloneConjunto()));
        System.out.println("Son iguales: " + primerConjunto.equals(segundoConjunto));
        System.out.println("Producto Cartesiano: " + FormateadorConjuntos.formatearConjuntoProductoCartesiano(primerConjunto.productC(conjuntoB)));
        System.out.println("Conjunto Potencia: " + FormateadorConjuntos.formatearConjuntoPotencia(primerConjunto.pow()));
    }
}