package model;

import java.time.LocalDateTime;

public class Reserva {
    private static int contadorIds = 1;
    private int id = 0;
    private int numeroMesa;
    private String nomeCliente;
    private LocalDateTime inicio;
    private LocalDateTime fim; 

    public Reserva(int numeroMesa, String nomeCliente, LocalDateTime inicio, LocalDateTime fim) {
        this.id = contadorIds++;
        this.numeroMesa = numeroMesa;
        this.nomeCliente = nomeCliente;
        this.inicio = inicio;
        this.fim = fim;
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

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
}
