package todolist.riis.riistodolist;

import android.content.Context;

import dagger.Provides;

@dagger.Module(library = true, injects = AddNotePresenterTest.class)
public class NoteOperationsTestObjectGraph {
    private Context mContext;

    public NoteOperationsTestObjectGraph(Context context) {
        mContext = context;
    }

    @Provides
    NoteOperations provideMockNoteOperations() {
        return new MockNoteOperations(mContext);
    }
}