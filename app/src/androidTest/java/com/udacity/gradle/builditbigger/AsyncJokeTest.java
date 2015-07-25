package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class AsyncJokeTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public AsyncJokeTest() {
        super(MainActivity.class);
    }

    @MediumTest
    public void testJokeAsynTask () throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<Void, Void, String> myTask = new AsyncTask<Void, Void, String>() {
            private MyApi myApiService = null;

            @Override
            protected String doInBackground(Void... params) {
                if (myApiService == null) {  // Only do this once
                    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            // options for running against local devappserver
                            // - 10.0.2.2 is localhost's IP address in Android emulator
                            // - turn off compression when running against local devappserver

                            //.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            // Genymotion uses 10.0.3.2
                            .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    // end options for devappserver

                    myApiService = builder.build();
                }

                try {
                    return myApiService.tellJoke().execute().getJoke();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                assertNotNull(result);
                assertFalse(result.isEmpty());
                signal.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                myTask.execute();
            }
        });

        signal.await();
    }

}
