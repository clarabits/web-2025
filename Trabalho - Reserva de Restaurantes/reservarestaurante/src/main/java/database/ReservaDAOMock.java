package database;

import java.util.List;
import java.util.ArrayList;

import model.Reserva;

public class ReservaDAOMock {
    private static final List<Reserva> reservas = new ArrayList<>();

    public List<Reserva> listar() {
        return reservas;
    }

    public void inserir(Reserva reserva) {
        reservas.add(reserva);
    }

    public boolean remover(int id) {
        return reservas.removeIf(r -> r.getId() == id);
    }

    public Reserva buscarPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public void atualizar(Reserva reservaAtualizada) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getId() == reservaAtualizada.getId()) {
                reservas.set(i, reservaAtualizada);
                break;
            }
        }
    }
}
