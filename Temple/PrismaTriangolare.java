package Temple;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

/**
 * Classe per istanziare oggetti che rappresentano un Prisma Triangolare.
 * 
 * @author Luca Geatti
 *
 */
public class PrismaTriangolare extends Shape3D{
	
	private IndexedTriangleArray faces;
	private Color3f color;
	private Texture texture;
	
	/**
	 * 
	 * @param h altezza
	 * @param l larghezza
	 * @param p profondità
	 * @param color colore
	 */
	public PrismaTriangolare(float h, float l, float p, Color3f color){
		//Geometry
		Geometry geom = createGeometry( h , l , p );
		setGeometry(geom);
		
		//Appearance
		this.color = color;
		Appearance appear = createAppearance();
		setAppearance(appear);
	}
	
	public PrismaTriangolare(float h, float l, float p, Texture texture){
		//Geometry
		Geometry geom = createGeometry( h , l , p );
		setGeometry(geom);
				
		//Appearance
		this.texture = texture;
		Appearance appear = createAppearance();
		setAppearance(appear);
	}
	
	
	/*
	 * Creo la geometria e le normali.
	 */
	public Geometry createGeometry(float h, float largh, float p){
		float l = largh/2.0f;
		
		Point3f p0 = new Point3f( -l , 0.0f , 0.0f );
		Point3f p1 = new Point3f( 0.0f , h , 0.0f );
		Point3f p2 = new Point3f( l , 0.0f , 0.0f );
		Point3f p3 = new Point3f( -l , 0.0f , -p );
		Point3f p4 = new Point3f( 0.0f , h , -p );
		Point3f p5 = new Point3f( l , 0.0f , -p );
		
		Point3f[] points = { p0 , p1 , p2 , p3 , p4 , p5 };
		int[] indiciV = {
				2,1,0,
				3,4,5,
				5,4,1,
				2,5,1,
				3,0,1,
				4,3,1,
				0,3,2,
				2,3,5,
		};
		
		Vector3f v0 = new Vector3f( 1.0f , 1.0f , 0.0f );
		Vector3f v1 = new Vector3f( -1.0f , 1.0f , 0.0f );
		Vector3f v2 = new Vector3f( 0.0f , 1.0f , 0.0f );
		Vector3f v3 = new Vector3f( 0.0f , -1.0f , 0.0f );
		Vector3f v4 = new Vector3f( 0.0f , 0.0f , 1.0f );
		Vector3f v5 = new Vector3f( 0.0f , 0.0f , -1.0f );
		
		Vector3f[] normals = {v0 , v1 , v2 , v3 , v4 , v5 };
		int[] indiciN = {
				4,4,4,
				5,5,5,
				0,0,0,
				0,0,0,
				1,1,1,
				1,1,1,
				3,3,3,
				3,3,3
		};
		
		//points.length = 24 = numero di vertici
		faces = new IndexedTriangleArray( 6, GeometryArray.COORDINATES | GeometryArray.NORMALS, 24  );
		
		faces.setCoordinates(0, points);
		faces.setCoordinateIndices(0, indiciV);
		
		faces.setNormals(0, normals);
		faces.setNormalIndices(0, indiciN);
		
		return faces;
	}
	
	
	/*
	 * Creazione dell'appearance.
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
