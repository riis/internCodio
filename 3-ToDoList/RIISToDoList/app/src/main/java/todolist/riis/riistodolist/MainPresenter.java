package todolist.riis.riistodolist;

import android.content.Context;
import android.content.Intent;

public class MainPresenter {
    private Context mContext;

    public MainPresenter(Context context) {
        mContext = context;
    }

    public void startNextActivity() {
        mContext.startActivity(new Intent(mContext, AddNoteActivity.class));
    }
}
