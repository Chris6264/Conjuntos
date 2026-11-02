
# Conjuntos — Operaciones de teoría de conjuntos en Java
## Tecnologías utilizadas

- **Java 26** (versión configurada en `pom.xml`).
- **Apache Maven** para compilar el proyecto.
- **Java Collections Framework**: `Set`, `LinkedHashSet`, `List` y `ArrayList`.
- **Stream API** para procesar elementos y construir subconjuntos.
- **Recursividad** para calcular el producto cartesiano y el conjunto potencia.
- **Records** para representar pares ordenados.

## Funcionalidades

| Método | Operación | Ejemplo |
| --- | --- | --- |
| `isEmpty()` | Verifica si un conjunto está vacío | `{}` → `true` |
| `contains(elemento)` | Comprueba la pertenencia de un elemento | `2 ∈ {1,2,3}` → `true` |
| `subSet(B)` | Verifica si el conjunto actual es subconjunto de B | `{1,2} ⊆ {1,2,3}` → `true` |
| `subSetP(B)` | Verifica si es subconjunto propio de B | `{1,2} ⊊ {1,2,3}` → `true` |
| `union(B)` | Unión | `{1,2} ∪ {2,3} = {1,2,3}` |
| `intersection(B)` | Intersección | `{1,2} ∩ {2,3} = {2}` |
| `difference(B)` | Diferencia | `{1,2} − {2,3} = {1}` |
| `complement()` | Complemento respecto al conjunto universal | `U − A` |
| `cloneConjunto()` | Crea una copia independiente del conjunto | `{1,2} → {1,2}` |
| `productC(B)` | Producto cartesiano | `{1,2} × {3} = {(1,3),(2,3)}` |
| `pow()` | Conjunto potencia | `P({1,2}) = {∅,{1},{2},{1,2}}` |
| `getConjunto()` | Obtiene una copia de los elementos | `Set<Integer>` |

El **conjunto universal** utilizado para el complemento es:

`U = {1,2,3,4,5,6,7,8,9}`

## Estructura del proyecto

```text
Conjuntos/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── mx/
                └── tecnm/
                    └── culiacan/
                        ├── Conjunto.java
                        ├── ConjuntoSet.java
                        ├── FormateadorConjuntos.java
                        ├── ParConjuntos.java
                        └── Main.java
```

### Responsabilidad de cada archivo

- **`Conjunto.java`**: interfaz que define el contrato de las operaciones de conjuntos.
- **`ConjuntoSet.java`**: implementación de las operaciones, incluyendo los algoritmos recursivos.
- **`ParConjuntos.java`**: `record` inmutable para los pares del producto cartesiano; proporciona igualdad y código hash por valor.
- **`FormateadorConjuntos.java`**: convierte conjuntos, productos cartesianos y conjuntos potencia a representaciones legibles.
- **`Main.java`**: punto de entrada con ejemplos de todas las operaciones.

## Instalación y ejecución

### Requisitos previos

Tener instalados **JDK 26** y **Maven**.

Verifica las versiones con:

```bash
java -version
mvn -version
```

> **Nota:** `pom.xml` define `maven.compiler.source` y `maven.compiler.target` en `26`. Si utilizas otro JDK, ajusta estas propiedades a una versión compatible antes de compilar.

### Compilar y ejecutar

Desde el directorio raíz del proyecto, donde se encuentra `pom.xml`:

```bash
mvn clean compile
java -cp target/classes mx.tecnm.culiacan.Main
```

También puedes abrir el proyecto como **proyecto Maven** en IntelliJ IDEA y ejecutar el método `main` de `Main.java`.

## Ejemplo de uso

```java
import mx.tecnm.culiacan.Conjunto;
import mx.tecnm.culiacan.ConjuntoSet;
import mx.tecnm.culiacan.FormateadorConjuntos;

import java.util.Set;

public class Ejemplo {
    public static void main(String[] args) {
        Conjunto a = new ConjuntoSet(Set.of(1, 2));
        Set<Integer> b = Set.of(2, 3);
        FormateadorConjuntos formato = new FormateadorConjuntos();

        System.out.println("Unión: " + formato.formatearConjunto(a.union(b)));
        System.out.println("Intersección: " + formato.formatearConjunto(a.intersection(b)));
        System.out.println("Diferencia: " + formato.formatearConjunto(a.difference(b)));
        System.out.println("Producto cartesiano: " + formato.formatearConjuntoProductoCartesiano(a.productC(b)));
        System.out.println("Conjunto potencia: " + formato.formatearConjuntoPotencia(a.pow()));
    }
}
```

### Resultados esperados

```text
Unión: {1, 2, 3}
Intersección: {2}
Diferencia: {1}
Producto cartesiano: {(1,2), (1,3), (2,2), (2,3)}
Conjunto potencia: {∅, {1}, {2}, {1,2}}
```

**Importante:** el orden en que aparecen algunos elementos o subconjuntos puede variar. Además, la clase `ParConjuntos` utiliza llaves en su `toString()` (por ejemplo, `{1, 2}`) aunque aquí se utiliza la notación matemática convencional `(1,2)` para expresar los pares ordenados.

## Algoritmos recursivos

### Producto cartesiano (`productC`)

Dados los conjuntos:

```text
A = {1,2}
B = {3,4}
```

El producto cartesiano es:

```text
A × B = {(1,3), (1,4), (2,3), (2,4)}
```

El método `productoCartesianoRecursivo` convierte los conjuntos a listas y utiliza dos índices, `i` y `j`, para recorrer todas las combinaciones.

- `i` representa la posición del elemento del primer conjunto.
- `j` representa la posición del elemento del segundo conjunto.
- Cuando `j` llega al final del segundo conjunto, avanza `i` y reinicia `j`.
- Cuando `i` llega al final del primero, termina la recursividad.
- Los pares se agregan al regresar de las llamadas recursivas.

**Complejidad temporal:** `O(|A| × |B|)` para generar los pares, suponiendo operaciones de inserción promedio constantes.

### Conjunto potencia (`pow`)

Para el conjunto:

```text
A = {1,2,3}
```

Su conjunto potencia es:

```text
P(A) = {∅, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}}
```

El método `potenciaRecursiva` selecciona un elemento, calcula recursivamente el conjunto potencia de los elementos restantes y luego combina dos grupos usando `Stream.concat()`:

1. Los subconjuntos que **no contienen** el elemento seleccionado.
2. Los subconjuntos que **sí contienen** ese elemento.

El caso base devuelve `{∅}` cuando el conjunto de entrada está vacío.

Un conjunto con `n` elementos tiene `2^n` subconjuntos, por lo que el resultado crece exponencialmente.

**Complejidad temporal:** `O(n × 2^n)` considerando la construcción de los subconjuntos.
