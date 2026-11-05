public class PedidoNormal extends Pedido {
    public PedidoNormal(int codigo, Cliente cliente, String destino, double pesoKg,
                        double distanciaKm, String fechaHora) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora);
    }

    public double calcularCosto(Vehiculo vehiculo) {
        return 1000 + getDistanciaKm() * vehiculo.getCostoPorKm();
    }
    public boolean esFragil() {
        return false;
    }

}
