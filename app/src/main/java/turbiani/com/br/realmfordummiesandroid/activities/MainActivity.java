package turbiani.com.br.realmfordummiesandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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
import turbiani.com.br.realmfordummiesandroid.adapter.RealmStudentListAdapter;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 25/07/15.
 */
public class MainActivity extends Activity{

    private List<Student>           students;
    private ListView                studentList;
    private Button                  btnAddStudent;
    private Realm                   realm;

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
        final EditText studentName = (EditText) findViewById(R.id.txtStudentName);

        students        = findAllAsList();
        studentList.setAdapter(getAdapterToStudentList());


        //ADD BUTTON ONCLICK LISTENER
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(studentName.getEditableText().toString());
                addStudent(student);

                studentList.setAdapter(getAdapterToStudentList());
            }
        });

        //ADD ITEM CLICK LISTENER
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int studentID = students.get(position).getId();

                Intent intent = new Intent(MainActivity.this, AddDiscipline.class);
                intent.putExtra("StudentID", studentID);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


    private RealmResults<Student> findAll(){
        RealmResults<Student> results = null;
        results = realm.where(Student.class).findAll();

        return results;
    }

    private List<Student> findAllAsList(){
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


    private ListAdapter getAdapterToStudentList(){
       final ListAdapter adapter = new RealmStudentListAdapter(this
                , R.id.studentDisciplineList
                , findAll()
                , true);

        return adapter;
    }
}
