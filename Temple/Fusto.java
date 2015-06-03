package Temple;

import javax.media.j3d.Texture;
import javax.vecmath.Color3f;


/**
 * Classe per istanziare un fusto di colonna Dorica. Si possono specificare il raggio della base,
 * l'altezza del fusto, e il numero di punti campionati: maggiore è questo numero, più definite
 * saranno le circonferenze di base e di soffitto.
 * 
 * E' una specializzazione della classe CilindroEstendibile: in particolare, è un cilindro estendibile
 * che ha raggio uguale sia per la circonferenza di base sia per quella superiore.
 * 
 * @author Luca Geatti
 *
 */
public class Fusto extends CilindroEstendibile{
	
	public Fusto(int n, float raggio, float altezza, Color3f color){
		super( n , raggio , raggio , altezza , color);
	}
	

	public Fusto(int n, float raggio, float altezza, Texture texture){
		super( n , raggio , raggio , altezza , texture);
	}
	
	
	
}
