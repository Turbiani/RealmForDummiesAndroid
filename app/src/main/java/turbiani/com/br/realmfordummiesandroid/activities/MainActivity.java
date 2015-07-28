package turbiani.com.br.realmfordummiesandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import turbiani.com.br.realmfordummiesandroid.DAO.StudentDAO;
import turbiani.com.br.realmfordummiesandroid.R;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 25/07/15.
 */
public class MainActivity extends Activity{

    private List<Student>   students;
    private StudentDAO      studentDAO;
    private ListView        studentList;
    private Button          btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        studentDAO = new StudentDAO(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        studentList     = (ListView) findViewById(R.id.studentList);
        btnAddStudent   = (Button) findViewById(R.id.btnAddStudent);
        students        = studentDAO.findAll();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(
                this,
                android.R.layout.simple_list_item_1,
                students
        );

        studentList.setAdapter(adapter);
        final EditText studentName = (EditText) findViewById(R.id.txtStudentName);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(studentName.getEditableText().toString());
                studentDAO.addStudent(student);
            }
        });
    }
}
