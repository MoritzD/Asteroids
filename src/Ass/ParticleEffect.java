package Ass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;

public class ParticleEffect
{
	FloatBuffer vertexBuffer;
	FloatBuffer texCoordBuffer;
	float rotationAngle = 0;
    Point3D pos = new Point3D(0.0f,0.0f,0.0f);
    float minTimeToLive=0.5f, maxTimeToLive=0.8f;
    int nbrOfPart = 1000;

    boolean burning = false;
	
	Texture tex;
	
	private class Particle
	{
		public Vector3D speed;
		public Point3D position;
		public float timeLived;
		public float timeToLive;
		public float orientationX;
		public float orientationY;
	}
	
	Particle[] particles;
	
	public ParticleEffect(String s)
	{
			vertexBuffer = BufferUtils.newFloatBuffer(12);
			vertexBuffer.put(new float[] {-0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f,
										  0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f});
			vertexBuffer.rewind();
			
			texCoordBuffer = BufferUtils.newFloatBuffer(8);
			texCoordBuffer.put(new float[] {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f});
			texCoordBuffer.rewind();
			
			tex = particleEffectSetTexture(s);

			
			particles = new Particle[nbrOfPart];
			for(int i = 0; i < nbrOfPart; i++)
			{
				particles[i] = new Particle();
                    particles[i].timeLived = 0;
                    particles[i].timeToLive = (float) Math.random() * maxTimeToLive-minTimeToLive + minTimeToLive;
                    particles[i].speed = new Vector3D((float) Math.random() - 0.5f, (float) Math.random(), (float) Math.random() - 0.5f);
                    particles[i].position = new Point3D(0.0f, 0.0f, 0.0f);
                    particles[i].orientationX = (float) Math.random() * 180.0f;
                    particles[i].orientationY = (float) Math.random() * 180.0f;
                if(!burning) {
                    particles[i].timeLived = particles[i].timeToLive;
                }
            }
    }
    public Texture particleEffectSetTexture(String filename){

        return new Texture (Gdx.files.internal(filename));

    }
	
	public void update(float deltaTime)
	{


		for(int i = 0; i < nbrOfPart; i++)
		{
			if(particles[i].timeLived < particles[i].timeToLive)
			{
				particles[i].position.add(Vector3D.scale( particles[i].speed,deltaTime));
				if(particles[i].timeToLive != 0)
				{
					particles[i].speed.x = particles[i].speed.x - particles[i].speed.x* (1 - ((particles[i].timeToLive - particles[i].timeLived) / particles[i].timeToLive));
					particles[i].speed.z = particles[i].speed.z - particles[i].speed.z* (1 - ((particles[i].timeToLive - particles[i].timeLived) / particles[i].timeToLive));
				}
                if(!burning) {
                    particles[i].timeLived += deltaTime*10;
                }else{
                    particles[i].timeLived += deltaTime;

                }
			}
			else if (burning)
			{
				particles[i].timeLived = 0;
				particles[i].timeToLive = (float)Math.random() * 1.5f + 0.1f;
                particles[i].speed = new Vector3D((float)Math.random() - 0.5f, (float)Math.random(), (float)Math.random() - 0.5f);
				particles[i].position = new Point3D(0.0f, 0.0f, 0.0f);
				particles[i].orientationX = (float)Math.random() * 180.0f;
				particles[i].orientationY = (float)Math.random() * 180.0f;
			}else{
                particles[i].timeLived = 0;
                particles[i].timeToLive = (float)Math.random() * 1.5f + 0.1f;
                particles[i].speed = new Vector3D((float)Math.random() - 0.5f, (float)Math.random(), (float)Math.random() - 0.5f);
                particles[i].position = new Point3D(0.0f, 0.0f, 0.0f);
                particles[i].orientationX = (float)Math.random() * 180.0f;
                particles[i].orientationY = (float)Math.random() * 180.0f;
            }
		}

	}
    public void reset(){
        for(int i = 0; i < nbrOfPart /2; i++)
        {

            particles[i].timeLived = (float)Math.random()*0.9f;
            particles[i].timeToLive = (float)Math.random() * 1.5f + 0.1f;
            particles[i].speed = new Vector3D((float)Math.random() - 0.5f, (float)Math.random(), (float)Math.random() - 0.5f);
            particles[i].position = new Point3D(0.0f, 0.0f, 0.0f);
            particles[i].orientationX = (float)Math.random() * 180.0f;
            particles[i].orientationY = (float)Math.random() * 180.0f;
        }
        for(int i = nbrOfPart /2; i < nbrOfPart; i++)
        {

            particles[i].timeLived = (float)Math.random()*0.5f; //(float)Math.random()*0.2f + 0.3f;
            particles[i].timeToLive = (float)Math.random() * 1.5f + 0.4f; //(float)Math.random() * 0.3f;
            particles[i].speed = new Vector3D((float)Math.random() - 0.5f, (float)Math.random(), (float)Math.random() - 0.5f);
            particles[i].position = new Point3D(0.0f, 0.0f, 0.0f);
            particles[i].orientationX = (float)Math.random() * 180.0f;
            particles[i].orientationY = (float)Math.random() * 180.0f;
        }
        burning = true;
    }
    public void end(){
        burning = false;
    }
	
