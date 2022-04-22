package file_io_ch04;

import java.awt.Label;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
// 콜리
public class Note extends Diary{
	
	JTextArea textArea;
	JTextField textField;
	
	InterFaceDiary interFaceDiary = new InterFaceDiary() {
		
		@Override
		public void printWriter() {
			int i;
			String st = "";
			
			try(FileReader fis = new FileReader(new File("note.txt"))){
				while ((i = fis.read()) != -1) {
					 st += ((char) i);
					/*
					 * 안녕하세요  > 안, 녕, 하, 세, 요
					 */
				 textField.setText(st);
				}
			
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	};
	
	
	
	public Note(String name) {
		super(name);
		panel.add(textField);
		
	}
	
	
	

	
}
