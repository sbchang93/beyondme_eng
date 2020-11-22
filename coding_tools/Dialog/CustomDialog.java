

private void showCustomDialog() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("아이디 / 패스워드");
	builder.setMessage("아이디 / 패스워드 입력하세요.");	
	// 긍정 버튼
	builder.setPositiveButton("삭제", null);
	// 부정 버튼
	builder.setNegativeButton("취소", null);
	// view 붙여넣기
	builder.setView(R.layout.dialog_login);
	builder.show();
}


--------------------------------------------	

private void showCustomDialog() {
	View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, null, false);
	// anomymous class 내에서 사용되려면 final 키워드를 앞에 붙여줘야 함.
	final EditText idEditText = (EditText) view.findViewById(R.layout.id_edit);
	final EditText passwordEditText = (EditText) view.findViewById(R.layout.password_edit);
		
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("아이디 / 패스워드");
	builder.setMessage("아이디 / 패스워드 입력하세요.");	
	// 긍정 버튼
	builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String id = idEditText.getText().toString;
			String pass = passwordEditText.getText().toString();
			Toast.makeText(MemoActivity.this, id + " " + pass, Toast.LENGTH_LONG)
		}
	});
	// 부정 버튼
	builder.setNegativeButton("취소", null);
	// view 붙여넣기
	builder.setView(view);  // view 를 설정 ( R.layout.dialog_login )
	builder.show();
}

--------------------------------------------


--------------------------------------------

