package objects3D;


import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Tent {

    // basic colours
    static float black[] = { 0.0f, 0.0f, 0.0f, 1.0f };
    static float white[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    static float grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
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

    public Tent() {

    }

    // Draw a Tent
    public void DrawTent(float delta, boolean Status, Texture text1) {
        float LimbRotation;
        float theta = (float) (delta * 2 * Math.PI);
        float limb;

        if (Status) {
            LimbRotation = (float) (Math.cos(delta * 0.4 * Math.PI));
            limb = (float) (Math.cos(theta));
        } else {
            LimbRotation = 0;
            limb = 0;
        }

        Cylinder cylinder = new Cylinder();
        Cube cube = new Cube();
        Tetrahedron tetrahedron = new Tetrahedron();


        GL11.glColor4f(brown[0], brown[1], brown[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(brown));
        GL11.glPushMatrix();{
            GL11.glScalef(2.5f, 2.5f, 2.5f);
            tetrahedron.DrawTetrahedron();
            GL11.glColor4f(red[0], red[1], red[2], 0.7f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
            GL11.glPushMatrix();{
                GL11.glTranslatef(0.4f,0.3f,-0.77f);
                GL11.glRotatef(20, 1,0,0);
                GL11.glRotatef(90, 1,0,0);
                if(Status){
                    if (delta >= 0 && delta < 2.5) {
                        GL11.glRotatef((90.0f - limb*90.0f) , 0, 0, 1);
                    }else if (delta >= 2.5 && delta < 5) {
                        GL11.glRotatef(90.0f, 0, 0, 1);
                    }else if (delta >= 5 && delta < 7.5) {
                        GL11.glRotatef((-limb * 90.0f), 0, 0, 1);
                    }
                }
                GL11.glScalef(0.2f, 0.2f, 0.05f);
                cylinder.DrawCylinder(0.2f,26,32);
                GL11.glColor4f(white[0], white[1], white[2], 0.5f);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();{
                    GL11.glTranslatef(-1,0,12.9f);
                    GL11.glScalef(1, 0.2f, 13f);
                    cube.DrawCube();

                }GL11.glPopMatrix();
            }GL11.glPopMatrix();
            GL11.glColor4f(red[0], red[1], red[2], 0.7f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(red));
            GL11.glPushMatrix();{
                GL11.glTranslatef(-0.4f,0.3f,-0.77f);
//                GL11.glRotatef(20, 1,0,0);
                GL11.glRotatef(20, 1,0,0);
                GL11.glRotatef(90, 1,0,0);
                if(Status){
                    if (delta >= 0 && delta < 2.5) {
                        GL11.glRotatef(-(90.0f - limb*90.0f) , 0, 0, 1);
                    }else if (delta >= 2.5 && delta < 5) {
                        GL11.glRotatef(-90.0f, 0, 0, 1);
                    }else if (delta >= 5 && delta < 7.5) {
                        GL11.glRotatef(-(-limb * 90.0f), 0, 0, 1);
                    }
                }
                GL11.glScalef(0.2f, 0.2f, 0.05f);
//            cube.DrawCube();
                cylinder.DrawCylinder(0.2f,26,32);
                GL11.glColor4f(white[0], white[1], white[2], 0.5f);
                GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(white));
                GL11.glPushMatrix();{
                    GL11.glTranslatef(1,0,12.9f);
                    GL11.glScalef(1, 0.2f, 13f);
                    cube.DrawCube();

                }GL11.glPopMatrix();
            }GL11.glPopMatrix();


        }GL11.glPopMatrix();
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
        GL11.glPushMatrix();{
            GL11.glTranslatef(0,-2.5f,-5);
            GL11.glScalef(2.5f, 0, 2.5f);
            GL11.glRotatef(-90,1,0,0);
            tetrahedron.DrawTetrahedron();
        }
        GL11.glPopMatrix();
    }

}
