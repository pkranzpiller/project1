package shared;

public class User {
	
	private int id;
	private String username, firstname, lastname, password;
	public static enum Type{EMPLOYEE, MANAGER, USER};
	private Type type;
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type t) {
		this.type = t;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
