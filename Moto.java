public class Moto extends Vehiculo {
    public Moto(String patente) {
        super(patente, 30, 40, 120);
    }

    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmH()) * 60 + 5;
    }

    public boolean puedeTransportar(Pedido pedido) throws CapacidadExcedidaException {
        if (pedido.getPesoKg() > getCapacidadKg())
            throw new CapacidadExcedidaException("El pedido supera la capacidad del vehículo (30 kg).");
        return true;
    }
}
