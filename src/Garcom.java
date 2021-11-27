import java.util.ArrayList;

public class Garcom implements Runnable {


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
        nmrPedidos = 0;
        disponivel = true;
        maximo = false;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        while (!estabelecimento.acabouRodadas()) {
            if (getDisponivel()) {
                recebeMaximoPedidos();
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
        this.disponivel = disponivel;
    }

    private void entregaPedidos() {
        listaCliente.clear();
        setDisponivel(false);
        System.out.println("Garcom"+id+" entreguei todos os Pedidos");
    }

    private void registraPedidos() throws InterruptedException {

        System.out.println("Garcom " + id + "  registrando pedidos");
        estabelecimento.recebeListPedidos(listaCliente);

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


}
