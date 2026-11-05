public class CityLink {
    private static final int MAX_CLIENTES = 50;
    private static final int MAX_PEDIDOS = 100;
    private static final int MAX_REPARTIDORES = 10;

    private Cliente[] clientes;
    private Pedido[] pedidos;
    private Repartidor[] repartidores;
    private Vehiculo[] vehiculos;
    private int cantidadClientes;
    private int cantidadPedidos;
    private int cantidadRepartidores;
    private int cantidadVehiculos;

    public CityLink() {
        clientes = new Cliente[MAX_CLIENTES];
        pedidos = new Pedido[MAX_PEDIDOS];
        repartidores = new Repartidor[MAX_REPARTIDORES];
        vehiculos = new Vehiculo[100];
        cantidadClientes = 0;
        cantidadPedidos = 0;
        cantidadRepartidores = 0;
        cantidadVehiculos = 0;
    }

    public boolean agregarCliente(Cliente cliente) throws AlmacenamientoLlenoException {
        if (cliente == null) return false;
        if (cantidadClientes >= clientes.length)
            throw new AlmacenamientoLlenoException("El arreglo de clientes esta lleno.");
        if (buscarCliente(cliente.getCodigo()) != null) return false;
        clientes[cantidadClientes++] = cliente;
        return true;
    }

    public Cliente buscarCliente(int codigo) {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getCodigo() == codigo) return clientes[i];
        }
        return null;
    }

    public Cliente buscarCliente(int codigo, String nombre) {
        Cliente cliente = buscarCliente(codigo);
        if (cliente != null && cliente.getNombre().equalsIgnoreCase(nombre)) return cliente;
        return null;
    }

    public Cliente buscarCliente(String nombre) {
        for (int i = 0; i < cantidadClientes; i++) {
            if (clientes[i].getNombre().equalsIgnoreCase(nombre)) return clientes[i];
        }
        return null;
    }

    public void agregarVehiculo(Vehiculo vehiculo) throws AlmacenamientoLlenoException {
        if (vehiculo != null) {
            if (cantidadVehiculos >= vehiculos.length)
                throw new AlmacenamientoLlenoException("El arreglo de vehículos está lleno.");
            vehiculos[cantidadVehiculos++] = vehiculo;
        }
    }

    public boolean agregarRepartidor(Repartidor repartidor) throws AlmacenamientoLlenoException {
        if (repartidor == null) return false;
        if (cantidadRepartidores >= repartidores.length)
            throw new AlmacenamientoLlenoException("El arreglo de repartidores está lleno.");
        repartidores[cantidadRepartidores++] = repartidor;
        agregarVehiculo(repartidor.getVehiculo());
        return true;
    }

    public Repartidor buscarRepartidor(int legajo) {
        for (int i = 0; i < cantidadRepartidores; i++) {
            if (repartidores[i].getLegajo() == legajo) return repartidores[i];
        }
        return null;
    }

    public Pedido buscarPedido(int codigo) {
        for (int i = 0; i < cantidadPedidos; i++) {
            if (pedidos[i].getCodigo() == codigo) return pedidos[i];
        }
        return null;
    }

    public void registrarPedido(Pedido pedido) throws PedidoInvalidoException, AlmacenamientoLlenoException {
        if (pedido == null) throw new PedidoInvalidoException("El pedido no puede ser nulo.");
        if (cantidadPedidos >= pedidos.length) throw new AlmacenamientoLlenoException("El arreglo de pedidos está lleno.");
        if (buscarPedido(pedido.getCodigo()) != null)
            throw new PedidoInvalidoException("Ya existe un pedido con ese codigo.");
        if (buscarCliente(pedido.getCliente().getCodigo()) == null)
            throw new PedidoInvalidoException("El cliente del pedido no esta registrado.");
        pedidos[cantidadPedidos++] = pedido;
    }

    public void asignarPedido(int codigoPedido, int legajo)
            throws PedidoInvalidoException, ClienteNoEncontradoException,
                   CapacidadExcedidaException, AlmacenamientoLlenoException {
        Pedido pedido = buscarPedido(codigoPedido);
        if (pedido == null) throw new PedidoInvalidoException("No existe ese pedido.");
        Repartidor repartidor = buscarRepartidor(legajo);
        if (repartidor == null) throw new ClienteNoEncontradoException("No existe ese repartidor.");
        repartidor.asignarPedido(pedido);
    }

    public void asignarPedido(Pedido pedido, Vehiculo vehiculo) throws PedidoInvalidoException, CapacidadExcedidaException {
        if (pedido == null || vehiculo == null) throw new PedidoInvalidoException("Pedido o vehiculo invalido.");
        vehiculo.puedeTransportar(pedido);
        System.out.println("El vehículo " + vehiculo.getPatente() + " puede transportar el pedido " + pedido.getCodigo() + ".");
    }

    public void listarClientes() {
        if (cantidadClientes == 0) System.out.println("No hay clientes registrados.");
        for (int i = 0; i < cantidadClientes; i++) System.out.println(clientes[i]);
    }

    public void listarPedidos() {
        if (cantidadPedidos == 0) System.out.println("No hay pedidos registrados.");
        for (int i = 0; i < cantidadPedidos; i++) {
            System.out.println(pedidos[i]);
            System.out.println("  Estado actual: " + pedidos[i].obtenerEstadoActual());
        }
    }

    public void listarRepartidores() {
        if (cantidadRepartidores == 0) System.out.println("No hay repartidores registrados.");
        for (int i = 0; i < cantidadRepartidores; i++) {
            System.out.println(repartidores[i]);
            System.out.println("  " + repartidores[i].obtenerEstadoActual());
            System.out.println("  Comision acumulada: $" + repartidores[i].calcularImporte());
        }
    }

    public void listarVehiculos() {
        if (cantidadVehiculos == 0) System.out.println("No hay vehiculos registrados.");
        for (int i = 0; i < cantidadVehiculos; i++) System.out.println(vehiculos[i]);
    }

    public void mostrarCostosYTiempo() {
        for (int i = 0; i < cantidadPedidos; i++) {
            Pedido pedido = pedidos[i];
            System.out.println("Pedido " + pedido.getCodigo() + " (" + pedido.getClass().getSimpleName() + ")");
            for (int j = 0; j < cantidadVehiculos; j++) {
                Vehiculo vehiculo = vehiculos[j];
                try {
                    vehiculo.puedeTransportar(pedido);
                    System.out.println("  " + vehiculo.getClass().getSimpleName() +
                            " - Costo: $" + pedido.calcularCosto(vehiculo) +
                            " - Tiempo: " + vehiculo.calcularTiempoEntrega(pedido.getDistanciaKm()) + " minutos");
                } catch (CapacidadExcedidaException e) {
                    System.out.println("  Vehículo no disponible: " + e.getMessage());
                }
            }
        }
    }

    public void registrarEntrega(int codigoPedido, int legajo) throws PedidoInvalidoException {
        Pedido pedido = buscarPedido(codigoPedido);
        if (pedido == null) throw new PedidoInvalidoException("No existe ese pedido.");
        Repartidor repartidor = buscarRepartidor(legajo);
        if (repartidor == null) throw new PedidoInvalidoException("No existe ese repartidor.");
        boolean pertenece = false;
        Pedido[] asignados = repartidor.getPedidos();
        for (int i = 0; i < repartidor.getCantidadPedidos(); i++) {
            if (asignados[i].getCodigo() == codigoPedido) pertenece = true;
        }
        if (!pertenece) throw new PedidoInvalidoException("Ese pedido no esta asignado al repartidor.");
        if (!repartidor.registrarEntrega(pedido))
            throw new PedidoInvalidoException("No se pudo registrar la entrega. Revise el estado.");
    }
}
