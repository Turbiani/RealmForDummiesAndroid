package turbiani.com.br.realmfordummiesandroid.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by turbiani on 30/07/15.
 */
public class RealmForDummiesAndroid extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(config);
    }
}
