package turbiani.com.br.realmfordummiesandroid.DAO;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 30/07/15.
 */
public final class StudentDAO {
    private static StudentDAO instance = null;
    private static Realm      realm    = null;

    private StudentDAO(){
        if(realm==null){
            realm = Realm.getDefaultInstance();
        }
    }

    public static synchronized StudentDAO getInstance(){
        if(instance==null){
            instance = new StudentDAO();
        }
        return instance;
    }

    public RealmResults<Student> findAll(){
        return realm.where(Student.class).findAll();
    }

    public Student findStudentById(int id){
        return realm.where(Student.class)
                .equalTo("id", id)
                .findFirst();
    }

    public void createOrUpdate(Student student){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(student);
        realm.commitTransaction();
    }

    public void create(Student student){
        realm.beginTransaction();
        realm.copyToRealm(student);
        realm.commitTransaction();
    }

    public RealmResults<Discipline> findDisciplinesFromStudentByID(int id){
        return realm.where(Discipline.class)
                .equalTo("studentId", id).findAll();
    }

    public void addDisciplineToStudent(Discipline discipline, Student student){
        realm.beginTransaction();
        student.getDisciplines().add(discipline);
        realm.copyToRealmOrUpdate(student);
        realm.commitTransaction();
    }
}
