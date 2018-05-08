package com.blackbox.archiTemplate;

/**
 * Created by umair on 11/01/2018.
 */

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.blackbox.archiTemplate.ui.activities.MainActivity;
import com.blackbox.archiTemplate.ui.fragments.PostListFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private PostListFragment postListFragment;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkFrameLayoutDisplayed() {
        //Check if container is visible
        onView(withId(R.id.main_content)).check(matches(isDisplayed()));
    }

    @Before
    public void init() {
        postListFragment = (PostListFragment) startFragment(PostListFragment.Companion.newInstance());
    }


    /**
     * Fragment added.
     * <p>
     * <p>This will test that, fragment added is not null.</p>
     */
    @Test
    public void fragmentAdded() {
        Assert.assertNotNull(postListFragment);
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
