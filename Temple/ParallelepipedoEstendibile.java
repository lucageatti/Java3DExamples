package Temple;


import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 * Classe per istanziare oggetti che raffigurano un Abaco. 
 * 
 * @author Luca Geatti
 * @see Abaco, Architrave
 */
public class ParallelepipedoEstendibile extends Shape3D{
	
	private IndexedQuadArray faces;
	private Color3f color;
	private Texture texture;
	
	public ParallelepipedoEstendibile(float larghezza, float lunghezza , float altezza, Color3f color){
		//Geometry
		Geometry geom = createGeometry(larghezza, lunghezza , altezza);
		setGeometry(geom);
		
		//Appearance
		this.color = color;
		Appearance appear = createAppearance();
		setAppearance(appear);
	}
	
	public ParallelepipedoEstendibile(float larghezza, float lunghezza , float altezza, Texture texture){
		//Geometry
		Geometry geom = createGeometry(larghezza, lunghezza , altezza);
		setGeometry(geom);
		
		//Appearance
		this.texture = texture;
		Appearance appear = createAppearance();
		setAppearance(appear);
	}
	
	private Geometry createGeometry(float larghezza, float lunghezza, float altezza){
		float delta = larghezza/2.0f;
		float gamma = altezza/2.0f;
		float eps = lunghezza/2.0f;
		
		Point3f p1 = new Point3f( -delta , gamma , eps );
		Point3f p2 = new Point3f( -delta , -gamma , eps );
		Point3f p3 = new Point3f( delta , -gamma , eps );
		Point3f p4 = new Point3f( delta , gamma , eps );
		Point3f p5 = new Point3f( -delta , gamma , -eps );
		Point3f p6 = new Point3f( -delta , -gamma , -eps );
		Point3f p7 = new Point3f( delta , -gamma , -eps );
		Point3f p8 = new Point3f( delta , gamma , -eps );
		
		Point3f[] points = {p1, p2, p3, p4, p5, p6, p7, p8};
		
		faces = new IndexedQuadArray( 8, GeometryArray.COORDINATES | GeometryArray.NORMALS , 24);
		int[] coordInd = { 0,1,2,3,
						   4,5,6,7,
						   0,3,7,4,
						   5,6,2,1,
						   0,4,5,1,
						   6,7,3,2};
		faces.setCoordinates(0, points);
		faces.setCoordinateIndices(0, coordInd);
		
		//Normals
		createNormals(faces);
		
		return faces;
	}
	
	/*
	 * Creo le normali manualmente, perchè l'utility NormalsGenerator non resituisce il
	 * risultato desiderato.
	 */
	private void createNormals(IndexedQuadArray faces){
		Vector3f v1 = new Vector3f( 0.0f , 0.0f , 1.0f );//0
		Vector3f v2 = new Vector3f( 0.0f , 0.0f , -1.0f );//1
		Vector3f v3 = new Vector3f( 1.0f , 0.0f , 0.0f );//2
		Vector3f v4 = new Vector3f( -1.0f , 0.0f , 0.0f );//3
		Vector3f v5 = new Vector3f( 0.0f , 1.0f , 0.0f );//4
		Vector3f v6 = new Vector3f( 0.0f , -1.0f , 0.0f );//5
		
		Vector3f[] normals = { v1,v2,v3,v4,v5,v6 };
		
		int[] coordInd = {0,0,0,0,
						   1,1,1,1,
						   4,4,4,4,
						   5,5,5,5,
						   3,3,3,3,
						   2,2,2,2};
		faces.setNormals( 0 , normals);
		faces.setNormalIndices(0, coordInd);
	}
	

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
