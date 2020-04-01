package org.example.coachdao;


import org.example.coachdao.Coach;

public interface CoachesDao {
    Coach getCoach(int id);
    boolean insertCoach(Coach coach);
    boolean updateCoach(Coach coach);
    boolean deleteCoach(int id);
}
