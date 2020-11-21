
// getter, setter를 이용해서 만들기

public class Memo {
	private String title;
	private String content;
	
	public Memo(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;		
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuffer("Moemo{");
		sb.append("content='").append(content).append('\'');
		sb.append("title='").append(title).append('\'');
		sb.append('}');
		return sb.toString();
	}
}