package Temple;

import javax.media.j3d.Texture;
import javax.vecmath.Color3f;

/**
 * Classe per istanziare oggetti che raffigurano un Abaco. 
 * 
 * @author Luca Geatti
 * @see ParallelepipedoEstendibile
 * @see Architrave
 */
public class Abaco extends ParallelepipedoEstendibile{
	
	public Abaco(float lato, float altezza, Color3f color){
		super( lato , lato , altezza , color);
	}
	
	public Abaco(float lato, float altezza, Texture texture){
		super( lato , lato, altezza , texture);
	}
	
}
