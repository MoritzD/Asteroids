package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.loaders.wavefront.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.still.StillModel;


/**
 * Created by Alexander on 30/10/2014.
 */

public class Spaceship {
    StillModel model;
    Point3D position;


    Spaceship(float x, float y, float z , ObjLoader loader){
        position = new Point3D(x,y,z);

        model = loader.loadObj(Gdx.files.internal("Spaceship/CityPatrolVehicle/CityPatrolVehicle.obj"), true);

    }

public void createSpaceship()

    {




    }
public void drawSpaceship(){

    Gdx.gl11.glPushMatrix();
    Gdx.gl11.glPushMatrix();
    Gdx.gl11.glTranslatef(position.x, position.y, position.z);
    Gdx.gl11.glRotatef(180, 0, 1, 0);
    model.render();
    Gdx.gl11.glPopMatrix();
}




}
