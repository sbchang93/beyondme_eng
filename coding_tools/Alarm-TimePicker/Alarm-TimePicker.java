-------------------------------------------------------------------------------------------------------------
#1
https://github.com/junsuk5/android-first-book/blob/master/AlarmManagerExam/src/main/java/com/example/alarmmanagerexam/MainActivity.java

public class MainActivity extends AppCompatActivity 

    public void showAlarmDialog(View view) {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
		
		// 사용자가 화면에서 시간 설정할 수 있도록 Dialog 화면이 보임.
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

------------------------------

. (연관) TimePickerFragment timePickerFragment = new TimePickerFragment();

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	
   private AlarmManager mAlarmManager;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 알람 매니저 인스턴스 얻기
        mAlarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        // 현재 시간으로 타임 피커를 설정
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // 타임 피커 다이얼로그를 현재 시간 설정으로 생성하고 반환 ( Dialog 생성해서 넘겨줌. )
        return new TimePickerDialog(getContext(), this, hour, minute,
                DateFormat.is24HourFormat(getContext()));
    }
	
------------------------------	

. (연관) timePickerFragment.show(getSupportFragmentManager(), "timePicker");

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // 설정된 시간 (사용자가 알람이 울리기를 설정한 시간)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

		// (PendingIntent를 이용해서 사용자가 설정한 시간이 되면, 특정 Activity를 실행시킴.)
        // 알람이 동작되면 MainActivity를 실행하도록 동작 정의
        // 여기서 브로드캐스트나 서비스를 실행할 수도 있음
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);

		// (알람 설정 시간이 되면, PendingIntent각 동작됨.)
        // 설정된 시간에 기기가 슬립상태에서도 알람이 동작되도록 설정
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
	
	
-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#


-------------------------------------------------------------------------------------------------------------
#

