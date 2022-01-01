package objects3D;

import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Brand {

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

    public Brand() {

    }


    // to draw a brand. a dog running gif create by multiple pictures
    public void DrawBrand(float delta,boolean Status, Texture[] text1, Texture text2, Texture text3) {

        Sphere sphere= new Sphere();
        Cylinder cylinder= new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        Cube cube = new Cube();
        TexCylinder texCylinder = new TexCylinder();

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        GL11.glPushMatrix();{
            GL11.glRotatef(90,1,0,0);
            GL11.glScalef(1, 1, 1);

            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            text2.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            texCylinder.DrawCylinder(0.25f,8,32, text2);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(GL11.GL_TEXTURE_2D);

//            cylinder.DrawCylinder(0.25f,12,32);
        }GL11.glPopMatrix();
        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,2.5f,0);
            GL11.glScalef(2, 1.5f, 0.5f);

            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            text2.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            texcube.DrawTexCube();
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(GL11.GL_TEXTURE_2D);

//            cylinder.DrawCylinder(0.25f,12,32);
        }GL11.glPopMatrix();
        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        GL11.glPushMatrix();{
            GL11.glRotatef(180, 0,0,1);
            GL11.glTranslatef(0,-2.5f,-0.2f);
            GL11.glScalef(1.6f, 1.2f, 0.4f);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();

//            System.out.println(delta);
            if(Status) {
                delta = (int) delta;
                if (delta % 11 == 0) {
                    text1[0].bind();
                } else if (delta % 11 == 1) {
                    text1[1].bind();
                } else if (delta % 11 == 2) {
                    text1[2].bind();
                } else if (delta % 11 == 3) {
                    text1[3].bind();
                } else if (delta % 11 == 4) {
                    text1[4].bind();
                } else if (delta % 11 == 5) {
                    text1[5].bind();
                } else if (delta % 11 == 6) {
                    text1[6].bind();
                } else if (delta % 11 == 7) {
                    text1[7].bind();
                } else if (delta % 11 == 8) {
                    text1[8].bind();
                } else if (delta % 11 == 9) {
                    text1[9].bind();
                } else if (delta % 11 == 10) {
                    text1[10].bind();
                }
            }
            else{
                text3.bind();
            }


            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            texcube.DrawTexCube();
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
//            cube.DrawCube();
        }GL11.glPopMatrix();
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,-4,0);
            GL11.glRotatef(180,1,0,0);
            GL11.glScalef(1, 0, 1f);
            cylinder.DrawCylinder(0.25f,8,32);
        }GL11.glPopMatrix();
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 1,0,0);
            GL11.glTranslatef(0,-7f,4f);
            GL11.glScalef(2, 1.5f, 0f);
            cube.DrawCube();
        }GL11.glPopMatrix();
    }

}
