package turbiani.com.br.realmfordummiesandroid.adapter;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import turbiani.com.br.realmfordummiesandroid.dto.DisciplineDTO;
import turbiani.com.br.realmfordummiesandroid.dto.StudentDTO;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 04/08/15.
 */
public class StudentAdapter {

    public StudentDTO adaptToStudentDTO(Student model){
        StudentDTO dto = new StudentDTO();
        dto.setStudentName(model.getName());
        dto.setStudentDisciplines(getDisciplinesAsList(model));
        dto.setStudentId(model.getId());
        return dto;
    }

    public Student adaptFromStudentDTO(StudentDTO dto){
        Student student = new Student();
        student.setId(dto.getStudentId());
        student.setName(dto.getStudentName());
        student.setDisciplines(getDisciplinesAsRealmList(dto));
        return student;
    }

    private List<DisciplineDTO> getDisciplinesAsList(Student student){
        DisciplineAdapter adapter = new DisciplineAdapter();
        List<DisciplineDTO> entities = new ArrayList<DisciplineDTO>();
        for (Discipline discipline:student.getDisciplines()) {
            entities.add(adapter.adaptToDisciplineDTO(discipline));
        }
        return entities;
    }

    private RealmList<Discipline> getDisciplinesAsRealmList(StudentDTO studentDTO){
        DisciplineAdapter adapter = new DisciplineAdapter();
        RealmList<Discipline> entities = new RealmList<Discipline>();
        for (DisciplineDTO disciplineDTO:studentDTO.getStudentDisciplines()) {
            entities.add(adapter.adaptFromDisciplineDTO(disciplineDTO));
        }
        return entities;
    }
}
