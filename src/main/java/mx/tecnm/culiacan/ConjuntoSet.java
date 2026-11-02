package mx.tecnm.culiacan;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementa las operaciones fundamentales de la teoría de conjuntos
 * utilizando la interfaz Set de Java.
 * <p>
 * Permite realizar operaciones de pertenencia, subconjuntos, unión,
 * intersección, diferencia, complemento, producto cartesiano
 * y conjunto potencia.
 * <p>
 * También proporciona métodos para representar los conjuntos
 * mediante cadenas de texto.
 */
public class ConjuntoSet implements Conjunto {

    /** Conjunto universal utilizado para calcular el complemento. */
    private static final Set<Integer> CONJUNTO_UNIVERSAL = Set.of(1,2,3,4,5,6,7,8,9);

    /** Conjunto de números enteros sobre el que se realizan las operaciones. */
    private final Set<Integer> conjunto;

    /**
     * Inicializa un conjunto vacío.
     */
    public ConjuntoSet() { conjunto = new LinkedHashSet<>(); }

    /**
     * Inicializa un conjunto a partir de una colección de elementos.
     *
     * @param conjunto Colección de elementos que formarán el conjunto.
     */
    public ConjuntoSet(Collection conjunto) { this.conjunto = new LinkedHashSet<>(conjunto); }

    /**
     * Comprueba si un elemento pertenece al conjunto.
     *
     * @param elemento Elemento que se desea buscar.
     * @return true si el elemento pertenece al conjunto, false en caso contrario.
     */
    @Override
    public boolean contains(int elemento) { return conjunto.contains(elemento); }

    /**
     * Determina si el primer conjunto es subconjunto del segundo.
     *
     * @param segundoConjunto Conjunto que podría contener al conjunto actual.
     * @return true si todos los elementos del primer conjunto pertenecen al segundo.
     */
    @Override
    public boolean subSet(Set<Integer> segundoConjunto) {
        return segundoConjunto.containsAll(conjunto);
    }

    /**
     * Determina si el primer conjunto es subconjunto propio del segundo.
     *
     * Un subconjunto propio contiene únicamente elementos del segundo
     * conjunto, pero ambos conjuntos no pueden ser iguales.
     *
     * @param segundoConjunto Conjunto que podría contener al actual.
     * @return true si el primer conjunto es subconjunto propio del segundo.
     */
    @Override
    public boolean subSetP(Set<Integer> segundoConjunto) {
        return segundoConjunto.containsAll(conjunto) && segundoConjunto.size() != conjunto.size();
    }

    /**
     * Calcula la unión del conjunto actual con otro conjunto.
     *
     * @param segundoConjunto Conjunto que se unirá al conjunto actual.
     * @return Nuevo conjunto que contiene todos los elementos de ambos conjuntos.
     */
    @Override
    public Set<Integer> union(Set<Integer> segundoConjunto) {
        Set<Integer> conjuntoResultante = new LinkedHashSet<>(conjunto);
        conjuntoResultante.addAll(segundoConjunto);
        return conjuntoResultante;
    }

    /**
     * Calcula la intersección del conjunto actual con otro conjunto.
     *
     * @param segundoConjunto Conjunto con el que se realizará la intersección.
     * @return Nuevo conjunto con los elementos comunes de ambos conjuntos.
     */
    @Override
    public Set<Integer> intersection(Set<Integer> segundoConjunto) {
        Set<Integer> conjuntoResultante = new LinkedHashSet<>(conjunto);
        conjuntoResultante.retainAll(segundoConjunto);
        return conjuntoResultante;
    }

    /**
     * Calcula la diferencia entre el conjunto actual y otro conjunto.
     *
     * @param segundoConjunto Conjunto cuyos elementos serán excluidos.
     * @return Nuevo conjunto con los elementos que pertenecen al conjunto
     * actual, pero no al segundo conjunto.
     */
    @Override
    public Set<Integer> difference(Set<Integer> segundoConjunto) {
        Set<Integer> conjuntoResultante = new LinkedHashSet<>(conjunto);
        conjuntoResultante.removeAll(segundoConjunto);
        return conjuntoResultante;
    }

    /**
     * Calcula el complemento del conjunto actual respecto al conjunto universal.
     *
     * @return Nuevo conjunto con los elementos del conjunto universal
     * que no pertenecen al conjunto actual.
     */
    @Override
    public Set<Integer> complement() {
        Set<Integer> conjuntoResultante = new LinkedHashSet<>(CONJUNTO_UNIVERSAL);
        conjuntoResultante.removeAll(conjunto);
        return conjuntoResultante;
    }

    /**
     * Crea una copia independiente del conjunto actual.
     *
     * @return Nuevo conjunto que contiene los mismos elementos.
     */
    @Override
    public Set<Integer> cloneConjunto() { return new LinkedHashSet<>(conjunto); }

