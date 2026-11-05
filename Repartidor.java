public class Repartidor {
    private int legajo;
    private String nombre;
    private Vehiculo vehiculo;
    private Pedido[] pedidos;
    private int cantidadPedidos;

    public Repartidor(int legajo, String nombre, Vehiculo vehiculo) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.vehiculo = vehiculo;
        this.pedidos = new Pedido[100];
        this.cantidadPedidos = 0;
    }

    public int getLegajo() { return legajo; }
    public String getNombre() { return nombre; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public int getCantidadPedidos() { return cantidadPedidos; }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) this.nombre = nombre;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) this.vehiculo = vehiculo;
    }

    public Pedido[] getPedidos() { return pedidos; }

    public boolean asignarPedido(Pedido pedido) throws CapacidadExcedidaException, AlmacenamientoLlenoException {
        if (pedido == null) return false;
        if (cantidadPedidos >= pedidos.length)
            throw new AlmacenamientoLlenoException("El arreglo de pedidos del repartidor está lleno.");
        vehiculo.puedeTransportar(pedido);
        pedidos[cantidadPedidos] = pedido;
        pedido.setVehiculoAsignado(vehiculo);
        cantidadPedidos++;
        return true;
    }

    public boolean registrarEntrega(Pedido pedido) {
        if (pedido == null) return false;
        if (pedido.getEstado().equals("PENDIENTE")) pedido.cambiarEstado("EN_CAMINO");
        if (pedido.getEstado().equals("EN_CAMINO")) {
            pedido.cambiarEstado("ENTREGADO");
            return pedido.getEstado().equals("ENTREGADO");
        }
        return false;
    }

    public String obtenerEstadoActual() {
        String texto = "Repartidor " + nombre + ": ";
        if (cantidadPedidos == 0) return texto + "sin pedidos asignados.";
        for (int i = 0; i < cantidadPedidos; i++) {
            texto += "Pedido " + pedidos[i].getCodigo() + "=" + pedidos[i].getEstado() + " ";
        }
        return texto;
    }

    public double calcularImporte() {
        double totalEntregado = 0;
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getEstado().equals("ENTREGADO")) {
                totalEntregado += pedidos[i].calcularCosto(vehiculo);
            }
        }
        return totalEntregado * 0.10;
    }

    public String toString() {
        return "Repartidor " + legajo + " - " + nombre + " - " + vehiculo;
    }
}
