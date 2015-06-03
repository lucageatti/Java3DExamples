package Temple;

import javax.media.j3d.Group;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Facciata extends Group{
	
	private ColonnaDorica[] colonne = new ColonnaDorica[6];
	private TransformGroup[] tgsColonne = new TransformGroup[6];
	
	private Architrave architrave;
	private TransformGroup tgArch = new TransformGroup();
	
	private Cornice cornice;
	private TransformGroup tgCornice = new TransformGroup();
	
	public Facciata(Texture texture){
		
		//Applico una sola trasformazione a cascata.
		Transform3D t3d = new Transform3D();
		t3d.setTranslation(new Vector3d( 1.0d , 0.0d , 0.0d ));
		
		for(int i=0; i<colonne.length; i++){
			colonne[i] = new ColonnaDorica(100 , 0.3f , 0.8f , 2.0f , 0.2f , 0.2f , texture);
			tgsColonne[i] = new TransformGroup();
			tgsColonne[i].addChild(colonne[i]);
			tgsColonne[i].setTransform(t3d);
			if( i>0 ){
				tgsColonne[i-1].addChild(tgsColonne[i]);
			}
		}
		//Aggiungo SOLO il primo TransformGroup della catena
		addChild(tgsColonne[0]);
		
		//Aggiungo l'architrave
		architrave = new Architrave(6.55f , 1.2f , 0.5f , texture);
		tgArch.addChild(architrave);
		Transform3D transArch = new Transform3D();
		transArch.setTranslation(new Vector3f( 3.5f , 0.65f , 0.0f ));
		tgArch.setTransform(transArch);
		addChild(tgArch);
		
		//Aggiungo la cornice
		cornice = new Cornice(1.0f , 6.55f , 1.0f , texture);
		tgCornice.addChild(cornice);
		Transform3D transCornice = new Transform3D();
		transCornice.setTranslation(new Vector3f( 3.5f , 0.9f , 0.5f ));
		tgCornice.setTransform(transCornice);
		addChild(tgCornice);
	}
	
	
	
}
