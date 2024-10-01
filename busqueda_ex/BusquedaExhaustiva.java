import java.util.*;

public class BusquedaExhaustiva {
    
    // Función que realiza la búsqueda exhaustiva
    public static String buscarPosicion(int posicionInicial, int objetivo, int[] estados) {
        Queue<Integer> cola = new LinkedList<>();
        Set<Integer> visitados = new HashSet<>();
        
        // Añadimos la posición inicial a la cola
        cola.add(posicionInicial);
        visitados.add(posicionInicial);

        //mientras hayas que explorar....
        while (!cola.isEmpty()) {
            int posicionActual = cola.poll();
            
            // verificación
            if (posicionActual == objetivo) {
                return "Posición de montaje encontrada en: " + posicionActual;
            } else {
                System.out.println("Pieza NO encontrada en la posición " + posicionActual);
            }

            // si no se verifica, busca la adyacente.
            for (int siguientePosicion : obtenerMovimientos(posicionActual, estados)) {
                if (!visitados.contains(siguientePosicion)) {
                    cola.add(siguientePosicion);
                    visitados.add(siguientePosicion);
                }
            }
        }
        
        return "No se encontró la posición de montaje";
    }
    
    // Función que genera los movimientos (izquierda y derecha)
    public static int[] obtenerMovimientos(int posicion, int[] estados) {
        // Generamos las posiciones adyacentes
        List<Integer> movimientos = new ArrayList<>();
        
        for (int estado : estados) {
            if (Math.abs(estado - posicion) == 1) { // Si es adyacente
                movimientos.add(estado);
            }
        }
        
        // Convertir la lista a un arreglo
        return movimientos.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        // En mi caso elegí 10 posiciones. Si bien el algoritmo se ejecutará periódicamente, asumo que la pieza no se desplazará demasiado.
        // pero estará el sistema preparado para un desplazamiento mayor, en el caso de que suceda.
        int[] estados = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        
        // posicionInicial -> Posición en donde el brazo robótico buscará la pieza para su colocación.  0 es la posición B
        int posicionInicial = 0;

        //Generamos el núimero aleatorio en donde en esta oportunidad se encontrará la pieza.
        //Random random = new Random();
        // int objetivo = estados[random.nextInt(estados.length)];  // Genera un número aleatorio dentro del rango de posiciones posibles
        int objetivo = -4;

        System.out.println("La posición de la pieza esta en la posición: " + objetivo);

        // Llamar a la función de búsqueda
        String resultado = buscarPosicion(posicionInicial, objetivo, estados);
        System.out.println(resultado);
    }
}
