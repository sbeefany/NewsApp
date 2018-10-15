package com.example.user.mycardapp.Domain;

import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableObserver;

public interface IInterator {

    void execute ( @NonNull DisposableObserver observer );

    void dispose ();
}
