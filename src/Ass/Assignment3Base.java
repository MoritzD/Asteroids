package Ass;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL11;

import java.util.ArrayList;


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
    Sphere sun;
    Sphere sky;
    Dodecahedron dodo, bobo;
    Box bo;
    float acc = 0;
    private float rotationAngle;

    ParticleEffect particl;
    boolean power = false;
    boolean wjusttouched = false;
    boolean oldSpace = false;
    
    Spaceship space;
    Point3D position;

    protected ArrayList<Asteroid> asteroids;
    protected ArrayList<Lazers> shots;

    int numastroids = 100;

    float speedfactorrot = 2.0f;
    float speedfactormove = 2.0f;

    Vector3D camSpeed;
    Vector3D camRotation;

    @Override
    public void create() {
        // TODO Auto-generated method stub
        Gdx.gl11.glEnable(GL11.GL_LIGHTING);
        Gdx.gl11.glEnable(GL11.GL_LIGHT0);
        Gdx.gl11.glEnable(GL11.GL_LIGHT1);
        Gdx.gl11.glEnable(GL11.GL_LIGHT2);
        Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);

        Gdx.gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

        Gdx.gl11.glEnable(GL11.GL_NORMALIZE);


        shots = new ArrayList<Lazers>();



        camFirstPerson = new Camera();
        camFirstPerson.lookAt(new Point3D(-1.0f, -2.0f, 8.0f), new Point3D(0.0f, 0.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
        camFirstPerson.perspective(90.0f, 1.777778f, 0.1f, 400.0f);

        camThirdPerson = new Camera();
        camThirdPerson.perspective(50.0f, 1.777778f, 0.01f, 40.0f);

        camTopDown = new Camera();
        camTopDown.perspective(40.0f, 1.777778f, 5.0f, 20.0f);

        hex = new Hexagon();
        hex2 = new Hexagon();
        hex3 = new Hexagon();
        pen = new Pentagon();
        camSpeed = new Vector3D(0,0,0);
        camRotation = new Vector3D(0,0,0);

        sun = new Sphere(12,24);

        planetEarth = new Sphere(12, 24);
        planetEarth.setTexture("Textures/earth_texture1024x512.png");
        planetEarth.setPosition(0, 0, -30);
        planetEarth.scale(10,10,10);

        sky = new Sphere(12, 24);
        sky.setTexture("Textures/awsomesky_4096.png");
        sky.setPosition(0, 0, 0);
        sky.scale(200,200,200);


        bo=new Box(new Point3D(0,0,0),new Vector3D(1.0f,1.0f,1.0f),new float[] {1.0f,1.0f,0.0f,0.0f},false);

        dodo = new Dodecahedron();
        bobo = new Dodecahedron();
//        bobo.setDirection(-1, -1, -1);
//        dodo.setDirection(1,1,1);
        dodo.pen.setTex("Textures/LavaAstero2.png");
        Gdx.gl11.glPushMatrix();
        space = new Spaceship(-5.0f,0.0f,0.f);
        Gdx.gl11.glPopMatrix();
      //  ObjLoader loader = new ObjLoader();
      //  model = loader.loadObj(Gdx.files.internal("Spaceship/CityPatrolVehicle/CityPatrolVehicle.obj"), true);
//        bobo.setDirection(-1,-1,-1);
//        dodo.setDirection(1,1,1);

        particl = new ParticleEffect("Textures/star03.bmp");

        asteroids = new ArrayList<Asteroid>();
        Asteroid tempdodo;
        for (int ast = 0; ast < numastroids; ast++){


            if(Math.random()>0.50f)
                tempdodo = new Asteroid(dodo);
            else
                tempdodo = new Asteroid(bobo);
            tempdodo.setCenter((float)Math.random()*200.0f - 100.0f,(float)Math.random()*200.0f - 100.0f,(float)Math.random()*200.0f - 100.0f);
            //tempdodo.setDirection((float)Math.random()*4.0f - 2.0f,(float)Math.random()*4.0f - 2.0f,(float)Math.random()*4.0f - 2.0f);
            // else if( Math.random() > 0.5f)
            //     tempdodo.pen.setTex("Textures/pentagon4.png");
            asteroids.add(tempdodo);
        }






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
        power = false;
        rotationAngle += 90.0f * deltaTime;

        for (int i1 = 0; i1 < asteroids.size(); i1++) {
            Asteroid ast = asteroids.get(i1);
            if(1+i1<asteroids.size()) {
                Asteroid ast2 = asteroids.get(i1+1);
                ast.collision(ast2);


            }

            ast.movement();
            double astSidesSquared=Math.pow(ast.center.x, 2) + Math.pow(ast.center.y, 2) + Math.pow(ast.center.z, 2);
            double astRadi=ast.radius*2.62f/2;
            if (astSidesSquared >= Math.pow((200 - astRadi), 2)||
                    Math.pow(ast.center.x-planetEarth.getPosition().x, 2) +
                    Math.pow(ast.center.y-planetEarth.getPosition().y, 2) +
                    Math.pow(ast.center.z-planetEarth.getPosition().z, 2)
                     <= Math.pow((planetEarth.getScale()+astRadi),2)) {
                ast.moveVector.times(-1);
            }
            if (ast.killME) {
                asteroids.remove(i1);

            }

        }

        if(Math.pow(camFirstPerson.eye.x,2)+Math.pow(camFirstPerson.eye.y,2)+Math.pow(camFirstPerson.eye.z,2) >= Math.pow((200-4),2)||
                Math.pow(camFirstPerson.eye.x-planetEarth.getPosition().x, 2) +
                        Math.pow(camFirstPerson.eye.y-planetEarth.getPosition().y, 2) +
                        Math.pow(camFirstPerson.eye.z-planetEarth.getPosition().z, 2)
                        <= Math.pow((planetEarth.getScale()+4),2)) {
            camSpeed.times(-1);
        }


            particl.update(deltaTime);



       // dodo.collision(bobo);
        // can't do this or it won't work
     //  bobo.collision(dodo);



        if(Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            if(camRotation.x > -90.0f)
                camRotation.x -= (20.0f*deltaTime)*speedfactorrot;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            if(camRotation.x < 90.0f) {
                camRotation.x += (20.0f*deltaTime)*speedfactorrot;
            }
        }
        camFirstPerson.pitch(camRotation.x * deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            if(camRotation.y > -90.0f) {
                camRotation.y -= (20.0f*deltaTime)*speedfactorrot;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            if(camRotation.y < 90.0f) {
                camRotation.y += (20.0f*deltaTime)*speedfactorrot;
            }
        }
        camFirstPerson.yaw(camRotation.y * deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            if(!wjusttouched){                                      // w just pressed
                particl.reset();
                //particl.burning=true;
            }
            if(camSpeed.z > -5.0f)
                camSpeed.z -= deltaTime * speedfactormove;
            power = true;
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.W) && wjusttouched)   // W just released
        {
            particl.end();
        }
        //if(Gdx.input.isKeyPressed(Input.Keys.I)) {
        //}
        wjusttouched = Gdx.input.isKeyPressed(Input.Keys.W);
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            if(camSpeed.z < 5.0f)
                camSpeed.z += 0.7f*deltaTime * speedfactormove;
        }
        camFirstPerson.slide(0.0f, 0.0f, camSpeed.z*deltaTime);
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if(camSpeed.x > -5.0f)
                camSpeed.x -= 0.7f*deltaTime * speedfactormove;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if(camSpeed.x < 5.0f)
                camSpeed.x += 0.7f*deltaTime* speedfactormove;
        }
        camFirstPerson.slide(camSpeed.x*deltaTime, 0.0f,0.0f);


        if(Gdx.input.isKeyPressed(Input.Keys.R))
        {
            if(camSpeed.y > -5.0f)
            camSpeed.y += 0.7f*deltaTime* speedfactormove;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.F))
        {
            if(camSpeed.y < 5.0f)
                camSpeed.y -= 0.7f*deltaTime* speedfactormove;
        }
        camFirstPerson.slide(0.0f ,camSpeed.y*deltaTime, 0.0f);

        if(Gdx.input.isKeyPressed(Input.Keys.V))
        {
            if(camSpeed.x > 0){
                camSpeed.x -=1.0*deltaTime * speedfactormove;
            }
            else if(camSpeed.x < 0) {
                camSpeed.x += 1.0 * deltaTime * speedfactormove;
            }
            if(camSpeed.y > 0){
                camSpeed.y -=1.0*deltaTime * speedfactormove;
            }
            else if(camSpeed.y < 0) {
                camSpeed.y += 1.0 * deltaTime * speedfactormove;
            }
            if(camSpeed.z > 0){
                camSpeed.z -=1.0*deltaTime * speedfactormove;
            }
            else if(camSpeed.z < 0) {
                camSpeed.z += 1.0 * deltaTime * speedfactormove;
            }

            if(camRotation.x > 0){
                camRotation.x -= 20.0*deltaTime * speedfactorrot;
            }
            else if(camRotation.x < 0) {
                camRotation.x += 20.0 * deltaTime * speedfactorrot;
            }
            if(camRotation.y > 0){
                camRotation.y -= 20.0*deltaTime * speedfactorrot;
            }
            else if(camRotation.y < 0) {
                camRotation.y += 20.0 * deltaTime * speedfactorrot;
            }
            if(camRotation.y > 0){
                camRotation.y -= 20.0*deltaTime * speedfactorrot;
            }
            else if(camRotation.y < 0) {
                camRotation.y += 20.0 * deltaTime * speedfactorrot;
            }
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
        for (int i = 0; i < shots.size(); i++) {
            Lazers sh = shots.get(i);

            sh.update(deltaTime);
            if (Math.pow(sh.position.x, 2) + Math.pow(sh.position.y, 2) + Math.pow(sh.position.z, 2) >= Math.pow((200 - 1), 2)) {
                shots.remove(i);
            }
            for (Asteroid ass : asteroids) {
                if (Math.pow(ass.center.x - sh.position.x, 2) + Math.pow(ass.center.y - sh.position.y, 2) + Math.pow(ass.center.z - sh.position.z, 2) <= Math.pow((2.62f/2)*ass.radius, 2)) {
                    ass.explode();
                    shots.remove(i);
                }
            }
        }


        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && !oldSpace) {
            float x=0.0f,y=-0.5f,z=-4.0f;
            shots.add(new Lazers(new Point3D(camFirstPerson.u.x*x + camFirstPerson.v.x*y + camFirstPerson.n.x*z + camFirstPerson.eye.x
                    ,camFirstPerson.u.y*x + camFirstPerson.v.y*y + camFirstPerson.n.y*z + camFirstPerson.eye.y
                    ,camFirstPerson.u.z*x + camFirstPerson.v.z*y + camFirstPerson.n.z*z + camFirstPerson.eye.z),camFirstPerson.u.getNewInstance(),camFirstPerson.v.getNewInstance(),camFirstPerson.n.getNewInstance()));
        }
        oldSpace = Gdx.input.isKeyPressed(Input.Keys.SPACE);

    }

    private void display()
    {
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

        camFirstPerson.setMatrices();
        float[] lightAmbient = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_AMBIENT, lightAmbient, 0);

        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse, 0);

        float[] lightPosition = {0.0f, 0.0f, 15.0f, 0.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);

        float[] lightDiffuse1 = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse1, 0);

        float[] lightPosition1 = {-5.0f, -10.0f, -13.0f, 0.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition1, 0);

        float[] lightDiffuse2 = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, lightDiffuse2, 0);

        float[] lightPosition2 = {5.0f, 10.0f, -5.0f, 0.0f};
        Gdx.gl11.glLightfv(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPosition2, 0);

        float[] materialDiffusesun = {0.5f, 0.5f, 0.5f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffusesun, 0);

        float[] materialambientsun = {5.0f, 5.0f, 5.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialambientsun, 0);

        for(Asteroid ast : asteroids){
            Gdx.gl11.glPushMatrix();
            ast.draw();
            Gdx.gl11.glPopMatrix();
        }


        sun.setPosition(lightPosition[0],lightPosition[1],lightPosition[2]);

        sun.draw();
        sun.setPosition((int)lightPosition1[0],(int)lightPosition1[1],(int)lightPosition1[2]);
        sun.draw();
        sun.setPosition((int)lightPosition2[0],(int)lightPosition2[1],(int)lightPosition2[2]);
        sun.draw();

        //bo.draw();

        Gdx.gl11.glPushMatrix();
        float[] materialDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);
        Gdx.gl11.glTranslatef(0.0f, 0.0f, -2.0f);
        //Gdx.gl11.glScalef(0.1f,0.1f,0.1f);
        //hex.draw();
        Gdx.gl11.glPopMatrix();

        Gdx.gl11.glPushMatrix();

        float[] materialspecularityearth = {0.0f, 0.0f, 1.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, materialspecularityearth, 0);
        Gdx.gl11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 5.0f);
        float[] materialambientearth = {0.8f, 0.8f, 1.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialambientearth, 0);

        sky.draw();

        planetEarth.setRotationAngle(rotationAngle);
        planetEarth.draw();


        Gdx.gl11.glPopMatrix();

        for(Lazers sh : shots){
            sh.draw();
        }

        float[] materialspecularityearth2 = {0.0f, 0.0f, 0.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, materialspecularityearth2, 0);
        Gdx.gl11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 0.0f);
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialspecularityearth2, 0);

        float[] materialdefuse = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialdefuse, 0);

        //Gdx.gl11.glPopMatrix();

        //Gdx.gl11.glScalef(5.0f,0.5f,1.0f);
        // pen.draw();
        // Gdx.gl11.glScalef(10.0f,10.0f,10.0f);
        Gdx.gl11.glPushMatrix();
        dodo.draw();
        bobo.draw();
        Gdx.gl11.glPopMatrix();





        /*space.position.x = camFirstPerson.eye.x+camFirstPerson.n.x;
        space.position.y = camFirstPerson.eye.y+camFirstPerson.n.y;
        space.position.z = camFirstPerson.eye.z-camFirstPerson.n.z;
        */Gdx.gl11.glPushMatrix();
        //Gdx.gl11.glRotatef(spacepitch,1.0f,0.0f,0.0f);
        //Gdx.gl11.glRotatef(spaceyaw,0.0f,1.0f,0.0f);




        float[] materialDiffuse2 = {0.5f, 0.5f, 0.5f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse2, 0);

        float[] materialspecularity = {0.5f, 0.5f, 0.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, materialspecularity, 0);

        float[] materialambient = {0.0f, 0.0f, 0.2f, 1.0f};
        //float[] materialambient = {1.0f, 1.0f, 1.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialambient, 0);

        Gdx.gl11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 5.0f);



        float[] matrix = new float[16];
        matrix[0] = camFirstPerson.u.x;	matrix[4] = camFirstPerson.v.x;	matrix[8] = camFirstPerson.n.x;	matrix[12] = camFirstPerson.eye.x;
        matrix[1] = camFirstPerson.u.y;	matrix[5] = camFirstPerson.v.y;	matrix[9] = camFirstPerson.n.y;	matrix[13] = camFirstPerson.eye.y;
        matrix[2] = camFirstPerson.u.z;	matrix[6] = camFirstPerson.v.z;	matrix[10] = camFirstPerson.n.z;matrix[14] = camFirstPerson.eye.z;
        matrix[3] = 0;					matrix[7] = 0;					matrix[11] = 0;					matrix[15] = 1;

        Gdx.gl11.glMultMatrixf(matrix, 0);

        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(5.0f, -1.5f, -2.0f);      //2.0
        space.drawSpaceship();
        Gdx.gl11.glPopMatrix();

        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(0.0f, -0.4f, -0.52f);     //0.52
        Gdx.gl11.glRotatef(90, 1.0f, 0.0f, 0.0f);

        Gdx.gl11.glPushMatrix();
        Gdx.gl11.glTranslatef(-0.12f, 0.0f, 0.0f);
        Gdx.gl11.glScalef(0.2f, 0.2f, 0.2f);
        particl.display();
        Gdx.gl11.glPopMatrix();

        Gdx.gl11.glTranslatef(0.12f, 0.0f, 0.0f);
        Gdx.gl11.glScalef(0.2f, 0.2f, 0.2f);
        particl.display();

        Gdx.gl11.glPopMatrix();




        float[] materialspecularity2 = {0.0f, 0.0f, 0.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, materialspecularity2, 0);

        float[] materialambient2 = {0.0f, 0.0f, 0.0f, 1.0f};
        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_AMBIENT, materialambient2, 0);

        Gdx.gl11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, 0.0f);

        Gdx.gl11.glPopMatrix();



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
