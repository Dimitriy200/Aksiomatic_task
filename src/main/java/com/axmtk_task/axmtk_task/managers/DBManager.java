package com.axmtk_task.axmtk_task.managers;

import com.axmtk_task.axmtk_task.models.Client;
import com.axmtk_task.axmtk_task.models.Contract;
import com.axmtk_task.axmtk_task.models.ContractStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    public List<Client> getClientOnNamePhonPassport(String client_name,
                                                    String passport_data,
                                                    String phone_number) {

        try (Session session = sessionFactory.openSession()) {
//            Query<Client> query = session.createQuery("FROM Client WHERE "
//                                                        + "client_name = " + client_name + " AND "
//                                                        + "passport_data = " + passport_data + " AND "
//                                                        + "phone_number = " + phone_number
//                                                        , Client.class);

            Query<Client> query = session.createQuery("from Client where client_name = :client_name AND passport_data = :passport_data AND phone_number = :phone_number");
            query.setParameter("client_name", client_name);
            query.setParameter("passport_data", passport_data);
            query.setParameter("phone_number", phone_number);
//            Contract getContract = session.find(Contract.class, contract.getContract_id());

            return query.list();
        }
    }

    public List<Client> getClientApproved() {
        try (Session session = sessionFactory.openSession()) {
//            Query<Client> query = session.createQuery("FROM Client WHERE "
//                                                        + "client_name = " + client_name + " AND "
//                                                        + "passport_data = " + passport_data + " AND "
//                                                        + "phone_number = " + phone_number
//                                                        , Client.class);

            List<Object[]> query = session.createQuery("Client.client_id, client_name, passport_data, family_status, address, phone_number, employment_information, contract_id_FK FROM Client JOIN Contract ON Client.contract_id_FK = Contract.contract_id WHERE contract_solution = 'approved'").list();
            List<Client> listClient = new ArrayList<Client>();

            for (Object[] result : query){
                Client client = (Client) result[0];
                listClient.add(client);
            }

            return listClient;
        }
    }

    public List<Contract> getContractSubscribe(ContractStatus contractStatus) {

        try (Session session = sessionFactory.openSession()) {

            Query<Contract> query = session.createQuery("from Contract where contract_status = :contract_status");
            query.setParameter("contract_status", contractStatus.toString());

            return query.list();
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