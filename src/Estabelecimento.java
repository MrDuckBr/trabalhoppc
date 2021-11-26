import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Estabelecimento {

    int rodadasTotais, rodadas, clientes, garcons, capacidade, clientesAtendidos;

    ArrayList<Cliente> lstClientes;
    ArrayList<Garcom> lstGarcons;
    ArrayList<Cliente> lstClientesFezPedido;

    Semaphore semaforo;

    public Estabelecimento(int rodadas, int clientes, int garcons, int capacidade) {
        this.clientes = clientes;
        this.garcons = garcons;
        this.rodadasTotais = rodadas;
        this.capacidade = capacidade;
        this.semaforo = new Semaphore(2);
        clientesAtendidos = 0;

        lstClientes = new ArrayList<>();
        lstClientesFezPedido = new ArrayList<>();
        lstGarcons = new ArrayList<>();

    }

    public synchronized boolean acabouRodadas() {
        if (rodadas >= rodadasTotais)
            return true;
        return false;
    }

    public void comecar() throws InterruptedException {
        System.out.println("Começou o atendimento de rodadas free");
        for (int i = 0; i < garcons; i++) {
            Garcom garcom = new Garcom(i, capacidade, this);
            lstGarcons.add(garcom);
        }

        for (int i = 0; i < clientes; ++i) {
            Cliente cliente = new Cliente(i, this);
            // System.out.println("Sou o Cliente: #36 " + Thread.currentThread().getId());
            lstClientes.add(cliente);
            // System.out.println("Sou o Cliente: #37 " + Thread.currentThread().getId());
        }
        // esperar();

        // if(rodadas <= rodadasTotais)
    }

    public synchronized void esperar(int id) throws InterruptedException {
        lstClientesFezPedido.add(lstClientes.get(id));
        System.out.println("Sou o Cliente: (54)#" + Thread.currentThread().getId());
        while (lstClientes.get(id).getAguardando()) {
            wait();
        }
        notify();

    }

    public synchronized void alterar() throws InterruptedException {
        wait();
    }

    public synchronized Cliente getCliente() {
        if (lstClientesFezPedido.size() > 0) {
            for (Cliente cliente : lstClientesFezPedido) {
                if (cliente.getPedido()) {
                    cliente.setAguardando(true);
                    clientesAtendidos++;
                    lstClientesFezPedido.remove(cliente);
                    return cliente;
                }
            }
        }
        return null;
    }

    public synchronized void registraPedido(Cliente cliente) throws InterruptedException {
        semaforo.acquire();
        lstClientes.get(cliente.getId()).calculaTempo();
        System.out.println("O tempo" + lstClientes.get(cliente.getId()).getTempo());

    }

    public synchronized void verificaPedido(int id) throws InterruptedException {
        for (Cliente c: lstClientesFezPedido) {
            if(lstClientes.get(id).getId() == c.getId()){
                Cliente cli = lstClientes.get(id);
                cli.setRecebeuPedido(c.getRecebeuPedido());
                cli.calculaTempo();
                wait(cli.getTempo());


            }
        }
    }

    public synchronized int getClientesAtendidos() {
        return clientesAtendidos;
    }

    public int getClientes() {
        return clientes;
    }
}

/*
 * esperaPedido(); recebePedido(); consomePedido();
 *
 */

// Garcom = recebeMaximoPedido,RegistraPedido,entregaPedido
// Cliente = fazPedido, esperaPedido, rebebePedido,ConsomePedido