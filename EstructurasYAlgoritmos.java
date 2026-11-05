import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.TreeSet;
import java.util.TreeMap;

public class EstructurasYAlgoritmos {
    private List<Pedido> historial = new ArrayList<Pedido>();

    private LinkedList<Pedido> listaEspera = new LinkedList<Pedido>();

    private Stack<Pedido> pilaPedidos = new Stack<Pedido>();

    private Queue<Pedido> colaPedidos = new ArrayDeque<Pedido>();

    private TreeSet<Integer> codigosOrdenados = new TreeSet<Integer>();

    private TreeMap<Integer, Pedido> pedidosPorCodigo = new TreeMap<Integer, Pedido>();

    public void agregarPedido(Pedido pedido) {
        if (pedido == null) return;
        historial.add(pedido);
        listaEspera.add(pedido);
        pilaPedidos.push(pedido);
        colaPedidos.offer(pedido);
        codigosOrdenados.add(pedido.getCodigo());
        pedidosPorCodigo.put(pedido.getCodigo(), pedido);
    }

    public Pedido sacarSiguienteDeLaCola() {
        return colaPedidos.poll();
    }

    public Pedido verUltimoDeLaPila() {
        if (pilaPedidos.empty()) return null;
        return pilaPedidos.peek();
    }

    public Pedido buscarPorCodigo(int codigo) {
        return pedidosPorCodigo.get(codigo);
    }

    public int buscarSecuencial(Pedido[] pedidos, int cantidad, int codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigo() == codigo) return i;
        }
        return -1;
    }

    // Busqueda binaria: el arreglo debe estar ordenado por codigo.
    public int buscarBinaria(Pedido[] pedidos, int cantidad, int codigo) {
        int inicio = 0;
        int fin = cantidad - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int valor = pedidos[medio].getCodigo();
            if (valor == codigo) return medio;
            if (valor < codigo) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }

    // Ordenamiento burbuja por codigo.
    public void ordenarPorCodigo(Pedido[] pedidos, int cantidad) {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad - 1 - i; j++) {
                if (pedidos[j].getCodigo() > pedidos[j + 1].getCodigo()) {
                    Pedido auxiliar = pedidos[j];
                    pedidos[j] = pedidos[j + 1];
                    pedidos[j + 1] = auxiliar;
                }
            }
        }
    }

    // Intercalacion de dos arreglos previamente ordenados por codigo.
    public Pedido[] intercalar(Pedido[] primero, int n1, Pedido[] segundo, int n2) {
        Pedido[] resultado = new Pedido[n1 + n2];
        int i = 0, j = 0, k = 0;
        while (i < n1 && j < n2) {
            if (primero[i].getCodigo() <= segundo[j].getCodigo()) {
                resultado[k] = primero[i];
                i++;
            } else {
                resultado[k] = segundo[j];
                j++;
            }
            k++;
        }
        while (i < n1) resultado[k++] = primero[i++];
        while (j < n2) resultado[k++] = segundo[j++];
        return resultado;
    }

    public void mostrarEstructuras() {
        System.out.println("Lista (historial): " + historial.size() + " pedidos");
        System.out.println("Lista enlazada (espera): " + listaEspera.size() + " pedidos");
        System.out.println("Pila: " + pilaPedidos.size() + " pedidos");
        System.out.println("Cola: " + colaPedidos.size() + " pedidos");
        System.out.println("Arbol (codigos ordenados): " + codigosOrdenados);
        System.out.println("Mapa ordenado (claves): " + pedidosPorCodigo.keySet());
    }
}
