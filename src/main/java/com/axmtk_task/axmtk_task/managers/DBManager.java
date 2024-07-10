package com.axmtk_task.axmtk_task.managers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Properties;

public class DBManager {
    SessionFactory sessionFactory;
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

        this.sessionFactory = new Configuration().
                addAnnotatedClass(Client.class).
                addAnnotatedClass(Contract.class).
                buildSessionFactory();
    }

    public List<Client> getAllClient() {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("FROM Client", Client.class);
            return query.list();
        }
    }

    public List<Contract> getAllContract() {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("FROM Contract", Contract.class);
            List<Contract> res = query.list();
            return res;
        }
    }

    public Contract getContract(Contract contract) {
        try (Session session = sessionFactory.openSession()) {
//            Query<Contract> query = session.createQuery("FROM Contract WHERE "
//                            + "contract_id = " + contract.getContract_id().toString()
////                            +" AND "
////                            + "contract_status = " + contract.getContract_status() +" AND "
////                            + "credit_amount = " + contract.getCredit_amount()
//                    , Contract.class);

            Contract getContract = session.find(Contract.class, contract.getContract_id());

            return getContract;
        }
    }

//    public void updateContract(Contract contract){
//        try (Session session = sessionFactory.openSession()) {
//            sessionFactory.openSession();
//            Query query = session.createQuery("INSERT INTO Contract (contract_status, contract_data) VALUES (:contract_status, :contract_data))");
//            query.setParameter("contract_status", contract.getContract_status());
//            query.setParameter("contract_data", contract.getContract_data());
//            query.executeUpdate();
//        }
//    }

//    public void deleteContract(Contract contract){
//        try (Session session = sessionFactory.openSession()) {
//            session.remove(contract);
//            session.flush();
//            session.clear();
//        }
//    }\

        public void updateContract(Contract contract){
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                Contract oldcontract = session.get(Contract.class, contract.getContract_id());
                oldcontract.setContract_status(contract.getContract_status());
                oldcontract.setContract_data(contract.getContract_data());
                session.getTransaction().commit();
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