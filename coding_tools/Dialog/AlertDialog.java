

AlertDialog.Builder builder = new AlertDialog.Builder.Builder(this);
builder.setTitle("확인");
builder.setMessage("정말 삭제하시겠습니까");
builder.setIcon(R.mipmap.ic_launcher);

// 긍정 버튼
builder.setPositiveButton("삭제", new DialogInterface.OnClickListener(
	@Override
	public void onClick(DialogInterface dialog, int which) {
			// 메모 삭제 등 원하는 동작 수행
			// deleteMemo(info.id);
	});

// 부정 버튼
builder.setNegativeButton("취소", null);
builder.show();

--------------------------------------------	

--------------------------------------------