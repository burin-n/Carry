/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package utility;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {

	private static int mouseX, mouseY;
	private static double lastmouseX,lastmouseY;
	private static boolean mouseLeftDown, mouseRightDown, mouseOnScreen;
	private static boolean mouseLeftLastDown, mouseRightLastDown;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();
	private static ArrayList<KeyCode> keyTriggered = new ArrayList<>();

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}

	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}

	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}

	public static boolean isMouseRightDown() {
		return mouseRightDown;
	}

	public static void setMouseRightDown(boolean mouseRightDown) {
		InputUtility.mouseRightDown = mouseRightDown;
	}

	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen;
	}

	public static boolean isMouseLeftLastDown(){
		return mouseLeftLastDown;
	}
	
	public static void setMouseLeftLastDown(boolean v) {
		InputUtility.mouseLeftLastDown = v;
	}

	public static boolean isMouseRightLastDown(){
		return mouseRightLastDown;
	}
	
	public static void setMouseRightLastDown(boolean v) {
		InputUtility.mouseRightLastDown = v;
	}

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.indexOf(keycode) >= 0;
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		int p = keyPressed.indexOf(keycode);
		if(pressed){
			if(p<0) keyPressed.add(keycode);
		}
		else keyPressed.remove(keycode);	
	}

	public static boolean getKeyTriggered(KeyCode keycode) {
		return keyTriggered.indexOf(keycode) >= 0;
	}

	public static void setKeyTriggered(KeyCode keycode, boolean pressed) {
		int p = keyTriggered.indexOf(keycode);
		if(pressed){
			if(p<0) keyTriggered.add(keycode);
		}
		else keyTriggered.remove(keycode);
	}

	public static void postUpdate() {
		mouseLeftDown = false;
		mouseRightDown = false;
		keyTriggered.clear();
	}

	public static double getLastmouseX() {
		return lastmouseX;
	}

	public static void setLastmouseX(double d) {
		InputUtility.lastmouseX = d;
	}

	public static double getLastmouseY() {
		return lastmouseY;
	}

	public static void setLastmouseY(double lastmouseY) {
		InputUtility.lastmouseY = lastmouseY;
	}
}
