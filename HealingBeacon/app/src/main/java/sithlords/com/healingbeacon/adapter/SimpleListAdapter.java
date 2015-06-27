package sithlords.com.healingbeacon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sithlords.com.healingbeacon.R;

public class SimpleListAdapter extends ArrayAdapter<String> {
    public SimpleListAdapter(Context context, List<String> objects) {
        super(context, R.layout.simple_list_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String string = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.prescribed_drug_item, parent, false);
        }

        final TextView titleField = (TextView) convertView.findViewById(R.id.simpleListItemTitle);

        titleField.setText(string);

        return convertView;
    }
}
