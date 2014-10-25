package Ass;

/**
 * Created by Alexander on 23/10/2014.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

public class Pentagon {


        protected static FloatBuffer circleVertexBuffer = BufferUtils.newFloatBuffer(21),texCoordBuffer;

        boolean vertexExists = false;
        protected Texture tex;

        public Pentagon()
        {
            if(!vertexExists) {


                //stacks = i_stacks;
                //slices = i_slices;
                //vertexCount = 0;
                //float[] array = new float[(stacks) * (slices + 1) * 6];
                //float stackInterval = (float) Math.PI / (float) stacks;
                //float sliceInterval = 2.0f * (float) Math.PI / (float) slices;

                //float[] texArray = new float[(stacks) * (slices + 1) * 4];

                //float stackAngle, sliceAngle;

                // FloatBuffer circleVertexBuffer = BufferUtils.newFloatBuffer(24);


                texCoordBuffer = BufferUtils.newFloatBuffer(14);
                texCoordBuffer.put(new float[] {0.5f,0.5f,          //midel
                                                1.0f,0.39f,
                                                0.81f,1.0f,
                                                0.19f,1.0f,
                                                0.0f,0.39f,
                                                0.5f,0.0f,
                                                1.0f,0.39f});
                texCoordBuffer.rewind();

                //for(int i=0; i <14; i++){
                //    texCoordBuffer0.put(i,((texCoordBuffer0.get(i)-0.5f)*0.5f)+0.5f);
                //}

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

            tex = new Texture(Gdx.files.internal("Textures/pentagon2.png"));  //Textures/pentagon.png      Textures/derpasteroidsquadrat.png
        }

    public void scaleTexture(float scale){
        for(int i=0; i <14; i++){
            texCoordBuffer.put(i,((texCoordBuffer.get(i)-texCoordBuffer.get(0))*scale)+texCoordBuffer.get(0));
        }
        texCoordBuffer.rewind();

    }
    public void schiftTexture(float x, float y){
        for(int i=0; i <14; i++){
            texCoordBuffer.put(i,(texCoordBuffer.get(i++)+x));
            texCoordBuffer.put(i,(texCoordBuffer.get(i)-y));
        }
        texCoordBuffer.rewind();

    }


        public void draw()
        {
            //   Gdx.gl11.glShadeModel(GL11.GL_SMOOTH);
            //   Gdx.gl11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
            Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, circleVertexBuffer);
            //Gdx.gl11.glNormalPointer(GL11.GL_FLOAT, 0, circleVertexBuffer);

            //   Gdx.gl11.glEnable(GL11.GL_ALPHA_TEST);
            //   Gdx.gl11.glAlphaFunc(GL11.GL_GREATER, 0.1f);

            //  Gdx.gl11.glEnable(GL11.GL_BLEND);
            //  Gdx.gl11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            //Gdx.gl11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

            Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
            Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

            Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);
            tex.bind();

            //for(int i = 0; i < vertexCount; i += (slices+1)*2)
            //  {
            //        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, i, (slices+1)*2);
            //      }
            Gdx.gl11.glPushMatrix();
            //Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, circleVertexBuffer );
            Gdx.gl11.glNormal3f(0.0f, 0.0f, 1.0f);
            Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_FAN, 0, 7);
            Gdx.gl11.glPopMatrix();

            Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
            Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

            //  Gdx.gl11.glDisable(GL11.GL_BLEND);
            //  Gdx.gl11.glDisable(GL11.GL_ALPHA_TEST);

            //  Gdx.gl11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        }

    }


