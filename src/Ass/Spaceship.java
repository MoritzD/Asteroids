package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import org.lwjgl.opengl.GL11;


/**
 * Created by Alexander on 30/10/2014.
 */

public class Spaceship {
    Model model;
    Mesh mesh_nave;
    Point3D position;
    ModelBatch modelBatch;
    Environment environment;
    ModelInstance instance;


    Spaceship(float x, float y, float z ){
        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        position = new Point3D(x,y,z);
        ObjLoader loader2 = new ObjLoader();


       model = loader2.loadModel(Gdx.files.internal("Spaceship/CityPatrolVehicle/CityPatrolVehicle.obj"), true);      //Gdx.files.internal("Spaceship/CityPatrolVehicle/City_Patrol_Vehicle.mtl"),
       instance = new ModelInstance(model);

       // ModelLoader loader = new ObjLoader();
        //model = loader.loadModel(Gdx.files.internal("Spaceship/CityPatrolVehicle/CityPatrolVehicle.obj"));
        //mesh_nave = model.meshes.get(0);
       // ModelLoader loader = new ObjLoader();




       // model = loader.load(Gdx.files.internal("data/ship.obj"), ModelLoaderHints());
        //instance = new ModelInstance(model);

    }

public void createSpaceship()

    {




    }
public void drawSpaceship(){

    GL11.glPushMatrix();
    GL11.glTranslatef(position.x, position.y, position.z);
    GL11.glPushMatrix();
    GL11.glScalef(0.005f, 0.005f, 0.005f);
    GL11.glRotatef(180, 0, 1, 0);
    modelBatch.render(instance, environment);
    GL11.glPopMatrix();
    GL11.glPopMatrix();
}




}
