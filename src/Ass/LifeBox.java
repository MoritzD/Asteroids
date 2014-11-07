package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by Alexander on 07/11/2014.
 */
public class LifeBox {
    Point3D position;
    private FloatBuffer vertexBuffer;
    float x, y;
    LifeBox(){
        if(vertexBuffer == null) {

            vertexBuffer = BufferUtils.newFloatBuffer(8);

            vertexBuffer.put(new float[]{1, 1, 1, 0, 2, 1, 2, 0});

            vertexBuffer.rewind();
        }
    }
    void drawLife(){
        Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        Gdx.gl11.glMatrixMode(GL11.GL_MODELVIEW);
        Gdx.gl11.glLoadIdentity();

        Gdx.gl11.glTranslatef(1,1,0);

        Gdx.gl11.glVertexPointer(2, GL11.GL_FLOAT, 0, vertexBuffer);

            Gdx.gl11.glColor4f(0, 1, 0, 0);



        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

    }
}
