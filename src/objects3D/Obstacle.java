package objects3D;


import GraphicsObjects.Utils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Obstacle {

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

    public Obstacle() {

    }


    public void DrawObstacle(float delta, boolean Status, int Flag,  Texture text1) {
        float LimbRotation;

        if (Flag == 1 || Flag == 2) {
            LimbRotation = (float) (Math.cos(delta * 0.2 * Math.PI));
        }else {
            LimbRotation = 0;
        }
//        System.out.println(LimbRotation);
//        System.out.println(Flag);
        Sphere sphere= new Sphere();
        Cylinder cylinder= new Cylinder();
        TexSphere texsphere = new TexSphere();
        TexCube texcube = new TexCube();
        TexCylinder texCylinder = new TexCylinder();
        Cube cube = new Cube();

        GL11.glColor4f(white[0], white[1], white[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(white));
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glScalef(10, 10, 10);
//            GL11.glTranslatef(0.0f, 0f, -0.4f);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            text1.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            texCylinder.DrawCylinder(10f,35,32, text1);
            GL11.glPushMatrix();{
                if(Flag == 1){
                    GL11.glRotatef(-(1-LimbRotation)*100, 1, 0, 0);
                }
                else if( Flag == 0 || Flag == 4){
                    GL11.glRotatef(0, 0, 0, 0);
                }
                else if( Flag == 2){
                    GL11.glRotatef(-90 + (1-LimbRotation)*100, 1, 0, 0);
                }
                else if( Flag == 3){
                    GL11.glRotatef(-90, 1, 0, 0);
                }
//                GL11.glRotatef(-90, 1, 0, 0);
                GL11.glScalef(1, 60, 4);
                GL11.glTranslatef(0.0f, 1.25f, -1.5f);
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
        }GL11.glPopMatrix();

        // shadow
        GL11.glColor4f(black[0], black[1], black[2], 1f);
        GL11.glMaterial(  GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE,  Utils.ConvertForGL(black));
        GL11.glPushMatrix();{
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glTranslatef(0,0,30);
            GL11.glScalef(10, 10, 0);
            GL11.glRotatef(90, 1, 0, 0);
//            GL11.glTranslatef(0,0,0);
            cylinder.DrawCylinder(10f,35,32);
            GL11.glPushMatrix();{
                if(Flag == 1){
                    GL11.glRotatef(-(1-LimbRotation)*100, 1, 0, 0);
                }
                else if( Flag == 0 || Flag == 4){
                    GL11.glRotatef(0, 0, 0, 0);
                }
                else if( Flag == 2){
                    GL11.glRotatef(-90 + (1-LimbRotation)*100, 1, 0, 0);
                }
                else if( Flag == 3){
                    GL11.glRotatef(-90, 1, 0, 0);
                }
//                GL11.glRotatef(-90, 1, 0, 0);
                GL11.glScalef(1, 60, 4);
                GL11.glTranslatef(0.0f, -1.25f, -1.5f);
//                GL11.glRotatef(180, 0, 0, 1);
                GL11.glRotatef(90, 1, 0, 0);
                cube.DrawCube();
            }GL11.glPopMatrix();
        }GL11.glPopMatrix();
    }


}
