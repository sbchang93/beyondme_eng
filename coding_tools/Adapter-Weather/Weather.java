
// Model 클래스의 멤버 변수 앞에 Prefix로 보통 'm'을 안 붙인다.
public class Weather {
	private int imageRes;
	private String location;
	private String temperature;
	
	public int getImageRes() {
		return imageRes;
	}
	
	public String setImageRes(int imageRes) {
		this.imageRes = imageres;
	}
	
	public int getLocation() {
		return location;
	}
	
	public String setLocation(int location) {
		this.location = location;
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public String setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuffer("Weather{");
		sb.append("imageRes=").append(imageRes);
		sb.append(", location='").append(location).append('\'');
		sb.append(", temperature='").append(temperature).append('\'');		
		sb.append('}');
		return sb.toString();
	}	

}