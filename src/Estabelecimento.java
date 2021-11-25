import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Estabelecimento {

    int rodadasTotais,rodadas, clientes, garcons, capacidade;

    ArrayList<Cliente> lstClientes;
    ArrayList<Thread> lstThreadsClientes;
    ArrayList<Garcom> lstGarcons;
   // ArrayList<Cliente> lstClientesPedirao = new ArrayList<>();

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

    public void comecar() throws InterruptedException {
        System.out.println("Começou o atendimento de rodadas free");


        for (int i = 0; i< clientes; ++i){
            Cliente cliente = new Cliente(i,this);
            lstClientes.add(cliente);
        }

        if(rodadas <= rodadasTotais) aberto();
    }


    public void aberto() throws InterruptedException {
          //VerificaClientesQueIraoPedir();
        lstClientes.get(0).

            novaRodada();
        }
/*
    public  void VerificaClientesQueIraoPedir() throws InterruptedException {
        for (Cliente c : lstClientes) {
            if(!c.getFazPedido()) {
                System.out.println(Thread.currentThread());
                c.esperar();
            }else{
                lstClientesPedirao.add(c);
            }
        }
    }

    public void atenderClientesQueIraoPedir() throws InterruptedException {
        for (Cliente c: lstClientesPedirao) {
            for (Garcom g: lstGarcons) {
                if(g.getDisponivel()){
                    g.addClienteLista(c);
                    c.wait();
                }
            }
        }

    }
*/

    public void novaRodada(){
        rodadas += 1;
    }

    public int getRodadas(){
        return rodadas;
    }

/*
    public void rodada(){

        while recebeMaximoPedido ou nao tem mais pedidos
        registrarPedido, faz pedido,
        recebePedido,entregaPedido

                consome pedido
    }
  */

    public Cliente AnotarPedido(){
        if(lstClientes.get(0).getFazPedido()){
             return lstClientes.get(0);
        }
        return  null;
    }


    public Cliente verificaClientesQueFaraoPedidos() throws InterruptedException {
        if(lstClientes.get(0).getFazPedido()){
           return lstClientes.get(0);
        }
        return null;

    }

    public void esperaGarcom(Cliente c) throws InterruptedException {
        semaforo.acquire();
    }

    public void esperaaiAmigao(long tempo) throws InterruptedException {
        this.wait(tempo);
    }

    public void liberaoamigao(){
        semaforo.release();
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