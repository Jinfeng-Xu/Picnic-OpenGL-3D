package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Utils;

public class Swing {

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
    static float brown[] = { 0.5f, 0.25f, 0.0f, 1.0f, 1.0f };
    static float dkgreen[] = { 0.0f, 0.5f, 0.0f, 1.0f, 1.0f };
    static float pink[] = { 1.0f, 0.6f, 0.6f, 1.0f, 1.0f };

    public Swing() {

    }

    // Draw a Swing
    public void DrawSwing(float delta, float lemonDelta, boolean Status, boolean lemonDown, int lemonDownFinished, Texture text1, Texture text2, Texture text3, Texture text4) {

        float theta = (float) (delta * 2 * Math.PI);
        float theta2 = (float) (lemonDelta * 2 * Math.PI);
//        System.out.println("板凳THETA: " + theta);
        float LimbRotation;
        float LemonRotation;

        if (Status) {
            LimbRotation = (float) Math.cos(theta) * 45;
            LemonRotation = (float) Math.cos(theta2/3) * 55;
//            LemonRotation2 = (float) Math.cos(theta2/3f) * 55;
        } else {
            LimbRotation = 0;
            LemonRotation = 0;
//            LemonRotation2 = 0;
        }

        Cylinder cylinder = new Cylinder();
        Rectangle rectangle = new Rectangle();
        TexCylinder texCylinder = new TexCylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Wafer wafer = new Wafer();

        GL11.glColor3f(white[0], white[1], white[2]);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
        GL11.glPushMatrix();
        {
            GL11.glRotatef(-90,0,0,0);
            GL11.glScalef(1, 1, 1);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            text1.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            texCylinder.DrawCylinder(0.8f, 32, 32, text1);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
//            cylinder.DrawCylinder(0.8f,12,32);

            GL11.glColor3f(white[0], white[1], white[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f, 0.0f, 13.0f);
                GL11.glRotatef(0,0,0,0);
                GL11.glScalef(1, 1, 1);
//                sphere.DrawSphere(4f,32,32);
                GL11.glTexParameteri(
                        GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                        GL11.GL_CLAMP);
                Color.white.bind();
                text2.bind();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                texsphere.DrawTexSphere(4f, 32, 32, text2);
                GL11.glDisable(GL11.GL_TEXTURE_2D);

                GL11.glPushMatrix();
                {
                    if(!lemonDown){
                        GL11.glTranslatef(-2,3.5f,-1f);
//                        GL11.glTranslatef(-2,3.5f,-12.8f);
                    }
                    else if(lemonDownFinished == 0){
//                        System.out.println(LemonRotation);
                        GL11.glTranslatef(-2,3.5f,-14.245f+(float)(LemonRotation/4.1525));
//                        System.out.println(-12f+LemonRotation/4);
                    }
                    else if(lemonDownFinished == 1){
                        if(LemonRotation >= -16.5) {
//                            System.out.println(-(LemonRotation+16.5f)/6);
                            GL11.glTranslatef(-2,3.5f,-9.1f + (-(LemonRotation+16.5f)/6));
                        }
                        else{
                            GL11.glTranslatef(-2,3.5f,-9.3f + (LemonRotation+16.5f)/6);
                        }
                    }
                    else{
                        GL11.glTranslatef(-2,3.5f,-12.8f);
                    }
                    GL11.glScalef(0.1f,0.1f,0.1f);
                    GL11.glTexParameteri(
                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                            GL11.GL_CLAMP);
                    Color.white.bind();
                    text4.bind();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                    GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                    texsphere.DrawTexSphere(4f, 32, 32, text4);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                }GL11.glPopMatrix();

                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(1.5f,3.5f,1.5f);
                    GL11.glScalef(0.1f,0.1f,0.1f);
                    GL11.glTexParameteri(
                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                            GL11.GL_CLAMP);
                    Color.white.bind();
                    text4.bind();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                    GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                    texsphere.DrawTexSphere(4f, 32, 32, text4);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                }GL11.glPopMatrix();

                GL11.glColor3f(brown[0], brown[1], brown[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 2.5f, 0.0f);
                    GL11.glRotatef(170,0,1,0);
                    if(Status)
                        GL11.glRotatef(-LimbRotation/2, 0.0f, 1.0f, 0.0f);
                    GL11.glScalef(1, 1, 1);
                    cylinder.DrawCylinder(0.1f,11,32);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 2.5f, 0.0f);
                    GL11.glRotatef(190,0,1,0);
                    if(Status)
                        GL11.glRotatef(-LimbRotation/2, 0.0f, 1.0f, 0.0f);
                    GL11.glScalef(1, 1, 1);
                    cylinder.DrawCylinder(0.1f,11,32);

                }
                GL11.glPopMatrix();
                //bench
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 2.5f, -11.0f);
                    GL11.glRotatef(0,0,0,0);
                    if (Status) {
                        GL11.glRotatef(-LimbRotation / 2, 0.0f, 1.0f, 0.0f);
                        GL11.glTranslatef(LimbRotation/11, 0.0f, 0.0f);
                    }
                    GL11.glScalef(2, 1f, 0.2f);
                    GL11.glTexParameteri(
                            GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                            GL11.GL_CLAMP);
                    Color.white.bind();
                    text3.bind();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                    GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                    texcube.DrawTexCube();
                    GL11.glDisable(GL11.GL_TEXTURE_2D);

                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();

