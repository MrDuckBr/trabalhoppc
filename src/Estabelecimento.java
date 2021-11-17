import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Estabelecimento {

    int rodadasTotais,rodadas, clientes, garcons, capacidade;

    ArrayList<Cliente> lstClientes;
    ArrayList<Garcom> lstGarcons;
    //--TMP------
    Cliente cliente1;
    Cliente cliente2;
    Garcom garcom;
    //--------------------
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
//https://www.javatpoint.com/how-to-create-a-thread-in-java
    public void comecar(){
        System.out.println("Começou o atendimento de rodadas free");
         cliente1 = new Cliente(1,100, this);
         cliente2 = new Cliente(1,100, this);
         lstClientes.add(cliente1);
         lstClientes.add(cliente2);
         garcom = new Garcom(1,0,1, this);

         if(rodadas <= rodadasTotais) aberto();
    }


    public void aberto(){
            cliente1.start(); // Todos os clientes
            cliente2.start(); // Todos os clientes
            garcom.start();

            novaRodada();
        }




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





//Garcom = recebeMaximoPedido,RegistraPedido,entregaPedido
//Cliente = fazPedido, esperaPedido, rebebePedido,ConsomePedido