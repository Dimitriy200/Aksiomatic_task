package com.axmtk_task.axmtk_task.managers;

import com.axmtk_task.axmtk_task.models.AppTable;
import com.axmtk_task.axmtk_task.models.ContractStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
                addAnnotatedClass(AppTable.class).
                addAnnotatedClass(AppTable.class).
                buildSessionFactory();
    }

    public AppTable getAppTable(AppTable appTable){
        try(Session session = sessionFactory.openSession()){
            return session.find(AppTable.class, appTable.getAppId());
        }
    }

    public List<AppTable> getAllAppTable() {
        try (Session session = sessionFactory.openSession()) {
            Query<AppTable> query = session.createQuery("FROM Client", AppTable.class);
            return query.list();
        }
    }

    public void updateAppTable(AppTable appTable){
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AppTable oldcontract = session.get(AppTable.class, appTable.getAppId());
            oldcontract.setContract_status(appTable.getContract_status());
            oldcontract.setContract_data(appTable.getContract_data());
            session.getTransaction().commit();
        }
    }

    public List<AppTable> getAppNamePhonePassport(String client_name,
                                                  String passport_data,
                                                  String phone_number) {

        try (Session session = sessionFactory.openSession()) {
            Query<AppTable> query = session.createQuery("from Client where client_name = :client_name AND passport_data = :passport_data AND phone_number = :phone_number");
            query.setParameter("client_name", client_name);
            query.setParameter("passport_data", passport_data);
            query.setParameter("phone_number", phone_number);
//            Contract getContract = session.find(Contract.class, contract.getContract_id());

            return query.list();
        }
    }

    public List<AppTable> getAppTableApproved() {
        try (Session session = sessionFactory.openSession()) {

            List<Object[]> query = session.createQuery("Client.client_id, client_name, passport_data, family_status, address, phone_number, employment_information, contract_id_FK FROM Client JOIN Contract ON Client.contract_id_FK = Contract.contract_id WHERE contract_solution = 'approved'").list();
            List<AppTable> listClient = new ArrayList<AppTable>();

            for (Object[] result : query){
                AppTable client = (AppTable) result[0];
                listClient.add(client);
            }

            return listClient;
        }
    }

    public List<AppTable> getAppTableSubscribe(ContractStatus contractStatus) {

        try (Session session = sessionFactory.openSession()) {

            Query<AppTable> query = session.createQuery("from Contract where contract_status = :contract_status");
            query.setParameter("contract_status", contractStatus.toString());

            return query.list();
        }
    }

    public void addAppTable(AppTable appTable) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(appTable);
            transaction.commit();
        }
    }
}