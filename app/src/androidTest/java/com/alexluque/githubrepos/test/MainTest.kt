package com.alexluque.githubrepos.test

import android.content.ContentResolver
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.SystemClock
import android.view.View
import androidx.core.content.ContextCompat
import androidx.multidex.MultiDexApplication
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.alexluque.domain.Repo
import com.alexluque.githubrepos.R
import com.alexluque.githubrepos.common.DaggerUiTestComponent
import com.alexluque.githubrepos.model.network.RetrofitBuilder
import com.alexluque.githubrepos.ui.main.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    private lateinit var remoteRepos: List<Repo>
    private lateinit var contentResolver: ContentResolver

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as MultiDexApplication
        val intent = Intent(instrumentation.targetContext, MainActivity::class.java)
        val component = DaggerUiTestComponent.factory().create(app)
        contentResolver = app.contentResolver

        runBlocking {
            remoteRepos = component.remoteDataSource.getRepos(RetrofitBuilder.instance, 1)
        }

        activityTestRule.launchActivity(intent)
    }

    @Test
    fun LongPressOnRepo_DisplaysNavigationDialog() {
        // Wait until Activity loads data
        SystemClock.sleep(2000)

        // Perform long click on first element of collection
        Espresso.onView(
            ViewMatchers.withId(R.id.repos_recycler_view)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(FIRST_REPO, ViewActions.longClick())
        )

        // Verify that Navigation Dialog is open
        Espresso.onView(
            ViewMatchers.withText(R.string.open_page)
        ).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
    }

    @Test
    fun LightGreenBackgroundWhenForkedRepo() {
        // Wait until Activity loads data
        SystemClock.sleep(2000)

        // Verify that recycler view contains a repo
        Espresso.onView(
            ViewMatchers.withId(R.id.repos_recycler_view)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.hasDescendant(
                    ViewMatchers.withId(R.id.repo_card)
                )
            )
        )

        // Verify that repo's background is the color defined for forked repos
        Espresso.onView(
            ViewMatchers.withId(R.id.repo_card)
        ).check(
            ViewAssertions.matches(withBackgroundColor(FORKED_REPO_COLOR))
        )
    }

    private fun withBackgroundColor(colorId: Int): Matcher<View?>? {
        val colorFromResource = ContextCompat.getColor(androidx.test.core.app.ApplicationProvider.getApplicationContext(), colorId)
        return object : BoundedMatcher<View?, View>(View::class.java) {
            override fun matchesSafely(view: View): Boolean {
                val backGroundColor = (view.background as ColorDrawable).color
                return colorFromResource == backGroundColor
            }

            override fun describeTo(description: Description?) {}
        }
    }

    private companion object {
        private const val FIRST_REPO = 0
        private const val FORKED_REPO_COLOR = R.color.colorLightGreen
    }
}