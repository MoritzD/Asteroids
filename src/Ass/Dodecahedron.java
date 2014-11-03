package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.Random;

/**
 * Created by moe on 23.10.14.
 */
public class Dodecahedron {

    Pentagon pen;
    static boolean floatBuffersExist = false;
    static FloatBuffer[] texCoordBuffer = new FloatBuffer[6];
    static FloatBuffer[] texCoordBufferUp = new FloatBuffer[6]; // Uper half of dodecahedron
    float rotationangel1 = -(float) Math.toRadians(72/2);
    Vector3D direction;
    Random rand = new Random();
    float deltaTime ;
    float Scalefaktor = 0.25f;              //0.25
    protected float explodefactor = 0.0f, explodeScaleFactor = 1.0f, distanceFaktor = 0.0f;
    protected Point3D center;
    int speed =2;
    float radius=1.31f;


    public Dodecahedron(Point3D position){
        setUpDodoIfNeeded();
        center = position;
    }

    public Dodecahedron(){
        setUpDodoIfNeeded();
        center = new Point3D(0.0f,0.0f,0.0f);
    }

    private void setUpDodoIfNeeded(){
        if(!floatBuffersExist) {

            texCoordBuffer[0] = BufferUtils.newFloatBuffer(14);
            texCoordBuffer[0].put(new float[]{0.5f, 0.5f,          //midel    0
                    1.0f, 0.39f,        //1
                    0.81f, 1.0f,        //2
                    0.19f, 1.0f,        //3
                    0.0f, 0.39f,        //4
                    0.5f, 0.0f,         //5
                    1.0f, 0.39f});      //6
            texCoordBuffer[0].rewind();


            System.out.println(texCoordBuffer[0].get(2));
            pen = new Pentagon();
//            pen.tex = new Texture(Gdx.files.internal("Textures/LavaAstero2.png"));  //Textures/pentagon4.png      Textures/derpasteroidsquadrat.png
            pen.texCoordBuffer = texCoordBuffer[0];
            pen.scaleTexture(Scalefaktor);
            System.out.println(texCoordBuffer[0].get(2));
            //pen.schiftTexture(0.5f,0.0f);
            //pen.tex = new Texture(Gdx.files.internal("Textures/derpasteroidsquadrat.png"));


            float BLx = 0, BLy = 0; // =  texCoordBuffer[0].get(8), BLy = texCoordBuffer[0].get(9);
            float BRx = 0, BRy = 0; // = texCoordBuffer[0].get(10), BRy = texCoordBuffer[0].get(11);
            float ONEx = 0, ONEy = 0;

            //float BRx = 0f, BRy = 0.39f;
            //float BLx = 0.5f, BLy = 0.0f;
/*
        texCoordBuffer[1] = BufferUtils.newFloatBuffer(14);
        texCoordBuffer[1].put(getTextureCoords(BLx,BLy,BRx,BRy));
        texCoordBuffer[1].rewind();

        rotationangel1 -= (float)Math.toRadians(72);

        BLx =  texCoordBuffer[0].get(6); BLy = texCoordBuffer[0].get(7);
        BRx = texCoordBuffer[0].get(8); BRy = texCoordBuffer[0].get(9);

        texCoordBuffer[2] = BufferUtils.newFloatBuffer(14);
        texCoordBuffer[2].put(getTextureCoords(BLx,BLy,BRx,BRy));
        texCoordBuffer[2].rewind();

        rotationangel1 -= (float)Math.toRadians(72);

        BLx =  texCoordBuffer[0].get(4); BLy = texCoordBuffer[0].get(5);
        BRx = texCoordBuffer[0].get(6); BRy = texCoordBuffer[0].get(7);

        texCoordBuffer[3] = BufferUtils.newFloatBuffer(14);
        texCoordBuffer[3].put(getTextureCoords(BLx,BLy,BRx,BRy));
        texCoordBuffer[3].rewind();
      */
            for (int e = 1; e < 6; e++) {

                switch (e) {
                    case 1:
                        BLx = texCoordBuffer[0].get(8);
                        BLy = texCoordBuffer[0].get(9);
                        BRx = texCoordBuffer[0].get(10);
                        BRy = texCoordBuffer[0].get(11);
                        ONEx = -100;
                        ONEy = -100;
                        break;
                    case 2:
                        BLx = texCoordBuffer[0].get(6);
                        BLy = texCoordBuffer[0].get(7);
                        BRx = texCoordBuffer[0].get(8);
                        BRy = texCoordBuffer[0].get(9);
                        break;
                    case 3:
                        BLx = texCoordBuffer[0].get(4);
                        BLy = texCoordBuffer[0].get(5);
                        BRx = texCoordBuffer[0].get(6);
                        BRy = texCoordBuffer[0].get(7);
                        break;
                    case 4:
                        BLx = texCoordBuffer[0].get(2);
                        BLy = texCoordBuffer[0].get(3);
                        BRx = texCoordBuffer[0].get(4);
                        BRy = texCoordBuffer[0].get(5);
                        break;
                    case 5:
                        BLx = texCoordBuffer[0].get(10);
                        BLy = texCoordBuffer[0].get(11);
                        BRx = texCoordBuffer[0].get(2);
                        BRy = texCoordBuffer[0].get(3);
                        break;
                }
                if (e != 1) {
                    ONEx = texCoordBuffer[e - 1].get(8);
                    ONEy = texCoordBuffer[e - 1].get(9);
                }
                texCoordBuffer[e] = BufferUtils.newFloatBuffer(14);
                texCoordBuffer[e].put(getTextureCoords(BLx, BLy, BRx, BRy, ONEx, ONEy));
                texCoordBuffer[e].rewind();

                rotationangel1 -= (float) Math.toRadians(72);
            }
            /*
            rotationangel1 = -(float) Math.toRadians(180 + 36);
            int Left = 2, Right = 3;
            for (int t = 1; t < 6; t++) {
                texCoordBufferUp[t] = BufferUtils.newFloatBuffer(14);
                texCoordBufferUp[t].put(new float[]{
                        0.5f, 0.22f * Scalefaktor,          //midel    0
                        texCoordBuffer[Right].get(10), texCoordBuffer[Right].get(11),        //1
                        (texCoordBuffer[Right].get(4) + texCoordBuffer[Right].get(6)) / 2, (texCoordBuffer[Right].get(5) + texCoordBuffer[Right].get(7)) / 2,         //2
                        (texCoordBuffer[Left].get(4) + texCoordBuffer[Left].get(6)) / 2, (texCoordBuffer[Left].get(5) + texCoordBuffer[Left].get(7)) / 2,        //3     mitte grundlienie 2er
                        texCoordBuffer[Left].get(10), texCoordBuffer[Left].get(11),        //4
                        texCoordBuffer[Right].get(2), texCoordBuffer[Right].get(3),         //5
                        texCoordBuffer[Right].get(10), texCoordBuffer[Right].get(11),
                });      //6

            rotationangel1 -= (float)Math.toRadians(72);
        }*/

        rotationangel1 = (float) Math.toRadians(36);
        int Left = 2, Right = 3;
        for(int t = 1; t<6; t++){
            texCoordBufferUp[t] = BufferUtils.newFloatBuffer(14);
            texCoordBufferUp[t].put(new float[]{
                    0.5f, 0.22f * Scalefaktor,          //midel    0
                    texCoordBuffer[Right].get(10), texCoordBuffer[Right].get(11),        //1
                    (texCoordBuffer[Right].get(4) + texCoordBuffer[Right].get(6)) / 2, (texCoordBuffer[Right].get(5) + texCoordBuffer[Right].get(7)) / 2,         //2
                    (texCoordBuffer[Left].get(4) + texCoordBuffer[Left].get(6)) / 2, (texCoordBuffer[Left].get(5) + texCoordBuffer[Left].get(7)) / 2,        //3     mitte grundlienie 2er
                    texCoordBuffer[Left].get(10), texCoordBuffer[Left].get(11),        //4
                    texCoordBuffer[Right].get(2), texCoordBuffer[Right].get(3),         //5
                    texCoordBuffer[Right].get(10), texCoordBuffer[Right].get(11),
            });      //6

                texCoordBufferUp[t].put(0, getMiddlePoint(t, true)[0]);
                texCoordBufferUp[t].put(1, getMiddlePoint(t, true)[1]);
                texCoordBufferUp[t].rewind();

                Left--;
                Right--;
                if (Left == 0) Left = 5;
                if (Right == 0) Right = 5;

                rotationangel1 -= (float) Math.toRadians(72);

            }
            rotationangel1 = (float) Math.toRadians(180);

            texCoordBufferUp[0] = BufferUtils.newFloatBuffer(14);
            texCoordBufferUp[0].put(new float[]{
                    0.5f, 0.22f * Scalefaktor,          //midel    0
                    texCoordBufferUp[4].get(6), texCoordBufferUp[4].get(7),        //1   Check
                    texCoordBufferUp[3].get(6), texCoordBufferUp[3].get(7),        //2   Check
                    texCoordBufferUp[3].get(4), texCoordBufferUp[3].get(5),       //3    Check
                    texCoordBufferUp[1].get(6), texCoordBufferUp[1].get(7),        //4   Check
                    texCoordBufferUp[1].get(4), texCoordBufferUp[1].get(5),         //5  check
                    texCoordBufferUp[4].get(6), texCoordBufferUp[4].get(7),          // Check
            });      //6


            texCoordBufferUp[0].put(0, getMiddlePoint(0, true)[0]);
            texCoordBufferUp[0].put(1, getMiddlePoint(0, true)[1]);
            texCoordBufferUp[0].rewind();
        /*
        texCoordBufferUp[2] = BufferUtils.newFloatBuffer(14);
        texCoordBufferUp[2].put(new float[]{
                                            0.5f, 0.5f,          //midel    0
                                            texCoordBuffer[2].get(10),texCoordBuffer[2].get(11),        //1
                                            (texCoordBuffer[2].get(4)+texCoordBuffer[2].get(6))/2, (texCoordBuffer[2].get(5)+texCoordBuffer[2].get(7))/2,         //2
                                            (texCoordBuffer[1].get(4)+texCoordBuffer[1].get(6))/2, (texCoordBuffer[1].get(5)+texCoordBuffer[1].get(7))/2,        //3     mitte grundlienie 2er
                                            texCoordBuffer[1].get(10),texCoordBuffer[1].get(11),        //4
                                            texCoordBuffer[2].get(2),texCoordBuffer[2].get(3),         //5
                                            texCoordBuffer[2].get(10),texCoordBuffer[2].get(11),
        });      //6


        texCoordBufferUp[2].put(0,getMiddlePoint(2,true)[0]);
        texCoordBufferUp[2].put(1,getMiddlePoint(2,true)[1]);
        texCoordBufferUp[2].rewind();


        texCoordBufferUp[3] = BufferUtils.newFloatBuffer(14);
        texCoordBufferUp[3].put(new float[]{
                                            0.5f, 0.5f,          //midel    0
                                            texCoordBuffer[1].get(10),texCoordBuffer[1].get(11),        //1
                                            (texCoordBuffer[1].get(4)+texCoordBuffer[1].get(6))/2, (texCoordBuffer[1].get(5)+texCoordBuffer[1].get(7))/2,         //2
                                            (texCoordBuffer[5].get(4)+texCoordBuffer[5].get(6))/2, (texCoordBuffer[5].get(5)+texCoordBuffer[5].get(7))/2,        //3     mitte grundlienie 2er
                                            texCoordBuffer[5].get(10),texCoordBuffer[5].get(11),        //4
                                            texCoordBuffer[1].get(2),texCoordBuffer[1].get(3),         //5
                                            texCoordBuffer[1].get(10),texCoordBuffer[1].get(11),
        });      //6


        texCoordBufferUp[3].put(0,getMiddlePoint(3,true)[0]);
        texCoordBufferUp[3].put(1,getMiddlePoint(3,true)[1]);
        texCoordBufferUp[3].rewind();

        texCoordBufferUp[4] = BufferUtils.newFloatBuffer(14);
        texCoordBufferUp[4].put(new float[]{
                                            0.5f, 0.5f,          //midel    0
                                            texCoordBuffer[5].get(10),texCoordBuffer[5].get(11),        //1
                                            (texCoordBuffer[5].get(4)+texCoordBuffer[5].get(6))/2, (texCoordBuffer[5].get(5)+texCoordBuffer[5].get(7))/2,         //2
                                            (texCoordBuffer[4].get(4)+texCoordBuffer[4].get(6))/2, (texCoordBuffer[4].get(5)+texCoordBuffer[4].get(7))/2,        //3     mitte grundlienie 2er
                                            texCoordBuffer[4].get(10),texCoordBuffer[4].get(11),        //4
                                            texCoordBuffer[5].get(2),texCoordBuffer[5].get(3),         //5
                                            texCoordBuffer[5].get(10),texCoordBuffer[5].get(11),
        });      //6


        texCoordBufferUp[4].put(0,getMiddlePoint(4,true)[0]);
        texCoordBufferUp[4].put(1,getMiddlePoint(4,true)[1]);
        texCoordBufferUp[4].rewind();




        System.out.println(texCoordBufferUp[1].get(0)+" ; "+texCoordBufferUp[1].get(1));
        System.out.println(texCoordBufferUp[1].get(2)+" ; "+texCoordBufferUp[1].get(3));
        System.out.println(texCoordBufferUp[1].get(4)+" ; "+texCoordBufferUp[1].get(5));
*/
        }


    }

