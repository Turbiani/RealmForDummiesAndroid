package turbiani.com.br.realmfordummiesandroid.model;

import io.realm.RealmObject;

/**
 * Created by turbiani on 23/07/15.
 */
public class Disciplines extends RealmObject {

    private String name;
    private String course;

    public Disciplines(){}

    public Disciplines(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
