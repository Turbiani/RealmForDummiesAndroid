package turbiani.com.br.realmfordummiesandroid.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import io.realm.RealmResults;
import turbiani.com.br.realmfordummiesandroid.adapter.StudentAdapter;
import turbiani.com.br.realmfordummiesandroid.dao.StudentDAO;
import turbiani.com.br.realmfordummiesandroid.R;
import turbiani.com.br.realmfordummiesandroid.adapter.RealmStudentListAdapter;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 25/07/15.
 */
public class MainActivity extends Activity{

    private RealmResults<Student>   students;
    private ListView                studentList;
    private Button                  btnAddStudent;
    private StudentDAO              studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        studentDAO  = StudentDAO.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
        studentList     = (ListView) findViewById(R.id.studentList);
        btnAddStudent   = (Button) findViewById(R.id.btnAddStudent);
        final EditText studentName = (EditText) findViewById(R.id.txtStudentName);

        students        = studentDAO.findAll();
        studentList.setAdapter(getAdapterToStudentList(students));


        //ADD BUTTON ONCLICK LISTENER
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(studentName.getEditableText().toString());
                studentDAO.create(student);
                studentList.setAdapter(getAdapterToStudentList());
            }
        });

        //ADD ITEM CLICK LISTENER
        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentAdapter adapter = new StudentAdapter();
                Intent intent = new Intent(MainActivity.this, AddDiscipline.class);
                intent.putExtra("StudentDTO", adapter.adaptToStudentDTO(students.get(position)));
                startActivity(intent);
            }
        });
    }

    private ListAdapter getAdapterToStudentList(){
        students        = studentDAO.findAll();
        final ListAdapter adapter = new RealmStudentListAdapter(this
                , R.id.studentDisciplineList
                , students
                , true);

        return adapter;
    }

    private ListAdapter getAdapterToStudentList(RealmResults<Student> students){
        final ListAdapter adapter = new RealmStudentListAdapter(this
                , R.id.studentDisciplineList
                , students
                , true);

        return adapter;
    }
}
