package database;

import model.Reserva;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ReservaDAO {
    
    //precisa listar todas as mesas, 
    // inserir nova reserva, buscar uma reserva por id 
    // e remover uma reserva

    public List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT * FROM Reserva";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

                while(rs.next()) {
                    Reserva r = new Reserva(
                        rs.getInt("numeromesa"),
                        rs.getString("nomecliente"),
                        rs.getTimestamp("inicio").toLocalDateTime(),
                        rs.getTimestamp("fim").toLocalDateTime()
                    );
                        r.setId(rs.getInt("id"));

                    reservas.add(r);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        return reservas;
    }

    public void inserir(Reserva r) {

        String sql = "INSERT INTO Reserva(numeromesa, nomecliente, inicio, fim) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                //ao inserir, o BD vai gerar o id 
                ps.setInt(1, r.getNumeroMesa());
                ps.setString(2, r.getNomeCliente());
                ps.setTimestamp(3, Timestamp.valueOf(r.getInicio()));
                ps.setTimestamp(4, Timestamp.valueOf(r.getFim()));

                //execute update pra ações que modificam o BD 
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            MesaDAO mesaDao = new MesaDAO();
            mesaDao.setIndisponivel(r.getNumeroMesa());
    }

    public Reserva buscarPorId(int id) {

        String sql = "SELECT * FROM Reserva WHERE id = ?";
        Reserva r = null; 

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    r = new Reserva(
                        rs.getInt("numeromesa"),
                        rs.getString("nomecliente"),
                        rs.getTimestamp("inicio").toLocalDateTime(),
                        rs.getTimestamp("fim").toLocalDateTime()
                    );
                    r.setId(rs.getInt("id"));
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    public boolean remover(int id) {

        //eu acho que vai buscar a reserva aqui pra achar o num da mesa 
        //e ai chamar mesa pra liberar 
        MesaDAO mesaDao = new MesaDAO();
        Reserva r = buscarPorId(id);

        if (r == null) return false; 
        return mesaDao.setDisponivel(r.getNumeroMesa());
    }

}

