package sithlords.com.healingbeacon;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author FleenMobile at 2015-06-28
 */
public class HistoryAdapter extends ArrayAdapter{

    private List<History> data;
    private HistoryActivity activity;
    private int layoutResourceID;

    public HistoryAdapter(HistoryActivity historyActivity, int patient_list_item, List<History> objects) {
        super(historyActivity, patient_list_item, objects);

        this.data = newArrayList();
        this.data.addAll(objects);
        this.activity = historyActivity;
        this.layoutResourceID = patient_list_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        HistoryHolder holder = null;

        // Get a holder (existing or a new one)
        if (row == null) {
            LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
            row = inflater.inflate(layoutResourceID, parent, false);

            holder = new HistoryHolder();
            holder.date = (TextView) row.findViewById(R.id.history_item_date);
            holder.content = (TextView) row
                    .findViewById(R.id.history_item_content);
            row.setTag(holder);
        } else {
            holder = (HistoryHolder) row.getTag();
        }

        // Set holder according to history data
        final History history = data.get(position);

        // Set data
        holder.date.setText(history.getDate());
        holder.content.setText(history.getContent());

        return row;
    }

    static class HistoryHolder {
        TextView date;
        TextView content;
    }

    @Override
    public void clear() {
        super.clear();
        this.data.clear();
    }

    @Override
    public void add(Object object) {
        super.add(object);

        this.data.add((History)object);

    }

    @Override
    public int getCount() {
        return data != null? data.size() : 0;
    }



}