    /**
     * Calcula el producto cartesiano del conjunto actual con otro conjunto.
     *
     * Convierte ambos conjuntos en listas y utiliza un método recursivo
     * para generar todos los pares ordenados posibles.
     *
     * @param segundoConjunto Conjunto con el que se realizará el producto cartesiano.
     * @return Conjunto de pares ordenados del producto cartesiano.
     */
    @Override
    public Set<ParConjuntos> productC(Set<Integer> segundoConjunto) {
        return productoCartesianoRecursivo(new ArrayList<>(conjunto), new ArrayList<>(segundoConjunto), 0, 0);
    }

    /**
     * Genera recursivamente los pares ordenados del producto cartesiano.
     *
     * Recorre ambos conjuntos mediante dos índices y agrega cada par
     * al regresar de las llamadas recursivas.
     *
     * @param primerConjunto Lista de elementos del primer conjunto.
     * @param segundoConjunto Lista de elementos del segundo conjunto.
     * @param i Índice actual del primer conjunto.
     * @param j Índice actual del segundo conjunto.
     * @return Conjunto de pares ordenados generados recursivamente.
     */
    private Set<ParConjuntos> productoCartesianoRecursivo(List<Integer> primerConjunto, List<Integer> segundoConjunto, int i, int j) {
        if (i == primerConjunto.size()) return new LinkedHashSet<>();
        if (j == segundoConjunto.size()) return productoCartesianoRecursivo(primerConjunto, segundoConjunto, i + 1, 0);

        Set<ParConjuntos> resultado = productoCartesianoRecursivo(primerConjunto, segundoConjunto, i, j + 1);
        resultado.add(new ParConjuntos(primerConjunto.get(i), segundoConjunto.get(j)));
        return resultado;
    }

    /**
     * Calcula el conjunto potencia del conjunto actual.
     *
     * @return Conjunto que contiene todos los subconjuntos posibles,
     * incluyendo el conjunto vacío y el conjunto original.
     */
    @Override
    public Set<Set<Integer>> pow() { return potenciaRecursiva(conjunto); }

    /**
     * Genera recursivamente todos los subconjuntos posibles de un conjunto.
     *
     * Selecciona un elemento y calcula el conjunto potencia de los
     * elementos restantes. Posteriormente, mediante Streams, combina
     * los subconjuntos que contienen y no contienen dicho elemento.
     *
     * @param conjunto Conjunto del que se obtendrán los subconjuntos.
     * @return Conjunto potencia generado recursivamente.
     */
    private Set<Set<Integer>> potenciaRecursiva(Set<Integer> conjunto) {
        if (conjunto.isEmpty()) return Set.of(Set.of());

        Integer elemento = conjunto.iterator().next();

        Set<Integer> restantes = conjunto.stream()
                .filter(x -> !x.equals(elemento))
                .collect(Collectors.toSet());

        Set<Set<Integer>> subconjuntos = potenciaRecursiva(restantes);

        return Stream.concat(
                subconjuntos.stream(),
                subconjuntos.stream().map(subconjunto -> Stream.concat(subconjunto.stream(), Stream.of(elemento)).collect(Collectors.toSet()))
        ).collect(Collectors.toSet());
    }

    /**
     * Obtiene el conjunto actual.
     *
     * @return Referencia al conjunto de números enteros.
     */
    @Override
    public Set<Integer> getConjunto() { return new LinkedHashSet<>(conjunto); }

    /**
     * Comprueba si el conjunto actual está vacío.
     *
     * @return true si el conjunto no contiene elementos, false en caso contrario.
     */
    @Override
    public boolean isEmpty() { return conjunto.isEmpty(); }

    /**
     * Compara el conjunto actual con otro objeto para determinar su igualdad.
     *
     * @param o Objeto con el que se realizará la comparación.
     * @return true si ambos objetos son de la misma clase y contienen
     * conjuntos iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConjuntoSet conjuntoSetB = (ConjuntoSet) o;
        return Objects.equals(conjunto, conjuntoSetB.conjunto);
    }

    /**
     * Calcula el código hash del conjunto actual.
     *
     * @return Código hash asociado al contenido del conjunto.
     */
    @Override
    public int hashCode() { return Objects.hashCode(conjunto); }

    /**
     * Genera una representación textual del conjunto actual.
     *
     * Los elementos se muestran entre llaves y separados por comas.
     * Si el conjunto está vacío, se utiliza el símbolo de conjunto vacío.
     *
     * @return Representación del conjunto en formato de cadena.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (conjunto.isEmpty()) stringBuilder.append("∅");
        else stringBuilder.append("{").append(String.join(", ", conjunto.stream().map(String::valueOf).toList())).append("}");

        return stringBuilder.toString();
    }
}