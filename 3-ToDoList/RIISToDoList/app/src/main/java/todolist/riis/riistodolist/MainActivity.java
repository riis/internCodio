package todolist.riis.riistodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView mNoteRecyclerView;
    private FrameLayout mCreateNoteButton;
    private NoteRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setUpListeners();
    }

    @Override
    protected void onResume() {
        mAdapter = new NoteRecyclerViewAdapter(this);
        mNoteRecyclerView.setAdapter(mAdapter);
        super.onResume();
    }

    private void setUpViews() {
        mCreateNoteButton = (FrameLayout) findViewById(R.id.addButton);
        mNoteRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNoteRecyclerView.setLayoutManager(layoutManager);
        mNoteRecyclerView.setHasFixedSize(true);
    }

    private void setUpListeners() {
        mCreateNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
    }
}
