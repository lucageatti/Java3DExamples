package Temple;

import javax.media.j3d.Texture;
import javax.vecmath.Color3f;

/**
 * Classe per istanzione un Echino di una colonna.
 * 
 * @author Luca Geatti
 *
 */
public class Echino extends CilindroEstendibile{
	
	public Echino(int n , float raggioInf, float raggioSup, float altezza, Color3f color){
		super( n , raggioInf , raggioSup , altezza , color);
	}
	
	

	public Echino(int n , float raggioInf, float raggioSup, float altezza, Texture texture){
		super( n , raggioInf , raggioSup , altezza , texture);
	}
	
}
