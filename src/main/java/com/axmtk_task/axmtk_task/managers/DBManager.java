package com.axmtk_task.axmtk_task.managers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DBManager {
    private SessionFactory sessionFactory;
//    private static DBManager instance;

//    public static DBManager init(){
//        if(instance == null){
//            instance = new DBManager();
//
//            sessionFactory = new Configuration().
//                    addAnnotatedClass(Client.class).
//                    addAnnotatedClass(Contract.class).
//                    buildSessionFactory();
//        }
//        return instance;
//    }

    public void init(){
        sessionFactory = new Configuration().
                addAnnotatedClass(Client.class).
                addAnnotatedClass(Contract.class).
                buildSessionFactory();
    }

    public List<Client> getAllClient() {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("SELECT * FROM Client", Client.class);
            return query.list();
        }
    }

    public List<Contract> getAllContract() {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("SELECT * FROM Contract", Contract.class);
            return query.list();
        }
    }

    public Contract getContract(Contract contract) {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("SELECT * FROM Contract WERE "
                            + " contract_id = " + contract.getContract_id().toString() +" AND "
                            + " status" + contract.getContract_status() +" AND "
                            + " credit_amount" + contract.getCredit_amount() +" AND "
                    , Contract.class);

            return query.uniqueResult();
        }
    }

    public void addClient(Client client ) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }
    }

    public void addContract(Contract contract ) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(contract);
            transaction.commit();
        }
    }
}