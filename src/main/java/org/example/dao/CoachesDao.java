package org.example.dao;


import org.example.entity.Coach;

public interface CoachesDao {
    Coach getCoach(int id);
    boolean insertCoach(Coach coach);
    boolean updateCoach(Coach coach);
    boolean deleteCoach(int id);
}
