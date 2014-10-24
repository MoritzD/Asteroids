package Ass;

/**
 * Created by Alexander on 23/10/2014.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

public class Pentagon {


        private static FloatBuffer circleVertexBuffer = BufferUtils.newFloatBuffer(21);
        boolean vertexExists = false;

        public Pentagon()
        {
            if(!vertexExists) {

                circleVertexBuffer.put(0.0f);
                circleVertexBuffer.put(0.0f);
                circleVertexBuffer.put(0.0f);
                for (double i = ((2 * Math.PI)) / 5; i <= (2 * Math.PI); i += ((2 * Math.PI)) / 5) {
                    circleVertexBuffer.put((float) Math.sin(i));
                    circleVertexBuffer.put((float) Math.cos(i));
                    circleVertexBuffer.put(0.0f);
                }
                circleVertexBuffer.put((float) Math.sin(((2 * Math.PI)) / 5));
                circleVertexBuffer.put((float) Math.cos(((2 * Math.PI)) / 5));
                circleVertexBuffer.put(0.0f);
                circleVertexBuffer.rewind();

                vertexExists = true;
            }

        }


        public void draw()
        {

            Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, circleVertexBuffer);

            Gdx.gl11.glPushMatrix();

            Gdx.gl11.glNormal3f(0.0f, 0.0f, 1.0f);
            Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_FAN, 0, 7);
            Gdx.gl11.glPopMatrix();

        }

    }


