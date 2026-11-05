public abstract class Vehiculo {
    private String patente;
    private double capacidadKg;
    private double velocidadKmH;
    private double costoPorKm;

    public Vehiculo(String patente, double capacidadKg, double velocidadKmH, double costoPorKm) {
        this.patente = patente;
        this.capacidadKg = capacidadKg;
        this.velocidadKmH = velocidadKmH;
        this.costoPorKm = costoPorKm;
    }

    public String getPatente() { return patente; }
    public double getCapacidadKg() { return capacidadKg; }
    public double getVelocidadKmH() { return velocidadKmH; }
    public double getCostoPorKm() { return costoPorKm; }

    public void setPatente(String patente) {
        if (patente != null && !patente.trim().isEmpty()) this.patente = patente;
    }

    public void setCapacidadKg(double capacidadKg) {
        if (capacidadKg > 0) this.capacidadKg = capacidadKg;
    }

    public void setVelocidadKmH(double velocidadKmH) {
        if (velocidadKmH > 0) this.velocidadKmH = velocidadKmH;
    }

    public void setCostoPorKm(double costoPorKm) {
        if (costoPorKm >= 0) this.costoPorKm = costoPorKm;
    }

    public abstract double calcularTiempoEntrega(double km);
    public abstract boolean puedeTransportar(Pedido pedido) throws CapacidadExcedidaException;

    public String toString() {
        return getClass().getSimpleName() + " | Patente: " + patente +
               " | Capacidad: " + capacidadKg + " kg | Velocidad: " +
               velocidadKmH + " km/h | Costo/km: $" + costoPorKm;
    }
}
