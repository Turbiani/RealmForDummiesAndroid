package turbiani.com.br.realmfordummiesandroid.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by turbiani on 23/07/15.
 */
public class Students extends RealmObject {

    private String name;
    private RealmList<Disciplines> disciplines;

    public Students(){}

    public Students(String name, RealmList<Disciplines> disciplines) {
        this.name = name;
        this.disciplines = disciplines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Disciplines> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(RealmList<Disciplines> disciplines) {
        this.disciplines = disciplines;
    }
}
