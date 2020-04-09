package org.example.service;

import org.example.entity.SwimCompet;

public interface CompetResultService {

    SwimCompet getResult(int id);
    boolean createResult(SwimCompet swimCompet);
    boolean deleteResult(int id);
}
