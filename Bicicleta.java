public class Bicicleta extends Vehiculo {
    public Bicicleta(String patente) {
        super(patente, 10, 15, 50);
    }
    public double calcularTiempoEntrega(double km) {
        return (km / getVelocidadKmH()) * 60;
    }
    public boolean puedeTransportar(Pedido pedido) throws CapacidadExcedidaException {
        if (pedido.getPesoKg() > getCapacidadKg())
            throw new CapacidadExcedidaException("El pedido supera los 10 kg de capacidad de la bicicleta.");
        if (pedido.esFragil())
            throw new CapacidadExcedidaException("La bicicleta no puede transportar pedidos frágiles.");
        return true;
    }
}
