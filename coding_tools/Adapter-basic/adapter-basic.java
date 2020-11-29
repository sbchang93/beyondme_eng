
-------------------------------------------------------------------------------------------------------------
#1
https://github.com/junsuk5/android-first-book/blob/master/AdapterViewExam/src/main/java/com/example/adapterviewexam/MainActivity.java


. 안드로이드에서 제공하는 ArrayAdapter를 이용해서 간단하게 예제 Adapter 와 예제 ListView로 화면을 구성


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 자료 ( String 리스트 데이타를 예제로 30개 만들어서 넣어둠. )
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("data " + i);
        }
	
        // 어댑터 ( 어댑터에 simple_list_item_1의 layout 형태와 String 리스트의 data 전달해 줌)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);

        // 뷰 ( ListView를 activity_main.xml에서 찾아옮. 그리고 ListView에 adapter를 연결시켜줌. )
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}

----------------------------------

. activity_main.xml 내용

    <ListView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:listitem="@layout/item_weather" />
		

	
-------------------------------------------------------------------------------------------------------------
#2
https://github.com/junsuk5/android-first-book/blob/master/AdapterViewExam/src/main/java/com/example/adapterviewexam/MainActivity.java

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		// 자료 
        ArrayList<Weather> data = new ArrayList<>();
        data.add(new Weather("수원", "25도", "맑음"));
        data.add(new Weather("서울", "26도", "비"));
        data.add(new Weather("안양", "24도", "구름"));
        data.add(new Weather("부산", "29도", "구름"));
        data.add(new Weather("인천", "23도", "맑음"));
        data.add(new Weather("대구", "28도", "비"));
        data.add(new Weather("용인", "25도", "비"));
		
		// 어댑터 
        MyFirstAdapter adapter = new MyFirstAdapter(data);

        // 뷰
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}

----------------------------------

. activity_main.xml 내용  ( MainActivity클래스의 ListView와 연결 )

    <ListView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:listitem="@layout/item_weather" />
		
----------------------------------

. 나만의 Adapter 구현

public class MyFirstAdapter extends BaseAdapter {
    private final List<Weather> mData;

    private Map<String, Integer> mWeatherImageMap;

	
----------------------------------

. Weather 클래스  
  ( Model 클래스의 멤버 변수는 prefix로 앞에 'm'을 안 붙임. - String city (O)  : String mCity (X) )
  
public class Weather {
    private String city;        // 도시명
    private String temp;        // 기온
    private String weather;     // 날씨 (맑음, 비, 구름, 눈)

	
----------------------------------

public class MyFirstAdapter 클래스

    // List 를 구현한 모든 것(ArrayList 등) 을 받는 생성자
    public MyFirstAdapter(List<Weather> data) {
        mData = data;

		// String(맑음)을 이미지 리소스 id번호(R.drawable.sunny)로 변경하기 위해 Map<String, Integer> 클래스를 이용함.
        mWeatherImageMap = new HashMap<>();
        mWeatherImageMap.put("맑음", R.drawable.sunny);
        mWeatherImageMap.put("폭설", R.drawable.blizzard);
        mWeatherImageMap.put("구름", R.drawable.cloudy);
        mWeatherImageMap.put("비", R.drawable.rainy);
        mWeatherImageMap.put("눈", R.drawable.snow);
    }
	
	
----------------------------------

. Adapter클래스에서 구현해 줘야하는 4개 Override 함수중에 3개의 Override 함수
  ( getCount(), getIem(..), getItemId(..) )

public class MyFirstAdapter 클래스

    // 아이템의 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position 번째의 아이템
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position 번째의 아이디
    @Override
    public long getItemId(int position) {
        return position;
    }
	
----------------------------------

. Adapter에서 성능향상을 위해 사용하는 ViewHolder 패턴의 나만의 ViewHolder 클래스
  ( 클래스 내에 클래스를 사용하는 경우에 static class로 정의해서 사용. )

public class MyFirstAdapter 클래스

    static class ViewHolder {
        ImageView weatherImage;
        TextView cityText;
        TextView tempText;
    }

	
----------------------------------

. Adapter클래스에서 구현해 줘야하는 4개 Override 함수중에 1개의 Override 함수
  ( getView() )
  
public class MyFirstAdapter 클래스

    // position 번째의 아이템의 View 를 구성하는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
			// ViewHolder 패턴, 
			// findViewById(..)함수를 ListView의 Adapter 클래스의 getView(..)함수에서 사용하게 되면,
			// xml내에 id들을 찾기위해 계속 검색을 해야되어서 시간이 많이 걸림, 그래서 ViewHolder에 미리 연결시켜두고 재사용하는 형태로 사용하는 패턴			
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_weather, parent, false);

            // 날씨, 도시, 기온 View  (** Comment 01 참조)
            ImageView weatherImage = (ImageView) convertView.findViewById(R.id.weather_image);
            TextView cityText = (TextView) convertView.findViewById(R.id.city_text);
            TextView tempText = (TextView) convertView.findViewById(R.id.temp_text);

            holder.weatherImage = weatherImage;
            holder.cityText = cityText;
            holder.tempText = tempText;
			
            convertView.setTag(holder);
        } else {
			// convertView가 남아 있어서, 재사용되는 경우 ( ViewHolder클래스를 저장했던 ViewHolder Tag를 읽어와서 ViewHolder를 이용함. )
            holder = (ViewHolder) convertView.getTag();
        }


        // 현재 position 의 날씨 데이터
        Weather weather = mData.get(position);

        // 데이터 설정 => 홀더에 저장
        holder.weatherImage.setImageResource(mWeatherImageMap.get(weather.getWeather()));
        holder.cityText.setText(weather.getCity());
        holder.tempText.setText(weather.getTemp());

		// ListView의 화면에 뿌려줄 convertView 클래스를 넘겨줌. ( 날씨그림, 도시명, 온도를 나타내는 List Item 클래스 )
        return convertView;
    }
	
	
// (** Comment 01 참조)
// 위의  6줄을 아래아 같이 3줄로 사용하면 안 될까? 
// - 뭔가 메모리 할당 이슈가 있어서 위와 같이 사용하는 걸까? 
// - 아니면 다른 사람이 읽기 편하게 하기 위해 나누어서 적은 걸까? 아래와 같은 형태가 더 보기 좋아 보임.
//holder.weatherImage = (ImageView) convertView.findViewById(R.id.weather_image);
//holder.cityText = (TextView) convertView.findViewById(R.id.city_text);
//holder.tempText = (TextView) convertView.findViewById(R.id.temp_text); 	


----------------------------------

. item_weather.xml 화일
   . id/weather_image  (날씨 그림)
   . id/city_text      (도시 이름)
   . id/temp_text      (온도 숫자)

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">

    <ImageView
        android:id="@+id/weather_image"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@mipmap/ic_launcher" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:orientation="vertical"
        android:padding="8dp">

        <TextView
            android:id="@+id/city_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="도시명"
            android:textSize="30sp" />

        <TextView
            android:id="@+id/temp_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:gravity="end"
            android:text="기온" />

    </LinearLayout>

</LinearLayout>


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
