package todolist.riis.riistodolist;

import android.content.Context;

import java.util.List;

public class MockNoteOperations extends NoteOperations {
    public MockNoteOperations(Context context) {
        super(context);
    }

    @Override
    public DbNote addNote(String note) {
        DbNote testNote = new DbNote();
        testNote.setId(1);
        testNote.setNote("Test");

        return testNote;
    }

    @Override
    public List<DbNote> getAllNotes() {
        return super.getAllNotes();
    }
}
