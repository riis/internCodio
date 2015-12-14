package todolist.riis.riistodolist;

import android.content.Context;

import javax.inject.Inject;

import dagger.ObjectGraph;

public class AddNotePresenter {
    protected String mNoteContent = "";
    private DbNote mNoteModel;
    @Inject
    NoteOperations mNoteOperations;

    public AddNotePresenter(Context context) {
        ObjectGraph objectGraph = ObjectGraph.create(new NoteOperationsObjectGraph(context));
        objectGraph.inject(this);
    }

    public void storeNoteContent(String noteContent) {
        mNoteContent = noteContent;
    }

    public DbNote createDbNote() {
        if (!mNoteContent.isEmpty()) {
            mNoteOperations.open();
            mNoteModel = mNoteOperations.addNote(mNoteContent);
            mNoteOperations.close();

            return mNoteModel;
        }

        return null;
    }
}
