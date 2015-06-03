package Earth;

import java.awt.Container;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;

public class Earth extends Shape3D {
	
	private TextureLoader loader;
	private Appearance appear;

	public Earth(float raggio) {
		//Carico la texture
		this.loader = new TextureLoader(
					"/Users/luca/Documents/workspace/EserciziJava3D/bin/earth2.jpg",
					new Container());
		//Appearance
		this.appear = new Appearance();
		
		// Texture
		Texture texture = loader.getTexture();
		appear.setTexture(texture);
		
		TextureAttributes texAtt = new TextureAttributes();
		// Attributo necessario per l'illuminazione: modula il colore dell'oggetto con il
		// colore della texure.
		texAtt.setTextureMode(TextureAttributes.MODULATE);
		appear.setTextureAttributes(texAtt);

		// Material: è necessario definire un materiale per l'oggetto se voglio illuminarlo.
		Material material = new Material();
		appear.setMaterial(material);

		// La prima costante serve per creare automaticamente le normali per l'illuminazione.
		// La seconda serve per generare automaticamente le coordinate su cui la
		// texture sarà mappata.
		Sphere sfera = new Sphere( raggio , Primitive.GENERATE_NORMALS
				| Primitive.GENERATE_TEXTURE_COORDS, 100, appear);
		
		setGeometry(sfera.getShape().getGeometry());
		setAppearance(sfera.getAppearance());
	}

}
