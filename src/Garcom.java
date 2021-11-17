import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Garcom extends Thread {

    float tempoOcupado;
    boolean disponivel;
    int id,capacidade, nmrPedidos;
    ArrayList<Cliente> listaCliente;


    Estabelecimento estabelecimento;


    public Garcom(int id, float tempoOcupado, int capacidade,Estabelecimento e){
        this.id= id;
        this.tempoOcupado = tempoOcupado;
        this.capacidade = capacidade;
        listaCliente = new ArrayList<>();


        estabelecimento = e;
        //Estaticas-----------------
        nmrPedidos = 0;
        disponivel = false;
        //-----------------------------------
    }

    public void run() {
        try {
            while (verificaDisponibilidadePedidos() && estabelecimento.verificaClientesQueFaraoPedidos() != null) { // Pode

                    if (addClienteLista(estabelecimento.verificaClientesQueFaraoPedidos())) {
                        registraPedidos();
                    } else {
                        break;

                    }
            }
            if(!verificaDisponibilidadePedidos() || estabelecimento.verificaClientesQueFaraoPedidos() == null){
                System.out.println("Passei aqui meu consagra");
                estabelecimento.liberaoamigao();

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public boolean getDisponibilidade(){
        return  disponivel;
    }


    public boolean verificaDisponibilidadePedidos(){
        disponivel = (nmrPedidos <= capacidade) ? true : false;
            return disponivel;
    }

    public void registraPedidos(){
        if(verificaDisponibilidadePedidos()){
           /* nmrPedidos += 1;
            Random random = new Random();
            listaCliente.get(listaCliente.size()).setEsperaPedido(random.nextInt(3000));*/
           //Verificar se o mesmo está indo na copia ou no endereço

        }

    }
    public void entregaPedidos(){
        //zerar os pedidos ?
    }


    public boolean addClienteLista(Cliente c){
        if(c != null) {
            listaCliente.add(c);
            return true;
        }
        return false;
    }

}
