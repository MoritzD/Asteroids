package Ass;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by Alexander on 04/11/2014.
 */
public class Asteroid {

    Dodecahedron dodo;
    Point3D center;
    Vector3D moveVector;
    float speed;
    float deltaTime;
    float radius;
    Random rand = new Random();

    boolean killME = false;
    boolean explode = false;

    Asteroid(Dodecahedron dodo) {
        center = new Point3D(0, 0, 0);
        speed = rand.nextFloat() * 5.0f+2;
        radius = rand.nextFloat() * 10;
        this.dodo = dodo;

    }

    public void draw() {

        Gdx.gl11.glTranslatef(center.x,center.y,center.z);
        Gdx.gl11.glScalef(radius,radius,radius);
        dodo.draw();

    }
    public void setCenter(float x, float y, float z){

        center.x=x;
        center.y=y;
        center.z=z;

    }

    public void movement() {
        deltaTime = Gdx.graphics.getDeltaTime() * 0.5f;

        if(explode){
            dodo.explodefactor += 5 * deltaTime;

            if(0 < dodo.explodeScaleFactor ) {
                dodo.explodeScaleFactor -= 2 * deltaTime;
                // Discard object!
            }else if( dodo.explodeScaleFactor <= 0){
                killME = true;
            }
        }
        if (moveVector == null) {
            float temp1, temp2, temp3;
            temp1 = rand.nextInt(3);
            if (temp1 == 2) {
                temp1 = -1;
            }
            temp2 = rand.nextInt(3);
            if (temp2 == 2) {
                temp2 = -1;
            }
            temp3 = rand.nextInt(3);
            if (temp3 == 2) {
                temp3 = -1;
            }
            if (temp1 == 0 && temp2 == 0 && temp3 == 0) {
                temp1 = rand.nextInt(2);
                temp2 = rand.nextInt(2);
                temp3 = rand.nextInt(2);
            }
            moveVector = new Vector3D(temp1, temp2, temp3);
            this.createMoveVector(speed);

        } else {

            center.add(Vector3D.scale(moveVector, deltaTime));
        }

    }

    public void explode(){
        explode = true;
    }



    private void createMoveVector(float speed) {

        if (moveVector.x == 0.0f)
            ;
        else {

            if (moveVector.x > 0) {
                moveVector.x += speed;
            } else {
                moveVector.x -= speed;
            }

        }

        if (moveVector.y == 0.0f)
            ;
        else {

            if (moveVector.y > 0) {
                moveVector.y += speed;
            } else {
                moveVector.y -= speed;
            }
        }

        if (moveVector.z == 0.0f)
            ;
        else {

            if (moveVector.z > 0) {
                moveVector.z += speed;
            } else {
                moveVector.z -= speed;
            }
        }
    }

    public void collisionHandling(Dodecahedron ast) {

        this.moveVector.times(-1);
    }

    public void collision(Dodecahedron ast) {

        if ((this.center.x - ast.center.x) * (this.center.x - ast.center.x) +
                (this.center.y - ast.center.x) * (this.center.y - ast.center.x) +
                (this.center.z - ast.center.z) * (this.center.z - ast.center.z)
                <= (this.radius + ast.radius) * (this.radius + ast.radius)) {
            collisionHandling(ast);
        }

    }
}
