package com.farshad.taproject.dao;

import com.farshad.taproject.config.HibernateConfiguration;
import com.farshad.taproject.entities.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {

    public User getByUsername(String username) {
        User user = null;
        Transaction transaction = null;
        try {
            String script = "SELECT * FROM User WHERE username=:username";
            SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
            Session session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery(script);
            sqlQuery.setParameter("username",username);
            sqlQuery.addEntity(User.class);
            List list = sqlQuery.list();
            if (!list.isEmpty()) {
                user = (User) list.get(0);
            }
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if ( transaction != null) {
                transaction.rollback();
            }
        }
        return user;
    }
}
