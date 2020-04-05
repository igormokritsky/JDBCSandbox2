package org.example.dao;

import org.example.entity.Swimmer;

public interface SwimmersDao {

    Swimmer getSwimmer(int id);
    boolean insertSwimmer(Swimmer swimmer);
    boolean updateSwimmer(Swimmer swimmer);
    boolean deleteSwimmer(int id);
}
