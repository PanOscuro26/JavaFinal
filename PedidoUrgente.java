public class PedidoUrgente extends Pedido {
    public PedidoUrgente(int codigo, Cliente cliente, String destino, double pesoKg,
                         double distanciaKm, String fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }

    public double calcularCosto(Vehiculo vehiculo) {
        double total = 1000 + getDistanciaKm() * vehiculo.getCostoPorKm();
        return total * 1.5;
    }
    public boolean esFragil() {
        return false;
    }

}
