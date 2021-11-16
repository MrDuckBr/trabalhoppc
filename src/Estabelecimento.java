import java.util.ArrayList;

public class Estabelecimento {

    int rodadas, clientes, garcons, capacidade;
    ArrayList<Thread> lstClientes, lstGarcons;

    public Estabelecimento(int rodadas, int clientes, int garcons, int capacidade){
        this.clientes = clientes;
        this.garcons = garcons;
        this.rodadas = rodadas;
        this.capacidade = capacidade;

        lstClientes = new ArrayList<>();
        lstGarcons = new ArrayList<>();
    }
//https://www.javatpoint.com/how-to-create-a-thread-in-java
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
