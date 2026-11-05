import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        CityLink sistema = new CityLink();
        int opcion = 0;

        while (opcion != 12) {
            System.out.println("\\n===== CITYLINK =====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar repartidor y vehículo");
            System.out.println("3. Registrar pedido");
            System.out.println("4. Asignar pedido a repartidor");
            System.out.println("5. Registrar entrega");
            System.out.println("6. Listar clientes");
            System.out.println("7. Listar pedidos");
            System.out.println("8. Listar repartidores");
            System.out.println("9. Listar vehículos");
            System.out.println("10. Mostrar costos y tiempos");
            System.out.println("11. Cambiar estado de un pedido a CANCELADO");
            System.out.println("12. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());

                if (opcion == 1) {
                    System.out.print("Código del cliente: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = teclado.nextLine();
                    System.out.print("Zona: ");
                    String zona = teclado.nextLine();

                    Cliente cliente = new Cliente(codigo, nombre, telefono, zona);
                    if (sistema.agregarCliente(cliente)) System.out.println("Cliente registrado.");
                    else System.out.println("No se pudo registrar. Verifique código repetido o límite.");
                } else if (opcion == 2) {
                    System.out.print("Legajo del repartidor: ");
                    int legajo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Nombre del repartidor: ");
                    String nombre = teclado.nextLine();
                    System.out.println("Vehículo: 1 Bicicleta, 2 Moto, 3 Camioneta");
                    int tipo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Patente o identificador: ");
                    String patente = teclado.nextLine();

                    Vehiculo vehiculo = null;
                    if (tipo == 1) vehiculo = new Bicicleta(patente);
                    else if (tipo == 2) vehiculo = new Moto(patente);
                    else if (tipo == 3) vehiculo = new Camioneta(patente);

                    if (vehiculo == null) System.out.println("Tipo de vehículo inválido.");
                    else if (sistema.agregarRepartidor(new Repartidor(legajo, nombre, vehiculo)))
                        System.out.println("Repartidor registrado.");
                    else System.out.println("No se pudo registrar el repartidor.");
                } else if (opcion == 3) {
                    System.out.print("Código del pedido: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    System.out.print("Código del cliente: ");
                    int codigoCliente = Integer.parseInt(teclado.nextLine());
                    Cliente cliente = sistema.buscarCliente(codigoCliente);
                    if (cliente == null) {
                        throw new ClienteNoEncontradoException("No existe ese cliente.");
                    }
                    System.out.print("Destino: ");
                    String destino = teclado.nextLine();
                    System.out.print("Peso en kg: ");
                    double peso = Double.parseDouble(teclado.nextLine());
                    System.out.print("Distancia en km: ");
                    double distancia = Double.parseDouble(teclado.nextLine());
                    System.out.print("Fecha y hora de ingreso: ");
                    String fecha = teclado.nextLine();
                    System.out.println("Tipo: 1 Normal, 2 Urgente, 3 Frágil");
                    int tipo = Integer.parseInt(teclado.nextLine());

                    Pedido pedido = null;
                    if (tipo == 1) pedido = new PedidoNormal(codigo, cliente, destino, peso, distancia, fecha);
                    else if (tipo == 2) pedido = new PedidoUrgente(codigo, cliente, destino, peso, distancia, fecha);
                    else if (tipo == 3) pedido = new PedidoFragil(codigo, cliente, destino, peso, distancia, fecha);

                    if (pedido == null) System.out.println("Tipo de pedido inválido.");
                    else {
                        sistema.registrarPedido(pedido);
                        System.out.println("Pedido registrado.");
                    }
                } else if (opcion == 4) {
                    System.out.print("Código del pedido: ");
                    int codigoPedido = Integer.parseInt(teclado.nextLine());
                    System.out.print("Legajo del repartidor: ");
                    int legajo = Integer.parseInt(teclado.nextLine());
                    sistema.asignarPedido(codigoPedido, legajo);
                    System.out.println("Pedido asignado.");
                } else if (opcion == 5) {
                    System.out.print("Código del pedido: ");
                    int codigoPedido = Integer.parseInt(teclado.nextLine());
                    System.out.print("Legajo del repartidor: ");
                    int legajo = Integer.parseInt(teclado.nextLine());
                    sistema.registrarEntrega(codigoPedido, legajo);
                    System.out.println("Entrega registrada.");
                } else if (opcion == 6) {
                    sistema.listarClientes();
                } else if (opcion == 7) {
                    sistema.listarPedidos();
                } else if (opcion == 8) {
                    sistema.listarRepartidores();
                } else if (opcion == 9) {
                    sistema.listarVehiculos();
                } else if (opcion == 10) {
                    sistema.mostrarCostosYTiempo();
                } else if (opcion == 11) {
                    System.out.print("Código del pedido a cancelar: ");
                    int codigo = Integer.parseInt(teclado.nextLine());
                    Pedido pedido = sistema.buscarPedido(codigo);
                    if (pedido == null) System.out.println("No existe ese pedido.");
                    else {
                        pedido.cambiarEstado("CANCELADO");
                        System.out.println("Estado actual: " + pedido.getEstado());
                    }
                } else if (opcion != 12) {
                    System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingrese un número válido.");
            } catch (ClienteNoEncontradoException e) {
                System.out.println("Error de cliente/repartidor: " + e.getMessage());
            } catch (PedidoInvalidoException e) {
                System.out.println("Error en pedido: " + e.getMessage());
            } catch (CapacidadExcedidaException e) {
                System.out.println("Error de capacidad: " + e.getMessage());
            } catch (AlmacenamientoLlenoException e) {
                System.out.println("Error de almacenamiento: " + e.getMessage());
            } catch (EstadoInvalidoException e) {
                System.out.println("Error de estado: " + e.getMessage());
            }
        }

        teclado.close();
        System.out.println("Programa finalizado.");
    }
}
