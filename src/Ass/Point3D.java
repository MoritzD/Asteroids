package Ass;

public class Point3D
{
	public float x;
	public float y;
	public float z;
	
	public Point3D(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public void set(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public void add(Vector3D v)
	{
		x = x + v.x;
		y = y + v.y;
		z = z + v.z;
	}
    public Point3D getNewInstance(){
        return new Point3D(this.x,this.y,this.z);
    }
}
