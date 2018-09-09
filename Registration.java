package lib;

public class Registration {
	
	
	private String name;
	private int mobile_no;
	private String email;
	private String place;
	
	public Registration(String name, int mobile_no, String email,String place)
	{
		this.name=name;
		this.mobile_no=mobile_no;
		this.email = email;
		this.place = place;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(int mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
