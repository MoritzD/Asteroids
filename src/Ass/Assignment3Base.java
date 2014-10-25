package Ass;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;


public class Assignment3Base implements ApplicationListener
{
    Camera camFirstPerson;
    Camera camTopDown;
    Camera camThirdPerson;
    Hexagon hex;
    Hexagon hex2;
    Hexagon hex3;
    Pentagon pen;
    Sphere planetEarth;
    Dodecahedron dodo;
    Box bo;
    private float rotationAngle;

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
        camFirstPerson.lookAt(new Point3D(0.0f, 0.0f, 1.0f), new Point3D(0.0f, 0.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
        camFirstPerson.perspective(90.0f, 1.777778f, 0.1f, 40.0f);

        camThirdPerson = new Camera();
        camThirdPerson.perspective(50.0f, 1.777778f, 0.01f, 40.0f);

        camTopDown = new Camera();
        camTopDown.perspective(40.0f, 1.777778f, 5.0f, 20.0f);

        hex = new Hexagon();
        hex2 = new Hexagon();
        hex3 = new Hexagon();
        pen = new Pentagon();
        dodo = new Dodecahedron();
        planetEarth = new Sphere(12, 24);
        planetEarth.setTexture("Textures/earth_texture1024x512.png");
        planetEarth.setPosition(0,0,-30);
        planetEarth.scale(10,10,10);


        bo=new Box(new Point3D(0,0,0),new Vector3D(1.0f,1.0f,1.0f),new float[] {1.0f,1.0f,0.0f,0.0f},false);







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
        float deltaTime = Gdx.graphics.getDeltaTime()*0.5f;
        rotationAngle += 90.0f * deltaTime;

        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            camFirstPerson.pitch(-90.0f * deltaTime);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            camFirstPerson.pitch(90.0f * deltaTime);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            camFirstPerson.yaw(-90.0f * deltaTime);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            camFirstPerson.yaw(90.0f * deltaTime);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            camFirstPerson.slide(0.0f, 0.0f, -10.0f * deltaTime);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            camFirstPerson.slide(0.0f, 0.0f, 10.0f * deltaTime);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            camFirstPerson.slide(-10.0f * deltaTime, 0.0f, 0.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            camFirstPerson.slide(10.0f * deltaTime, 0.0f, 0.0f);
        }


        if(Gdx.input.isKeyPressed(Input.Keys.R))
        {
            camFirstPerson.slide(0.0f, 10.0f * deltaTime, 0.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F))
        {
            camFirstPerson.slide(0.0f, -10.0f * deltaTime, 0.0f);
        }

    }

    private void display()
    {
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

        camFirstPerson.setMatrices();
        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse, 0);

        float[] lightPosition = {5.0f, 10.0f, 15.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);

        float[] lightDiffuse1 = {0.5f, 1.0f, 0.5f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse1, 0);

        float[] lightPosition1 = {-5.0f, 10.0f, -13.0f, 0.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition1, 0);

        //bo.draw();

        Gdx.gl11.glPushMatrix();
        float[] materialDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);
        Gdx.gl11.glTranslatef(0.0f, 0.0f, -2.0f);
        //Gdx.gl11.glScalef(0.1f,0.1f,0.1f);
        //hex.draw();
        Gdx.gl11.glPopMatrix();

        Gdx.gl11.glPushMatrix();
        planetEarth.setRotationAngle(rotationAngle);
        planetEarth.draw();
        Gdx.gl11.glPopMatrix();

        //Gdx.gl11.glScalef(5.0f,0.5f,1.0f);
       // pen.draw();
        dodo.draw();



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
