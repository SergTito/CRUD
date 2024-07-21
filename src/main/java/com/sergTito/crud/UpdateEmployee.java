package com.sergTito.crud;

import entityClasses.Employee;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateEmployee {

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

    public void update(int id,double salary) {
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();

            String hql = "UPDATE Employee SET salary = :newSalary WHERE id = :employeeId";
            Query query = session.createQuery(hql);

            // Устанавливаем параметры запроса
            query.setParameter("newSalary", salary);
            query.setParameter("employeeId", id);

            int updateSalary = query.executeUpdate();
            if (updateSalary > 0){
                System.out.println("Зарплата сотрудника успешно обновлена");
            }else {
                System.out.println("Сотрудник с указанным ID не существует");
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new HibernateException("Что то пошел не так ", e);
        }
    }
    public void updateAll(){
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            session.beginTransaction();

            session.createQuery("update Employee set salary = 1245"+
                                "Where name = 'Margarita'").executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new HibernateException("Что то пошел не так ", e);
        }
    }
}