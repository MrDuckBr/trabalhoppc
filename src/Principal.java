public class Principal {
    public static void main(String[] args) {

        int clientes, garcons, capacidade, rodadas;
        clientes = Integer.parseInt(args[0]);
        garcons = Integer.parseInt(args[1]);
        capacidade = Integer.parseInt(args[2]);
        rodadas = Integer.parseInt(args[3]);

        Estabelecimento estabelecimento = new Estabelecimento(rodadas, clientes,garcons, capacidade);
        estabelecimento.comecar();


    }
}
