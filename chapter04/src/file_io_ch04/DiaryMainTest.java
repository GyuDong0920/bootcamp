package file_io_ch04;

public class DiaryMainTest {

	public static void main(String[] args) {
		
		Pen pen = new Pen("Pen");
		
		Note note = new Note("note");
		
		note.setInterFaceDiary(pen.interFaceDiary);
	}

}
