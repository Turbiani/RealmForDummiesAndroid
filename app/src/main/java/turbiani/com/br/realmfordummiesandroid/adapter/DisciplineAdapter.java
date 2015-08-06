package turbiani.com.br.realmfordummiesandroid.adapter;

import android.os.Parcelable;

import turbiani.com.br.realmfordummiesandroid.dto.DisciplineDTO;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;

/**
 * Created by turbiani on 04/08/15.
 */
public class DisciplineAdapter {

    public DisciplineDTO adaptToDisciplineDTO(Discipline model){
        DisciplineDTO dto = new DisciplineDTO();
        dto.setDisciplineName(model.getName());
        dto.setDisciplineGrade(model.getGrade());
        dto.setStudentId(model.getStudentId());
        return dto;
    }

    public Discipline adaptFromDisciplineDTO(DisciplineDTO dto){
        Discipline discipline = new Discipline();
        discipline.setStudentId(dto.getStudentId());
        discipline.setName(dto.getDisciplineName());
        discipline.setGrade(dto.getDisciplineGrade());
        return discipline;
    }

}
