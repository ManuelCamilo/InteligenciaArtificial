import java.util.*;

public class BusquedaHeuristica {

    // Función principal que realiza la búsqueda heurística
    public static String buscarPosicionHeuristica(int posicionInicial, int objetivo, int[] estados) {

        // Creamos una cola de prioridad (PriorityQueue) que ordenará las posiciones según su valor heurístico (distancia al objetivo).
        PriorityQueue<Estado> cola = new PriorityQueue<>(Comparator.comparingInt(e -> e.heuristica));

        Set<Integer> visitados = new HashSet<>();

        // Añadimos la posición inicial a la cola con su valor heurístico (distancia al objetivo).
        cola.add(new Estado(posicionInicial, heuristica(posicionInicial, objetivo)));
        visitados.add(posicionInicial);

        // Mientras haya posiciones por explorar
        while (!cola.isEmpty()) {
            // Sacamos el estado con la menor heurística (el más cercano al objetivo según la estimación)
            Estado estadoActual = cola.poll();
            int posicionActual = estadoActual.posicion;

            // Mostramos en pantalla el mensaje de que estamos verificando esa posición
            System.out.println("Verificando posición " + posicionActual + " con heurística " + estadoActual.heuristica);

            // Si encontramos la posición del objetivo
            if (posicionActual == objetivo) {
                return "Posición de montaje encontrada en: " + posicionActual;
            }

            // Si no es la posición correcta, exploramos las posiciones adyacentes
            for (int siguientePosicion : obtenerMovimientos(posicionActual, estados)) {
                if (!visitados.contains(siguientePosicion)) {
                    // Calculamos la heurística de la posición adyacente y la añadimos a la cola
                    cola.add(new Estado(siguientePosicion, heuristica(siguientePosicion, objetivo)));
                    visitados.add(siguientePosicion);
                }
            }
        }

        return "No se encontró la posición de montaje";
    }

    // Función heurística que calcula la distancia absoluta entre la posición actual y el objetivo
    public static int heuristica(int posicion, int objetivo) {
        return Math.abs(posicion - objetivo);  // Distancia absoluta entre la posición actual y el objetivo
    }

    // Función que genera las posiciones adyacentes
    public static int[] obtenerMovimientos(int posicion, int[] estados) {
        List<Integer> movimientos = new ArrayList<>();
        for (int estado : estados) {
            if (Math.abs(estado - posicion) == 1) {
                movimientos.add(estado);
            }
        }
        return movimientos.stream().mapToInt(i -> i).toArray();
    }

    // Clase interna para representar un estado del robot (posición y heurística)
    static class Estado {
        int posicion;
        int heuristica;

        Estado(int posicion, int heuristica) {
            this.posicion = posicion;
            this.heuristica = heuristica;
        }
    }

    public static void main(String[] args) {
        int[] estados = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};

        int posicionInicial = 0;

        // Generamos el objetivo de forma aleatoria
        // Random random = new Random();
        //int objetivo = estados[random.nextInt(estados.length)];
        int objetivo = -4;
        // Mostramos la posición generada como objetivo
        System.out.println("La posición de montaje correcta (objetivo) está en: " + objetivo);

        // Llamamos a la función de búsqueda heurística
        String resultado = buscarPosicionHeuristica(posicionInicial, objetivo, estados);

        // Mostramos el resultado en pantalla
        System.out.println(resultado);
    }
}
