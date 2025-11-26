package ex_1126;

public class Student {
	
	String name;
	int kor;
	int eng;
	int math;
	
	Student (String n, int k, int e, int m) {
		this.name=n;
		this.kor=k;
		this.eng=e;
		this.math=m;
	}
	
	int getTotal() {
		return kor+eng+math;
	}
	
	double getAverage() {
		double avg = getTotal() / 3.0;
	    return Math.round(avg * 100) / 100.0;
	}
	
	char getGrade() {
		
		if (getAverage()>=90) {
			return 'A';
		}else if (getAverage()>=80) {
			return 'B';
		} else if (getAverage()>=70) {
			return 'C';
		} else if (getAverage()>=60) {
			return 'D';
		} else {
			return 'F';
		}
		
	}

}