    private float[] getMiddlePoint(int buff, boolean Up) {
        float sum[] = {0.0f,0.0f};
        if(Up){
            for (int e= 2 ; e < 10; e += 2) {
                sum[0] += texCoordBufferUp[buff].get(e);
            }
            for (int e= 1 ; e < 10; e += 2) {
                sum[1] += texCoordBufferUp[buff].get(e);
            }
            sum[0] = sum[0]/4;
            sum[1] = sum[1]/4;
           //return sum;
       //     sum[0] = (texCoordBufferUp[buff].get(8) + texCoordBufferUp[buff].get(2))/2;
       //     sum[1] = (texCoordBufferUp[buff].get(11) + texCoordBufferUp[buff].get(5))/2;
            //sum[0] = texCoordBufferUp[buff].get(6) + ((0.31f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.30f * Scalefaktor) * (float) Math.sin(rotationangel1));  // 0 x
            //sum[1] = texCoordBufferUp[buff].get(7) + ((0.31f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.30f * Scalefaktor) * (float) Math.cos(rotationangel1)); // 0 y
            //if(buff != 5) {
            //    sum[0] = texCoordBufferUp[buff].get(10) + texCoordBuffer[0].get((5 - buff)*2);
            //    sum[1] = texCoordBufferUp[buff].get(11) + texCoordBuffer[0].get(((5 - buff)*2)+1);
            //}else{
            //    sum[0] = texCoordBufferUp[buff].get(10) + texCoordBuffer[0].get(10);
            //    sum[1] = texCoordBufferUp[buff].get(11) + texCoordBuffer[0].get(11);
            //}
            return sum;
        }
        else{
            for (int e= 0 ; e < 12; e += 2) {
                sum[0] += texCoordBuffer[buff].get(e);
            }
            for (int e= 1 ; e < 12; e += 2) {
                sum[1] += texCoordBuffer[buff].get(e);
            }
            sum[0] = sum[0]/6;
            sum[1] = sum[1]/6;
            return sum;
        }


    }


