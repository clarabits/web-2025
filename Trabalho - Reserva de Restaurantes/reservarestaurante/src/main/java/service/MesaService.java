package service;

import java.util.List;

import model.Mesa;
import database.MesaDAOMock;

public class MesaService {

    private final MesaDAOMock mesaDAO;

    public MesaService() {
        this.mesaDAO = new MesaDAOMock();
    }

    public List<Mesa> listar() {
        return mesaDAO.listar();
    }

    public Mesa buscarMesa(int numero) {
        for (Mesa mesa : mesaDAO.listar()) {
            if (mesa.getNumero() == numero) {
                return mesa;
            }
        }
        return null;
    }

    public int mesasDisponiveis() {
        int count = 0;
        for (Mesa mesa : mesaDAO.listar()) {
            if (mesa.isDisponivel()) {
                count++;
            }
        }
        return count;
    }

    public int mesasPorCapacidade(int capacidade) {
        int count = 0;
        for (Mesa mesa : mesaDAO.listar()) {
            if (mesa.isDisponivel() && mesa.getCapacidade() == capacidade) {
                count++;
            }
        }
        return count;
    }

    public int mesasNaoDisponiveis() {
        int count = 0;
        for (Mesa mesa : mesaDAO.listar()) {
            if (!mesa.isDisponivel()) {
                count++;
            }
        }
        return count;
    }
}
