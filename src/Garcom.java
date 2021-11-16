public class Garcom extends Thread {

    float tempoOcupado;
    int id,capacidade, nmrPedidos;

    public Garcom(int id, float tempoOcupado, int capacidade){
        this.id= id;
        this.tempoOcupado = tempoOcupado;
        this.capacidade = capacidade;
    }

    public boolean recebeMaximoPedido(int nmrPedidos){
        if(capacidade >= nmrPedidos) return true;
        return false;
    }

    public void registraPedidos(){
        if(recebeMaximoPedido(nmrPedidos + 1)){
            nmrPedidos += 1;
        }else{
            System.out.println("Máximo de pedidos alcançado");
        }

    }
    public void entregaPedidos(){
        //zerar os pedidos ?
    }

}
