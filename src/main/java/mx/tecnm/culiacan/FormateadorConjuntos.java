package mx.tecnm.culiacan;

import java.util.Set;
import java.util.stream.Collectors;

public class FormateadorConjuntos {
    /**
     * Convierte un conjunto de números enteros a una representación textual.
     *
     * @param conjunto Conjunto que se desea formatear.
     * @return Cadena con los elementos entre llaves o el símbolo
     * de conjunto vacío cuando no contiene elementos.
     */
    public String formatearConjunto(Set<Integer> conjunto) {
        String cadenaConjunto = conjunto.toString();
        StringBuilder stringBuilder = new StringBuilder();

        if (conjunto.isEmpty()) stringBuilder.append("∅");
        else stringBuilder.append("{").append(cadenaConjunto, 1, cadenaConjunto.length() - 1).append("}");

        return stringBuilder.toString();
    }

    /**
     * Convierte el resultado de un producto cartesiano a una cadena de texto.
     *
     * Utiliza Streams para obtener la representación de cada par ordenado
     * y unirlos mediante comas dentro de llaves.
     *
     * @param conjuntoCartesiano Conjunto de pares ordenados que se desea formatear.
     * @return Representación textual del producto cartesiano.
     */
    public String formatearConjuntoProductoCartesiano(Set<ParConjuntos> conjuntoCartesiano) {
        StringBuilder stringBuilder = new StringBuilder();

        if (conjuntoCartesiano.isEmpty()) stringBuilder.append("∅");
        else stringBuilder.append(conjuntoCartesiano.stream()
                .map(ParConjuntos::toString)
                .collect(Collectors.joining(", ", "{", "}")));

        return stringBuilder.toString();
    }

    /**
     * Convierte un conjunto potencia a una representación textual.
     *
     * Utiliza Streams para formatear cada subconjunto, separando
     * sus elementos mediante comas y encerrándolos entre llaves.
     * Los subconjuntos vacíos se representan mediante el símbolo ∅.
     *
     * @param conjuntoPotencia Conjunto potencia que se desea formatear.
     * @return Representación textual de todos los subconjuntos.
     */
    public String formatearConjuntoPotencia(Set<Set<Integer>> conjuntoPotencia) {
        StringBuilder stringBuilder = new StringBuilder();

        if (conjuntoPotencia.isEmpty()) stringBuilder.append("∅");
        else stringBuilder.append(conjuntoPotencia.stream()
                .map(subconjunto -> subconjunto.isEmpty() ? "∅" : subconjunto.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "{", "}")))
                .collect(Collectors.joining(", ", "{", "}")));

        return stringBuilder.toString();
    }
}
