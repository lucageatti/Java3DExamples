package Earth;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * 
 * @author Luca Geatti
 *
 */
public class Esempio5 extends Applet{
	
	public Esempio5(){
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);
		add("Center", canvas);
		
		//BranchGroup
		BranchGroup scene = createSceneGraph();
		scene.compile();
		
		//SimpleUniverse
		SimpleUniverse simpleU = new SimpleUniverse(canvas);
		simpleU.getViewingPlatform().setNominalViewingTransform();
		simpleU.addBranchGraph(scene);
		
		//Cambio la posizione e la direzione dell'osservatore
		changeViewTransform(simpleU);
	}
	
	//BranchGroup
	public BranchGroup createSceneGraph(){
		BranchGroup branchG = new BranchGroup();
		TransformGroup tg = createSubGraph();
		branchG.addChild(tg);
		//Aggiungo il behavior
		addBehavior(branchG, tg);
		
		//Modifico la luce
		//ambientLight(branchG);
		directionalLight(branchG);	
		
		//Aggiungo lo sfondo: l'oggetto background è di tipo Leaf, per cui posso
		//aggiungerlo solo al BG che può avere più figli
		Background back = addBackground();
		branchG.addChild(back);
		
		return branchG;
	}
	
	//TransformGroup
	public TransformGroup createSubGraph(){
		TransformGroup tg1 = new TransformGroup();
		
		Earth earth = new Earth(1.0f);
		tg1.addChild(earth);
		
		return tg1;
	}
	
	
	
	
	/*
	 * Aggiungo il behavior al BranchGroup del primo parametro, che fa riferimento al
	 * TransformGroup del secondo parametro.
	 */
	private void addBehavior(BranchGroup bg, TransformGroup tg){
		//Setto le capability della terra
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		RotationBehavior rotation = new RotationBehavior(tg);
		
		BoundingSphere bounds = new BoundingSphere();//Costruttore di default
		rotation.setSchedulingBounds(bounds);
		
		bg.addChild(rotation);
	}
	
	
	
	/*
	 * Definisco un background.
	 */
	private Background addBackground(){
		//Carico l'immagine
		TextureLoader loader = new TextureLoader(
				"/Users/luca/Documents/workspace/EserciziJava3D/bin/space1.jpg", this);
		ImageComponent2D image = loader.getImage();
		//L'oggetto myBack è di tipo Leaf: lo aggiungerò al BranchGroup
		Background myBack = new Background();
		myBack.setImage(image);
		//L'immagine si adatta per ricoprire l'intera finestra.
		myBack.setImageScaleMode(Background.SCALE_FIT_MAX);
		//Definisco una regione di influenza, in questo caso molto grande.
		BoundingSphere myBounds = new BoundingSphere(new Point3d( ), 1000.0 ); 
		myBack.setApplicationBounds( myBounds );
		return myBack;
	}
	
	
	//Change View Transform : tutte le trasformazioni sono equivalenti all'inverso di quelle
	//sul Content Branch.
	public void changeViewTransform(SimpleUniverse simpleU){
		Transform3D t2 = new Transform3D();
		t2.lookAt(new Point3d(0.0d , 0.0d , 10.0d), new Point3d(0.0d,0.0d,0.0d), new Vector3d(0.0d,1.0d,0.0d));
		t2.invert();
		
		TransformGroup vtg = simpleU.getViewingPlatform().getViewPlatformTransform();
		vtg.setTransform(t2);
	}
	
	
	
	public void ambientLight(BranchGroup bg){
		//Definisco la zona da illuminare
		BoundingSphere bounds = new BoundingSphere( new Point3d(0.0d,0.0d,0.0d) , 10.0d );
		
		//Definisco la luce (in questo caso ambientale)
		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		Color3f green = new Color3f(1.0f, 1.0f, 1.0f);
		lightA.setColor(green);
		
		bg.addChild(lightA);
	}
	
	
	/*
	 * Definisco una luce direzionale che proviene dall'alto a destra, e che quindi punta
	 * in basso a sinistra (e in avanti) rispetto all'osservatore.
	 */
	public void directionalLight(BranchGroup bg){
		//Definisco la zona da illuminare
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d,0.0d,0.0d) , 1000.0d);
		
		//Definisco la luce direzionale
		DirectionalLight lightD = new DirectionalLight();
		lightD.setInfluencingBounds(bounds);
		lightD.setDirection(new Vector3f( 0.0f , 0.0f , -1.0f ));
		lightD.setColor(new Color3f( 1.0f , 1.0f , 1.0f ));
		
		//Aggiunta al Branch Group
		bg.addChild(lightD);
	}
	
	
	
	public static void main(String[] args){
		new MainFrame(new Esempio5(), 1024, 768);
	}
	
}