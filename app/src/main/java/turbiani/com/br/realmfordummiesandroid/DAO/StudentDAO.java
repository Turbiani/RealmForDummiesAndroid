package turbiani.com.br.realmfordummiesandroid.DAO;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 26/07/15.
 */
public class StudentDAO {

    private Realm realm;

    public StudentDAO(Context context) {
        this.realm = Realm.getInstance(context);
    }

    public List<Student> findAll(){
        List<Student> students = new ArrayList<Student>();
        RealmResults<Student> results = null;
        results = realm.where(Student.class).findAll();

        if(results.size() > 0){
            for (Student student : results) {
                students.add(student);
            }
        }

        return students;
    }

    public void addStudent(Student student){
        //OPEN TRANSACTION
        realm.beginTransaction();

        realm.copyToRealm(student);

        realm.commitTransaction();
        //COMMIT AND CLOSE TRANSACTION
    }

    public void addDisciplineToStudent(Student student, Discipline discipline){
        realm.beginTransaction();
        Student studentPersistable = realm.where(Student.class)
                .equalTo("id", student.getId())
                .findFirst();
        RealmList<Discipline> studentDisciplines = studentPersistable.getDisciplines();
        studentDisciplines.add(discipline);
        studentPersistable.setDisciplines(studentDisciplines);
        realm.commitTransaction();
    }

    public Student findStudentById(int id){
        return realm.where(Student.class)
                .equalTo("id", id)
                .findFirst();
    }
}
