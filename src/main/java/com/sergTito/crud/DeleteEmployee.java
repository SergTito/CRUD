package com.sergTito.crud;

import entityClasses.Employee;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployee {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
    Employee employee;

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

    public void delete(int id ) {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();

            employee = session.get(Employee.class,id);
            session.delete(employee);

            System.out.println("Сотрудник удален ");


            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new HibernateException("Во время удаления что то пошел не так ", e);
        }
    }
}
