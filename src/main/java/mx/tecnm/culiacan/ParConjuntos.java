package mx.tecnm.culiacan;

/**
 * Representa un par ordenado de números enteros utilizado
 * en las operaciones de producto cartesiano entre conjuntos.
 *
 * @param primerConjunto Elemento perteneciente al primer conjunto.
 * @param segundoConjunto Elemento perteneciente al segundo conjunto.
 */
public record ParConjuntos(int primerConjunto, int segundoConjunto) {

    /**
     * Genera una representación textual del par ordenado.
     *
     * @return Cadena que contiene ambos elementos separados por una coma
     * y encerrados entre llaves.
     */
    @Override
    public String toString() {
        return "{" + primerConjunto + ", " + segundoConjunto + "}";
    }
}