    public void draw(){




        GL11.glPushMatrix();
        GL11.glTranslatef(this.center.x,this.center.y,this.center.z);



        GL11.glPushMatrix();

        pen.texCoordBuffer = texCoordBuffer[0];

        GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
        GL11.glTranslatef(0.0f, 0.0f, 2.62f/2);


                GL11.glTranslatef(0.0f,0.0f,-distanceFaktor/2);

                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f,0.0f,explodefactor);
                GL11.glScalef(explodeScaleFactor,explodeScaleFactor,explodeScaleFactor);
                GL11.glRotatef(explodefactor*100,0.0f,0.0f,1.0f);
        pen.draw();
                GL11.glPopMatrix();

        GL11.glRotatef(72/2, 0.0f, 0.0f, 1.0f);

        //pen.texCoordBuffer = texCoordBuffer[1];
        boolean first = true;
        int i=1;
        for(int angle = 0; angle < 360 ; angle +=72) {
            //if(i<4){
           pen.texCoordBuffer = texCoordBuffer[i++];
           // }else{
           //     pen.texCoordBuffer = texCoordBuffer[0];
           // }
            GL11.glPushMatrix();
            GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            //GL11.glTranslatef(0.0f, 1.6f, 0.0f);

            GL11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            GL11.glRotatef(-63.5f, 1.0f, 0.0f, 0.0f);

            GL11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
                    GL11.glTranslatef(0.0f,0.0f,explodefactor);
                    GL11.glScalef(explodeScaleFactor,explodeScaleFactor,explodeScaleFactor);
                    GL11.glRotatef(explodefactor*100,0.0f,0.0f,1.0f);

            pen.draw();
            GL11.glPopMatrix();
            //first = false;
        }
        GL11.glPopMatrix();

