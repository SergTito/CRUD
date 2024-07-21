package com.sergTito.crud;

import entityClasses.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadEmployee {

    List<Employee> employeesList;
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure()
                    .addAnnotatedClass(Employee.class)
                    .setProperty("current_session_context_class", "thread")
                    .buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public void read(){
        Session session = SESSION_FACTORY.getCurrentSession();


        session.beginTransaction();
//        employeesList = session.createQuery("FROM Employee")
//                        .getResultList();
        employeesList = session.createQuery("FROM Employee " + "WHERE name = 'Margarita'")
                        .getResultList();
        for (Employee employee : employeesList) {
            System.out.println(employee);
        }

        session.getTransaction().commit();

    }
}
