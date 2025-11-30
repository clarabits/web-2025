package database;

import java.util.ArrayList;
import java.util.List;

import model.Mesa;

public class MesaDAOMock {
    public static final List<Mesa> mesas = new ArrayList<>();

    static {
        mesas.add(new Mesa(1, 2, true));
        mesas.add(new Mesa(2, 4, true));
        mesas.add(new Mesa(3, 4, true));
        mesas.add(new Mesa(4, 6, true));
    }

    public List<Mesa> listar() {
        return mesas;
    }
}
