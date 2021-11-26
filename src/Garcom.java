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
                if(estabelecimento.getClientesPediram() == estabelecimento.getClientesAtendidos())
                entregaPedidos();
                System.out.println("Pediram " + estabelecimento.getClientesPediram() + " atendidos " + estabelecimento.getClientesAtendidos());
            }
        }
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    // public void setDisponivel(boolean disponivel) {
    // if (nmrPedidos < capacidade) {
    // disponivel = true;
    // }
    // disponivel = false;
    // }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    private void entregaPedidos() {
        listaCliente.clear();
        setDisponivel(false);
        System.out.println("Garcom"+id+"entreguei todos os Pedidos");
    }

    private void registraPedidos() throws InterruptedException {
        System.out.println("Garcom " + id + " registrando pedidos");
        estabelecimento.recebeListPedidos(listaCliente);
        nmrPedidos -= 1;
        maximo = false;
        listaCliente.clear();
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

    public void imprimeClientesQueAtendera() {
        System.out.print("O garcom: " + id + " ira atender os clientes [");
        for (Cliente c : listaCliente) {
            System.out.print(c.getId() + " ");
        }
        System.out.println("]");
    }
}
