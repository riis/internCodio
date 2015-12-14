package todolist.riis.riistodolist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
    private MainPresenter mMainPresenter;
    private RecyclerView mNoteRecyclerView;
    private FrameLayout mCreateNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainPresenter = new MainPresenter(this);

        setUpViews();
        setUpListeners();
    }

    @Override
    protected void onResume() {
        NoteRecyclerViewAdapter mAdapter = new NoteRecyclerViewAdapter(this);
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
                mMainPresenter.startNextActivity();
            }
        });
    }
}
