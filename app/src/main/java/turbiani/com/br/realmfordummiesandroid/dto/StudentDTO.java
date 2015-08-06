package turbiani.com.br.realmfordummiesandroid.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import turbiani.com.br.realmfordummiesandroid.model.Discipline;

/**
 * Created by turbiani on 04/08/15.
 */
public class StudentDTO implements Parcelable{

    private int studentId;
    private String studentName;
    private List<DisciplineDTO> studentDisciplines;

    public StudentDTO(){}

    private StudentDTO(Parcel p){
        this.studentName = p.readString();
        this.studentDisciplines = new ArrayList<DisciplineDTO>();
        p.readTypedList(this.studentDisciplines, DisciplineDTO.CREATOR);
        this.studentId = p.readInt();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<DisciplineDTO> getStudentDisciplines() {
        return studentDisciplines;
    }

    public void setStudentDisciplines(List<DisciplineDTO> studentDisciplines) {
        this.studentDisciplines = studentDisciplines;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public static final Parcelable.Creator<StudentDTO> CREATOR = new Parcelable.Creator<StudentDTO>(){
        public StudentDTO createFromParcel(Parcel in){
            return new StudentDTO(in);
        }

        public StudentDTO[] newArray(int size){
            return new StudentDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentName);
        dest.writeTypedList(studentDisciplines);
        dest.writeInt(studentId);
    }
}
