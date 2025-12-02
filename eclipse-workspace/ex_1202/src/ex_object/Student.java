package ex_object;

public class Student {
	
	private int no;
	private String name;
	
	Student(int n, String na) {
		no = n;
		name = na;		
	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}
	
	public int hashCode() {
		int hashCode = no + name.hashCode();
		return hashCode;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Student target) {
			if(no == target.getNo() && name.equals(target.getName())) {
				return true;
			}
		}
		return false;
	}
	
}
