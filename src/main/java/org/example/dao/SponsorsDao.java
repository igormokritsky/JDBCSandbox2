package org.example.dao;
import org.example.entity.Sponsor;

public interface SponsorsDao {

    Sponsor getSponsor(int id);
    boolean insertSponsor(Sponsor sponsor);
    boolean updateSponsor(Sponsor sponsor);
    boolean deleteSponsor(int id);
}
