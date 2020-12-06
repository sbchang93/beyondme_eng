-------------------------------------------------------------------------------------------------------------
#1

https://github.com/junsuk5/android-first-book/blob/master/CursorAdapterExam/src/main/java/com/example/cursoradapterexam/MainActivity.java

-------------------------------

. androidManifest.xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.cursoradapterexam">

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

	
-------------------------------

. activity_main.xml 

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"

	// 사진이 가로로 2개씩 보이는 형태로 화면에 보여주는 리스트형태의 뷰
    <GridView
        android:id="@+id/photo_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:horizontalSpacing="4dp"
        android:numColumns="2"
        android:verticalSpacing="4dp"


-------------------------------

. MainActivity 클래스

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰  ( GridView 객체를 xml에서 받아와서 생성함. )
        GridView photoListView = (GridView) findViewById(R.id.photo_list);

        // 사진 데이터 ( 폰에 있는 사진 데이터들이 Cursor에서 사용할 수 있도록 커서가 구성됨. )
        Cursor cursor = getContentResolver().query
                (MediaStore.Images.Media.EXTERNAL_CONTENT_URI,    // From
                        null,   // Select 절
                        null,   // Where 절
                        null,   // Where 절
                        MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");// Order By


        // 어댑터 ( Cursor Adapter 생성)
        MyCursorAdapter adapter = new MyCursorAdapter(this, cursor);
		
		// Grid 뷰에 Cursor Adapter 연결
        photoListView.setAdapter(adapter);

		
        // 클릭 이벤트 처리 ( 사용자가 그림을 누르면, 해당 사진의 사진 경로를 Toast로 보여줌. )
		// setOnItemClickListener (..) 함수를 이용 : (parent, view, postion, id) 
		// (Cursor) parent.getAdapter().getItem(position) 처럼 parent에서 Adapter를 받아올 수 있음.
        photoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 부분의 cursor 데이타
                Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                String path = cursor.getString
                        (cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                Toast.makeText(MainActivity.this, "사진 경로 : " + path,
                        Toast.LENGTH_SHORT).show();

            }
        });		
		
-------------------------------

. item_photo.xml 

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    android:orientation="vertical"

	// 이미지 한 한장 ( 부모 넓이 크기 x 200dp , 그림 중앙 짜르기 )
    <ImageView
        android:id="@+id/photo_image"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:scaleType="centerCrop"
		
-------------------------------

. MyCursorAdapter 클래스

public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = (ImageView) view.findViewById(R.id.photo_image);

        // 사진 경로 가지고 오기 (URI)
        String uri = cursor.getString
                (cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

        // 사진을 이미지뷰에 표시하기
        // imageView.setImageURI(Uri.parse(uri)); // - 이미지 로딩 지연 고려 안 됨.
        Glide.with(context).load(uri).into(imageView);	// Glide 클래스를 이용하면, 이미지가 늦게 오며, 대체 이미지를 우선 띄울 수도 있음.

-------------------------------


-------------------------------

https://codechacha.com/ko/android-mediastore-read-media-files/

val projection = arrayOf(
    MediaStore.Images.Media._ID,
    MediaStore.Images.Media.DISPLAY_NAME,
    MediaStore.Images.Media.DATE_TAKEN
)
val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
val selectionArgs = arrayOf(
    dateToTimestamp(day = 1, month = 1, year = 1970).toString()
)
val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"

val cursor = contentResolver.query(
    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
    projection,
    selection,
    selectionArgs,
    sortOrder
)

private fun dateToTimestamp(day: Int, month: Int, year: Int): Long =
    SimpleDateFormat("dd.MM.yyyy").let { formatter ->
        formatter.parse("$day.$month.$year")?.time ?: 0
    }
	
-------------------------------

https://sijoo.tistory.com/127 ( 2013년 )

* 안드로이드 파일명으로 Cursor 위치 가져오기	

path에는 파일명이 들어간다. 예를들면 mnt/sdcard/camera/20110302.jpg

final Uri uriImages = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	mManagedCursor = getContentResolver().query(uriImages, null, "_data ='" + path + "'", null, null);
	String imageUrl = "";
	if (mManagedCursor != null)
	{
		mManagedCursor.moveToNext();
		int id = mManagedCursor.getInt(0);
		imageUrl = mManagedCursor.getString(0);
		double g1 = mManagedCursor.getDouble(5);
		double g2 = mManagedCursor.getDouble(6);
		//  위도 경도가 0.0 
		latitude = String.valueOf(g1); // 위도
		longitude = String.valueOf(g2); // 경도
		Log.e("imageUrl", imageUrl);
		mManagedCursor.close();
		Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id);
		Bitmap selPhoto = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPurgeable = true;
		options.inDither = true;
			
		try {
			selPhoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null, options);
		} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
-------------------------------


-------------------------------


-------------------------------------------------------------------------------------------------------------
#

-------------------------------------------------------------------------------------------------------------
#

-------------------------------------------------------------------------------------------------------------
#

-------------------------------------------------------------------------------------------------------------
#