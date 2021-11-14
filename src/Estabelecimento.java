public class Estabelecimento {

    int rodadas, clientes, garcons, capacidade;

    public Estabelecimento(int rodadas, int clientes, int garcons, int capacidade){
        this.clientes = clientes;
        this.garcons = garcons;
        this.rodadas = rodadas;
        this.capacidade = capacidade;
    }

    public void comecar(){
        System.out.println("Começou o atendimento de rodadas free");
        
    }



    public void novaRodada(boolean pedidosAtendidos){
        if(!pedidosAtendidos)
        rodadas += 1;
    }

    public int getRodadas(){
        return rodadas;
    }

}
