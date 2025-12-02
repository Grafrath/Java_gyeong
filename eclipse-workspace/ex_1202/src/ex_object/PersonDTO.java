package ex_object;

public class PersonDTO {
	
	private final int age;
	private final String name;
	
	PersonDTO(int a, String n) {
		age = a;
		name = n;
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}
	
	public int hashCode() {
		int hashCode = age + name.hashCode();
		return hashCode;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof PersonDTO target) {
			if(age == target.getAge() && name.equals(target.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return name;
	}
	
}
