package Temple;

import javax.media.j3d.Texture;
import javax.vecmath.Color3f;

/**
 *  Classe per istanziare oggetti di tipo Architrave. Sfrutta la classe "ParallelepipedoEstendibile"
 *  (come "Abaco"), quindi possiamo vedere l'architrave come una specializzazione di un parallelepipedo.
 *  
 * @author Luca Geatti
 * @see ParallelepipedoEstendibile
 * @see Abaco
 */
public class Architrave extends ParallelepipedoEstendibile{
	
	public Architrave(float larghezza, float lunghezza , float altezza, Color3f color){
		super( larghezza, lunghezza , altezza , color);
	}
	
	public Architrave(float larghezza, float lunghezza , float altezza, Texture texture){
		super( larghezza, lunghezza , altezza , texture);
	}
}
