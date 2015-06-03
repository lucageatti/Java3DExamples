package Earth;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnAWTEvent;

public class RotationBehavior extends Behavior{
	
	
	private TransformGroup targetTG;
	private Transform3D rotation = new Transform3D();
	private Transform3D rotation2 = new Transform3D();
	private double angle=0.0;
	private double angle2=0.0;
	
	
	public RotationBehavior(TransformGroup tg){
		this.targetTG = tg;
	}
	
	public void initialize(){
		this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
	}
	
	public void processStimulus(Enumeration criteria){
		AWTEvent[] ev = null;
		while(criteria.hasMoreElements()){
			Object obj = criteria.nextElement();
			if(obj instanceof WakeupOnAWTEvent){
				ev = ((WakeupOnAWTEvent) obj).getAWTEvent();
			}
		}
		

		if(ev!=null){
			//Scorre tutti gli eventi AWT in cerca di un evento di tastiera
			for(int i=0; i<ev.length; i++){
				if(ev[i] instanceof KeyEvent){
					//recupera l'evento
					KeyEvent key = (KeyEvent) ev[i];
					int code = key.getKeyCode();
					if(code == KeyEvent.VK_LEFT){
						angle -= 0.1;
						if(angle<=0){
							angle = 2*Math.PI;
						}
					}else if(code == KeyEvent.VK_RIGHT){
						angle += 0.1;
						if(angle >=Math.PI*2){
							angle = 0;
						}
					}else if(code == KeyEvent.VK_UP){
						angle2 -= 0.1;
						if(angle2<=0){
							angle2=Math.PI*2;
						}
					}else if(code == KeyEvent.VK_DOWN){
						angle2 += 0.1;
						if(angle2>=Math.PI*2){
							angle2 = 0;
						}
					}
					
				}
			}
		}
		
		rotation.rotY(angle);
		rotation2.rotX(angle2);
		rotation.mul(rotation2);
		
		targetTG.setTransform(rotation);
		
		//Resetta il behavior per continuare a rispondere ad eventi della tastiera.
		this.wakeupOn(new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED));
		
	}
	
}
