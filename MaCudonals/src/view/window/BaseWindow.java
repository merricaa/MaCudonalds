package view.window;

import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.Window;

public abstract class BaseWindow extends Window {
	protected CloseIcon closeIcon;
	
	public BaseWindow(String title) {
		super(title);
		
		this.closeIcon = new CloseIcon(this);
		this.getRightIcons().add(closeIcon);
	}
	
	public abstract void setEventHandlers();
}
