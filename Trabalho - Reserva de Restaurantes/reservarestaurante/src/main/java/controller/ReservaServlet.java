package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Reserva;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.ReservaService;
import database.ReservaDAO;

@WebServlet("/api/reservas")
public class ReservaServlet extends HttpServlet {

    private ReservaService reservaService;
    private Gson gson = new GsonBuilder().create();

    @Override
    public void init() {
        ReservaDAO reservaDAO = new ReservaDAO();

        reservaService = new ReservaService(reservaDAO);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);

        int numeroMesa = body.get("numeroMesa").getAsInt();
        String nomeCliente = body.get("nomeCliente").getAsString();
        LocalDate data = LocalDate.parse(body.get("data").getAsString());

        boolean sucesso = reservaService.realizarReserva(numeroMesa, nomeCliente, data);
        resp.setContentType("application/json; charset=UTF-8");

        JsonObject resposta = new JsonObject();
        resposta.addProperty("sucesso", sucesso);

        resp.getWriter().write(resposta.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    Gson gson = new GsonBuilder().create();

    List<Reserva> reservas = reservaService.listarReservas();

    String json = gson.toJson(reservas);
    resp.getWriter().write(json);
}

}
