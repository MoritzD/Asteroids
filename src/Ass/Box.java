package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created by moe on 27.09.14.
 */
public class Box {
    private static FloatBuffer vertexBuffer;
    private static Boolean vertexBufferExists = false;

    protected Point3D nwCorner;         // north west Corner
    protected Point3D seCorner;         // south east Corner
    protected Vector3D mScale;
    float[] materialDiffuse = new float[4];
    private boolean wall=false;


    public Box(Point3D pos,Vector3D scale,float[] materialDiffuse) {
        if(!vertexBufferExists){
            vertexBuffer = BufferUtils.newFloatBuffer(72);
            vertexBuffer.put(new float[]{-0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
                    0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f});
            vertexBuffer.rewind();
            vertexBufferExists = true;
            System.out.println("Creating new Box vertex buffer");
        }

        nwCorner = pos;
        seCorner = new Point3D(pos.x*scale.x,pos.y*scale.y,pos.z*scale.z);
        mScale = scale;
        this.materialDiffuse[0] = materialDiffuse[0];
        this.materialDiffuse[1] = materialDiffuse[1];
        this.materialDiffuse[2] = materialDiffuse[2];
        this.materialDiffuse[3] = materialDiffuse[3];
    }
    public Box(Point3D pos,Vector3D scale,float[] materialDiffuse,boolean wall) {
        if(!vertexBufferExists){
            vertexBuffer = BufferUtils.newFloatBuffer(72);
            vertexBuffer.put(new float[]{-0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
                    0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f});
            vertexBuffer.rewind();
            vertexBufferExists = true;
            System.out.println("Creating new Box vertex buffer");
        }

        nwCorner = pos;
        seCorner = new Point3D(pos.x*scale.x,pos.y*scale.y,pos.z*scale.z);
        mScale = scale;
        this.materialDiffuse[0] = materialDiffuse[0];
        this.materialDiffuse[1] = materialDiffuse[1];
        this.materialDiffuse[2] = materialDiffuse[2];
        this.materialDiffuse[3] = materialDiffuse[3];

        this.wall = wall;
    }
    public Box(Point3D pos,boolean south, boolean extended) { //true: south false: east
        if(!vertexBufferExists){
            vertexBuffer = BufferUtils.newFloatBuffer(72);
            vertexBuffer.put(new float[]{-0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,
                    0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
                    0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
                    -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f,
                    0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,
                    -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f,
                    0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f});
            vertexBuffer.rewind();
            vertexBufferExists = true;
            System.out.println("Creating new Box vertex buffer");
        }


        if(!south) {
            if(extended)
                mScale = new Vector3D(6.0f, 3.0f, 1.0f);
            else
                mScale = new Vector3D(5.0f, 3.0f, 1.0f);
            nwCorner = pos;
            nwCorner.x = nwCorner.x * 5.0f;
            nwCorner.z = nwCorner.z * 5.0f;
            nwCorner.x += 0.5f;
            nwCorner.z -= 0.5f;
            seCorner = new Point3D(nwCorner.x*mScale.x,nwCorner.y*mScale.y,nwCorner.z*mScale.z);

        }
        else{
            if(extended)
                mScale = new Vector3D(1.0f, 3.0f, 6.0f);
            else
                mScale = new Vector3D(1.0f,3.0f,5.0f);
            nwCorner = pos;
            nwCorner.x = nwCorner.x * 5.0f;
            nwCorner.z = nwCorner.z * 5.0f;
            nwCorner.z -= 0.5f;
            nwCorner.x -= 0.5f;
            seCorner = new Point3D(nwCorner.x*mScale.x,nwCorner.y*mScale.y,nwCorner.z*mScale.z);
        }

        materialDiffuse = new float[] {1.0f, 1.0f, 0.0f, 1.0f};
        this.wall = true;
    }

    protected void draw()
    {
        Gdx.gl11.glPushMatrix();

        Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);

        Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);

        Gdx.gl11.glTranslatef(nwCorner.x, nwCorner.y, nwCorner.z);
        Gdx.gl11.glScalef(mScale.x,mScale.y,mScale.z);

        if(wall) {
            Gdx.gl11.glTranslatef(0.5f,0.5f,0.5f);
        }



        Gdx.gl11.glNormal3f(0.0f, 0.0f, -1.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
        Gdx.gl11.glNormal3f(1.0f, 0.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 4, 4);
        Gdx.gl11.glNormal3f(0.0f, 0.0f, 1.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 8, 4);
        Gdx.gl11.glNormal3f(-1.0f, 0.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 12, 4);
        Gdx.gl11.glNormal3f(0.0f, 1.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 16, 4);
        Gdx.gl11.glNormal3f(0.0f, -1.0f, 0.0f);
        Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 20, 4);

        Gdx.gl11.glPopMatrix();
    }

}
