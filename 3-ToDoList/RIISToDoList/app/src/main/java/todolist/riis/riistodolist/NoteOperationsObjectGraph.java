package todolist.riis.riistodolist;

import android.content.Context;

import dagger.Provides;

@dagger.Module(library = true, injects = AddNotePresenter.class)
public class NoteOperationsObjectGraph {
    private Context mContext;

    public NoteOperationsObjectGraph(Context context) {
        mContext = context;
    }

    @Provides
    NoteOperations provideNoteOperations() {
        return new NoteOperations(mContext);
    }
}