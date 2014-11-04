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
    // calculates the distance between two 3D points caller this and callee p2
    public float distance(Point3D p2){
        float distanceSquaredX = this.x-p2.x;
        float distanceSquaredY = this.y-p2.y;
        float distanceSquaredZ = this.z-p2.z;
        distanceSquaredX*=distanceSquaredX;
        distanceSquaredY*=distanceSquaredY;
        distanceSquaredZ*=distanceSquaredZ;
        return (float)Math.sqrt(distanceSquaredX+distanceSquaredY+distanceSquaredZ);
    }

    public static Point3D vector3DToPoint(Vector3D scale) {
        return new Point3D(scale.x,scale.y,scale.z);
    }
}
