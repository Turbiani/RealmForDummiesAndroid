package turbiani.com.br.realmfordummiesandroid.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import turbiani.com.br.realmfordummiesandroid.adapter.StudentAdapter;
import turbiani.com.br.realmfordummiesandroid.dao.StudentDAO;
import turbiani.com.br.realmfordummiesandroid.R;
import turbiani.com.br.realmfordummiesandroid.adapter.RealmStudentDisciplinesListAdapter;
import turbiani.com.br.realmfordummiesandroid.dto.StudentDTO;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 25/07/15.
 */
public class AddDiscipline extends Activity {

    private StudentDTO          studentDTO;
    private ListView            studentDisciplineList;
    private Button              btnAddDiscipline;
    private StudentDAO          studentDAO;
    private StudentAdapter      adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_discipline);
        studentDAO  = StudentDAO.getInstance();
        adapter     = new StudentAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        studentDisciplineList           = (ListView) findViewById(R.id.studentDisciplineList);
        btnAddDiscipline                = (Button) findViewById(R.id.btnAddDiscipline);
        final EditText disciplineName   = (EditText) findViewById(R.id.txtDisciplineName);
        final EditText disciplineGrade  = (EditText) findViewById(R.id.txtGrade);

        //this.student = studentDAO.findStudentById((Integer.parseInt(getIntent().getExtras().get("StudentID").toString())));
        try {
            this.studentDTO = getIntent().getParcelableExtra("StudentDTO");
            studentDisciplineList.setAdapter(getAdapterToStudentDisciplineList());
        }catch (Exception e){
            Log.e("Parcelable", e.getMessage());
        }


        //CHANGE DE MENU TEXT
        TextView labelHome = (TextView) findViewById(R.id.labelHome);
        labelHome.setText("Add " + this.studentDTO.getStudentName() + "'s Disciplines");
        //ADD BUTTON ONCLICK LISTENER
        btnAddDiscipline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Discipline discipline = new Discipline();
                discipline.setName(disciplineName.getEditableText().toString());
                discipline.setGrade(disciplineGrade.getEditableText().toString());
                discipline.setStudentId(studentDTO.getStudentId());
                studentDAO.addDisciplineToStudent(discipline, adapter.adaptFromStudentDTO(studentDTO));
                studentDisciplineList.setAdapter(getAdapterToStudentDisciplineList());
            }
        });

    }

    private ListAdapter getAdapterToStudentDisciplineList(){
        final ListAdapter adapter = new RealmStudentDisciplinesListAdapter(this
                , R.id.studentDisciplineList
                , studentDAO.findDisciplinesFromStudentByID(this.studentDTO.getStudentId())
                , true);

        return adapter;
    }


}
