package Temple;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TriangleStripArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;


/**
 * Classe per istanziare cilindri di vari tipi. E' molto utile specialmente per le classi Fusto ed Echino.
 * E' molto parametrizzabile: si possono specificare il numero di punti con cui campionare le due 
 * circonferenze, il raggio della circonferenza di base, il raggio di quella superiore, ed infine
 * l'altezza del cilindro.
 * 
 * Si noti come le classe che estendono questa classe possano comunque fare un Override di tutti questi
 * metodi, e quindi implementare la propria politica per la loro Appearance, per esempio.
 * 
 * @author Luca Geatti
 * 
 * @see Fusto, Echino
 */
public class CilindroEstendibile extends Shape3D{
	
	private TriangleStripArray strips;
	private Point3f[] points;
	private Color3f color;
	private Texture texture;
	
	public CilindroEstendibile(int n, float raggioInf, float raggioSup , float altezza, Color3f color){
		//Geometry
		Geometry geom = createGeometry( n , raggioInf, raggioSup , altezza);
		setGeometry( geom );
		
		//Normals
		Geometry normals = createNormals();
		setGeometry(normals);
		
		//Appearance
		this.color = color;
		Appearance appear = createAppearance();
		setAppearance(appear);
	}
	
	public CilindroEstendibile(int n, float raggioInf, float raggioSup , float altezza, Texture texture){
		//Geometry
		Geometry geom = createGeometry( n , raggioInf, raggioSup , altezza);
		setGeometry( geom );
		
		//Normals
		Geometry normals = createNormals();
		setGeometry(normals);
		
		//Appearance
		this.texture = texture;
		Appearance appear = createAppearance();
		setAppearance(appear);
	}
	
	/*
	 * Crea la geometria per il cilindro estendibile. 
	 */
	private Geometry createGeometry(int n, float raggioInf, float raggioSup , float altezza){
		points = new Point3f[(n+1)*2];
		int[] stripCount = {(n+1)*2};
		strips = new TriangleStripArray( (n+1)*2 , GeometryArray.COORDINATES, stripCount );
		for(int i=0; i<n; i++){
			float angle = (float) (2.0f*Math.PI*i)/(float)n;
			
			float x = (float) (Math.sin(angle)*raggioInf);
			float y = (float) (Math.cos(angle)*raggioInf);
			
			float z = (float) (Math.sin(angle)*raggioSup);
			float w = (float) (Math.cos(angle)*raggioSup);
			
			points[i*2+0] = new Point3f( x , y , 0 );
			points[i*2+1] = new Point3f( z , w , altezza );
		}
		points[2*n+0] = new Point3f(0.0f , raggioInf , 0.0f);
		points[2*n+1] = new Point3f(0.0f , raggioSup , altezza);
		strips.setCoordinates(0, points);
		return strips;
	}
	
	
	/*
	 * Metodo per crere le normali del cilindro.
	 * In questo caso, l'utility NormalGenerator restituisce il risultato corretto, quindi posso usarla, 
	 * invece di definire a mano le normali.
	 */
	private Geometry createNormals(){
		GeometryInfo geomInfo = new GeometryInfo(strips);
		NormalGenerator gen = new NormalGenerator();
		gen.generateNormals(geomInfo);
		Geometry geom = geomInfo.getGeometryArray();
		return geom;
	}
	
	
	/*
	 * Crea l'appearance dell'oggetto. Si può scegliere, commentando in modo esclusivo una tra la seconda
	 * e la terza riga, di rappresentare l'oggetto solo con linee, oppure con le facce e le loro
	 * superfici.
	 */
	private Appearance createAppearance(){
		Appearance appear = new Appearance();
		//onlyLines(appear);
		material(appear);
		return appear;
	}
	
	
	private void onlyLines(Appearance appear){
		PolygonAttributes polyAttributes = new PolygonAttributes();
		//Impostazione aspetto wireframe
		polyAttributes.setPolygonMode(PolygonAttributes.POLYGON_LINE);//Solo linee
		//Indica di renderizzare tutti i poligoni
		polyAttributes.setCullFace(PolygonAttributes.CULL_NONE);//Disabilita il taglio di qualsiasi faccia
		appear.setPolygonAttributes(polyAttributes);
	}
	
	
	private void material(Appearance appear){
		if(texture == null){
			Material material = new Material(color, color, color, color , 20f);
			appear.setMaterial(material);
		}else{
			Material material = new Material();
			appear.setMaterial(material);
			//Definisco alcuni attributi della texture
			TextureAttributes texAtt = new TextureAttributes();
			texAtt.setTextureMode(TextureAttributes.MODULATE);
			//Applico le modifiche
			appear.setTexture(texture);
			appear.setTextureAttributes(texAtt);
		}
	}
	
}
