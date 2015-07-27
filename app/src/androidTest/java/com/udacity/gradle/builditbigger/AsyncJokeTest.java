package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import java.util.concurrent.CountDownLatch;


public class AsyncJokeTest extends ActivityInstrumentationTestCase2<MainActivity> implements EndpointsAsyncTask.OnTaskCompleted {
    CountDownLatch signal;

    public AsyncJokeTest() {
        super(MainActivity.class);
    }

    @MediumTest
    public void testJokeAsynTask () throws Throwable {
        // create  a signal to let us know when our task is done.
        signal = new CountDownLatch(1);

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                new EndpointsAsyncTask(AsyncJokeTest.this).execute();
            }
        });

        signal.await();
    }

    @Override
    public void onTaskCompleted(String result) {
        assertNotNull(result);
        assertFalse(result.isEmpty());
        signal.countDown();
    }
}
