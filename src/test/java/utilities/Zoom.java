package utilities;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.android.AndroidDriver;

public class Zoom {

	public static void zoomin(AndroidDriver driver, WebElement element) throws InterruptedException
	{
		Thread.sleep(5000);
	    element.click();
	    Thread.sleep(3000);
	   
		
			Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());
			PointerInput finger1 = new PointerInput (PointerInput.Kind. TOUCH, "finger1");
			PointerInput finger2 = new PointerInput (PointerInput.Kind. TOUCH, "finger2");
			Sequence sequence = new Sequence(finger1,1)
			.addAction(finger1.createPointerMove (Duration. ZERO, PointerInput. Origin.viewport(), centerOfElement))
			.addAction (finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			.addAction(new Pause (finger1, Duration.ofMillis(288)))
			.addAction(finger1.createPointerMove (Duration.ofMillis(208),
			PointerInput.Origin.viewport(),
			centerOfElement.getX() + 100,
			centerOfElement.getY()- 100))
			.addAction (finger1.createPointerUp (PointerInput.MouseButton.LEFT.asArg()));
			Sequence sequence2 = new Sequence(finger2,1)
			.addAction (finger2.createPointerMove(Duration.ZERO, PointerInput. Origin.viewport(), centerOfElement))
			.addAction (finger2.createPointerDown(PointerInput.MouseButton. LEFT.asArg()))
			.addAction(new Pause (finger2, Duration.ofMillis(200)))
			.addAction (finger2.createPointerMove(Duration.ofMillis(200),
			PointerInput.Origin.viewport(),
			centerOfElement.getX()-100,
			centerOfElement.getY()+ 100))
			.addAction (finger2.createPointerUp(PointerInput. MouseButton.LEFT.asArg()));
			driver.perform(Arrays.asList(sequence,sequence2));

			}
			    private static Point getCenterOfElement(Point location, Dimension size)
			    {
			    return new Point(location.getX() + size.getWidth()/2,
			            location.getY()+ size.getHeight()/2);
			    }
			    }
