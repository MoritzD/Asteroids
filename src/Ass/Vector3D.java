package Ass;

public class Vector3D
{
	public float x;
	public float y;
	public float z;
	
	public Vector3D(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
    // Create vector from point p1 to point p2
    public Vector3D(Point3D p1, Point3D p2){
        x = p2.x - p1.x;
        y = p2.y - p1.y;
        z = p2.z - p1.z;
    }
    public static Vector3D point3DToVector(Point3D p1){

        return new Vector3D(p1.x, p1.y, p1.z);

    }
	
	public void set(float xx, float yy, float zz)
	{
		x = xx;
		y = yy;
		z = zz;
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x+y*y+z*z);
	}
	
	public void normalize()
	{
		float len = length();
		x = x / len;
		y = y / len;
		z = z / len;
	}
	
	public static Vector3D difference(Point3D P1, Point3D P2)
	{
		return new Vector3D(P1.x - P2.x, P1.y - P2.y, P1.z - P2.z);
	}
	
	public static float dot(Vector3D v1, Vector3D v2)
	{
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}
	
	public static Vector3D cross(Vector3D P1, Vector3D P2)
	{
		return new Vector3D(P1.y*P2.z - P1.z*P2.y, P1.z*P2.x - P1.x*P2.z, P1.x*P2.y - P1.y*P2.x);
	}
	
	public static Vector3D scale(Vector3D v, float s)
	{
		return new Vector3D(v.x*s, v.y*s, v.z*s);
	}
    public void times(float s){
        x*=s;
        y*=s;
        z*=s;
    }
	
	public static Vector3D add(Vector3D v1, Vector3D v2)
	{
		return new Vector3D(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
	}
    public Vector3D getNewInstance(){
        return new Vector3D(this.x,this.y,this.z);
    }
    public  Vector3D addition(Vector3D v){
       return new Vector3D( this.x+v.x,this.y+v.y,this.z+v.z);
    }
    public boolean isNullVector3D(){
        if(this.x+this.y+this.z==0)
           return true;
        else
           return false;
    }
    public void transfer(Vector3D v){
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public static Vector3D inverse(Vector3D v) {
        v.x *=-1;
        v.y *=-1;
        v.z *=-1;
        return v;
    }
    // This method subtracts the values of v2 from v1 ergo v1 - v2
    public static Vector3D subtraction(Vector3D v1, Vector3D v2){

        return new Vector3D(v1.x-v2.x,v1.y-v2.y,v1.z-v2.z);

    }
    public boolean isCollinear(Vector3D v){
        if(Vector3D.cross(this,v).isNullVector3D()) {

            return true;
        }
        else
            return false;
    }
    public void printVector3D(){
        System.out.println("v.x = "+x+", v.y ="+y+", v.z ="+z);
    }
    public void addScalar(float s){
        x+=s;
        y+=s;
        z+=s;
    }

}