        GL11.glPushMatrix();


        GL11.glRotatef(90, 1.0f, 0.0f, 0.0f);
        GL11.glTranslatef(0.0f, 0.0f, -2.62f/2);
                GL11.glTranslatef(0.0f,0.0f,distanceFaktor/2);
        GL11.glRotatef(180, 1.0f, 0.0f, 0.0f);

        pen.texCoordBuffer = texCoordBufferUp[0];
        //pen.texCoordBuffer = texCoordBuffer[0];

                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f,0.0f,explodefactor);
                GL11.glScalef(explodeScaleFactor,explodeScaleFactor,explodeScaleFactor);
                GL11.glRotatef(explodefactor*100,0.0f,0.0f,1.0f);
        pen.draw();
                GL11.glPopMatrix();

        i=1;

        GL11.glRotatef(72/2, 0.0f, 0.0f, 1.0f);
        for(int angle = 0; angle < 360 ; angle +=72) {
            //if(i<3){
            pen.texCoordBuffer = texCoordBufferUp[i++];
            //}else{
            //     pen.texCoordBuffer = texCoordBuffer[0];
            // }
            GL11.glPushMatrix();
            GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            //GL11.glTranslatef(0.0f, 1.4f, 0.0f);

            GL11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            GL11.glRotatef(-63.5f, 1.0f, 0.0f, 0.0f);

            GL11.glTranslatef(0.0f, 0.809016994f, 0.0f);
                    GL11.glTranslatef(0.0f,0.0f,explodefactor);
                    GL11.glScalef(explodeScaleFactor,explodeScaleFactor,explodeScaleFactor);
                    GL11.glRotatef(explodefactor*100,0.0f,0.0f,1.0f);
            pen.draw();
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();


    }

    private float[] getTextureCoords(float BLx, float BLy, float BRx, float BRy, float ONEx, float ONEy){

        float rotationangel2 = rotationangel1 - (float) Math.toRadians(18);
        float rotationangel3 = rotationangel1 + (float) Math.toRadians(18);

        if((ONEx == ONEy)&& (ONEx == -100)) {
            return new float[]{  // 0.31  0.42                                         0.30  / 0.37
                    BLx + ((0.31f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.30f * Scalefaktor) * (float) Math.sin(rotationangel1)),  // 0 x
                    BLy + ((0.31f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.30f * Scalefaktor) * (float) Math.cos(rotationangel1)), // 0 y

                    //BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 1 x
                    //BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 1 y        //midel    0

                    BRx + ((0.195f * Scalefaktor) * (float) Math.cos(rotationangel3) - (-0.57f * Scalefaktor) * (float) Math.sin(rotationangel3)),  // 1 x
                    BRy + ((0.195f * Scalefaktor) * (float) Math.sin(rotationangel3) + (-0.57f * Scalefaktor) * (float) Math.cos(rotationangel3)),  // 1 y

                    BRx, BRy,           //2
                    BLx, BLy,           //3

                    BLx + ((-0.195f * Scalefaktor) * (float) Math.cos(rotationangel2) - (-0.57f * Scalefaktor) * (float) Math.sin(rotationangel2)),  // 4 x
                    BLy + ((-0.195f * Scalefaktor) * (float) Math.sin(rotationangel2) + (-0.57f * Scalefaktor) * (float) Math.cos(rotationangel2)),  // 4 y
                    //0.33                                                            //0.948f    or : 0.85  or best: 0.9
                    BLx + ((0.33f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.95f * Scalefaktor) * (float) Math.sin(rotationangel1)),  // 5 x
                    BLy + ((0.33f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.95f * Scalefaktor) * (float) Math.cos(rotationangel1)),  // 5 y

                    //BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 6 x
                    //BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 6 y

                    BRx + ((0.195f * Scalefaktor) * (float) Math.cos(rotationangel3) - (-0.57f * Scalefaktor) * (float) Math.sin(rotationangel3)),  // 6 x
                    BRy + ((0.195f * Scalefaktor) * (float) Math.sin(rotationangel3) + (-0.57f * Scalefaktor) * (float) Math.cos(rotationangel3)),  // 6 y
            };
        }else{
            return new float[]{  // 0.31  0.42                                         0.30  / 0.37
                    BLx+((0.31f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.30f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 0 x
                    BLy+((0.31f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.30f * Scalefaktor)* (float) Math.cos(rotationangel1)), // 0 y

                    //BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 1 x
                    //BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 1 y        //midel    0

                    ONEx, ONEy,  // 1

                    BRx, BRy,           //2
                    BLx, BLy,           //3

                    BLx+((-0.195f * Scalefaktor) * (float) Math.cos(rotationangel2) - (-0.57f * Scalefaktor)* (float) Math.sin(rotationangel2)),  // 4 x
                    BLy+((-0.195f * Scalefaktor) * (float) Math.sin(rotationangel2) + (-0.57f * Scalefaktor)* (float) Math.cos(rotationangel2)),  // 4 y
                    //0.33                                                            //0.948f    or : 0.85  or best: 0.9
                    BLx+((0.33f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.95f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 5 x
                    BLy+((0.33f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.95f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 5 y

                    //BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 6 x
                    //BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 6 y

                    ONEx, ONEy,  // 6
            };
        }
    }


    /*
    private float[] getTextureCoords(float BLx, float BLy, float BRx, float BRy, float ONEx){
        float rotationangel2 = rotationangel1 - (float) Math.toRadians(18);
        float rotationangel3 = rotationangel1 + (float) Math.toRadians(18);

        return new float[]{  // 0.31  0.42                                         0.30  / 0.37
                BLx+((0.31f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.30f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 0 x
                BLy+((0.31f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.30f * Scalefaktor)* (float) Math.cos(rotationangel1)), // 0 y

                //BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 1 x
                //BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 1 y        //midel    0

                ONEx, ONEy,  // 1

                BRx, BRy,           //2
                BLx, BLy,           //3

                BLx+((-0.195f * Scalefaktor) * (float) Math.cos(rotationangel2) - (-0.57f * Scalefaktor)* (float) Math.sin(rotationangel2)),  // 4 x
                BLy+((-0.195f * Scalefaktor) * (float) Math.sin(rotationangel2) + (-0.57f * Scalefaktor)* (float) Math.cos(rotationangel2)),  // 4 y
                //0.33                                                            //0.948f    or : 0.85  or best: 0.9
                BLx+((0.33f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.95f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 5 x
                BLy+((0.33f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.95f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 5 y

                //BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 6 x
                //BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 6 y

                ONEx, ONEy,  // 6
        };
    }*/
    // changes the direction vector so that the asteroid moves in a random direction
    public void movement(){
        deltaTime = Gdx.graphics.getDeltaTime()*0.5f;

        if(direction == null) {
            float temp1, temp2, temp3;
            // this is the code that usually generates random direction vectors but I want to test collision so
            // I want to manually hardcode direction vectors
            temp1 = rand.nextInt(3);
            if(temp1==2)
                temp1 = -1;
            temp2 = rand.nextInt(3);
            if(temp2 == 2)
                temp2 = -1;
            temp3 = rand.nextInt(3);
            if(temp3 ==2)
                temp3 =-1;
            if(temp1==0&& temp2==0&&temp3==0) {
                temp1 = rand.nextInt(2);
                temp2 = rand.nextInt(2);
                temp3 = rand.nextInt(2);
            }
            this.setDirection(temp1, temp2, temp3);

        }
        else{
            if(direction.x == 0.0f)
             ;
            else{

              //  System.out.println("Never get here"+deltaTime);
                if(direction.x>0)
                   this.center.x +=speed*deltaTime;
                else
                   this.center.x-=speed*deltaTime;

            }

            if(direction.y == 0.0f)
                ;
            else{

              //  System.out.println("Never get here"+deltaTime);
                if(direction.y>0)
                    this.center.y +=speed*deltaTime;
                else
                    this.center.y-=speed*deltaTime;
            }

            if(direction.z==0.0f)
                ;
            else {

              //  System.out.println("Never get here"+deltaTime);
                if(direction.z>0)
                    this.center.z +=speed*deltaTime;
                else
                    this.center.z-=speed*deltaTime;
            }
        }

    }
    public  void setCenter(float x, float y, float z){
        center.x = x;
        center.y = y;
        center.z = z;
    }

    public void collision(Dodecahedron asteroid){

        //ToDo diffrent cases (x2-x1)^2 + (y1-y2)^2 + (z1-z2)^2 <= (r1+r2)^2)
        // If the distance between the center points is less then the radius of the asteroids summed
        // then we have  a collision
        // at the moment the problem is that the center doesn't move with the decahedron


        if((this.center.x-asteroid.center.x)*(this.center.x-asteroid.center.x) +
           (this.center.y-asteroid.center.x)*(this.center.y-asteroid.center.x) +
           (this.center.z-asteroid.center.z)*(this.center.z-asteroid.center.z)
                <=(this.radius+asteroid.radius)*(this.radius+asteroid.radius)){

            System.out.println("Hit!! "+this.center.x+","+this.center.y);
            if(this.direction.addition(asteroid.direction).isNullVector3D()) {
                this.direction.inverse();
                asteroid.direction.inverse();
                System.out.println("x =" + direction.x + ";y =" + direction.y + ";z =" + direction.z);
            }
            else{
                // fucking rotations
            }


        }





    }

    public void setDirection(float x, float y, float z) {


        direction = new Vector3D(x, y, z);
        System.out.println("x =" + direction.x + ";y =" + direction.y + ";z =" + direction.z);
        // }
    }
}
