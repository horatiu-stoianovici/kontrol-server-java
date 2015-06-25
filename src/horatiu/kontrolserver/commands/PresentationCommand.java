package horatiu.kontrolserver.commands;

import horatiu.kontrolserver.components.Request;
import horatiu.kontrolserver.components.Response;
import horatiu.kontrolserver.components.Response.TcpStatusCodes;
import static java.awt.event.KeyEvent.*;

import java.awt.AWTException;
import java.awt.Robot;

public class PresentationCommand implements Command {

	public Response handleRequest(Request request) {
		int control = Integer.parseInt(request.getParameters().get(0));
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Response(TcpStatusCodes.WrongFormat);
		}
		switch (control)
        {
            //start the presentation with F5
            case 0:
                robot.keyPress(VK_F5);
                break;

            //stop the presentation with ESC
            case 1:
                robot.keyPress(VK_ESCAPE);
                break;

            //next slide is arrow right
            case 2:
            	robot.keyPress(VK_RIGHT);
                break;

            //previous is arrow left
            case 3:
                robot.keyPress(VK_LEFT);
                break;

            default:
                break;
        }
		return new Response(TcpStatusCodes.Ok);
	}

	public String getCommandName() {
		// TODO Auto-generated method stub
		return "presentation";
	}

}
