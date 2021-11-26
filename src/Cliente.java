import java.util.Random;

public class Cliente implements Runnable {

    int id;
    int tempo;
    boolean fazPedido, aguardando;
    public boolean teste;
    Estabelecimento estabelecimento;
    Thread t;
    boolean recebeuPedido;

    public Cliente(int id, Estabelecimento e) {
        this.id = id;
        fazPedido = false;
        aguardando = false;
        recebeuPedido = false;
        this.estabelecimento = e;
        t = new Thread(this);
        teste = false;
        t.start();

    }

    @Override
    public void run() {
        // System.out.println("Sou o Cliente: #" + Thread.currentThread().getId());
        while (!estabelecimento.acabouRodadas()) {
            if (fazPedido()) {
                System.out.println("Sou o Cliente: #" + id + " e vou fazer um pedido");
                try {
                    estabelecimento.esperar(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!getAguardando()) {
                    try {
                        esperaPedido();
                        System.out.println("Sou o Cliente: #" + id + " Vai demorar " + getTempo() + " para consumir.");
                        wait(getTempo());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // sair();

            }
        }

    }

    public void setAguardando(boolean aguardando) {
        this.aguardando = aguardando;
    }

    public boolean getAguardando() {
        return aguardando;
    }

    public synchronized void esperar() throws InterruptedException {
        System.out.println("Sou o Cliente: #" + Thread.currentThread().getId());
        wait();
        // wait();
    }

    public synchronized void sair() {
        System.out.println("Notifica amigo");
        notify();
    }

    public boolean getPedido() {
        return fazPedido;
    }

    public boolean fazPedido() {
        if (!getFazPedido()) {
            Random random = new Random();
            int pedido = random.nextInt(5);
            if (pedido <= 3)
                return fazPedido = true;
        }
        return fazPedido = false;
    }

    public void esperaPedido() throws InterruptedException {
        while (!recebeuPedido) {
            wait();
        }
        System.out.println("aqui");
        notify();
        calculaTempo();
    }

    public void calculaTempo() {
        Random random = new Random();
        int tempo = random.nextInt(1000);
        this.tempo = tempo;
    }

    public int getTempo() {
        return tempo;
    }

    public void setRecebeuPedido(boolean recebeu) {
        this.recebeuPedido = recebeu;
    }

    public boolean getRecebeuPedido() {
        return recebeuPedido;
    }

    public void setEsperaPedido(int valor) {
        this.tempo = valor;
    }

    public synchronized boolean getFazPedido() {
        return fazPedido;
    }

    public void recebePedido() {
        System.out.println("Carai foi fazer a cerva ?");
        Random random = new Random();
        tempo = random.nextInt(3000); // Verificar se o mesmo está indo na copia ou no endereço
    }

    public void consomePedido() throws InterruptedException { // IMplementar
        System.out.println("Vamo pedir mais uma então, se paga");
        // 5this.wait(tempo);
        // estabelecimento.esperaaiAmigao(tempo);
        reset();

    }

    public void reset() {
        tempo = 0;
        fazPedido = false;
    }

    public int getId() {
        return id;
    }

}
