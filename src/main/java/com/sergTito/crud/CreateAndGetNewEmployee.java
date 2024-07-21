package com.sergTito.crud;

import entityClasses.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CreateAndGetNewEmployee {
    Employee emp;
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


    public void createEmployee(String name, String surname, String department, int salary) throws HibernateException {


        Session session = SESSION_FACTORY.getCurrentSession();

        try {

            session.beginTransaction();
            emp = new Employee(name, surname, department, salary);
            List<Employee> employeeList = session.createQuery("FROM Employee WHERE name = :name AND surname = :surname")
                    .setParameter("name", name)
                    .setParameter("surname", surname)
                    .getResultList();
            session.getTransaction().commit();

            boolean exists = !employeeList.isEmpty();
            if (!exists) {
                session.save(emp);// Сохранение нового сотрудника, если такого нет
                session.getTransaction().commit();// Коммит транзакции
                System.out.println("Сотрудник добавлен");
            } else {
                session.getTransaction().rollback(); // Откат так как сотрудник с таким именем и ф. Уже есть
                System.out.println("Такой пользователь уже есть ");
            }
        } catch (HibernateException e) {

            throw new HibernateException("Ошибка вовремя добавлении пользователья ", e);
        } finally {
            session.close();
        }
    }

    public void getId() {
        int empId = emp.getId();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        emp = session.get(Employee.class, empId);
        session.getTransaction().commit();
        System.out.println(emp);
    }
}
