package sithlords.com.healingbeacon;

import android.app.Activity;
import android.content.Context;
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
public class NotesAdapter extends ArrayAdapter {

    private List<NotesPair> data;
    private Context context;
    private int layoutResourceId;

    public NotesAdapter(Context context, int resource, List<NotesPair> notes) {
        super(context, resource, notes);

        this.data = newArrayList();
        this.data.addAll(notes);
        this.context = context;
        this.layoutResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        NotesHolder holder = null;

        // Get a holder (existing or a new one)
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new NotesHolder();
            holder.first = (TextView) row.findViewById(R.id.notes_item_first);
            holder.second = (TextView) row
                    .findViewById(R.id.notes_item_second);
            row.setTag(holder);
        } else {
            holder = (NotesHolder) row.getTag();
        }

        // Set holder according to notes data
        final NotesPair notesPair = data.get(position);

        // Set data
        holder.first.setText(trim(notesPair.getFirstContent()));
        holder.second.setText(trim(notesPair.getSecondContent()));
        holder.first.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startEditing(notesPair.getFirstID());
            }
        });
        holder.second.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startEditing(notesPair.getSecondID());
            }
        });

        return row;
    }

    // Open activity allowing you to edit this note
    private void startEditing(long noteID) {

    }

    // Trim contents so as to display too long texts
    private String trim(String firstContent) {
        return firstContent.substring(0,55) + "...";
    }


    static class NotesHolder {
        TextView first;
        TextView second;
    }

    @Override
    public void clear() {
        super.clear();
        this.data.clear();
    }

    @Override
    public void add(Object object) {
        super.add(object);

        this.data.add((NotesPair)object);

    }

    @Override
    public int getCount() {
        return data != null? data.size() : 0;
    }

}
