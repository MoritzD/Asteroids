package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.loaders.wavefront.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.still.StillModel;


/**
 * Created by Alexander on 30/10/2014.
 */

public class Spaceship {
    StillModel model;
    Point3D position;
    Texture tex;



    Spaceship(float x, float y, float z ){
        position = new Point3D(x,y,z);
        ObjLoader loader2 = new ObjLoader();

        model = loader2.loadObj(Gdx.files.internal("Spaceship/CityPatrolVehicle.obj"), true);

        tex = new Texture(Gdx.files.internal("Textures/Red-Digital-Camo1024.png"));//Spaceship/CityPatrolVehicle/Maps/ed1a.png"));

    }
public void drawSpaceship(){

    Gdx.gl11.glPushMatrix();
    Gdx.gl11.glTranslatef(position.x, position.y, position.z);
    Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
    Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

    //tex2.bind();
    tex.bind();
    Gdx.gl11.glScalef(0.005f,0.005f,0.005f);
    Gdx.gl11.glRotatef(180, 0, 1, 0);
    model.render();

    Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
    Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
    Gdx.gl11.glPopMatrix();

}




}
