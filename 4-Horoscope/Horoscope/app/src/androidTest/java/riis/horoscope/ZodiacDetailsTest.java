package riis.horoscope;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class ZodiacDetailsTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void GeminiTest() {
        onView(withText("Gemini")).perform(click());

        onView(withId(R.id.name)).check(matches(withText("Gemini")));
        onView(withId(R.id.month)).check(matches(withText("June")));
        onView(withId(R.id.symbol)).check(matches(withText("Twins")));
    }
}
