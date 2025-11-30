package model;

public class Mesa {
    private int numero;
    private int capacidade;
    private boolean disponivel; // reservada ou não

    public Mesa(int numero, int capacidade, boolean disponivel) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.disponivel = disponivel;
    }

    public int getNumero(){
        return numero;
    }
    public int getCapacidade(){
        return capacidade;
    }
    public boolean isDisponivel(){
        return disponivel;
    }
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }

}

