package inner_class;

public class Button {
	
	interface OnClickListener {
		void OnClick();
	}
	
	void setOnClickListener (OnClickListener Listener) {
		
		Listener.OnClick();
		
	}

}
