public class WeatherAdapter extends BaseAdapter {
	private Context mContext;
	private final List<Weather> mData;
	
	public WeatherAdapter(Context context, List<Weather> data) {
		mContext = context;
			mData = data;
	}
	
	// 아이템 갯수
	@Override
	public int getCount() {
		return mData.size();
	}
	
	// position번째 아이템
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}
	
	// position번째 id ( id는 DB를 사용하지 않는 이상 position과 동일함.)
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	// position번째의 레이아웃을 정의
	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		ViewHolder viewHolder;
		//convertView : 재사용 되는 뷰
		if (convertView = null) {
			viewHolder = new ViewHolder();
			
			// 뷰를 새로 만들 때
			ConvertView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_weather, parent, false);
				
			// 레이아웃 들고 오기
			ImageView imageView = (ImageView) convertView.findViewById(R.id.weather_image);
			TextView locationTextView = (TextView) convertView.findViewById(R.id.location_text);
			TextView temperatureTextView = (TextView) convertView.findViewById(R.id.temperature_text);
				
			// 뷰 홀더에 넣는다.
			viewHolder.weatherImage = imageView;
			viewHolder.locationTextView = locationTextView;
			viewHolder.temperatureTextView = temperatureTextView;
			
			// Tag를 이용해서 ViewHolder를 ConvertView와 엮는다.
			convertView.setTag(viewHolder);
		} else {
			// 재사용 할 때
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		// 데이터
		mData weather = mData.get(position);
		
		// 화면에 뿌리기
		
		viewHolder.weatherImage.setImageResource(weather.getImageRes());
		viewHolder.locationTextView.setText(weather.getLocation());
		viewHolder.temperatureTextViewsetText(weather.getTemperature());
		
//		//홀수 줄은 빨간 색
//		if (position % 2 == 1 ) {
//			convertView.setBackgroundColor(Color.RED);
//		} else {
//			convertView.setBackgroundColor(Color.WHITE);
//		}
		
//		// 클랙 된 아이템이면 노란색
//		if (mSelectdPosition == position) {
//			convertView.setBackgroundColor(Color.YELLOW);
//		}
		
		return convertView;		
	}
	
	// -1이면 선택된게 없다.
	private int mSelectedPosition = -1;
	
	public void setSelect(int position) { mSelectedPostion = position; }
	
	
	// findViewById로 가져온 View들을 보관
	private static class ViewHolder {
		ImageView weatherImage
		TextView locationTextView;
		TextView temperatureTextView;
	}
}