package org.example.service.impl;

import org.example.DAOException;
import org.example.dao.CoachesDao;
import org.example.dao.SwimmersDao;
import org.example.entity.SwimmersSponsor;
import org.example.service.CoachesWorkService;
import org.example.transaction.TransactionManager;
import org.example.transaction.TransactionalOperation;

public class CoachesWorkServiceImpl implements CoachesWorkService {

    private final CoachesDao coachesDao;
    private final SwimmersDao swimmersDao;
    private final TransactionManager transactionManager;

    public CoachesWorkServiceImpl(CoachesDao coachesDao, SwimmersDao swimmersDao, TransactionManager transactionManager) {
        this.coachesDao = coachesDao;
        this.swimmersDao = swimmersDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void getCoachWorkInfo(final int id) {
        try{
            transactionManager.executeTransaction(new TransactionalOperation<SwimmersSponsor>() {
                @Override
                public SwimmersSponsor execute() throws DAOException {
                    coachesDao.getCoach(id);
                    swimmersDao.getSwimmer(id);
                    return null;
                }
            });
        } catch (DAOException e){

        }
    }
}
