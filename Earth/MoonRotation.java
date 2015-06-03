package Earth;

import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnElapsedTime;
import javax.vecmath.Vector3f;

/**
 * Classe per definire il moto di rivoluzione della luna intorno alla terra.
 * 
 * @author Luca Geatti <geatti.luca@spes.uniud.it>
 *
 */
public class MoonRotation extends Behavior{
	
	private TransformGroup tg;
	private Transform3D t;
	private float x = 0.0f;
	private float z = 0.0f;
	private float angle = 0.0f;
	private float radius;
	
	public MoonRotation(TransformGroup tg, float radius){
		this.tg = tg;
		this.t = new Transform3D();
		this.radius = radius;
	}
	
	public void initialize(){
		this.wakeupOn(new WakeupOnElapsedTime(100));
	}
	
	public void processStimulus(Enumeration criteria){
		boolean flag = false;
		while(criteria.hasMoreElements()){
			Object obj = criteria.nextElement();
			if(obj instanceof WakeupOnElapsedTime){
				flag = true;
			}
		}
		
		if(flag){
			angle += 0.1f;
			x = (float) (Math.sin(angle)*radius);
			z = (float) (Math.cos(angle)*radius);
			t.setTranslation(new Vector3f( x, 0.0f , z ));
			tg.setTransform(t);
		}
		
		//Resetta il behavior per continuare a rispondere ad eventi della tastiera.
		this.wakeupOn(new WakeupOnElapsedTime(100));
		
	}
	
}
