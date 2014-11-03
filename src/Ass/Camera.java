package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

public class Camera
{
	public Point3D eye;
	Vector3D u;
	Vector3D v;
	Vector3D n;
    Vector3D up;

	float left;
	float right;
	float bottom;
	float top;
	float N;
	float F;

	public Camera()
	{
	}
	
	public void lookAt(Point3D pEye, Point3D pCenter, Vector3D up)
	{
		eye = pEye;
		n = Vector3D.difference(pEye, pCenter);
		n.normalize();
		u = Vector3D.cross(up, n);
		u.normalize();
		v = Vector3D.cross(n, u);
        this.up=up;
	}
	
	private float tangent(float angle)
	{
		return MathUtils.sin(angle * MathUtils.PI / 180.0f) / MathUtils.cos(angle * MathUtils.PI / 180.0f);
	}
	
	public void perspective(float angle, float ratio, float N, float F)
	{
		top = N * tangent(angle / 2.0f);
		bottom = -top;
		right = top * ratio;
		left = -right;
		
		this.N = N;
		this.F = F;
	}
	
	public void setModelViewMatrix()
	{
		Vector3D minusEye = Vector3D.difference(new Point3D(0,0,0), eye);

		
		float[] matrix = new float[16];
		matrix[0] = u.x;	matrix[4] = u.y;	matrix[8] = u.z;	matrix[12] = Vector3D.dot(minusEye, u);
		matrix[1] = v.x;	matrix[5] = v.y;	matrix[9] = v.z;	matrix[13] = Vector3D.dot(minusEye, v);
		matrix[2] = n.x;	matrix[6] = n.y;	matrix[10] = n.z;	matrix[14] = Vector3D.dot(minusEye, n);
		matrix[3] = 0;		matrix[7] = 0;		matrix[11] = 0;		matrix[15] = 1;
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
        FloatBuffer matrixbuff =  BufferUtils.newFloatBuffer(16).put(matrix);
        matrixbuff.rewind();
		GL11.glLoadMatrix(matrixbuff);
	}
	
	public void setProjectionMatrix()
	{
		float[] matrix = new float[16];
		matrix[0] = 2*N / (right-left);	matrix[4] = 0;	matrix[8] = 0;	matrix[12] = 0;
		matrix[1] = 0;	matrix[5] = 2*N / (top-bottom);	matrix[9] = 0;	matrix[13] = 0;
		matrix[2] = 0;	matrix[6] = 0;	matrix[10] = -(F+N)/(F-N);	matrix[14] = -(2*F*N)/(F-N);
		matrix[3] = 0;		matrix[7] = 0;		matrix[11] = -1;		matrix[15] = 0;

		GL11.glMatrixMode(GL11.GL_PROJECTION);
        FloatBuffer matrixbuff =  BufferUtils.newFloatBuffer(16).put(matrix);
        matrixbuff.rewind();
		GL11.glLoadMatrix(matrixbuff);
	}
	
	public void setMatrices()
	{
		setProjectionMatrix();
		setModelViewMatrix();
	}
	
	public void slide(float delU, float delV, float delN)
	{
		eye.add(Vector3D.scale(u, delU));
		eye.add(Vector3D.scale(v, delV));
		eye.add(Vector3D.scale(n, delN));
	}

    public void slide2d(float delU, float delV, float delN) {
        eye.add(Vector3D.scale(u, delU));
        eye.add(Vector3D.scale(v, delV));
        eye.add(Vector3D.scale(n, delN));
        eye.y=1.0f;
    }
    public Point3D wouldBeSlide2d(float delU, float delV, float delN) {
        Point3D eyetemp = eye.getNewInstance();
        eyetemp.add(Vector3D.scale(u, delU));
        eyetemp.add(Vector3D.scale(v, delV));
        eyetemp.add(Vector3D.scale(n, delN));
        eyetemp.y=1.0f;
        return eyetemp;
    }

	
	public void roll(float angle)
	{
		float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
		float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);
		
		Vector3D newU = Vector3D.add(Vector3D.scale(u, cos), Vector3D.scale(v, sin));
		v = Vector3D.add(Vector3D.scale(u, -sin), Vector3D.scale(v, cos));
		u = newU;
	}

	public void yaw(float angle)
	{
		float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
		float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);
		
		Vector3D newN = Vector3D.add(Vector3D.scale(n, cos), Vector3D.scale(u, sin));
		u = Vector3D.add(Vector3D.scale(n, -sin), Vector3D.scale(u, cos));
		n = newN;
	}
    public void yawup(float angle)
    {
        yaw(angle);
       /* float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
        float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);

        v.normalize();
        u.normalize();
        n.normalize();

        v.x=v.x-cos;
        v.y=v.y-sin;
        u.x=u.x-cos;
        u.y=u.y-sin;
        n.x=n.x-cos;
        n.y=n.y-sin;
        v.normalize();
        u.normalize();
        n.normalize();
*/
    }

	public void pitch(float angle)
	{
		float cos = MathUtils.cos(angle * MathUtils.PI / 180.0f);
		float sin = MathUtils.sin(angle * MathUtils.PI / 180.0f);
		
		Vector3D newV = Vector3D.add(Vector3D.scale(v, cos), Vector3D.scale(n, sin));
		n = Vector3D.add(Vector3D.scale(v, -sin), Vector3D.scale(n, cos));
		v = newV;
	}
}
