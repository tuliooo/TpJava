package Gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{

	public ImageLabel(String ima){
		this(new ImageIcon(ima));
	}
}
