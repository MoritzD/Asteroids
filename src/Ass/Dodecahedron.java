package Ass;

import com.badlogic.gdx.Gdx;

/**
 * Created by moe on 23.10.14.
 */
public class Dodecahedron {

    Pentagon pen;
    public Dodecahedron(){
        pen = new Pentagon();
    }


    public void draw(){
        Gdx.gl11.glPushMatrix();

        Gdx.gl11.glRotatef(90, 1.0f, 0.0f, 0.0f);
        pen.draw();

        Gdx.gl11.glRotatef(72/2, 0.0f, 0.0f, 1.0f);

        for(int angle = 0; angle < 360 ; angle +=72) {
            Gdx.gl11.glPushMatrix();
            Gdx.gl11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            //Gdx.gl11.glTranslatef(0.0f, 1.6f, 0.0f);

            Gdx.gl11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            Gdx.gl11.glRotatef(-63.5f, 1.0f, 0.0f, 0.0f);

            Gdx.gl11.glTranslatef(0.0f, 0.809016994f, 0.0f); //0.783
            pen.draw();
            Gdx.gl11.glPopMatrix();
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
