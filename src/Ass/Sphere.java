package Ass;

/**
 * Created by Alexander on 23/10/2014.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.graphics.Texture;

import java.nio.FloatBuffer;

public class Sphere {


        private static FloatBuffer vertexBuffer;
        int stacks;
        int slices;
        private static FloatBuffer normalBuffer;
        private static FloatBuffer texCoordBuffer;
        int vertexCount;
        boolean vertexExists = false;
        private Texture tex;
        private Point3D position = new Point3D(0,0,0);
        private Vector3D scale = new Vector3D(1,1,1);

    public void setRotationAngle(float rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    private float rotationAngle;

        public Sphere(int i_stacks, int i_slices)
        {
            if(!vertexExists) {


                stacks = i_stacks;
                slices = i_slices;
                vertexCount = 0;
                float[] array = new float[(stacks)*(slices+1)*6];
                float stackInterval = (float)Math.PI / (float)stacks;
                float sliceInterval = 2.0f*(float)Math.PI / (float)slices;

                float[] texArray = new float[(stacks)*(slices+1)*4];

                float stackAngle, sliceAngle;
                for(int stackCount = 0; stackCount < stacks; stackCount++)
                {
                    stackAngle = stackCount * stackInterval;
                    for(int sliceCount = 0; sliceCount < slices+1; sliceCount++)
                    {
                        sliceAngle = sliceCount * sliceInterval;
                        array[vertexCount*3] = 	 (float)Math.sin(stackAngle) * (float)Math.cos(-sliceAngle);
                        array[vertexCount*3 + 1] = (float)Math.cos(stackAngle);
                        array[vertexCount*3 + 2] = (float)Math.sin(stackAngle) * (float)Math.sin(-sliceAngle);

                        array[vertexCount*3 + 3] = (float)Math.sin(stackAngle + stackInterval) * (float)Math.cos(-sliceAngle);
                        array[vertexCount*3 + 4] = (float)Math.cos(stackAngle + stackInterval);
                        array[vertexCount*3 + 5] = (float)Math.sin(stackAngle + stackInterval) * (float)Math.sin(-sliceAngle);

                        texArray[vertexCount*2] = (float)sliceCount / (float)slices;
                        texArray[vertexCount*2 + 1] = (float)stackCount / (float)stacks;
                        texArray[vertexCount*2 + 2] = (float)sliceCount / (float)slices;
                        texArray[vertexCount*2 + 3] = (float)(stackCount+1) / (float)stacks;

                        vertexCount += 2;
                    }
                }
                vertexBuffer = BufferUtils.newFloatBuffer(vertexCount*3);
                vertexBuffer.put(array);
                vertexBuffer.rewind();
                normalBuffer = BufferUtils.newFloatBuffer(vertexCount*3);
                normalBuffer.put(array);
                normalBuffer.rewind();
                texCoordBuffer = BufferUtils.newFloatBuffer(vertexCount*2);
                texCoordBuffer.put(texArray);
                texCoordBuffer.rewind();

                vertexExists = true;
            }

        }
         public void setPosition(float x, float y, float z) {

             position.x=x;
             position.y=y;
             position.z=z;

        }
        public void scale(float x, float y, float z){
            scale.x = x;
            scale.y = y;
            scale.z = z;
        }


        public void draw()
        {
            Gdx.gl11.glPushMatrix();
            Gdx.gl11.glShadeModel(GL11.GL_SMOOTH);
            Gdx.gl11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
            Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
            Gdx.gl11.glNormalPointer(GL11.GL_FLOAT, 0, normalBuffer);

            if(tex != null) {
                Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
                Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            }
            Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);
            if(tex != null) {
                tex.bind();
            }
            Gdx.gl11.glTranslatef(position.x, position.y,  position.z);
            Gdx.gl11.glScalef(scale.x,scale.y,scale.z);
            Gdx.gl11.glRotatef(rotationAngle, 0.0f, 1.0f, 0.0f);
            for(int i = 0; i < vertexCount; i += (slices+1)*2)
            {
                Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, i, (slices+1)*2);
            }
            Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
            Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            Gdx.gl11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
            Gdx.gl11.glPopMatrix();
        }

    public void setTexture(String fileLocation) {

        tex = new Texture(Gdx.files.internal(fileLocation));

    }
    public int getScale(){
        return (int)scale.x;
    }
    public Point3D getPosition(){
        return new Point3D(position.x,position.y,position.z);
    }
}


