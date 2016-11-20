package io.romain.rxjavasample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(v -> Observable.range(10, 20)
                .flatMap(integer -> Observable.zip(
                        Observable.just(integer),
                        Observable.just(30),
                        (integer1, integer2) -> integer1
                ))
                .doOnNext(integer -> Log.v("RxJava", "1) >> " + integer))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> Log.v("RxJava", "2) >> " + integer), throwable -> {
                }));
    }
}
