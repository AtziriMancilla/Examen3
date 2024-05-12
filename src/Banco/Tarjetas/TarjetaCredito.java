package Banco.Tarjetas;

import Banco.Banco;
import Banco.utils.SolicitudTarjetaCredito;
import Banco.utils.TipoTarjetaCredito;
import Usuarios.Cliente;
import Usuarios.utils.DatosComun;

import java.util.ArrayList;
import java.util.List;

public class TarjetaCredito extends Tarjeta {
    private TipoTarjetaCredito tipoCredito;
    //credito que tiene dependiendo el tipo de tarjeta
    private double creditoMaximo;
    //credito que le queda disponible al cliente
    private double creditoActual;
    private double saldoPendiente;

    public TarjetaCredito(int clave,TipoTarjetaCredito tipo) {
        super(clave);
        tipoCredito=tipo;
        creditoMaximo=asignarCreditoMaximo(tipo);
        creditoActual=creditoMaximo;
        saldoPendiente=0;
    }
    public static int asignarCreditoMaximo(TipoTarjetaCredito tipo){
        int cantidad=0;
        if(tipo == TipoTarjetaCredito.Simplicity){
            cantidad=60000;
        }
        if(tipo==TipoTarjetaCredito.Platino){
            cantidad=150000;
        }
        if(tipo==TipoTarjetaCredito.Oro){
            cantidad=400000;
        }
        return cantidad;
    }


    @Override
    public String toString() {
        String tipoDeTarjeta = "" + tipoCredito; //Convierte a string el dato que indica el tipo de tarjeta.
        return String.format("\n%s\nCrédito Máximo: %.2f\nTipo de Tarjeta: %s", super.toString(), creditoMaximo, tipoDeTarjeta);
    }

    public void pagarTarjeta() {
        boolean flag = true;
        double pago;
        do {
            System.out.println("\nIngrese el depósito de la Tarjeta:");
            System.out.printf("Saldo Pendiente: %.2f\n",saldoPendiente);
            pago = DatosComun.pedirValorDouble();
            if (pago <= saldoPendiente && pago!=0) {
                System.out.println("Realizando pago...");
                saldoPendiente -= pago;
                System.out.println("Actualizando saldo y crédito disponible...");
                creditoActual += pago;
                System.out.println("Pago realizado.\n");
                flag = false;
            }
            if (pago > saldoPendiente) {
                System.out.println("Error. No puedes ingresar un monto mayor a la deuda actual.");
            }
            if (pago == 0) {
                System.out.println("Cancelando la operación...\n");
                flag = false;
            }
        } while(flag);
    }

    public void comprarCredito() {
        boolean flag = true;
        double importe;
        do {
            System.out.printf("\nSaldo Disponible: %.2f",creditoActual);
            System.out.println("\nIngrese el monto de la compra a crédito:");
            importe = DatosComun.pedirValorDouble();
            if (importe <= creditoActual && importe != 0) {
                System.out.println("Realizando pago...");
                creditoActual -= importe;
                System.out.println("Actualizando saldo y crédito disponible...");
                saldoPendiente += importe;
                System.out.println("Compra a crédito realizada.\n");
                flag = false;
            }
            if (importe > creditoActual) {
                System.out.println("No se puede realizar la compra. Límite de crédito alcanzado. Intenta de nuevo.");
            }
            if (importe == 0) {
                System.out.println("Cancelando la compra...\n");
                flag = false;
            }
        } while(flag);
    }

    public TipoTarjetaCredito getTipoCredito() {
        return tipoCredito;
    }

