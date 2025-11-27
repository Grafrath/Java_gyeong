package ex_1127;

public class Score {
	private int kor;
	private int eng;
	private int math;
	
	int getTotal () {
		int total;
		total = kor + eng + math;
		return total;
		
	}
	double getAverage () {
		double average;
		average = getTotal()/ 3.0;
		return Math.round(average * 100) / 100.0;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		if (kor < 0 || kor > 100) {
			System.out.println("점수는 0 ~ 100점 사이여야 합니다.");
		} else {
			this.kor = kor;
		}
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		if (eng < 0 || eng > 100) {
			System.out.println("점수는 0 ~ 100점 사이여야 합니다.");
		} else {
			this.eng = eng;
		}
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		if (math < 0 || math > 100) {
			System.out.println("점수는 0 ~ 100점 사이여야 합니다.");
		} else {
			this.math = math;
		}
	}
	
	
}
