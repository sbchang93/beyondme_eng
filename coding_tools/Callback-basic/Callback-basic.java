
-------------------------------------------------------------------------------------------------------------
# Callback 1
https://github.com/junsuk5/android-first-book/blob/master/CallbackExam/src/main/java/com/example/callbackexam/MainActivity.java


-------------------------------

. MainActivity 

public class MainActivity extends AppCompatActivity implements ColorListFragment.OnColorSelectedListener {

    private ColorFragment mColorFragment;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

		// xml의 color fragment 연결
        mColorFragment = (ColorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_color);

				
	// ColorListFragment 클래스에 선언되어 있는 OnColorSelectedListener 인터페이스의 onColorSelected(int color) 함수 구현
	// Color List Fragment에서 선택된 색깔로 Color Fragment의 색깔을 변경함. 
	// ( ColorListFragment -> Activity -> ColorFragment 순으로 메세지 전달됨. )
    public void onColorSelected(int color) {
        mColorFragment.setColor(color);
		
	
-------------------------------

. activity_main.xml 

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    android:orientation="vertical"

	// 상단에 R-G-B 3개의 스트링을 가진 List Fragment를 정의
    <fragment
        android:id="@+id/fragment_color_list"
        android:name="com.example.callbackexam.ColorListFragment"

	// 선택된 색깔로 변경화면이 변경되는 Fragment 정의
    <fragment
        android:id="@+id/fragment_color"
        android:name="com.example.callbackexam.ColorFragment"

		
-------------------------------

. ColorListFragment 클래스

public class ColorListFragment extends ListFragment {
    private OnColorSelectedListener mListener;

    interface OnColorSelectedListener {
        void onColorSelected(int color);
    }

    public void onAttach(Context context) {
        mListener = (OnColorSelectedListener) context;	
			
			
	public void onViewCreated(View view, Bundle savedInstanceState) {
        List<String> colorList = Arrays.asList("Red", "Green", "Blue");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, colorList);

        setListAdapter(adapter); // R-G-B String을 가진 adapter 등록

		
    public void onListItemClick(ListView l, View v, int position, long id) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) l.getAdapter();
        String colorString = adapter.getItem(position);
        int color = Color.RED;
        switch (colorString) {
            case "Red":
                color = Color.RED;
                break;
            case "Green":
                color = Color.GREEN;
                break;
            case "Blue":
                color = Color.BLUE;
                break;

        if (mListener != null) {
            mListener.onColorSelected(color);  // ColorListFragment -> Activity로 메세지 전달
		

-------------------------------

public class MainActivity 

    public void onColorSelected(int color) {
        mColorFragment.setColor(color); // Activity -> ColorFragment 로 메세지 전달

-------------------------------

. ColorFragment 클래스

public class ColorFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new View(getActivity());


    public void setColor(int color) {
        getView().setBackgroundColor(color);  // Activity에서 ColorFragment의 setColor 호출되어서 ColorFragment의 색깔이 변경됨.
		

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

