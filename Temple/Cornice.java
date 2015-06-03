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
 * Classe per istanziare oggetti che rappresentano una Cornice di un tempio.
 * E' un prisma triangolare, ed infatti estende l'omonima classe.
 * 
 * @author Luca Geatti
 *
 */
public class Cornice extends PrismaTriangolare{
	
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
	public Cornice(float h, float l, float p, Color3f color){
		super( h , l , p , color);
	}
	
	public Cornice(float h, float l, float p, Texture texture){
		super( h , l , p , texture);
	}
	
	
}
