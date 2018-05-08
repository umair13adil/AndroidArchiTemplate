package com.blackbox.archiTemplate;

/**
 * Created by umair on 11/01/2018.
 */

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.blackbox.archiTemplate.ui.activities.MainActivity;
import com.blackbox.archiTemplate.ui.fragments.PostListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class PostListFragmentTest {

    private PostListFragment postListFragment;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        postListFragment = (PostListFragment) startFragment(PostListFragment.Companion.newInstance());

        onView(withId(R.id.recycler_view)).check(matches(atPosition(0, hasDescendant(withText("First Element")))));
                .atPositionOnView(1, R.id.txt_title))
                .check(matches(withText("Test text")));

        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(80))
                .check(matches(atPositionOnView(80, withText("Test Test"), R.id.targetview)));
    }

    /**
     * Start Fragment.
     * <p>
     * <p>This will add fragment to container and then wait for transaction to complete.
     * It will return fragment if it's found by TAG name.</p>
     */
    private Fragment startFragment(Fragment fragment) {
        FragmentManager fragmentManager = mActivityRule.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_content, fragment, "tag");
        transaction.commit();

        getInstrumentation().waitForIdleSync();

        return fragmentManager.findFragmentByTag("tag");
    }
}
