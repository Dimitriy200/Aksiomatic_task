package com.axmtk_task.axmtk_task.services;

import com.axmtk_task.axmtk_task.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserManager {

    SessionFactory sessionFactory;

    public void init(){
        this.sessionFactory = new Configuration().
                configure().
                addAnnotatedClass(com.axmtk_task.axmtk_task.models.User.class).
                buildSessionFactory();
    }

    public List<User> getAllUser() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    public void addEmployee(User user ) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }
}