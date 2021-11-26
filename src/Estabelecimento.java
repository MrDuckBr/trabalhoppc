import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Estabelecimento {

    int rodadasTotais,rodadas, clientes, garcons, capacidade;

    ArrayList<Cliente> lstClientes;
    ArrayList<Thread> lstThreadsClientes;
    ArrayList<Garcom> lstGarcons;


    Semaphore semaforo;

    public Estabelecimento(int rodadas, int clientes, int garcons, int capacidade){
        this.clientes = clientes;
        this.garcons = garcons;
        this.rodadasTotais = rodadas;
        this.capacidade = capacidade;
        this.semaforo = new Semaphore(1);

        lstClientes = new ArrayList<>();

        lstGarcons = new ArrayList<>();


    }

    public synchronized boolean acabouRodadas(){
        if(rodadas >= rodadasTotais) return true;
        return false;
    }


    public void comecar() throws InterruptedException {
        System.out.println("Começou o atendimento de rodadas free");


        for (int i = 0; i< clientes; ++i){
            Cliente cliente = new Cliente(i,this);
           // System.out.println("Sou o Cliente: #36 " + Thread.currentThread().getId());
            lstClientes.add(cliente);
         //   System.out.println("Sou o Cliente: #37 " + Thread.currentThread().getId());
        }
        //esperar();

       // if(rodadas <= rodadasTotais)
    }

    public synchronized void esperar(int id) throws InterruptedException {
            lstClientes.get(id).wait();
            System.out.println("Sou o Cliente: (54)#" + Thread.currentThread().getId());
    }

    public synchronized void alterar() throws InterruptedException{
       // lstClientes.get(0).setAguardando(true);
        System.out.println("Sou o Cliente: (54)#" + Thread.currentThread().getId());
    }

}

/*
* esperaPedido();
                 recebePedido();
                consomePedido();
*
* */



//Garcom = recebeMaximoPedido,RegistraPedido,entregaPedido
//Cliente = fazPedido, esperaPedido, rebebePedido,ConsomePedido