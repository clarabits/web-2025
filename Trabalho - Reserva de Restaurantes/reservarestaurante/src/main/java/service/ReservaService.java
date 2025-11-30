package service;

import java.time.LocalDateTime;
import java.util.List;

import database.ReservaDAOMock;
import model.Mesa;
import model.Reserva;

public class ReservaService {

    private MesaService mesaService;
    private final ReservaDAOMock reservaDAO;

    public ReservaService(ReservaDAOMock reservaDAO) {
        this.reservaDAO = reservaDAO;
        this.mesaService = new MesaService();
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    private boolean haConflito(int numeroMesa, LocalDateTime inicio, LocalDateTime fim) {
        for (Reserva reserva : reservaDAO.listar()) {
            if (reserva.getNumeroMesa() == numeroMesa) {
                if (inicio.isBefore(reserva.getFim()) && fim.isAfter(reserva.getInicio())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reservarMesa(int numeroMesa, String responsavel, LocalDateTime inicio, LocalDateTime fim) {

        // 1. Mesa existe?
        Mesa mesa = mesaService.buscarMesa(numeroMesa);
        if (mesa == null) return false;

        // 2. Mesa está disponível?
        if (!mesa.isDisponivel()) return false;

        // 3. Há conflito de horário?
        if (haConflito(numeroMesa, inicio, fim)) return false;

        // 4. Criar reserva
        Reserva reserva = new Reserva(numeroMesa, responsavel, inicio, fim);

        // 5. Salvar no DAO
        reservaDAO.inserir(reserva);

        // 6. Atualizar disponibilidade
        mesa.setDisponivel(false);

        return true;
    }

    public boolean liberarReserva(int reservaId) {

        Reserva r = reservaDAO.buscarPorId(reservaId);
        if (r == null) return false;

        Mesa mesa = mesaService.buscarMesa(r.getNumeroMesa());
        if (mesa != null) {
            mesa.setDisponivel(true);
        }

        return reservaDAO.remover(reservaId);
    }

    public void expirarReservas() {
        LocalDateTime agora = LocalDateTime.now();

        for (Reserva r : reservaDAO.listar()) {
            if (!r.getFim().isAfter(agora)) {
                Mesa mesa = mesaService.buscarMesa(r.getNumeroMesa());
                if (mesa != null) mesa.setDisponivel(true);

                reservaDAO.remover(r.getId());
            }
        }
    }
}
