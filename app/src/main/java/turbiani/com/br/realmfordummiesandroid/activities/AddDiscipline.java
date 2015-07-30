package turbiani.com.br.realmfordummiesandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import turbiani.com.br.realmfordummiesandroid.R;
import turbiani.com.br.realmfordummiesandroid.adapter.RealmStudentDisciplinesListAdapter;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 25/07/15.
 */
public class AddDiscipline extends Activity {

    private Student             student;
    private List<Discipline>    disciplines;
    private ListView            studentDisciplineList;
    private Button              btnAddDiscipline;
    private Realm               realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_discipline);
        realm = Realm.getInstance(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        studentDisciplineList           = (ListView) findViewById(R.id.studentDisciplineList);
        btnAddDiscipline                = (Button) findViewById(R.id.btnAddDiscipline);
        final EditText disciplineName   = (EditText) findViewById(R.id.txtDisciplineName);
        final EditText disciplineGrade  = (EditText) findViewById(R.id.txtGrade);

        //MAKE USER BY ID
        this.student = findStudentById((Integer.parseInt(getIntent().getExtras().get("StudentID").toString())));
        studentDisciplineList.setAdapter(getAdapterToStudentDisciplineList());

        //CHANGE DE MENU TEXT
        TextView labelHome = (TextView) findViewById(R.id.labelHome);
        labelHome.setText("Add " + this.student.getName() + "'s Disciplines");
        //ADD BUTTON ONCLICK LISTENER
        btnAddDiscipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discipline discipline = new Discipline();
                discipline.setName(disciplineName.getEditableText().toString());
                discipline.setGrade(disciplineGrade.getEditableText().toString());
                addDisciplineToCurrentStudent(discipline);

                studentDisciplineList.setAdapter(getAdapterToStudentDisciplineList());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private Student findStudentById(int id){
        return realm.where(Student.class)
                .equalTo("id", id)
                .findFirst();
    }

    private List<Discipline> findAllDisciplinesFromStudentByID(int id){
        Student student = findStudentById(id);
        return student.getDisciplines();
    }

    private void addDisciplineToCurrentStudent(Discipline discipline){
        realm.beginTransaction();

        Student studentPersistable = findStudentById(this.student.getId());
        RealmList<Discipline> studentDisciplines = studentPersistable.getDisciplines();
        studentDisciplines.add(discipline);
        studentPersistable.setDisciplines(studentDisciplines);

        realm.copyToRealm(studentPersistable);
        realm.commitTransaction();
    }


    private ListAdapter getAdapterToStudentDisciplineList(){
        disciplines   = findAllDisciplinesFromStudentByID(this.student.getId());

        final ArrayAdapter<Discipline> adapter = new ArrayAdapter<Discipline>(
                this,
                android.R.layout.simple_list_item_1,
                disciplines
        );

        return adapter;
    }

}
