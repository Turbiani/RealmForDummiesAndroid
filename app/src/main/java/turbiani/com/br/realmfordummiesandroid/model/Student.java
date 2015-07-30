package turbiani.com.br.realmfordummiesandroid.model;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by turbiani on 23/07/15.
 */
public class Student extends RealmObject implements Serializable {

    private static final long serialVersionID = 1l;

    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Discipline> disciplines;

    public Student(){
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }

    public Student(String name, RealmList<Discipline> disciplines) {
        this.name = name;
        this.disciplines = disciplines;
        this.id = new Random().nextInt(Integer.MAX_VALUE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(RealmList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
