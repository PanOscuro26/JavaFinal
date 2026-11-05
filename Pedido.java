public abstract class Pedido {
    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private String fechaHora;
    private String estado;
    private Vehiculo vehiculoAsignado;

    public Pedido(int codigo, Cliente cliente, String destino, double pesoKg,
                  double distanciaKm, String fechaHora) throws PedidoInvalidoException {
        if (codigo <= 0) throw new PedidoInvalidoException("El código debe ser mayor que cero.");
        if (cliente == null) throw new PedidoInvalidoException("El cliente no puede ser nulo.");
        if (destino == null || destino.trim().isEmpty())
            throw new PedidoInvalidoException("El destino no puede estar vacío.");
        if (pesoKg <= 0) throw new PedidoInvalidoException("El peso debe ser mayor que cero.");
        if (distanciaKm <= 0) throw new PedidoInvalidoException("La distancia debe ser mayor que cero.");

        this.codigo = codigo;
        this.cliente = cliente;
        this.destino = destino;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.fechaHora = fechaHora;
        this.estado = "PENDIENTE";
    }

    public int getCodigo() { return codigo; }
    public Cliente getCliente() { return cliente; }
    public String getDestino() { return destino; }
    public double getPesoKg() { return pesoKg; }
    public double getDistanciaKm() { return distanciaKm; }
    public String getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }

    public void setDestino(String destino) {
        if (destino != null && !destino.trim().isEmpty()) this.destino = destino;
    }

    public void setPesoKg(double pesoKg) {
        if (pesoKg > 0) this.pesoKg = pesoKg;
    }

    public void setDistanciaKm(double distanciaKm) {
        if (distanciaKm > 0) this.distanciaKm = distanciaKm;
    }

    public abstract double calcularCosto(Vehiculo vehiculo);
    public abstract boolean esFragil();

    public void setVehiculoAsignado(Vehiculo vehiculo) {
        this.vehiculoAsignado = vehiculo;
    }

    public void cambiarEstado(String nuevoEstado) {
        boolean permitido = false;
        if (nuevoEstado != null && estado.equals("PENDIENTE") &&
            (nuevoEstado.equals("EN_CAMINO") || nuevoEstado.equals("CANCELADO"))) {
            permitido = true;
        } else if (nuevoEstado != null && estado.equals("EN_CAMINO") && nuevoEstado.equals("ENTREGADO")) {
            permitido = true;
        }
        if (!permitido) {
            throw new EstadoInvalidoException("Transición de estado no permitida: " + estado + " -> " + nuevoEstado);
        }
        estado = nuevoEstado;
    }

    public String obtenerEstadoActual() {
        return estado;
    }

    public double calcularImporte() {
        if (vehiculoAsignado == null) return 0;
        return calcularCosto(vehiculoAsignado);
    }

    public String toString() {
        return "Pedido " + codigo + " | Cliente: " + cliente.getNombre() +
               " | Destino: " + destino + " | Peso: " + pesoKg +
               " kg | Distancia: " + distanciaKm + " km | Estado: " + estado;
    }
}
