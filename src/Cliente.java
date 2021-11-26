import java.util.Random;

public class Cliente implements Runnable{

    int id;
    long tempo;
    boolean fazPedido,aguardandando;
    public boolean teste;
    Estabelecimento estabelecimento;
    Thread t;



    public Cliente(int id, Estabelecimento e)  {
        this.id = id;
        fazPedido = false;
        aguardandando = false;
        this.estabelecimento = e;
       t = new Thread(this);
       teste = false;
        t.start();


    }


    @Override
    public void run(){
       // System.out.println("Sou o Cliente: #" + Thread.currentThread().getId());
        while(!estabelecimento.acabouRodadas()) {
            if (fazPedido()) {
                System.out.println("Sou o Cliente: #" + Thread.currentThread().getId());
                    while(isAguardandando()) {
                        System.out.println("entrei aqui");
                        try {
                            estabelecimento.esperar(id);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                   // sair();

            }
        }

    }

    public boolean isAguardandando() {
        return aguardandando;
    }

    public void setAguardandando(boolean aguardandando) {
        this.aguardandando = aguardandando;
    }




    public synchronized void esperar() throws InterruptedException {
        System.out.println("Sou o Cliente: #" + Thread.currentThread().getId());
        wait();
       //wait();
    }
    public synchronized void sair(){
        System.out.println("Notifica amigo");
        notify();
    }

    public boolean fazPedido(){

        if(!getFazPedido()){
            Random  random = new Random();
            int pedido = random.nextInt(5);
            if(pedido <= 3) return  true;
        }
        return false;
    }

    public  void esperaPedido() throws InterruptedException {
     //   estabelecimento.esperaGarcom(this);
        System.out.println("Porra desse jeito a cerva vai chegar quente");
        fazPedido = true;
    }
    public  void setEsperaPedido(int valor){
        this.tempo = valor;
    }

    public synchronized boolean getFazPedido(){
        return  fazPedido;
    }

    public void recebePedido(){
        System.out.println("Carai foi fazer a cerva ?");
        Random random = new Random();
        tempo = random.nextInt(3000);        //Verificar se o mesmo está indo na copia ou no endereço
    }


    public void consomePedido() throws InterruptedException { // IMplementar
        System.out.println("Vamo pedir mais uma então, se paga");
        //5this.wait(tempo);
        //estabelecimento.esperaaiAmigao(tempo);
        reset();


    }

    public void reset(){
        tempo = 0;
        fazPedido = false;
    }




}
