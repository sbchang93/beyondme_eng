
public class MemoAdapter extends BaseAdapter {
	private final List<Memo> mData;
	
	public MemoAdapter(List<Memo> memoList) {
			mData = memoList;
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
			convertView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_memo, parent, false);
				
			// 레이아웃 들고 오기
			TextView titleTextView = (TextView) convertView.findViewById(R.id.title_text);
			TextView contentTextView = (TextView) convertView.findViewById(R.id.content_text);
				
			// 뷰 홀더에 넣는다.
			viewHolder.titleTextView = titleTextView;
			viewHolder.contentTextView = contentTextView;
			
			// Tag를 이용해서 ViewHolder를 ConvertView와 엮는다.
			convertView.setTag(viewHolder);
		} else {
			// 재사용 할 때
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		// 데이터
		Memo memo = mData.get(position);
		
		// 화면에 뿌리기
		viewHolder.tittleTextView.setText(memo.getTitle());
		viewHolder.contentTextView.setText(memo.getContent());
		
		return convertView;		
	}
	
	
	// findViewById로 가져온 View들을 보관
	private static class ViewHolder {
		TextView titleTextView;
		TextView contentTextView;
	}
}