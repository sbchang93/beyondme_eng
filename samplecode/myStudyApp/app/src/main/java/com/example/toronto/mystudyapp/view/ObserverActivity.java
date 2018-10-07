package com.example.toronto.mystudyapp.view;

// Reference Homepage URL 
// https://github.com/yudong80/reactivejava.git
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.toronto.mystudyapp.R;
import com.example.toronto.mystudyapp.common.CommonUtils;

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
import io.reactivex.schedulers.Schedulers;

import static com.example.toronto.mystudyapp.common.Shape.BLUE;
import static com.example.toronto.mystudyapp.common.Shape.GREEN;
import static com.example.toronto.mystudyapp.common.Shape.RED;

public class ObserverActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.text01)    TextView text01;
    @BindView(R.id.text02)    TextView text02;
    @BindView(R.id.textView)    TextView textView;
    @BindView(R.id.textView2)    TextView textView2;
    @BindView(R.id.button1)    Button button1;
    @BindView(R.id.button2)    Button button2;
    
    //BindView( https://medium.com/@Rhee_JH/...1-butterknife-4bcf1ba68424 )
    //@BindView(R.id.user) EditText username;
    // => EditText username = (EditText) findViewById(R.id.user);

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
        Observable<String> source = Observable.fromPublisher(publisher)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread());
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
        source = Observable.fromPublisher(publisher)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        source.subscribe(s ->{
            text02.setText(s);
            Toast.makeText(ObserverActivity.this, s , Toast.LENGTH_SHORT).show();
        });


        // -----------------------------------------------------------
//
//        DisposableObserver<String> observer = new DisposableObserver<String>() {
//            @Override
//            public void onNext(String s) {
//                textView.setText(s);
//                Toast.makeText(ObserverActivity.this, "e.onNext(\"Hello world!\");" , Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onError(Throwable e) { }
//            @Override
//            public void onComplete() {
//                Toast.makeText(ObserverActivity.this, "e.onComplete()" , Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        mDisposable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("Hello world!");
//                e.onComplete();
//            }
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribeWith(observer);



        // -----------------------------------------------------------
//        Disposable disposable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("[CompositeDisposable] hello world!");
//                e.onComplete();
//            }
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe( textView::setText );  // => textView::setText 
//
//        mCompositeDisposable.add(disposable);
//
//
//        disposable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("hello world!");
//                e.onComplete();
//            }
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe( s -> {
//            textView.setText("Second : " + s );  // textView.setText : 
//            Toast.makeText(ObserverActivity.this, "Second : " + s , Toast.LENGTH_SHORT).show();
//        });
//
//        mCompositeDisposable.add(disposable);


        // -----------------------------------------------------------
//        mDisposable = Observable.create(e ->  button1.setOnClickListener(e::onNext))
//                                    .debounce(1000, TimeUnit.MILLISECONDS)
//                                    .observeOn(AndroidSchedulers.mainThread())
//                                    .subscribe(s -> {
//                                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
//                                        String time = sdf.format(Calendar.getInstance().getTime());
//                                        textView2.setText("Clicked : " + time.toString());
//                                    });
//
//        mDisposable = getObservable()
//                        .debounce(1000, TimeUnit.MILLISECONDS)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(s -> {
//                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
//                            String time = sdf.format(Calendar.getInstance().getTime());
//                            textView2.setText("Clicked : " + time.toString());
//                        });


        //-----------------------------------------------------------
//        CommonUtils.exampleStart();
//        String[] balls = {RED, GREEN, BLUE}; //1, 3, 5
//        source = Observable.interval(100L, TimeUnit.MILLISECONDS)
//                .map(Long::intValue)
//                .map(idx -> balls[idx])
//                .take(balls.length)
//                .concatMap(
//                        ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
//                                .map(notUsed -> ball + "<>")
//                                .take(2))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        source.subscribe(s -> {
//            Log.d("test",s);
//            Toast.makeText(ObserverActivity.this, s, Toast.LENGTH_SHORT).show();
//        });
//        CommonUtils.sleep(2000);
//        CommonUtils.exampleComplete();


        //-----------------------------------------------------------
//        CommonUtils.exampleStart();
//        source = Observable.interval(100L, TimeUnit.MILLISECONDS)
//                .map(Long::intValue)
//                .map(idx -> balls[idx])
//                .take(3)
//                .flatMap(
//                        ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
//                                .map(notUsed -> ball + "<>")
//                                .take(2))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//
//        source.subscribe(s -> {
//            Log.d("test",s);
//            Toast.makeText(ObserverActivity.this, s, Toast.LENGTH_SHORT).show();
//        });
//        CommonUtils.sleep(2000);
//        CommonUtils.exampleComplete();


        //참조 링크 : http://developer88.tistory.com/68
        //-----------------------------------------------------------
        // Map
        String[] name = new String[] {"John", "Hong", "Yong"};
        Observable.fromArray(name)
                .map(s -> "Kim" + s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("test",s);
                    Toast.makeText(ObserverActivity.this, s, Toast.LENGTH_SHORT).show();
                });

        String[] name2 = new String[] {"Soo", "Min", "You"};
        Observable.fromArray(name2)
                .map(ObserverActivity::attachFirstName)  // ObserverActivity 
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("test",s);
                    Toast.makeText(ObserverActivity.this, s, Toast.LENGTH_SHORT).show();
                });


        //-----------------------------------------------------------
        // flatmap
        String[] name3 = new String[] {"John", "Hong", "Yong"};
        Observable.fromArray(name3)
                .flatMap(s -> Observable.just("Park"+ s +"<>"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(s -> {
                    Log.d("test",s);
                    Toast.makeText(ObserverActivity.this, s, Toast.LENGTH_SHORT).show();
                });

        String[] name4 = new String[] {"Dong", "Soo", "Muk"};
        Observable.fromArray(name4)
                .flatMap(this::attachPostfix)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.d("test",s);
                    Toast.makeText(ObserverActivity.this, s, Toast.LENGTH_SHORT).show();
                });

        // Subscribe
        // => subscribe (...) 
        String[] name5 = new String[] {"NoSubcribe-Soo", "NoSubcribe-Sung", "NoSubcribe-Min"};
        Observable.fromArray(name5)
                .map(ObserverActivity::noSubscribeFunc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());



    }

    public static String attachFirstName (String obj) {
        return "Kim" + obj;
    }

    private Observable<String> attachPostfix(String obj) {
        return Observable.just(obj+" Sir");
    }

    public static String noSubscribeFunc (String obj) {
        return "NoSubcribe-" + obj;
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
