package turbiani.com.br.realmfordummiesandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;
import turbiani.com.br.realmfordummiesandroid.model.Discipline;
import turbiani.com.br.realmfordummiesandroid.model.Student;

/**
 * Created by turbiani on 30/07/15.
 */
public class RealmStudentDisciplinesListAdapter extends RealmBaseAdapter<Discipline> implements ListAdapter {

    private static class ViewHolder {
        TextView discipline;
    }

    public RealmStudentDisciplinesListAdapter(Context context, int resId,
                                              RealmResults<Discipline> realmResults,
                                              boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.discipline = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Discipline item = realmResults.get(position);
        viewHolder.discipline.setText(item.getName());
        return convertView;
    }

    public RealmResults<Discipline> getRealmResults() {
        return realmResults;
    }
}
