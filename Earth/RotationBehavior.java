package Earth;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedTime;

public class RotationBehavior extends Behavior{
	
	
	private TransformGroup targetTG;
	private Transform3D rotation = new Transform3D();
	private double angle=0.0;
	
	
	public RotationBehavior(TransformGroup tg){
		this.targetTG = tg;
	}
	
	public void initialize(){
		this.wakeupOn(new WakeupOnElapsedTime(50));
	}
	
	public void processStimulus(Enumeration criteria){
		boolean flag = false;
		while(criteria.hasMoreElements()){
			Object obj = criteria.nextElement();
			if(obj instanceof WakeupOnElapsedTime){
				flag = true;
			}
		}
		

		if( flag ){
			angle += 0.01;
			rotation.rotY(angle);
			targetTG.setTransform(rotation);
		}
		
		//Resetta il behavior per continuare a rispondere ad eventi della tastiera.
		this.wakeupOn(new WakeupOnElapsedTime(50));
		
	}
	
}
