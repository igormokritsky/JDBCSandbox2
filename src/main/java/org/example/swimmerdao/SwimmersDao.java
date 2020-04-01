package org.example.swimmerdao;

import org.example.coachdao.Coach;

public interface SwimmersDao {

    Swimmer getSwimmer(int id);
    boolean insertSwimmer(Swimmer swimmer);
    boolean updateSwimmer(Swimmer swimmer);
    boolean deleteSwimmer(int id);
}
