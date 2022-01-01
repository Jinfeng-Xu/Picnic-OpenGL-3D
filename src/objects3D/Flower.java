package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;

public class Flower {

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

    public Flower() {

    }

    // to draw a brand. a dog running gif create by multiple pictures
    public void DrawFlower(float delta, boolean Status) {
        float LimbRotation;
        int lightFlag = (int) (delta * 50);

        if (Status) {
            LimbRotation = (float) (Math.cos(delta * 2 * Math.PI));
        }else {
            LimbRotation =0;
        }

        Sphere sphere= new Sphere();
        Cylinder cylinder= new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Cube cube = new Cube();
        TexCylinder texCylinder = new TexCylinder();
        Wafer wafer = new Wafer();

        GL11.glPushMatrix();
        {
            GL11.glRotatef(45, 0, 1, 0);
            GL11.glRotatef(-5+LimbRotation * 33, 0, 0, 1);
            // stem
            GL11.glColor3f(green[0], green[1], green[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 0, 0);
                GL11.glRotatef(90.0f, 1.0f, 0, 0);
                GL11.glRotatef(180.0f, 1.0f, 0, 0);
                GL11.glScalef(1.5f, 1.5f, 10.0f);
                cylinder.DrawCylinder(3.5f, 14, 32);
            }
            GL11.glPopMatrix();
            // flower
            GL11.glColor3f(yellow[0], yellow[1], yellow[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 130, 0);
                GL11.glRotatef(30.0f, 1f, 0, 0);
//                GL11.glScalef(1.5f, 1.5f, 10.0f);
                sphere.DrawSphere(22, 32, 32);
                GL11.glColor3f(red[0], red[1], red[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(24, 0, 0);
                    GL11.glScalef(26, 14f, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0, 24, 0);
                    GL11.glScalef(14, 26, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0, -24, 0);
                    GL11.glScalef(14, 26, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-24, 0, 0);
                    GL11.glScalef(26, 14f, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-16, -16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-16, 16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(15, -16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(16, 16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            GL11.glRotatef(-90,1,0,0);
            GL11.glRotatef(-5+LimbRotation*33,0,0,1);
            GL11.glRotatef(45,0,1,0);
            // stem
            GL11.glPushMatrix();
            {
                GL11.glColor3f(black[0], black[1], black[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                GL11.glTranslatef(0, 0, 0);
                GL11.glRotatef(90.0f, 1.0f, 0, 0);
                GL11.glRotatef(180.0f, 1.0f, 0, 0);
                GL11.glScalef(1.5f, 1.5f, 10.0f);
                cylinder.DrawCylinder(3.5f, 14, 32);
            }
            GL11.glPopMatrix();
            // flower
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0, 130, 0);
                GL11.glRotatef(30.0f, 1f, 0, 0);
//                GL11.glScalef(1.5f, 1.5f, 10.0f);
                sphere.DrawSphere(22, 32, 32);
                GL11.glColor3f(black[0], black[1], black[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(24, 0, 0);
                    GL11.glScalef(26, 14f, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0, 24, 0);
                    GL11.glScalef(14, 26, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0, -24, 0);
                    GL11.glScalef(14, 26, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-24, 0, 0);
                    GL11.glScalef(26, 14f, 20f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-16, -16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(-16, 16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(15, -16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(16, 16, 0);
                    GL11.glScalef(16f, 16f, 16f);
                    wafer.DrawWafer();
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }GL11.glPopMatrix();
    }
}
