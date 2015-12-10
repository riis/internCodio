package todolist.riis.riistodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteOperations {
    // Database fields
    private DatabaseHelper dbHelper;
    private String[] NOTES_TABLE_COLUMNS = { DatabaseHelper.NOTES_ID, DatabaseHelper.NOTES_CONTENT };
    protected SQLiteDatabase database;

    public NoteOperations(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public DbNote addNote(String note) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.NOTES_CONTENT, note);

        long noteId = database.insert(DatabaseHelper.NOTES, null, values);

        Cursor cursor = database.query(DatabaseHelper.NOTES,
                NOTES_TABLE_COLUMNS, DatabaseHelper.NOTES_ID + " = "
                        + noteId, null, null, null, null);

        cursor.moveToFirst();

        DbNote newNote = parseNote(cursor);
        cursor.close();

        return newNote;
    }

    public List<DbNote> getAllNotes() {
        List<DbNote> notes = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.NOTES,
                NOTES_TABLE_COLUMNS, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                DbNote note = parseNote(cursor);
                notes.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return notes;
    }

    private DbNote parseNote(Cursor cursor) {
        DbNote note = new DbNote();
        note.setId((cursor.getInt(cursor.getColumnIndex(DatabaseHelper.NOTES_ID))));
        note.setNote(cursor.getString(cursor.getColumnIndex(DatabaseHelper.NOTES_CONTENT)));

        return note;
    }
}