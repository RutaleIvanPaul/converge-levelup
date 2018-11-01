package com.example.ivanpaulrutale.convergelevelup;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.ivanpaulrutale.convergelevelup.view.MainActivity;
import com.example.ivanpaulrutale.convergelevelup.view.ProfileDetailsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.openLinkWithText;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.pressBackUnconditionally;
import static android.support.test.espresso.action.ViewActions.pressMenuKey;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class RecyclerViewOpensDetailsActivityTest {
//    @Rule
//    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToPositionandClick(){
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(15,click()));

    }

    @Test
    public void checkForDetailsActivity(){
        scrollToPositionandClick();
        intended(hasComponent(new ComponentName(getTargetContext(), ProfileDetailsActivity.class)));
    }

    @Test
    public void checkMainActivityDisplayed(){
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkSwipeDisplayed(){
        Espresso.onView(ViewMatchers.withId(R.id.swiper))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkImageHolderDisplayed(){
        scrollToPositionandClick();
        Espresso.onView(ViewMatchers.withId(R.id.profile_image))
                .check(matches(isDisplayed()));
    }


    @Test
    public void shareButtonLaunchesShareIntent(){
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,null));
    }

}
