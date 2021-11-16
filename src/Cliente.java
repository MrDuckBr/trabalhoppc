import java.util.Random;

public class Cliente {

    int id;
    float tempo;
    boolean fezPedido;

    public Cliente(int id, float tempo){
        this.id = id;
        this.tempo = tempo;
    }

    public boolean fazPedido(){
        if(!fezPedido){
            Random  random = new Random();
            int pedido = random.nextInt(5);
            if(pedido <= 3) return  true;
        }
        return false;
    }

    public  void esperaPedido()
    {
        //gera tempo aleatorio
        //semaforo esperando o cliente
    }

    public void recebePedido(){

        tempo = consomePedido();
    }


    public float consomePedido(){ // IMplementar
        return 0;

    }
}
