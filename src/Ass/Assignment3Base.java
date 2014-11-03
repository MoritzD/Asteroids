package Ass;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
import java.nio.FloatBuffer;


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
    Dodecahedron dodo, bobo;
    Box bo;
    float acc = 0;
    private float rotationAngle;
    
    Spaceship space;
    Point3D position;


    PerspectiveCamera cam;
    CameraInputController camController;

    @Override
    public void create() {
        // TODO Auto-generated method stub
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_LIGHT1);
        GL11.glEnable(GL11.GL_LIGHT2);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        GL11.glEnable(GL11.GL_NORMALIZE);



        camFirstPerson = new Camera();
        camFirstPerson.lookAt(new Point3D(-1.0f, -2.0f, 8.0f), new Point3D(0.0f, 0.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
        camFirstPerson.perspective(90.0f, 1.777778f, 0.1f, 40.0f);

        camThirdPerson = new Camera();
        camThirdPerson.perspective(50.0f, 1.777778f, 0.01f, 40.0f);

        camTopDown = new Camera();
        camTopDown.perspective(40.0f, 1.777778f, 5.0f, 20.0f);

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(1f, 1f, 1f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        hex = new Hexagon();
        hex2 = new Hexagon();
        hex3 = new Hexagon();
        pen = new Pentagon();

        planetEarth = new Sphere(12, 24);
        planetEarth.setTexture("Textures/earth_texture1024x512.png");
        planetEarth.setPosition(0, 0, -30);
        planetEarth.scale(10,10,10);


        bo=new Box(new Point3D(0,0,0),new Vector3D(1.0f,1.0f,1.0f),new float[] {1.0f,1.0f,0.0f,0.0f},false);

        dodo = new Dodecahedron();
        bobo = new Dodecahedron();
        bobo.setCenter(5,5,5);
        dodo.setCenter(-5,-5,-5);
        bobo.setDirection(-1, -1, -1);
        dodo.setDirection(1,1,1);
        dodo.pen.setTex("Textures/pentagon4.png");
        GL11.glPushMatrix();
        space = new Spaceship(-5.0f,0.0f,0.f);
        GL11.glPopMatrix();

      //  ObjLoader loader = new ObjLoader();
      //  model = loader.loadObj(Gdx.files.internal("Spaceship/CityPatrolVehicle/CityPatrolVehicle.obj"), true);
//        bobo.setDirection(0,0,-1);
//        dodo.setDirection(1,1,0);
        bobo.setDirection(0,0,0);
        dodo.setDirection(0,0,0);






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
        bobo.movement();
        dodo.movement();






       // dodo.collision(bobo);
        // can't do this or it won't work
       bobo.collision(dodo);



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
        if(Gdx.input.isKeyPressed(Input.Keys.Q))
        {
            dodo.explodefactor = 0;
            dodo.explodeScaleFactor =  1;
            acc = dodo.distanceFaktor = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            dodo.explodefactor += 5 * deltaTime;
            //acc -= 20 * deltaTime;
            if(0 < dodo.explodeScaleFactor ) {
                dodo.explodeScaleFactor -= 2 * deltaTime;
                // Discard object!
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            dodo.distanceFaktor = - Math.abs(2 * (float) Math.sin(acc));
            acc += 2*deltaTime;
        }

        }

    private void display()
    {
        camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        //camFirstPerson.setMatrices();
        FloatBuffer lightDiffuse =  BufferUtils.newFloatBuffer(4).put(new float[] {1.0f, 1.0f, 1.0f, 1.0f});
        lightDiffuse.rewind();
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse);

        FloatBuffer lightPosition = BufferUtils.newFloatBuffer(4).put(new float[] {0.0f, 0.0f, 15.0f, 1.0f});
        lightPosition.rewind();
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition);

        FloatBuffer lightDiffuse1 = BufferUtils.newFloatBuffer(4).put(new float[] {1.0f, 1.0f, 1.0f, 1.0f});
        lightDiffuse1.rewind();
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse1);

        FloatBuffer lightPosition1 = BufferUtils.newFloatBuffer(4).put(new float[] {-5.0f, -10.0f, -13.0f, 0.0f});
        lightPosition1.rewind();
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition1);

        FloatBuffer lightDiffuse2 = BufferUtils.newFloatBuffer(4).put(new float[] {1.0f, 1.0f, 1.0f, 1.0f});
        lightDiffuse2.rewind();
        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, lightDiffuse2);

        FloatBuffer lightPosition2 = BufferUtils.newFloatBuffer(4).put(new float[] {5.0f, 10.0f, -5.0f, 0.0f});
        lightPosition2.rewind();
        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPosition2);
        space.drawSpaceship();
        //bo.draw();

        GL11.glPushMatrix();
        FloatBuffer materialDiffuse = BufferUtils.newFloatBuffer(4).put(new float[] {1.0f, 1.0f, 1.0f, 1.0f});
        materialDiffuse.rewind();
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse);
        GL11.glTranslatef(0.0f, 0.0f, -2.0f);
        //GL11.glScalef(0.1f,0.1f,0.1f);
        //hex.draw();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        planetEarth.setRotationAngle(rotationAngle);
        planetEarth.draw();
        GL11.glPopMatrix();

        //GL11.glScalef(5.0f,0.5f,1.0f);
       // pen.draw();
       // GL11.glScalef(10.0f,10.0f,10.0f);
        dodo.draw();
        bobo.draw();









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
    public void SpaceshipPos(float x, float y, float z ){

        position = new Point3D(x,y,z);

    }

}
