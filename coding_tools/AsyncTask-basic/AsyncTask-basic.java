-------------------------------------------------------------------------------------------------------------
# AsyncTask 1
https://github.com/junsuk5/android-first-book/blob/master/CountDownExam/src/main/java/com/example/countexam/MainActivity.java

-------------------------------

. activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout x
    android:orientation="vertical"

    <TextView
        android:id="@+id/count"

    <Button
        android:onClick="start"
        android:text="시작"

    <Button
        android:onClick="clear"
        android:text="초기화"
		

-------------------------------

. MainActivity 클래스 

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private CountTask mTask;


    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.count);
		
		
	// android:onClick="start" 와 연결됨.
    public void start(View view) {
        mTask = new CountTask();
        mTask.execute(0);		

	// android:onClick="clear" 와 연결됨.        
    public void clear(View view) {
        mTask.cancel(true);
        mTextView.setText("0");

				
-------------------------------

. MainActivity 클래스 

public class MainActivity 내에서 


    . CountTask ( AsyncTask 클래스 상속 받음 )
    public class CountTask extends AsyncTask<Integer, Integer, Integer> {
	        protected Integer doInBackground(Integer... params) {
            do {                
                Thread.sleep(1000); // 1초 쉬기								
				params[0]++; // 0 부터 10 까지 1씩 증가				
				publishProgress(params[0]);  // 증가된 값을 텍스트뷰에 표시하기 위해 전달. - onProgressUpdate 함수				
			} while (params[0] < 10);ms[0]);
			
	----------		
	protected void onProgressUpdate(Integer... progress) {            
		mTextView.setText(String.valueOf(progress[0])); // 텍스트뷰에 증가된 값 표시

	----------	
	protected void onPostExecute(Integer result) {            
		mTextView.setText(String.valueOf(result)); // 종료시 마지막 값 텍스트뷰에 표시
		

-------------------------------


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



-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#

