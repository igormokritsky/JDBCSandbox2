package org.example.dao;

import org.example.entity.SwimmersSponsor;

public interface SwimmerSponsorsDao {

    SwimmersSponsor getSwimmerSponsor(int id);
    boolean insertSwimmerSponsor(SwimmersSponsor swimmersSponsor);
    boolean updateSwimmerSponsor(SwimmersSponsor swimmersSponsor);
    boolean deleteSwimmerSponsor(int id);

}
