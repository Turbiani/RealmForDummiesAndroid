package turbiani.com.br.realmfordummiesandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by turbiani on 23/07/15.
 */
public class Discipline extends RealmObject {


    private String name;
    private String grade;
    private int    studentId;

    public Discipline(){}

    public Discipline(String name, String grade, int studentId) {
        this.name = name;
        this.grade = grade;
        this.studentId = studentId;
    }

    private Discipline(Parcel p){
        this.name       = p.readString();
        this.grade      = p.readString();
        this.studentId  = p.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

}
