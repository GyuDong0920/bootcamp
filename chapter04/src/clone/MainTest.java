package clone;

public class MainTest {

	public static void main(String[] args) {
		
		Activity1 activity1 = new Activity1("1");
		Activity2 activity2 = new Activity2("2");
		activity2.setCallBackCheckPosition(activity1.callback);
		

	}

}
