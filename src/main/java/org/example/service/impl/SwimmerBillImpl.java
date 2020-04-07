package org.example.service.impl;

import org.example.DAOException;
import org.example.ServiceException;
import org.example.dao.SponsorsDao;
import org.example.dao.SwimmersDao;
import org.example.entity.Sponsor;
import org.example.entity.Swimmer;
import org.example.entity.SwimmersSponsor;
import org.example.transaction.TransactionManager;
import org.example.transaction.TransactionalOperation;

public class SwimmerBillImpl {

    private final SwimmersDao swimmersDao;
    private final SponsorsDao sponsorsDao;
    private final TransactionManager transactionManager;

    public SwimmerBillImpl(SwimmersDao swimmersDao, SponsorsDao sponsorsDao, TransactionManager transactionManager) {
        this.swimmersDao = swimmersDao;
        this.sponsorsDao = sponsorsDao;
        this.transactionManager = transactionManager;
    }

    public void getSwimmerBill(final SwimmersSponsor swimmersSponsor) {
        try {
            transactionManager.executeTransaction(new TransactionalOperation<Object>() {
                @Override
                public Object execute() throws DAOException {
                    swimmersDao.insertSwimmer(swimmersSponsor.getSwimmer_id());
                    sponsorsDao.insertSponsor(swimmersSponsor.getContract_amount());
                    return null;
                }
            });
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
