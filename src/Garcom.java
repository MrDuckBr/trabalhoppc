import java.util.ArrayList;

public class Garcom implements Runnable {

    float tempoOcupado;
    boolean disponivel;
    int id, capacidade, nmrPedidos;
    ArrayList<Cliente> listaCliente;
    Thread t;
    boolean maximo;

    Estabelecimento estabelecimento;

    public Garcom(int id, int capacidade, Estabelecimento e) {
        this.id = id;
        this.capacidade = capacidade;
        listaCliente = new ArrayList<>();
        this.estabelecimento = e;

        // Estaticas-----------------
        nmrPedidos = 0;
        disponivel = true;
        maximo = false;
        t = new Thread(this);
        t.start();
        // -----------------------------------
        /*
         *
         * recebeMaximoPedidos(); registraPedidos(); entregaPedidos();
         */

    }

    @Override
    public void run() {
        while (!estabelecimento.acabouRodadas()) {
            if (getDisponivel()) {
                recebeMaximoPedidos();
                imprimeClientesQueAtendera();
                try {
                    registraPedidos();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                entregaPedidos();
            }
        }
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        if (nmrPedidos < capacidade) {
            disponivel = true;
        }
        disponivel = false;
    }

    private void entregaPedidos() {
    }

    private  void registraPedidos() throws InterruptedException {

            System.out.println("Garcom " + id + " registrando pedidos");
            //wait(100);
            System.out.println(listaCliente.size());
            for (Cliente cliente : listaCliente) {
               estabelecimento.registraPedido(cliente);
               listaCliente.remove(cliente);
            }

    }

    private void recebeMaximoPedidos() {
        while (!maximo) {
            if (estabelecimento.getClientesAtendidos() != estabelecimento.getClientes()) {
                if (nmrPedidos < capacidade) {
                    Cliente c = estabelecimento.getCliente();
                    if (c != null) {
                        nmrPedidos++;
                        listaCliente.add(c);
                        System.out.println("Garcom " + id + " recebeu um pedido de " + c.getId());
                    }
                } else {
                    maximo = true;
                }
            } else {
                maximo = true;
            }
        }
    }

    public void imprimeClientesQueAtendera(){
        System.out.print("O garcom: " + id + " ira atender os clientes [");
        for (Cliente c:
             listaCliente) {
            System.out.print(c.getId() + " ");
        }
        System.out.println("]");

    }
}
