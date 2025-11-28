package Exam;

public class Main {

	public static void main(String[] args) {
		
		Animal[] zoo = new Animal[3];
		
		
		zoo[0] = new Animal();
		zoo[1] = new Dog();
		zoo[2] = new Cat();
		
		for (int i=0;i<zoo.length;i++) {
			zoo[i].sound();
		}
		
		System.out.println();
		
		AnimalTraniner traniner = new AnimalTraniner();
		traniner.train(new Dog());
		traniner.train(new Cat());
		
	}

}
