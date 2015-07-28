package turbiani.com.br.realmfordummiesandroid.model;

import io.realm.RealmObject;

/**
 * Created by turbiani on 23/07/15.
 */
public class Discipline extends RealmObject {

    private String name;
    private String grade;

    public Discipline(){}

    public Discipline(String name, String grade) {
        this.name = name;
        this.grade = grade;
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
}
