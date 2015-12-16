package Dominio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Notepad {

	BufferedWriter bufferWriter = null;
	
	public Notepad() throws ErrorTxt {
		try{
			bufferWriter = new BufferedWriter(new FileWriter("Notepad.txt"));
		}catch (IOException error){
			throw new ErrorTxt();
			
		}
	}
	public void write(String txt) throws ErrorWriteNotepad{
		try{
			txt = txt+"\n";
			bufferWriter.write(txt);
			bufferWriter.flush();
		}catch (IOException error){
			throw new ErrorWriteNotepad();
			
		}
	}
	
}
