package com.example.toronto.mystudyapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ObserverActivity extends Activity {

    @BindView(R.id.text01)    TextView text01;
    @BindView(R.id.text02)    TextView text02;
    @BindView(R.id.textView)    TextView textView;
    @BindView(R.id.textView2)    TextView textView2;
    @BindView(R.id.button1)    Button button1;
    @BindView(R.id.button2)    Button button2;

    //BindView 설명 참조 링크 ( https://medium.com/@Rhee_JH/안드로이드-라이브러리-써보기-1-butterknife-4bcf1ba68424 )
    //@BindView(R.id.user) EditText username;
    // => EditText username = (EditText) findViewById(R.id.user); 의미

    //@BindView(R.id.pass) EditText password;

    private Disposable mDisposable;
    private Unbinder mUnbinder;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observer);

        mUnbinder = ButterKnife.bind(this);


        // -----------------------------------------------------------
        // basic
        Publisher<String> publisher = (Subscriber<? super String> s) -> {
            s.onNext("(With Lambda) Observable.fromPublisher()");
            s.onComplete();
        };
        Observable<String> source = Observable.fromPublisher(publisher);
        source.subscribe(s ->{
            text01.setText(s);
            Toast.makeText(ObserverActivity.this, s , Toast.LENGTH_SHORT).show();
        });


        //without Lambda
        publisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("(Without Lambda) Observable.fromPublisher");
                s.onComplete();
            }
        };
        source = Observable.fromPublisher(publisher);
        source.subscribe(s ->{
            text02.setText(s);
            Toast.makeText(ObserverActivity.this, s , Toast.LENGTH_SHORT).show();
        });


        // -----------------------------------------------------------

        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                textView.setText(s);
                Toast.makeText(ObserverActivity.this, "e.onNext(\"Hello world!\");" , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(Throwable e) { }
            @Override
            public void onComplete() {
                Toast.makeText(ObserverActivity.this, "e.onComplete()" , Toast.LENGTH_SHORT).show();
            }
        };

        mDisposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello world!");
                e.onComplete();
            }
        }).subscribeWith(observer);



        // -----------------------------------------------------------
        Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("[CompositeDisposable] hello world!");
                e.onComplete();
            }
        }).subscribe( textView::setText );  // => textView::setText 은 입력변수가 1개인 setText( string ) 함수 호출하라는 의미

        mCompositeDisposable.add(disposable);


        disposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello world!");
                e.onComplete();
            }
        }).subscribe( s -> {
            textView.setText("Second : " + s );  // textView.setText : textView에 직접 string을 넣을 때는 textView.setText 표기법 사용
            Toast.makeText(ObserverActivity.this, "Second : " + s , Toast.LENGTH_SHORT).show();
        });

        mCompositeDisposable.add(disposable);


        // -----------------------------------------------------------

        mDisposable = Observable.create(e ->  button1.setOnClickListener(e::onNext))
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
                    String time = sdf.format(Calendar.getInstance().getTime());
                    textView2.setText("Clicked : " + time.toString());
                });

        mDisposable = getObservable()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
                    String time = sdf.format(Calendar.getInstance().getTime());
                    textView2.setText("Clicked : " + time.toString());
                });

    }

    private Observable<View> getObservable() {
        return Observable.create(e ->  button2.setOnClickListener(e::onNext));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
