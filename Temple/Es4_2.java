package Temple;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import Utils.*;

public class Es4_2 extends Applet{
	
	private Color3f col = new Color3f( 255.0f/255.0f , 158.0f/255.0f , 81.0f/255.0f );
	
	public Es4_2(){
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
		
		//Cambio la posizione e la direzione dell'osservatore. 
		changeViewTransform(simpleU);
	}
	
	//BranchGroup
	public BranchGroup createSceneGraph(){
		BranchGroup branchG = new BranchGroup();
		//Modifico la luce
		directionalLight(branchG);	
		//ambientLight(branchG);
		
		TransformGroup tg = createSubGraph();
		
		branchG.addChild(tg);
		return branchG;
	}
	
	//TransformGroup
	public TransformGroup createSubGraph(){
		TransformGroup tg = new TransformGroup();
		
		//Rotazione da applicare alla colonna dorica per osservarla in tutte le sue angolazioni
		Transform3D t1 = new Transform3D();
		t1.rotX(Math.PI/4);
		//tg.setTransform(t1);
		
		//Definisco tutte le grandezze per la colonna dorica e anche il suo colore: il colore
		//lo ho specificato negli attributi PRIVATE di cui sopra.
		tg.addChild( new ColonnaDorica( 100 , 0.3f , 0.8f , 2.0f , 0.2f , 0.2f , col) );
		
		return tg;
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
	
	
	
	public void directionalLight(BranchGroup bg){
		//Definisco la zona da illuminare
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d,0.0d,0.0d) , 10.0d);
		
		//Definisco la luce direzionale
		DirectionalLight lightD = new DirectionalLight();
		lightD.setInfluencingBounds(bounds);
		lightD.setDirection(new Vector3f( 0.0f , 0.0f , -1.0f ));
		lightD.setColor(new Color3f( 1.0f , 1.0f , 1.0f ));
		
		//Aggiunta al Branch Group
		bg.addChild(lightD);
	}
	
	

	public void ambientLight(BranchGroup bg){
		//Definisco la zona da illuminare
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0d,0.0d,0.0d) , 10.0d);
		
		//Definisco la luce ambientale
		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		lightA.setColor(new Color3f( 1.0f , 1.0f , 1.0f ));
		
		//Aggiunta al Branch Group
		bg.addChild(lightA);
	}
	
	
	
	public static void main(String[] args){
		new MainFrame(new Es4_2(), 1024, 768);
	}
	
}