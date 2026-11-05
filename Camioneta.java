public class Camioneta extends Vehiculo {
    public Camioneta(String patente) {
        super(patente, 500, 30, 300);
    }

    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmH()) * 60 + 10;
    }

    public boolean puedeTransportar(Pedido pedido) throws CapacidadExcedidaException {
        if (pedido.getPesoKg() > getCapacidadKg())
            throw new CapacidadExcedidaException("El pedido supera la capacidad del vehículo (500 kg).");
        return true;
    }
}
