
-------------------------------------------------------------------------------------------------------------
#1
https://github.com/junsuk5/android-first-book/blob/master/BadThreadExam/src/main/java/com/example/badthreadexam/MainActivity.java

------------------------------

. MainActivity에서 Thread 클래스와 Count 변수를 하나씩 선언
public class MainActivity extends AppCompatActivity {

    // 스레드 객체
    private Thread mThread;
    // 카운팅
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

	
------------------------------

. activity_main.xml 레이아웃에 스레드 시작, 스레즈 중지 버튼을 하나씩 만듬
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="startThread"
        android:text="스레드 시작" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="stopThread"
        android:text="스레드 중지" />

------------------------------

public class MainActivity 
	

.  Thread 클래스를 하나 생성해서 mTread.start()함수를 호출한다.
   그러면, Thread의 run()함수가 한번 호출된다. 
   ( run()함수는 1초마다 mCount를 증가시키고, Toast를 띄우기를 100번 실행한다. )   
    // 스레드 시작
    public void startThread(View view) {
        if (mThread == null) {
            // 스레드 초기화 및 시작
            mThread = new Thread("My Thread ") {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        try {
                            mCount++;
                            // 1초 마다 쉬기
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // 인터럽트되면 오래 걸리는 처리 종료
                            break;
                        }
                        // 1초 마다 로그 남기기
                        Log.d("My Thread", "스레드 동작 중 " + mCount);
                    }
                }
            };
            mThread.start();
        }
    }

	
------------------------------

. Stop 버튼이 눌러지면, 현재 Thread가 실행 중이라면, 실행을 중지시키고, 멤버 변수들을 초기화 한다.

    // 스레드 종료
    public void stopThread(View view) {
        if (mThread != null) {
            mThread.interrupt();
            mThread = null;
            mCount = 0;
        }
    }


-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#

