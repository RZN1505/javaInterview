import entity.Student;

import org.hibernate.Session;
import persistence.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Student user = new Student();

        user.setName("Alexander");
        user.setMark(5);

        session.save(user);
        session.getTransaction().commit();

        HibernateUtil.createRecord();

        List showStudents = HibernateUtil.displayRecords();
        if(showStudents != null & showStudents.size() > 0) {
            for(Object studentObj : showStudents) {
                studentObj.toString();
            }
        }

        int updateId = 1;
        HibernateUtil.updateRecord(updateId);
        List updateStudent = HibernateUtil.displayRecords();
        if(updateStudent != null & updateStudent.size() > 0) {
            for(Object studentObj : updateStudent) {
                studentObj.toString();
            }
        }
        int deleteId = 5;
        HibernateUtil.deleteRecord(deleteId);
        List deleteStudentRecord = HibernateUtil.displayRecords();
        for(Object studentObj : deleteStudentRecord) {
            studentObj.toString();
        }

        HibernateUtil.deleteAllRecords();
        List deleteAll = HibernateUtil.displayRecords();
        System.exit(0);
    }

}
