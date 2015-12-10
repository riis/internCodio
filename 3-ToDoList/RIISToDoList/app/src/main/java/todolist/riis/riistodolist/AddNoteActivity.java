package todolist.riis.riistodolist;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity {
    private EditText mTodoEditText;
    private Button mSaveButton;
    private NoteOperations mNoteOperations;
    private String mNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mNoteOperations = new NoteOperations(this);

        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        mTodoEditText = (EditText) findViewById(R.id.todoEditText);
        mSaveButton = (Button) findViewById(R.id.saveButton);
    }

    private void setUpListeners() {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mNoteText.isEmpty()) {
                    mNoteOperations.open();
                    mNoteOperations.addNote(mNoteText);
                    mNoteOperations.close();

                    finish();
                }
            }
        });

        mTodoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNoteText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
