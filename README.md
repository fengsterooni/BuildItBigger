Build It Bigger
=============

This project, **Build It Bigger**, is for [Udacity](www.udacity.com) [**"Android Developer Nanodegree"** program](https://www.udacity.com/course/android-developer-nanodegree--nd801).

### **_Introduction:_**

This project creates an app with multiple flavors that uses multiple libraries and Google Cloud Endpoints.

The finished app consists of four modules:

* A Java library that provides jokes
* A Google Cloud Endpoints (GCE) project that serves those jokes
* An Android Library containing an activity for displaying jokes
* An Android app that fetches jokes from the GCE module and passes them to the Android Library for display

![alt text](https://github.com/fengsterooni/BuildItBigger/blob/master/builditbigger.png "Build It Bigger")


##**_Completed user stories:_**

#### Required Components

* Project contains a Java library for supplying jokes
* Project contains an Android library with an activity that displays jokes passed to it as intent extras.
* Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an async task.
* Project contains connected tests to verify that the async task is indeed loading jokes.
* Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.

#### Required Behavior

* App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.

#### Optional Components

To receive "exceeds specifications", your app must fully implement all of the following items.

* The free app variant displays interstitial ads between the main activity and the joke-displaying activity.
* The app displays a loading indicator while the joke is being fetched from the server.
* The root build.gradle file contains a task that will start up the GCE development server, run all Android tests, then shutdown the development server.

##**_Screencast:_**

![screenshot](https://github.com/fengsterooni/BuildItBigger/blob/master/bigger.gif)
