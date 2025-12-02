package ex_object;

public class Student {
	
	private int no;
	private String name;
	
	public Student(int n, String na) {
		no = n;
		name = na;		
	}
	
	public Student(String na, int n) {
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
