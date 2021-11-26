import javax.management.ValueExp;
import java.util.Random;

public class Cliente implements Runnable {

    int id;
    int tempo;
    boolean fazPedido, aguardando;
    public boolean teste;
    Estabelecimento estabelecimento;
    Thread t;
    boolean recebeuPedido;

    public Cliente(int id, Estabelecimento e) {
        this.id = id;
        fazPedido = false;
        aguardando = false;
        recebeuPedido = false;
        this.estabelecimento = e;
        t = new Thread(this);
        teste = false;
        t.start();

    }

    @Override
    public void run() {
        // System.out.println("Sou o Cliente: #" + Thread.currentThread().getId());
        while (!estabelecimento.acabouRodadas()) {
            if (fazPedido()) {
                System.out.println("Sou o Cliente: #" + id + " e vou fazer um pedido");
                try {
                    estabelecimento.esperar(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               while(!getRecebeuPedido()) {
                   esperaPedido();
               }
                System.out.println("Sou o Cliente: #" + id + " Vai demorar " + getTempo() + " para consumir.");
                        //wait(getTempo());
                try {
                    consomePedido();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
                // sair();

            }
        }



    public void setAguardando(boolean aguardando) {
        this.aguardando = aguardando;
    }

    public boolean getAguardando() {
        return aguardando;
    }


    public boolean getPedido() {
        return fazPedido;
    }

    public boolean fazPedido() {
        if (!getFazPedido()) {
            Random random = new Random();
            int pedido = random.nextInt(5);
            if (pedido <= 3)
                return fazPedido = true;
        }
        return fazPedido = false;
    }



    public void calculaTempo() {
        Random random = new Random();
        int tempo = random.nextInt(1000);
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }

    public  void setRecebeuPedido(boolean recebeu) {
        this.recebeuPedido = recebeu;
    }

    public  boolean getRecebeuPedido() {
        return recebeuPedido;
    }

    public void setEsperaPedido(int valor) {
        this.tempo = valor;
    }

    public void esperaPedido(){
      //  while(estabelecimento.)
    }

    public synchronized boolean getFazPedido() {
        return fazPedido;
    }



    public synchronized void consomePedido() throws InterruptedException { // IMplementar
        System.out.println("Cliente: " + Thread.currentThread().getId() + " comecou a consumir o pedido");
        wait(getTempo());
    }



    public int getId() {
        return id;
    }

}
