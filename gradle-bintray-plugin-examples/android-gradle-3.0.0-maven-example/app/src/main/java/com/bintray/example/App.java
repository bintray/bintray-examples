package com.bintray.example;

import timber.log.Timber;

public class App {

    public App() {
        Timber.plant(new Timber.DebugTree());
    }

    public void init() {
        Timber.d("Hello, world.");
    }
}
