
-------------------------------------------------------------------------------------------------------------
#1 ActionSendExam

https://github.com/junsuk5/android-first-book/blob/master/ActionSendExam/src/main/java/com/example/actionsendexam/MainActivity.java

. MainActivity 예제

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

------------------

activity_main.xml 의 android:onClick 이벤트 처리 - sendMessage 함수
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:onClick="sendMessage"
        android:text="전달하기" />
		
    <EditText
        android:id="@+id/message_edit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="메시지" />
		
------------------

public class MainActivity 

.sendMessage 함수 구현
 : new Intent를 통해서 "Intent.ACTION_SEND" Intent를 만들고, 
   messageEditText 텍스트 박스의 글자를 안드로이드 시스템에 보냄.
   안드로이드 시스템이 "text/plain"을 처리할 수 있는 앱에 Intent 메세지를 전달하기  
   ( 전달하기 전에 intent.resolveActivity(getPackageManager()) 통해서 intent를 처리할 수 있는 앱이 있는가를 우선 체크함. )

    public void sendMessage(View view) {
        EditText messageEditText = (EditText) findViewById(R.id.message_edit);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageEditText.getText().toString());

        // 처리할 수 있는 액티비티가 있으면 그 액티비티를 실행하라
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
	
-------------------------------------------------------------------------------------------------------------
#2 Activity들간에 통신
https://github.com/junsuk5/android-first-book/blob/master/ActivityExam/src/main/java/com/example/activityexam/MainActivity.java

.  View.OnClickListener 인터페이스를 상속받고, onClick(View v) 구현을 함.

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 1000;
    private EditText mNameEditText;
    private EditText mAgeEditText;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 화면에 layout 표시
        setContentView(R.layout.activity_main);
        // 이름, 나이
        mNameEditText = (EditText) findViewById(R.id.name_edit);
        mAgeEditText = (EditText) findViewById(R.id.age_edit);
        // 버튼 이벤트 처리
        findViewById(R.id.submit_button).setOnClickListener(this);


------------------

activity_main.xml  내용

    <EditText
        android:id="@+id/name_edit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="이름" />

    <EditText
        android:id="@+id/age_edit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="나이" />

   <Button
        android:id="@+id/submit_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="전송" />
		
------------------
		
사용자가 submit 버튼 클릭
// 버튼 이벤트 처리를 설명한 부분
findViewById(R.id.submit_button).setOnClickListener(this);
		
------------------

. 사용자가 submit 버튼 누르면, 이름과 나이를 SecondActivity.class로 전달하고 응답을 기다림. 
  ( REQUEST_CODE 1000 번호표로 메세지를 보내고, 응답이 오기를 기다림. )		
		
    @Override
    public void onClick(View v) {
        // SecondActivity로 전환하겠다는 intent
        Intent intent = new Intent(this, SecondActivity.class);
        // 이름, 나이 가져와서 intent에 추가
        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("age", mAgeEditText.getText().toString());
        // intent의 정보를 토대로 다른 Activity를 시작
        startActivityForResult(intent, REQUEST_CODE);
    }

------------------

. SecondActivity 클래스에서 응답을 보내줌.

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
	@Override
    protected void onCreate(Bundle savedInstanceState) { 
	...
        findViewById(R.id.result_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("result", mMessageTextView.getText().toString());
        // 결과 전달
        setResult(RESULT_OK, intent);
        // 이 액티비티 종료
        finish();
    }		
		
		
------------------

. REQUEST_CODE 1000 번호표와 RESULT_OK와 intent클래스의 result에 "10살 홍길동" 메세지가 담겨져서 넘어옮.
  화면에 Toast 팝업 띄우고 마무리를 지음.
 
public class MainActivity 

    // SecondActivity에서 돌려받은 결과를 처리하는 콜백
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE
                && resultCode == RESULT_OK
                && data != null) {
            // 결과를 받음
            String result = data.getStringExtra("result");
            // 토스트 메시지 표시
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
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

	