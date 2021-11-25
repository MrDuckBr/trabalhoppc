import java.util.ArrayList;


public class Garcom implements Runnable {

    float tempoOcupado;
    boolean disponivel;
    int id,capacidade, nmrPedidos;
    ArrayList<Cliente> listaCliente;


    Estabelecimento estabelecimento;


    public Garcom(int id, int capacidade,Estabelecimento e){
        this.id= id;
        //this.tempoOcupado = tempoOcupado;
        this.capacidade = capacidade;
        listaCliente = new ArrayList<>();
        this.estabelecimento = e;
        Thread t = new Thread(this);
        t.start();


        //Estaticas-----------------
        nmrPedidos = 0;
        disponivel = false;
        //-----------------------------------
        /*
        *
        recebeMaximoPedidos();
        registraPedidos();
        entregaPedidos();
*/

    }
    @Override
    public void run() {
    }

       /* try {
            while (verificaDisponibilidadePedidos() && estabelecimento.verificaClientesQueFaraoPedidos() != null) { // Pode

                  /*  if (addClienteLista(estabelecimento.verificaClientesQueFaraoPedidos())) {
                        registraPedidos();
                    }
            }
            if(!verificaDisponibilidadePedidos() || estabelecimento.verificaClientesQueFaraoPedidos() == null){
                System.out.println("Passei aqui meu consagra");

                estabelecimento.liberaoamigao();

            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }*/

    public boolean getDisponivel(){
        return  disponivel;
    }


    public boolean verificaDisponibilidadePedidos(){
        disponivel = (nmrPedidos <= capacidade) ? true : false;
            return disponivel;
    }

    public void registraPedidos() {
        if (verificaDisponibilidadePedidos()) {
            nmrPedidos += 1;
        }
    }


    public void addClienteLista(Cliente c){
        registraPedidos();
        listaCliente.add(c);


        }



}
