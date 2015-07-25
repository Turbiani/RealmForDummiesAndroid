package turbiani.com.br.realmfordummiesandroid.model;

import io.realm.RealmObject;

/**
 * Created by turbiani on 23/07/15.
 */
public class Disciplines extends RealmObject {

    private String name;
    private String grade;

    public Disciplines(){}

    public Disciplines(String name, String grade) {
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
