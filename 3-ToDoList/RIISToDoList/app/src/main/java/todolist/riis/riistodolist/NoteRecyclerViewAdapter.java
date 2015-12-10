package todolist.riis.riistodolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder> {
    private List<DbNote> mNotes = new ArrayList<>();
    private Context mContext;

    public NoteRecyclerViewAdapter(Context context){
        mContext = context;
        NoteOperations mNoteOperations = new NoteOperations(mContext);
        mNoteOperations.open();
        mNotes = mNoteOperations.getAllNotes();
        mNoteOperations.close();
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final DbNote dbNote = mNotes.get(position);
        holder.mNoteContent.setText(dbNote.getNote());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    final static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView mNoteContent;

        public NoteViewHolder(final View itemView) {
            super(itemView);
            mNoteContent = (TextView) itemView.findViewById(R.id.noteTextView);
        }
    }
}
