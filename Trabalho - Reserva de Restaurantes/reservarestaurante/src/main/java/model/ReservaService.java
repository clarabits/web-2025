package model;

import java.time.LocalDate;
import java.util.List;

import database.ReservaDAO;

public class ReservaService {

    private MesaService mesaService;
    private ReservaDAO reservaDAO;

    public ReservaService(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
        this.mesaService = new MesaService();
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listarReservas();
    }


    public boolean liberarReserva(int reservaId) {

        Reserva r = reservaDAO.buscarPorId(reservaId);
        if (r == null) return false;

        Mesa mesa = mesaService.buscarMesa(r.getNumeroMesa());
        if (mesa != null) {
            mesaService.atualizarDisponibilidade(true, mesa);
        }

        return reservaDAO.remover(reservaId);
    }

    // Verifica se a data fornecida é válida (não é no passado)
    private boolean dataInvalida(LocalDate data) {
        LocalDate hoje = LocalDate.now();
            return data.isBefore(hoje); // true = data inválida
        }


    public boolean realizarReserva(int numeroMesa, String responsavel, LocalDate data) {
        // 1. Mesa existe?
        Mesa mesa = mesaService.buscarMesa(numeroMesa);
        if (mesa == null) return false;

        // 2. Mesa está disponível?
        if (!mesa.isDisponivel()) return false;

        // 3. Data válida?
        if (dataInvalida(data)) return false;

        // 4. Criar objeto de reserva
        Reserva reserva = new Reserva(numeroMesa, responsavel, data);

        // 5. Inserir reserva no banco
        reservaDAO.inserir(reserva);

        // 6. Atualizar disponibilidade SOMENTE da mesa reservada
        mesaService.atualizarDisponibilidade(false, mesa);

        return true;
    }

}
