package turbiani.com.br.realmfordummiesandroid.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by turbiani on 04/08/15.
 */
public class DisciplineDTO implements Parcelable {

    private String disciplineName;
    private String disciplineGrade;
    private int    studentId;

    public DisciplineDTO(){}

    private DisciplineDTO(Parcel p){
        this.disciplineName     = p.readString();
        this.disciplineGrade    = p.readString();
        this.studentId          = p.readInt();
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineGrade() {

        return disciplineGrade;
    }

    public void setDisciplineGrade(String disciplineGrade) {
        this.disciplineGrade = disciplineGrade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(disciplineName);
        dest.writeString(disciplineGrade);
        dest.writeInt(studentId);
    }

    public static final Parcelable.Creator<DisciplineDTO> CREATOR = new Parcelable.Creator<DisciplineDTO>(){
        public DisciplineDTO createFromParcel(Parcel in){
            return new DisciplineDTO(in);
        }

        public DisciplineDTO[] newArray(int size){
            return new DisciplineDTO[size];
        }
    };

}
