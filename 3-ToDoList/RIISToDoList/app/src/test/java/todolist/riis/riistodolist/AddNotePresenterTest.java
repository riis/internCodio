package todolist.riis.riistodolist;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import dagger.ObjectGraph;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class AddNotePresenterTest {
    private AddNotePresenter mNotePresenter;
    @Inject
    NoteOperations mNoteOperations;

    @Before
    public void setUp() {
        mNotePresenter = new AddNotePresenter(RuntimeEnvironment.application);
        ObjectGraph objectGraph = ObjectGraph.create(new NoteOperationsTestObjectGraph(RuntimeEnvironment.application));
        objectGraph.inject(this);
    }

    @Test
    public void testStoreNoteContent() {
        mNotePresenter.storeNoteContent("testing");
        Assert.assertEquals(mNotePresenter.mNoteContent, "testing");
    }

    @Test
    public void testCreateDbNote() {
        mNotePresenter.storeNoteContent("Test");
        DbNote testNote = mNotePresenter.createDbNote();

        Assert.assertEquals(testNote.getNote(), "Test");
    }
}
