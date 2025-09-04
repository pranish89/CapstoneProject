package utilities;


	import java.time.Duration;
	import java.util.Collections;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.Point;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Pause;
	import org.openqa.selenium.interactions.PointerInput;
	import org.openqa.selenium.interactions.Sequence;
	import io.appium.java_client.android.AndroidDriver;
	
	public class Tap {
		
		public static void tap (AndroidDriver driver, WebElement element) {
	        Point location = element.getLocation();
	        Dimension size = element.getSize();
	        Point centerOfElement = getCenterOfElement(location, size);
	        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
	        Sequence sequence = new Sequence(finger1, 1)
	                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
	                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	                .addAction(new Pause(finger1, Duration.ofMillis(200)))
	                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	       
	        driver.perform(Collections.singletonList(sequence));
	}
		
		  public static Point getCenterOfElement(Point location, Dimension size)
		    {
		    return new Point(location.getX() + size.getWidth()/2,
		            location.getY()+ size.getHeight()/2);
		    }

}
