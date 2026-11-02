package mx.tecnm.culiacan;

import java.util.Set;

/**
 * Define las operaciones fundamentales de la teoría de conjuntos.
 *
 * Permite realizar operaciones de pertenencia, subconjuntos,
 * unión, intersección, diferencia, complemento, clonación,
 * producto cartesiano y conjunto potencia.
 */
public interface Conjunto {

    /**
     * Comprueba si el conjunto está vacío.
     *
     * @return true si el conjunto no contiene elementos, false en caso contrario.
     */
    boolean isEmpty();

    /**
     * Comprueba si un elemento pertenece al conjunto.
     *
     * @param elemento Número entero que se desea buscar.
     * @return true si el elemento pertenece al conjunto, false en caso contrario.
     */
    boolean contains(int elemento);

    /**
     * Determina si el conjunto actual es subconjunto del conjunto proporcionado.
     *
     * @param segundoConjunto Conjunto que podría contener al conjunto actual.
     * @return true si todos los elementos del conjunto actual pertenecen al segundo conjunto.
     */
    boolean subSet(Set<Integer> segundoConjunto);

    /**
     * Determina si el conjunto actual es subconjunto propio del conjunto proporcionado.
     *
     * @param segundoConjunto Conjunto con el que se realizará la comparación.
     * @return true si el conjunto actual es subconjunto del segundo y ambos son diferentes.
     */
    boolean subSetP(Set<Integer> segundoConjunto);
    /**
     * Calcula la unión del conjunto actual con otro conjunto.
     *
     * @param segundoConjunto Conjunto con el que se realizará la unión.
     * @return Conjunto que contiene todos los elementos de ambos conjuntos.
     */
    Set<Integer> union(Set<Integer> segundoConjunto);

    /**
     * Calcula la intersección del conjunto actual con otro conjunto.
     *
     * @param segundoConjunto Conjunto con el que se realizará la intersección.
     * @return Conjunto que contiene los elementos comunes de ambos conjuntos.
     */
    Set<Integer> intersection(Set<Integer> segundoConjunto);

    /**
     * Calcula la diferencia entre el conjunto actual y otro conjunto.
     *
     * @param segundoConjunto Conjunto cuyos elementos serán excluidos.
     * @return Conjunto con los elementos que pertenecen al conjunto actual,
     * pero no al segundo conjunto.
     */
    Set<Integer> difference(Set<Integer> segundoConjunto);

    /**
     * Calcula el complemento del conjunto actual respecto al conjunto universal.
     *
     * @return Conjunto que contiene los elementos del conjunto universal
     * que no pertenecen al conjunto actual.
     */
    Set<Integer> complement();

    /**
     * Crea una copia independiente del conjunto actual.
     *
     * @return Nuevo conjunto que contiene los mismos elementos del original.
     */
    Set<Integer> cloneConjunto();

    /**
     * Calcula el producto cartesiano del conjunto actual con otro conjunto.
     *
     * @param segundoConjunto Conjunto con el que se realizará el producto cartesiano.
     * @return Conjunto que contiene todos los pares ordenados posibles.
     */
    Set<ParConjuntos> productC(Set<Integer> segundoConjunto);

    /**
     * Calcula el conjunto potencia del conjunto actual.
     *
     * @return Conjunto que contiene todos los subconjuntos posibles,
     * incluyendo el conjunto vacío y el conjunto original.
     */
    Set<Set<Integer>> pow();

    /**
     * Obtiene los elementos del conjunto actual.
     *
     * @return Conjunto de números enteros.
     */
    Set<Integer> getConjunto();
}