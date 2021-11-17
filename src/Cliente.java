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
        if(!getFazPedido()){
            Random  random = new Random();
            int pedido = random.nextInt(5);
            if(pedido <= 3) return  true;
        }
        return false;
    }

    public  void esperaPedido() throws InterruptedException {
        estabelecimento.esperaGarcom(this);
        fazPedido = true;
    }
    public  void setEsperaPedido(int valor){
        this.tempo = valor;
    }

    public synchronized boolean getFazPedido(){
        return  fazPedido;
    }

    public void recebePedido(){
        Random random = new Random();
        tempo = random.nextInt(3000);        //Verificar se o mesmo está indo na copia ou no endereço
    }


    public void consomePedido() throws InterruptedException { // IMplementar
        this.wait(tempo);
        reset();


    }

    public void reset(){
        tempo = 0;
        fazPedido = false;
    }
}
