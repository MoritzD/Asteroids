package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by moe on 23.10.14.
 */
public class Dodecahedron {

    Pentagon pen;
    FloatBuffer texCoordBuffer0,texCoordBuffer1,texCoordBuffer2;
    float rotationangel1 = -(float) Math.toRadians(72/2);
    float Scalefaktor = 0.25f;

    public Dodecahedron(){

        texCoordBuffer0 = BufferUtils.newFloatBuffer(14);
        texCoordBuffer0.put(new float[]{0.5f, 0.5f,          //midel    0
                                        1.0f, 0.39f,        //1
                                        0.81f, 1.0f,        //2
                                        0.19f, 1.0f,        //3
                                        0.0f, 0.39f,        //4
                                        0.5f, 0.0f,         //5
                                        1.0f, 0.39f});      //6
        texCoordBuffer0.rewind();



        System.out.println(texCoordBuffer0.get(2));
        pen = new Pentagon();
        pen.texCoordBuffer = texCoordBuffer0;
        pen.scaleTexture(Scalefaktor);
        System.out.println(texCoordBuffer0.get(2));
        //pen.schiftTexture(0.5f,0.0f);
        //pen.tex = new Texture(Gdx.files.internal("Textures/derpasteroidsquadrat.png"));


        float BLx =  texCoordBuffer0.get(8), BLy = texCoordBuffer0.get(9);
        float BRx = texCoordBuffer0.get(10), BRy = texCoordBuffer0.get(11);

        //float BRx = 0f, BRy = 0.39f;
        //float BLx = 0.5f, BLy = 0.0f;

        texCoordBuffer1 = BufferUtils.newFloatBuffer(14);
        texCoordBuffer1.put(new float[]{
                                        BLx+((0.31f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.42f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 0 x
                                        BLy+((0.31f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.42f * Scalefaktor)* (float) Math.cos(rotationangel1)), // 0 y

                                        BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 1 x
                                        BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 1 y        //midel    0

                                        BRx, BRy,           //2
                                        BLx, BLy,           //3

                                        BLx+((-0.195f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.57f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 4 x
                                        BLy+((-0.195f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.57f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 4 y

                                        BLx+((0.33f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.948f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 5 x
                                        BLy+((0.33f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.948f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 5 y

                                        BLx+((0.835f * Scalefaktor) * (float) Math.cos(rotationangel1) - (-0.60f * Scalefaktor)* (float) Math.sin(rotationangel1)),  // 6 x
                                        BLy+((0.835f * Scalefaktor) * (float) Math.sin(rotationangel1) + (-0.60f * Scalefaktor)* (float) Math.cos(rotationangel1)),  // 6 y
                                         });
        texCoordBuffer1.rewind();


        texCoordBuffer2 = BufferUtils.newFloatBuffer(14);
        texCoordBuffer2.put(new float[]{0.5f, 0.5f,          //midel
                1.0f, 0.39f,
                texCoordBuffer0.get(11), texCoordBuffer0.get(12),
                texCoordBuffer0.get(9), texCoordBuffer0.get(10),
                0.0f, 0.39f,
                0.5f, 0.0f,
                1.0f, 0.39f});
        texCoordBuffer2.rewind();
    }


    public void draw(){
        Gdx.gl11.glPushMatrix();
        pen.texCoordBuffer = texCoordBuffer0;

        Gdx.gl11.glRotatef(90, 1.0f, 0.0f, 0.0f);
        pen.draw();

        Gdx.gl11.glRotatef(72/2, 0.0f, 0.0f, 1.0f);

        pen.texCoordBuffer = texCoordBuffer1;
        boolean first = true;

        for(int angle = 0; angle < 360 ; angle +=72) {
            if(!first){
                pen.texCoordBuffer = texCoordBuffer0;
            }
            Gdx.gl11.glPushMatrix();
            Gdx.gl11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            //Gdx.gl11.glTranslatef(0.0f, 1.6f, 0.0f);

            Gdx.gl11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            Gdx.gl11.glRotatef(-63.5f, 1.0f, 0.0f, 0.0f);

            Gdx.gl11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            pen.draw();
            Gdx.gl11.glPopMatrix();
            first = false;
        }
        Gdx.gl11.glPopMatrix();

        Gdx.gl11.glPushMatrix();


        Gdx.gl11.glRotatef(90, 1.0f, 0.0f, 0.0f);
        Gdx.gl11.glTranslatef(0.0f, 0.0f, -2.62f);
        Gdx.gl11.glRotatef(180, 1.0f, 0.0f, 0.0f);

        pen.draw();

        Gdx.gl11.glRotatef(72/2, 0.0f, 0.0f, 1.0f);
        for(int angle = 0; angle < 360 ; angle +=72) {
            Gdx.gl11.glPushMatrix();
            Gdx.gl11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            //Gdx.gl11.glTranslatef(0.0f, 1.4f, 0.0f);

            Gdx.gl11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            Gdx.gl11.glRotatef(-63.5f, 1.0f, 0.0f, 0.0f);

            Gdx.gl11.glTranslatef(0.0f, 0.809016994f, 0.0f);
            pen.draw();
            Gdx.gl11.glPopMatrix();
        }
        Gdx.gl11.glPopMatrix();
    }
}