	public void display()
	{

		Gdx.gl11.glShadeModel(GL11.GL_SMOOTH);
		Gdx.gl11.glVertexPointer(3, GL11.GL_FLOAT, 0, vertexBuffer);
		
		Gdx.gl11.glEnable(GL11.GL_TEXTURE_2D);
		Gdx.gl11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		tex.bind();  //Gdx.gl11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

		Gdx.gl11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, texCoordBuffer);

		Gdx.gl11.glEnable(GL11.GL_BLEND);
		Gdx.gl11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		
		Gdx.gl11.glDisable(GL11.GL_DEPTH_TEST);

		float[] materialEm = {1.0f, 1.0f, 1.0f, 1.0f};
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_EMISSION, materialEm, 0);


		Gdx.gl11.glPushMatrix();

        Gdx.gl11.glTranslatef(pos.x,pos.y,pos.z);

        Gdx.gl11.glRotatef(rotationAngle, 0.0f, 0.0f, 1.0f);

		for(int i = 0; i < nbrOfPart; i++)
		{

			float intensity;
			if(particles[i].timeLived < 0.3)
			{
				intensity = 1.0f - ((0.3f - particles[i].timeLived) / 0.3f);
			}
			else if(particles[i].timeLived < particles[i].timeToLive - 1.2)
			{
				intensity = 1.0f;
			}
			else
			{
				intensity = 1 - ((particles[i].timeLived - (particles[i].timeToLive - 1.2f)) / 1.2f);
			}
			
			float[] materialDiffuse = {0.0f, 0.0f, 0.0f, intensity * 0.03f};
			Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);
			

			Gdx.gl11.glPushMatrix();
			Gdx.gl11.glTranslatef(particles[i].position.x, particles[i].position.y, particles[i].position.z);
			Gdx.gl11.glScalef(intensity*0.4f, intensity*0.4f, intensity*0.4f);
			Gdx.gl11.glRotatef(particles[i].orientationX, 1.0f, 0.0f, 0.0f);
			Gdx.gl11.glRotatef(particles[i].orientationY, 0.0f, 1.0f, 0.0f);
			Gdx.gl11.glNormal3f(0.0f, 0.0f, -1.0f);
			Gdx.gl11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
			Gdx.gl11.glPopMatrix();
		}

		Gdx.gl11.glPopMatrix();
		

		materialEm[3] = 0.0f;
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_EMISSION, materialEm, 0);


		Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);

		Gdx.gl11.glDisable(GL11.GL_BLEND);

		Gdx.gl11.glDisable(GL11.GL_TEXTURE_2D);
		Gdx.gl11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		

		materialEm[0] = 0.0f;
		materialEm[1] = 0.0f;
		materialEm[2] = 0.0f; 
		materialEm[3] = 1.0f;
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_EMISSION, materialEm, 0);
	}
}
