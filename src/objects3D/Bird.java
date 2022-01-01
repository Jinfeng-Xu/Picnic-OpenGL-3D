package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Bird {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 0.8f, 1.0f, 0.9f, 1.0f };

    static float grey[] = { 0.6f, 0.6f, 0.6f, 0.8f };
    static float spot[] = { 0.1f, 0.1f, 0.1f, 0.5f };

    // primary colours
    static float red[] = { 0.7f, 0.3f, 0.2f, 1.0f };
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

    public Bird() {

    }

    // Draw a Bird
    public void DrawBird(float delta, boolean toward, Texture text1, Texture text2) {
        float LimbRotation;
        float theta = (float) (delta * 2 * Math.PI);
        float limb;

        if (true) {
            LimbRotation = (float) (Math.cos(delta * 10 * Math.PI));
            limb = (float) (Math.cos(theta));
        } else {
            LimbRotation = 0;
            limb = 0;
        }
        Sphere sphere = new Sphere();
        Cylinder cylinder = new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Cube cube = new Cube();
        Tetrahedron tetrahedron = new Tetrahedron();
        Wafer wafer = new Wafer();

        GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
        GL11.glPushMatrix();
        {

            GL11.glRotatef(90, 1, 0, 0);
            if(!toward) {
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glRotatef(180, 1, 0, 0);
            }
            GL11.glScalef(1, 1, 1);
            sphere.DrawSphere(60, 32, 32);
            GL11.glColor4f(yellow[0], yellow[1], yellow[2], 1f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,0,0);
                GL11.glRotatef(90,0,0,1);
                GL11.glScalef(40, 40, 40);
                tetrahedron.DrawTetrahedron();
            }GL11.glPopMatrix();
            //eyes
            GL11.glColor4f(black[0], black[1], black[2], 1f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,-55,-20);
                GL11.glScalef(15, 10, 15);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,55,-20);
                GL11.glScalef(15, 10, 15);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            //pupil
            GL11.glColor4f(white[0], white[1], white[2], 1f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,-57,-25);
                GL11.glScalef(7, 7, 7);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,57,-25);
                GL11.glScalef(7, 7, 7);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            // body
            GL11.glColor4f(pink[0], pink[1], pink[2], 1f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(pink));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(80,0,0);
                GL11.glScalef(1.6f, 1.2f, 1);
                GL11.glTexParameteri(
                        GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                        GL11.GL_CLAMP);
                Color.white.bind();
                text2.bind();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                texsphere.DrawTexSphere(60, 32, 32, text2);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
//                sphere.DrawSphere(60, 32, 32);
                // swing
                GL11.glColor4f(white[0], white[1], white[2], 1f);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();
                {
                    GL11.glRotatef(10+LimbRotation*30,1,0,0);
                    GL11.glTranslatef(15,-60,0);
                    GL11.glScalef(32, 30, 6);
                    GL11.glRotatef(45,0,0,1);
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
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glRotatef(-(10+LimbRotation*30),1,0,0);
                    GL11.glTranslatef(15,60,0);
                    GL11.glScalef(32, 30, 6);
                    GL11.glRotatef(45,0,0,1);
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
                }GL11.glPopMatrix();

                GL11.glColor4f(brown[0], brown[1], brown[2], 1f);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(brown));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(50,-40,15);
                    GL11.glRotatef(-20,0,1,0);
                    GL11.glScalef(32, 5, 6);
                    GL11.glRotatef(45,0,1,0);
                    cube.DrawCube();
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(50,40,15);
                    GL11.glRotatef(-20,0,1,0);
                    GL11.glScalef(32, 5, 6);
                    GL11.glRotatef(45,0,1,0);
                    cube.DrawCube();
                }GL11.glPopMatrix();
                GL11.glColor4f(grey[0], grey[1], grey[2], 1f);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(grey));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(50,-15,0);
                    GL11.glRotatef(15,0,1,0);
                    GL11.glScalef(30, 45, 10);
                    GL11.glRotatef(90,0,0,1);
                    tetrahedron.DrawTetrahedron();
                }GL11.glPopMatrix();
            }GL11.glPopMatrix();
        }
        GL11.glPopMatrix();


        //shadow
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
        GL11.glPushMatrix();
        {
            GL11.glTranslatef(0,-1780,0);
            GL11.glRotatef(90, 1, 0, 0);
            if(!toward) {
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glRotatef(180, 1, 0, 0);
            }
            GL11.glScalef(1, 1, 0);
            sphere.DrawSphere(60, 32, 32);
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,0,0);
                GL11.glRotatef(90,0,0,1);
                GL11.glScalef(40, 40, 40);
                tetrahedron.DrawTetrahedron();
            }GL11.glPopMatrix();
            //eyes
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,-55,-20);
                GL11.glScalef(15, 10, 15);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,55,-20);
                GL11.glScalef(15, 10, 15);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            //pupil
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,-57,-25);
                GL11.glScalef(7, 7, 7);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(-30,57,-25);
                GL11.glScalef(7, 7, 7);
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,1,0);
                wafer.DrawWafer();
            }GL11.glPopMatrix();
            // body
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(80,0,0);
                GL11.glScalef(1.6f, 1.2f, 1);
                sphere.DrawSphere(60, 32, 32);
                // swing
                GL11.glPushMatrix();
                {
                    GL11.glRotatef(10+LimbRotation*30,1,0,0);
                    GL11.glTranslatef(15,-60,0);
                    GL11.glScalef(32, 30, 6);
                    GL11.glRotatef(45,0,0,1);
                    cube.DrawCube();
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glRotatef(-(10+LimbRotation*30),1,0,0);
                    GL11.glTranslatef(15,60,0);
                    GL11.glScalef(32, 30, 6);
                    cube.DrawCube();
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(50,-40,15);
                    GL11.glRotatef(-20,0,1,0);
                    GL11.glScalef(32, 5, 6);
                    GL11.glRotatef(45,0,1,0);
                    cube.DrawCube();
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(50,40,15);
                    GL11.glRotatef(-20,0,1,0);
                    GL11.glScalef(32, 5, 6);
                    GL11.glRotatef(45,0,1,0);
                    cube.DrawCube();
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(50,-15,0);
                    GL11.glRotatef(15,0,1,0);
                    GL11.glScalef(30, 45, 10);
                    GL11.glRotatef(90,0,0,1);
                    tetrahedron.DrawTetrahedron();
                }GL11.glPopMatrix();
            }GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}
