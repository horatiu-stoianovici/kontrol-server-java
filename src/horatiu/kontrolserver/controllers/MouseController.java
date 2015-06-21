package horatiu.kontrolserver.controllers;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseController {
	private static Robot robot = null;
	
	/**
	 * initializes the robot object if it is not yet initialized
	 * @throws AWTException
	 */
	private static void initRobot() throws AWTException{
		if(robot == null){
			robot = new Robot();
		}
	}
	

	/**
	 * Triggers the left button click down event
	 * @throws AWTException
	 */
	public static void leftClickDown() throws AWTException{
		initRobot();
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	}
	

	/**
	 * Triggers the left button click up event
	 * @throws AWTException
	 */
	public static void leftClickUp() throws AWTException{
		initRobot();
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	

	/**
	 * Triggers the right button click down event
	 * @throws AWTException
	 */
	public static void rightClickDown() throws AWTException{
		initRobot();
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
	}
	
	/**
	 * Triggers the right button click up event
	 * @throws AWTException
	 */
	public static void rightClickUp() throws AWTException{
		initRobot();
		robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}
	
	/**
	 * @param position - sets the position of the mouse on the screen
	 * @throws AWTException
	 */
	public static void setMousePosition(Point position) throws AWTException{
		initRobot();
		robot.mouseMove(position.x, position.y);
	}
	
	/**
	 * @return - the current position of the mouse
	 * @throws AWTException
	 */
	public static Point getCurrentMousePosition() throws AWTException{
		return MouseInfo.getPointerInfo().getLocation();
	}
	
	private static float verticalAmount = 0;
	
	public static void scrollVertically(int amount) throws AWTException{
		initRobot();
		verticalAmount += amount * 1.0f / 20;
		if(verticalAmount > 1 || verticalAmount < 1){
			robot.mouseWheel((int)verticalAmount);
			verticalAmount %= 1;
		}
	}
	
	public static void scrollHorizontally(int amount){
		//TODO: implement; may be impossible
	}
	
	public static void stopScrolling(){
		//TODO: implement if needed
	}
	
	public static void scrollWithInitialVelocity(float velocityX, float velocityY){
		//TODO: implement
	}
}
