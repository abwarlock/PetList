package com.example.petlist.activity


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @After
    fun tearDown(){
        mActivityRule.scenario.close()
    }

    @Before
    fun init() {
        mActivityRule.scenario.onActivity {

        }

    }
}