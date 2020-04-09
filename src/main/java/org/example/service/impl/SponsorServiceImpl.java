package org.example.service.impl;

import org.example.DAOException;
import org.example.ServiceException;
import org.example.dao.SwimmerSponsorsDao;
import org.example.entity.SwimmersSponsor;
import org.example.service.SponsorService;
import org.example.transaction.TransactionManager;
import org.example.transaction.TransactionalOperation;

public class SponsorServiceImpl implements SponsorService {


    private final SwimmerSponsorsDao swimmerSponsorsDao;
    private final TransactionManager transactionManager;


    public SponsorServiceImpl(SwimmerSponsorsDao swimmerSponsorsDao, TransactionManager transactionManager) {
        this.swimmerSponsorsDao = swimmerSponsorsDao;
        this.transactionManager = transactionManager;
    }



    public SwimmersSponsor getInfo(final int id){

        try{
            return transactionManager.executeTransaction(new TransactionalOperation<SwimmersSponsor>() {
                @Override
                public SwimmersSponsor execute() throws DAOException {
                     return swimmerSponsorsDao.getSwimmerSponsor(id);
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }



}
