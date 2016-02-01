package demo.gyw.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2015/10/15.
 */
public class RxJavaActivity extends Activity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.inject(this);


    }

    /*btn_rxjava*/
    Observable myObservable = Observable.create(
            new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext("Hello RxJAVA");
                    subscriber.onNext("Hello Android");
                    subscriber.onNext("Hello World");
                    subscriber.onCompleted();
                }
            }
    );

    @OnClick(R.id.btn_rxjava1)
    void clickBtnRxJava1() {
        myObservable.subscribe(mySubscriber);
    }


    /*btn_rxjava2*/
    Observable myObservable2 = Observable.just("HELLO", "RxJava", "World");


    @OnClick(R.id.btn_rxjava2)
    void clickBtnRxJava2() {
        myObservable2.subscribe(mySubscriber);

    }

    /*  btn_rxjava3*/
    String[] wordArr = {"HELLO", "RxJava", "World"};
    Observable<String> myObservable3 = Observable.from(wordArr);

    Action1<String> onNextAction = new Action1<String>() {
        // onNext()
        @Override
        public void call(String s) {
            Log.d(TAG, s);
            Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
        }
    };
    Action0 onCompletedAction = new Action0() {
        // onCompleted()
        @Override
        public void call() {
            Log.d(TAG, "completed");
        }
    };

    @OnClick(R.id.btn_rxjava3)
    void clickBtnRxJava3() {
        myObservable3.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }


    @OnClick(R.id.btn_rxjava4)
    void clickBtnRxJava4() {
        Observable.just(1, 2, 3, 4).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Toast.makeText(RxJavaActivity.this, "number : " + integer, Toast.LENGTH_LONG).show();
                    }
                });
    }






    Subscriber<String> mySubscriber = new Subscriber<String>() {
        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Toast.makeText(RxJavaActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };











    @OnClick(R.id.btn_rxjava5)
    void clickBtnRxJava5() {
        Observable.just("aaa","bbb").map(new Func1<String, List<String>>() {
            @Override
            public List<String> call(String s) {
                List<String> list = new ArrayList<String>();
                list.add(s);
                return list;
            }
        }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                Toast.makeText(RxJavaActivity.this, strings.toString(), Toast.LENGTH_LONG).show();
                Log.d("RxJava", strings.toString());
            }
        });
    }
}
