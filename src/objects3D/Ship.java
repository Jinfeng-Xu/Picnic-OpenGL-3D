package objects3D;


import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Ship {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    static float green[] = { 0.0f, 1.0f, 0.0f, 1.0f };
    static float blue[] = { 0.0f, 0.0f, 1.0f, 1.0f };

    // secondary colours
    static float yellow[] = { 1.0f, 1.0f, 0.0f, 1.0f };
    static float magenta[] = { 1.0f, 0.0f, 1.0f, 1.0f };
    static float cyan[] = { 0.0f, 1.0f, 1.0f, 1.0f };

    // other colours
    static float orange[] = { 1.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float brown[] = { 0.45f, 0.32f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

    public Ship() {

    }


    public void DrawShip(float delta,boolean Status, Texture text1) {
        float LimbRotation;

        if (Status) {
            LimbRotation = (float) (Math.cos(delta * 0.2 * Math.PI));
        }else {
            LimbRotation =0;
        }

        Sphere sphere= new Sphere();
        Cylinder cylinder= new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Cube cube = new Cube();

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        GL11.glPushMatrix();{
            GL11.glScalef(3, 0.2f, 2.5f);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            text1.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            texcube.DrawTexCube();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
//            cube.DrawCube();
            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            for (int i = 1; i <= 30; i++){
                GL11.glPushMatrix();{
//                    System.out.println("i: " + i);
                    GL11.glScalef(1.0f + (float)(i * 0.01), 0.11f, 1.0f + (float)(i * 0.01));
                    GL11.glTranslatef(0.0f, 1.2f + i, 0.0f);
                GL11.glTexParameteri(
                        GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                        GL11.GL_CLAMP);
                Color.white.bind();
                text1.bind();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                texcube.DrawTexCube();
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                    cube.DrawCube();
                }GL11.glPopMatrix();
            }
        }GL11.glPopMatrix();

        GL11.glColor4f(black[0], black[1], black[2], 0.6f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(yellow));
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glScalef(1.5f, 1.5f, 3.5f);
            GL11.glTranslatef(0.0f, 0f, -0.4f);
            cylinder.DrawCylinder(1.2f,1,1000);
        }GL11.glPopMatrix();
        GL11.glColor4f(brown[0], brown[1], brown[2], 0.8f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(yellow));
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glScalef(1.5f, 1.5f, 3.5f);
            GL11.glTranslatef(0.0f, 0f, -0.4f);
            cylinder.DrawCylinder(1.5f,1,1000);
        }GL11.glPopMatrix();

        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,0,-3.5f);
            GL11.glRotatef(90,1,0,0);
            GL11.glScalef(3, 0.2f, 0f);

            GL11.glEnable(GL11.GL_TEXTURE_2D);
            cube.DrawCube();
            GL11.glColor3f(black[0], black[1], black[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            for (int i = 1; i <= 30; i++){
                GL11.glPushMatrix();{
                    GL11.glScalef(1.0f + (float)(i * 0.01), 0.11f, 1.0f + (float)(i * 0.01));
                    GL11.glTranslatef(0.0f, 1.2f + i, 0.0f);
                    cube.DrawCube();
                }GL11.glPopMatrix();
            }
        }GL11.glPopMatrix();
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(yellow));
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,0,-4f);
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glScalef(1.5f, 0f, 3.5f);
            GL11.glTranslatef(0.0f, 0f, -0.4f);
            cylinder.DrawCylinder(0.6f,1,1000);
        }GL11.glPopMatrix();

    }


}
