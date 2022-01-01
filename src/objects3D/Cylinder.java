package objects3D;

import org.lwjgl.opengl.GL11;
import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

import java.math.*;

public class Cylinder {


    public Cylinder() {
    }

    // remember to use Math.PI isntead PI
    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    public void DrawCylinder(float radius, float height, int nSegments) {
        for (float i = 0; i < nSegments; i += 1) {
            // A loop around the circumference of the tube
            float angle = (float) (Math.PI * i * 2 / nSegments);
            float nextAngle = (float) (Math.PI * (i + 1) * 2 / nSegments);
            // Compute x1 and x2
            float x1 = (float) (radius * Math.sin(angle)), y1 = (float) (radius * Math.cos(angle));
            float x2 = (float) (radius * Math.sin(nextAngle)), y2 = (float) (radius * Math.cos(nextAngle));
            // To draw bottom part
            GL11.glBegin(GL11.GL_TRIANGLES);
            Vector4f v = new Vector4f(x1 - x2, y1 - y2, 0, 0);
            Vector4f w = new Vector4f(x1, y1, 0, 0);
            glNormalByVW(v,w);
            // Set normal for lights
            GL11.glVertex3f(x1, y1, 0);
            GL11.glVertex3f(x2, y2, 0);
            GL11.glVertex3f(0, 0, 0);
            GL11.glEnd();
            // To draw top part
            GL11.glBegin(GL11.GL_TRIANGLES);
            v = new Vector4f(x1 - x2, y1 - y2, 0, 0);
            w = new Vector4f(x1, y1, 0, 0);
            glNormalByVW(v,w);
            // Set normal for light.
            GL11.glVertex3f(x1, y1, height);
            GL11.glVertex3f(x2, y2, height);
            GL11.glVertex3f(0, 0, height);
            GL11.glEnd();
            //To draw top triangle part
            GL11.glBegin(GL11.GL_TRIANGLES);
            v = new Vector4f(x1 - x2, y1 - y2, 0 - height, 0);
            w = new Vector4f(0, 0, 0 - height, 0);
            glNormalByVW(v,w);
            GL11.glVertex3f(x1, y1, 0);
            GL11.glVertex3f(x2, y2, height);
            GL11.glVertex3f(x1, y1, height);
            GL11.glEnd();
            // To draw bottom triangle part
            GL11.glBegin(GL11.GL_TRIANGLES);
            v = new Vector4f(x1 - x2, y1 - y2, 0, 0);
            w = new Vector4f(x1 - x2, y1 - y2, 0 - height, 0);
            glNormalByVW(v,w);
            // Set normal for light.
            GL11.glVertex3f(x1, y1, 0);
            GL11.glVertex3f(x2, y2, 0);
            GL11.glVertex3f(x2, y2, height);
            GL11.glEnd();
        }
    }
    // Encapsulation, convenient method reuse
    private void glNormalByVW(Vector4f v, Vector4f w){
        Vector4f normal = v.cross(w).Normal();
        GL11.glNormal3f(normal.x, normal.y, normal.z);
    }
}
