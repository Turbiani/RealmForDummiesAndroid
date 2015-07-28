package turbiani.com.br.realmfordummiesandroid.model;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by turbiani on 23/07/15.
 */
public class Student extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private RealmList<Discipline> disciplines;

    public Student(){}

    public Student(String name, RealmList<Discipline> disciplines) {
        this.name = name;
        this.disciplines = disciplines;
        this.id = Integer.parseInt(UUID.randomUUID().toString());
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
