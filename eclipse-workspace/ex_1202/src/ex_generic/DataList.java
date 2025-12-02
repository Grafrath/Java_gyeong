package ex_generic;

public class DataList<T> {
	private Object[] data;
	private int size;
	private int defaultSize = 10;
	
	public DataList() {
		data = new Object[defaultSize];
	}
	
	public DataList(int size) {
		data = new Object[size];
	}
	
	void add(T value) {
		data[size++] = value;
	}
	
	public Object get(int index) {
		return (T)data[index];
	}
	
	public int size() {
		return size;
	}
	
	public void printAll() {
	    for (int i = 0; i < size; i++) {
	        System.out.println(data[i]);
	    }
	}
}
