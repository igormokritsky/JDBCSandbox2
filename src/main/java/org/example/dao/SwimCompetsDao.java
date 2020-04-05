package org.example.dao;

import org.example.entity.SwimCompet;

public interface SwimCompetsDao {

    SwimCompet getSwimCompet(int id);
    boolean insertSwimCompet(SwimCompet swimCompet);
    boolean updateSwimCompet(SwimCompet swimCompet);
    boolean deleteSwimCompet(int id);
}
