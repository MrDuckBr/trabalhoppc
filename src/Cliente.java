import java.util.Random;

public class Cliente extends Thread{

    int id;
    long tempo;
    boolean fazPedido;
    Estabelecimento estabelecimento;

    public Cliente(int id, long tempo, Estabelecimento e){
        this.id = id;
        this.tempo = tempo;
        fazPedido = false;
        this.estabelecimento = e;
    }

    public void run(){
        try {
            if(fazPedido()){
                esperaPedido();
                 recebePedido();
                consomePedido();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean fazPedido(){
        System.out.println("Manda um pingadão ai meu consagrado");
        if(!getFazPedido()){
            Random  random = new Random();
            int pedido = random.nextInt(5);
            if(pedido <= 3) return  true;
        }
        return false;
    }

    public  void esperaPedido() throws InterruptedException {
        estabelecimento.esperaGarcom(this);
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
