public class Principal {
    public static void main(String[] args) throws InterruptedException {

        int clientes, garcons, capacidade, rodadas;
        clientes = 10;
        garcons = 3;
        capacidade = 2;
        rodadas = 3;

        Estabelecimento estabelecimento = new Estabelecimento(rodadas, clientes, garcons, capacidade);
        estabelecimento.comecar();
    }
}
