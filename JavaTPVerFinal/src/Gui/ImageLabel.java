package Gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{

	public ImageLabel(String img){
		this(new ImageIcon(img));
	}
	
	public ImageLabel(ImageIcon icon){
		setIcon(icon);
		setIconTextGap(0);
		setBorder(null);
		setText(null);
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
	}
	
	public void setImage(ImageIcon icon){
		setIcon(icon);
		setIconTextGap(0);
		setBorder(null);
		setText(null);
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
	   setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
	}
	
	public ImageLabel(){
		
	}
}
