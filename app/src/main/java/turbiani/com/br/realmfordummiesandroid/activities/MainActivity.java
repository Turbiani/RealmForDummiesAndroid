package turbiani.com.br.realmfordummiesandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import turbiani.com.br.realmfordummiesandroid.R;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 25/07/15.
 */
public class MainActivity extends Activity{

    private List<Student>   students;
    private ListView        studentList;
    private Button          btnAddStudent;
    private Realm           realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        realm = Realm.getInstance(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        studentList     = (ListView) findViewById(R.id.studentList);
        btnAddStudent   = (Button) findViewById(R.id.btnAddStudent);
        students        = findAll();

        studentList.setAdapter(getAdapterToStudentList());
        final EditText studentName = (EditText) findViewById(R.id.txtStudentName);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(studentName.getEditableText().toString());
                addStudent(student);

                students = findAll();
                studentList.setAdapter(getAdapterToStudentList());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }


    private List<Student> findAll(){
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

    private void addStudent(Student student){
        realm.beginTransaction();
        realm.copyToRealm(student);
        realm.commitTransaction();
    }

    private void addDisciplineToStudent(Student student, Discipline discipline){
        realm.beginTransaction();
        Student studentPersistable = realm.where(Student.class)
                .equalTo("id", student.getId())
                .findFirst();
        RealmList<Discipline> studentDisciplines = studentPersistable.getDisciplines();
        studentDisciplines.add(discipline);
        studentPersistable.setDisciplines(studentDisciplines);
        realm.commitTransaction();
    }

    private Student findStudentById(int id){
        return realm.where(Student.class)
                .equalTo("id", id)
                .findFirst();
    }

    private ListAdapter getAdapterToStudentList(){
        final ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(
                this,
                android.R.layout.simple_list_item_1,
                students
        );

        return adapter;
    }
}
