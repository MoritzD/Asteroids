package Ass;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;


public class Assignment3Base implements ApplicationListener
{
    Camera camFirstPerson;
    Camera camTopDown;
    Camera camThirdPerson;


    @Override
    public void create() {
        // TODO Auto-generated method stub
        Gdx.gl11.glEnable(GL11.GL_LIGHTING);
        Gdx.gl11.glEnable(GL11.GL_LIGHT0);
        Gdx.gl11.glEnable(GL11.GL_LIGHT1);
        Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);

        Gdx.gl11.glClearColor(0.5f, 0.0f, 0.0f, 1.0f);

        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        Gdx.gl11.glEnable(GL11.GL_NORMALIZE);


        camFirstPerson = new Camera();
        camFirstPerson.lookAt(new Point3D(0.0f, 1.0f, 2.0f), new Point3D(0.0f, 1.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
        camFirstPerson.perspective(90.0f, 1.777778f, 0.1f, 40.0f);

        camThirdPerson = new Camera();
        camThirdPerson.perspective(50.0f, 1.777778f, 0.01f, 40.0f);

        camTopDown = new Camera();
        camTopDown.perspective(40.0f, 1.777778f, 5.0f, 20.0f);





    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    private void update()
    {
        float deltaTime = Gdx.graphics.getDeltaTime();

    }

    private void display()
    {

    }

    @Override
    public void render()
    {
        update();
        display();
    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

}
