package model;

import java.time.LocalDate;

public class Reserva {
    private int id = 0;
    private int numeroMesa;
    private String nomeCliente;
    private LocalDate data;


    public Reserva(int numeroMesa, String nomeCliente, LocalDate data) {
        this.numeroMesa = numeroMesa;
        this.nomeCliente = nomeCliente;
        this.data = data;
    }

    public void setId(int id) {
        this.id = id; 
    }

    public int getId() {
        return id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public LocalDate getData() {
        return data;
    }
}
