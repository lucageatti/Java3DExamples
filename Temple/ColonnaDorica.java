package Temple;

import javax.media.j3d.Group;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 * Classe per istanziare una colonna di stile dorico.
 * 
 * @author Luca Geatti
 *
 */
public class ColonnaDorica extends Group {

	private Fusto fusto;
	private Echino echino;
	private Abaco abaco;

	/**
	 * 
	 * @param n
	 *            numero di punti interpolati per la mia geometria
	 * @param raggioInf
	 *            lunghezza del raggio della circonferenza di base
	 * @param raggioSup
	 *            lunghezza del raggio della circonferenza superiore
	 * @param altezzaFusto
	 *            altezza del fusto della colonna
	 * @param altezzaEchino
	 *            altezza dell'echino della colonna
	 * @param altezzaAbaco
	 *            altezza dell'abaco della colonna
	 * @param color
	 *            colore di tutta la colonna dorica
	 */
	public ColonnaDorica(int n, float raggioInf, float raggioSup,
			float altezzaFusto, float altezzaEchino, float altezzaAbaco,
			Color3f color) {

		this.fusto = new Fusto(n, raggioInf, altezzaFusto, color);
		this.echino = new Echino(n, raggioInf, raggioSup, altezzaEchino, color);
		this.abaco = new Abaco(raggioSup * 2, altezzaAbaco, color);

		TransformGroup tgFusto = new TransformGroup();
		Transform3D t3dFusto = new Transform3D();
		t3dFusto.rotX(Math.PI / 2);
		tgFusto.setTransform(t3dFusto);
		tgFusto.addChild(fusto);

		TransformGroup tgEchino = new TransformGroup();
		Transform3D t3dEchino = new Transform3D();
		t3dEchino.rotX(Math.PI * (3.0f / 2.0f));
		tgEchino.setTransform(t3dEchino);
		tgEchino.addChild(echino);

		TransformGroup tgAbaco = new TransformGroup();
		Transform3D t3dAbaco = new Transform3D();
		t3dAbaco.setTranslation(new Vector3d(0.0d, altezzaEchino + 0.1d, 0.0d));
		tgAbaco.setTransform(t3dAbaco);
		tgAbaco.addChild(abaco);

		addChild(tgFusto);
		addChild(tgEchino);
		addChild(tgAbaco);

	}

	public ColonnaDorica(int n, float raggioInf, float raggioSup,
			float altezzaFusto, float altezzaEchino, float altezzaAbaco,
			Texture texture) {

		this.fusto = new Fusto(n, raggioInf, altezzaFusto, texture);
		this.echino = new Echino(n, raggioInf, raggioSup, altezzaEchino, texture);
		this.abaco = new Abaco(raggioSup * 2, altezzaAbaco, texture);

		TransformGroup tgFusto = new TransformGroup();
		Transform3D t3dFusto = new Transform3D();
		t3dFusto.rotX(Math.PI / 2);
		tgFusto.setTransform(t3dFusto);
		tgFusto.addChild(fusto);

		TransformGroup tgEchino = new TransformGroup();
		Transform3D t3dEchino = new Transform3D();
		t3dEchino.rotX(Math.PI * (3.0f / 2.0f));
		tgEchino.setTransform(t3dEchino);
		tgEchino.addChild(echino);

		TransformGroup tgAbaco = new TransformGroup();
		Transform3D t3dAbaco = new Transform3D();
		t3dAbaco.setTranslation(new Vector3d(0.0d, altezzaEchino + 0.1d, 0.0d));
		tgAbaco.setTransform(t3dAbaco);
		tgAbaco.addChild(abaco);

		addChild(tgFusto);
		addChild(tgEchino);
		addChild(tgAbaco);

	}
}