    public double getCreditoMaximo() {
        return creditoMaximo;
    }
    //Pendiente de uso.
    public double getCreditoActual() {
        return creditoActual;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }
    //Métodos Jafet
    public static void solicitarTarjeta (Banco banco, Cliente cliente) {
            int opciones = 0, opcion;
            boolean band = false;
            SolicitudTarjetaCredito solicitud;
            TarjetaDebito tarjetaDebito = cliente.getTarjetaDebito();//Se obtiene la tarjeta de débito del cliente.
            if(cliente.getTarjetasCredito().isEmpty()) {//Si el cliente NO tiene ninguna tarjeta.
                if (tarjetaDebito.simplicity(tarjetaDebito.getSaldo())) {//Dependiendo del saldo, se motrarán las opciones disponibles.
                    opciones = 1;
                    if (tarjetaDebito.platino(tarjetaDebito.getSaldo())) {
                        opciones = 2;
                        if (tarjetaDebito.oro(tarjetaDebito.getSaldo())) {
                            opciones = 3;
                        }
                    }
                }
                switch (opciones) {
                    case 1:
                        do {
                            System.out.println("Puedes seleccionar el tipo Simplicity");
                            System.out.println("1.Solicitar\n2. Salir");
                            opcion = DatosComun.pedirNumero();
                            if (opcion == 1) {
                                solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                banco.solicitudes.add(solicitud);
                                System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                band = false;
                            }
                            if (opcion == 2) band = false;
                            else {
                                System.out.println("Opción no válida.");
                                band = true;
                            }
                        } while (band);
                        break;
                    case 2:
                        do {
                            System.out.println("Seleccione el tipo de tarjeta: ");
                            System.out.println("1. Simplicity\n2. Platino\n3. Salir");
                            opcion = DatosComun.pedirNumero();
                            switch (opcion) {
                                case 1:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                    banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                    band = false;
                                    break;
                                case 2:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                    banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Platino realizada\n");
                                    band = false;
                                    break;
                                case 3:
                                    band = false;
                                    break;
                                default:
                                    System.out.println("Opcion no valida");
                                    band = true;
                                    break;
                            }
                        } while (band);
                        break;
                    case 3:
                        do {
                            System.out.println("Seleccione el tipo de tarjeta: ");
                            System.out.println("1. Simplicity\n2. Platino\n3. Oro\n4. Salir");
                            opcion = DatosComun.pedirNumero();
                            switch (opcion) {
                                case 1:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                    banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                    band = false;
                                    break;
                                case 2:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                    banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Platino realizada\n");
                                    band = false;
                                    break;
                                case 3:
                                    solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                    banco.solicitudes.add(solicitud);
                                    System.out.println("Solicitud de tarjeta Oro realizada\n");
                                    band = false;
                                    break;
                                case 4:
                                    band = false;
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                                    band = true;
                                    break;
                            }
                        } while (band);
                        break;
                    default:
                        System.out.println("No tienes ofertas disponibles.\n");
                        break;
                }
            }
            if(!cliente.getTarjetasCredito().isEmpty()) {//Si el cliente ya cuenta con al menos 1 tarjeta de crédito. Recordar límite máximo: 3 tarjetas DIFERENTES.
                int tarjetasDisponibles = 3 - cliente.getTarjetasCredito().size();//Para determinar cuántas tarjetas más se le pueden ofrecer.
                boolean contieneSimplicity = false, contienePlatino = false, contieneOro = false;//Para determinar si contiene el tipo seleccionado.
                switch (tarjetasDisponibles) {//Solo contendrá dos casos
                    case 1://Se le puede ofrecer solo una tarjeta más. Se buscará la tarjeta faltante a ofertar, siempre que tenga el saldo suficiente para ella.
                        for(TarjetaCredito tarjeta: cliente.getTarjetasCredito()) {//Se itera cuáles tarjetas tiene el cliente.
                            if(tarjeta.tipoCredito == TipoTarjetaCredito.Simplicity) {
                                contieneSimplicity = true;
                            }
                            if(tarjeta.tipoCredito == TipoTarjetaCredito.Platino) {
                                contienePlatino = true;
                            }
                            if(tarjeta.tipoCredito == TipoTarjetaCredito.Oro) {
                                contieneOro = true;
                            }
                        }//Las siguientes validaciones comprueban cuál es la tarjeta que aún no tiene, para ofertarla si cuenta con el saldo necesario.
                        if(!contieneSimplicity) {//Si no tiene todavía la Simplicity.
                            if(tarjetaDebito.simplicity(tarjetaDebito.getSaldo())) {//Si el cliente cuenta con el saldo mínimo para solicitarla.
                                do {
                                    System.out.println("Puedes seleccionar el tipo Simplicity");
                                    System.out.println("1. Solicitar\n2. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }else System.out.println("No tienes ofertas disponibles.\n");
                        }
                        if(!contienePlatino) {//Si no cuenta con la platino.
                            if(tarjetaDebito.platino(tarjetaDebito.getSaldo())) {//Si el cliente cuenta con el saldo mínimo para solicitarla.
                                do {
                                    System.out.println("Puedes seleccionar el tipo Platino");
                                    System.out.println("1. Solicitar\n2. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Platino realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }else System.out.println("No tienes ofertas disponibles.\n");
                        }
                        if(!contieneOro) {//Si no cuenta con la oro.
                            if(tarjetaDebito.oro(tarjetaDebito.getSaldo())) {//Si el cliente cuenta con el saldo mínimo para solicitarla.
                                do {
                                    System.out.println("Puedes seleccionar el tipo Oro");
                                    System.out.println("1. Solicitar\n2. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Oro realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }else System.out.println("No tienes ofertas disponibles.\n");
                        }
                        break;
                    case 2://Se le pueden ofrecer dos tarjetas más.
                        for(TarjetaCredito tarjeta: cliente.getTarjetasCredito()) {//Se itera cuál de las tarjetas tiene el cliente.
                            if(tarjeta.tipoCredito == TipoTarjetaCredito.Simplicity) {
                                contieneSimplicity = true;
                            }
                            if(tarjeta.tipoCredito == TipoTarjetaCredito.Platino) {
                                contienePlatino = true;
                            }
                            if(tarjeta.tipoCredito == TipoTarjetaCredito.Oro) {
                                contieneOro = true;
                            }
                        }//Las siguientes validaciones comprueban la tarjeta del cliente, y le ofertarán las otras dos disponibles si tiene el saldo requerido.
                        if(contieneSimplicity) {//Si la única tarjeta que tiene es la simplicity
                            if(tarjetaDebito.platino(tarjetaDebito.getSaldo()) && tarjetaDebito.oro(tarjetaDebito.getSaldo())) {//Si tiene suficiente saldo para las otras dos tarjetas.
                                do {
                                    System.out.println("Seleccione el tipo de tarjeta: ");
                                    System.out.println("1. Platino\n2. Oro\n3. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Platino realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Oro realizada\n");
                                            band = false;
                                            break;
                                        case 3:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opcion no valida");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }
                            if(tarjetaDebito.platino(tarjetaDebito.getSaldo()) && !tarjetaDebito.oro(tarjetaDebito.getSaldo())) {//Si tiene saldo para la platino, PERO NO para la oro.
                                do {
                                    System.out.println("Puedes seleccionar el tipo Platino");
                                    System.out.println("1. Solicitar\n2. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Platino realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }else if(!tarjetaDebito.platino(tarjetaDebito.getSaldo()))System.out.println("No tienes ofertas disponibles.\n");//No tiene, es pobre :(
                        }
                        if(contienePlatino) {//Tiene solo la platino.
                            if(tarjetaDebito.simplicity(tarjetaDebito.getSaldo()) && tarjetaDebito.oro(tarjetaDebito.getSaldo())) {//Si tiene suficiente saldo para las otras dos tarjetas.
                                do {
                                    System.out.println("Seleccione el tipo de tarjeta: ");
                                    System.out.println("1. Simplicity\n2. Oro\n3. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Oro);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Oro realizada\n");
                                            band = false;
                                            break;
                                        case 3:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opcion no valida");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }
                            if(tarjetaDebito.simplicity(tarjetaDebito.getSaldo()) && !tarjetaDebito.oro(tarjetaDebito.getSaldo())) {//Si tiene saldo para la simplicity, PERO NO para la oro.
                                do {
                                    System.out.println("Puedes seleccionar el tipo Simplicity");
                                    System.out.println("1. Solicitar\n2. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                            band = true;
                                            break;
                                    }
                                }while (band);
                            }else if(!tarjetaDebito.simplicity(tarjetaDebito.getSaldo())) System.out.println("No tienes ofertas disponibles.\n");//No tiene, es pobre :(
                        }
                        if(contieneOro) {//Tiene solo la tarjeta oro.
                            if(tarjetaDebito.simplicity(tarjetaDebito.getSaldo()) && tarjetaDebito.platino(tarjetaDebito.getSaldo())) {//Si tiene suficiente saldo para las otras dos tarjetas.
                                do {
                                    System.out.println("Seleccione el tipo de tarjeta: ");
                                    System.out.println("1. Simplicity\n2. Platino\n3. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Platino);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Platino realizada\n");
                                            band = false;
                                            break;
                                        case 3:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opcion no valida");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }
                            if(tarjetaDebito.simplicity(tarjetaDebito.getSaldo()) && !tarjetaDebito.platino(tarjetaDebito.getSaldo())) {//Si tiene saldo para la simplicity, PERO NO para la platino.
                                do {
                                    System.out.println("Puedes seleccionar el tipo Simplicity");
                                    System.out.println("1. Solicitar\n2. Salir");
                                    opcion = DatosComun.pedirNumero();
                                    switch (opcion) {
                                        case 1:
                                            solicitud = new SolicitudTarjetaCredito(cliente, TipoTarjetaCredito.Simplicity);
                                            banco.solicitudes.add(solicitud);
                                            System.out.println("Solicitud de tarjeta Simplicity realizada\n");
                                            band = false;
                                            break;
                                        case 2:
                                            band = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                            band = true;
                                            break;
                                    }
                                } while (band);
                            }else if(!tarjetaDebito.simplicity(tarjetaDebito.getSaldo())) System.out.println("No tienes ofertas disponibles.\n");//No tiene, es pobre :(
                        }
                        break;
                }
            }
        }
    public static void revisarStatus(Banco banco, Cliente cliente) {
        System.out.println("\nSolicitud en curso:");//El ciclo buscará la solicitud hecha por el cliente y le mostrará el status.
        boolean solicitudEncontrada = false;//Determina si se encontró una solicitud pendiente del cliente.
        SolicitudTarjetaCredito solicitudEliminar = null;
        for(SolicitudTarjetaCredito solicitud : banco.solicitudes) {
            if (solicitud.getCliente() == cliente){
                solicitudEncontrada = true;
                System.out.println("Tarjeta solicitada: "+ solicitud.getTipoTarjeta());
                System.out.println("Status: "+solicitud.getStatus());
                if(solicitud.getStatus().equals("Solicitud Aprobada")||solicitud.getStatus().equals("Solicitud Rechazada")){//Revisión del status
                    System.out.println("Eliminando solicitud del historial...\n");
                    cliente.setNumeroSolicitudesEnProceso(0);//Una vez que el cliente consulte el estatus, si la solicitud ya fue atendida, su contador pasa a 0.
                    solicitudEliminar = solicitud;
                }
            }
        }
        if(solicitudEliminar!=null) banco.solicitudes.remove(solicitudEliminar);//Una vez consultada y atendida la solicitud, se elimina del Banco.
        if(!solicitudEncontrada) System.out.println("No tienes solicitudes pendientes.\n");
    }
}
