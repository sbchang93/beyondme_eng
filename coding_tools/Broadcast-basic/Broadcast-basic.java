
-------------------------------------------------------------------------------------------------------------
# Broadcast 1
https://github.com/junsuk5/android-first-book/blob/master/BroadcastReceiverExam/src/main/java/com/example/broadcastreceiverexam/MainActivity.java

---------------------------------

. AndroidManifest.xml

        <receiver
            android:name=".MyReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter android:priority="9999">
                <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
                <action android:name="com.example.broadcastreceiverexam.broadcast.ACTION_MY_BROADCAST" />
                <action android:name="android.intent.action.BATTERY_LOW" />
            </intent-filter>
        </receiver>
		
---------------------------------

. MainActivity

public class MainActivity extends AppCompatActivity 

    private BroadcastReceiver mReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mReceiver = new MyReceiver();

    protected void onResume() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(MyReceiver.MY_ACTION);
        filter.setPriority(100);
        registerReceiver(mReceiver, filter);
	
    protected void onPause() {
        unregisterReceiver(mReceiver);

		
	
---------------------------------

. activity_main

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    android:orientation="vertical"

    <Button
        android:onClick="sendMyBroadcast"
        android:text="나만의 방송"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

		
---------------------------------
		
. MyReceiver 
		
public class MyReceiver extends BroadcastReceiver {
    public static final String MY_ACTION = "com.example.broadcastreceiverexam.action.ACTION_MY_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "전원 연결 됨", Toast.LENGTH_SHORT).show();
        } else if (MY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "이 방송은 나만의 방송", Toast.LENGTH_SHORT).show();

            // 이후의 브로드캐스트의 전파를 막기
            abortBroadcast();


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
