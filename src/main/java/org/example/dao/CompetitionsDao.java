package org.example.dao;

import org.example.entity.Competition;

public interface CompetitionsDao {

    Competition getCompetition(int id);
    boolean insertCompetition(Competition competition);
    boolean updateCompetition(Competition competition);
    boolean deleteCompetition(int id);

}
