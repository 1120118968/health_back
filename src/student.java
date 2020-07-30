
/**
 * @author ������
 *
 */
public class student {
	
	public String Id;
    public String name;
    public String password;
    public String LoginTimer;
	public String Attribute;
	public String getAttribute(){
		return Attribute;
	}
	public void setAttribute(String Attribute){
		this.Attribute=Attribute;
	}
	
    //id 姓名 密码 签到时间
	public student() {
		super();
	}

	public student(String Id, String name, String password ,String LoginTimer)  {
		super();
		this.Id = Id;
		this.name = name;
		this.password = password;
		this.LoginTimer = LoginTimer;
		
	}
	public String getLoginTimer(){
		return LoginTimer;
	}
	public void setLoginTimer(String LoginTimer){
		this.LoginTimer=LoginTimer;
	}
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
