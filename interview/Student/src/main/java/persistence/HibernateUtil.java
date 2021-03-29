package persistence;

import entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;
    static Session sessionObj;

    private static SessionFactory buildSessionFactory() {
        try {
            // Создает сессию с hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Чистит кеш и закрывает соединение с БД
        getSessionFactory().close();
    }

    public static void createRecord() {
        int count = 0;
        Student studentObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entities
            for(int j = 101; j <= 105; j++) {
                count = count + 1;
                studentObj = new Student();
                studentObj.setId(j);
                studentObj.setName("Student " + j);
                studentObj.setMark(2);
                sessionObj.save(studentObj);
            }

            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static List displayRecords() {
        List studentsList = new ArrayList();
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            studentsList = sessionObj.createQuery("FROM Student").list();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
        return studentsList;
    }

    public static void updateRecord(int student_id) {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();


            Student stuObj = (Student) sessionObj.get(Student.class, student_id);
            stuObj.setName("WWW");
            stuObj.setMark(5);

            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }


    public static void deleteRecord(Integer student_id) {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            Student studObj = findRecordById(student_id);
            sessionObj.delete(studObj);

            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static Student findRecordById(Integer find_student_id) {
        Student findStudentObj = null;
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            findStudentObj = (Student) sessionObj.load(Student.class, find_student_id);
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findStudentObj;
    }

    public static void deleteAllRecords() {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM Student");
            queryObj.executeUpdate();

            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

}