        GL11.glColor3f(black[0], black[1], black[2]);
        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
        GL11.glPushMatrix();
        {
            GL11.glRotatef(180,0,0,0);
            GL11.glScalef(1, 1, 1);
            rectangle.DrawRectangle(0.8f, 18, 32);
            GL11.glDisable(GL11.GL_TEXTURE_2D);

            GL11.glColor3f(black[0], black[1], black[2]);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
            GL11.glPushMatrix();
            {
                GL11.glTranslatef(0.0f, 0.0f, 16.0f);
                GL11.glRotatef(0,0,0,0);
                GL11.glScalef(4,4,4);
                GL11.glRotatef(90,1,0,0);
                wafer.DrawWafer();
                GL11.glScalef(0.25f,0.25f,0.25f);
                GL11.glRotatef(-90,1,0,0);
                GL11.glColor3f(black[0], black[1], black[2]);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(black));
                //lemon shadow
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0,-0.05f,0.5f);
                    if(!lemonDown){
                        GL11.glTranslatef(-2,0,-1f);
//                        GL11.glTranslatef(-2,3.5f,-12.8f);
                    }
                    else if(lemonDownFinished == 0){
//                        System.out.println(LemonRotation);
                        GL11.glTranslatef(-2,0,-14.245f+(float)(LemonRotation/4.1525));
//                        System.out.println(-12f+LemonRotation/4);
                    }
                    else if(lemonDownFinished == 1){
                        if(LemonRotation >= -16.5) {
//                            System.out.println(-(LemonRotation+16.5f)/6);
                            GL11.glTranslatef(-2,0,-9.1f + (-(LemonRotation+16.5f)/6));
                        }
                        else{
                            GL11.glTranslatef(-2,0,-9.3f + (LemonRotation+16.5f)/6);
                        }
                    }
                    else{
                        GL11.glTranslatef(-2,0,-12.8f);
                    }
                    GL11.glRotatef(90,1,0,0);
                    GL11.glScalef(0.4f,0.4f,0.4f);
                    wafer.DrawWafer();
                }GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0f, 0.0f);
                    GL11.glRotatef(190,0,1,0);
//                    System.out.println("板凳： " + LimbRotation);
                    if(Status)
                        GL11.glRotatef(-LimbRotation/2, 0.0f, 1.0f, 0.0f);
                    GL11.glScalef(1, 1, 1);
                    cylinder.DrawCylinder(0.1f,7.5f,32);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0f, 0.0f);
                    GL11.glRotatef(170,0,1,0);
                    if(Status)
                        GL11.glRotatef(-LimbRotation/2, 0.0f, 1.0f, 0.0f);
                    GL11.glScalef(1, 1, 1);
                    cylinder.DrawCylinder(0.1f,7.5f,32);
                }
                GL11.glPopMatrix();
                //板凳
                GL11.glPushMatrix();
                {
                    GL11.glTranslatef(0.0f, 0f, -8.5f);
                    GL11.glRotatef(0,0,0,0);
                    if (Status) {
                        GL11.glRotatef(-LimbRotation / 2f, 0.0f, 1.0f, 0.0f);
                        GL11.glTranslatef(LimbRotation/14, 0.0f, 0.0f);
                    }
                    GL11.glScalef(2f, 0.1f, 0.04f);
                    rectangle.DrawRectangle(0.8f, 18, 32);
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

}
