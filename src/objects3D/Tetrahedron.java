package objects3D;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;

public class Tetrahedron {


    public Tetrahedron() {

    }

    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    public void DrawTetrahedron() {

        Point4f vertices[] = {new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),
                new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
//                new Point4f(-1.0f, 1.0f, -1.0f, 0.0f),
//                new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
                new Point4f(1.0f, -1.0f, -1.0f, 0.0f),
                new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
//                new Point4f(1.0f, 1.0f, -1.0f, 0.0f),
                new Point4f(0, (float) Math.sqrt(3), 0f, 0.0f)
        };

        int faces[][] =
                {
                        {0, 1, 3, 2},
                        {4, 3, 1},
                        {4, 3, 2},
                        {4, 2, 0},
                        {4, 0, 1}
                };


        // Draw the base of the rectangle
        GL11.glBegin(GL11.GL_QUADS);
        Vector4f v = vertices[faces[0][1]].MinusPoint(vertices[faces[0][0]]);
        Vector4f w = vertices[faces[0][3]].MinusPoint(vertices[faces[0][0]]);
        Vector4f normal = v.cross(w).Normal();
        GL11.glNormal3f(normal.x, normal.y, normal.z);

        GL11.glVertex3f(vertices[faces[0][0]].x, vertices[faces[0][0]].y, vertices[faces[0][0]].z);

        GL11.glVertex3f(vertices[faces[0][1]].x, vertices[faces[0][1]].y, vertices[faces[0][1]].z);

        GL11.glVertex3f(vertices[faces[0][2]].x, vertices[faces[0][2]].y, vertices[faces[0][2]].z);

        GL11.glVertex3f(vertices[faces[0][3]].x, vertices[faces[0][3]].y, vertices[faces[0][3]].z);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLES);
        // other faces
        for (int face = 1; face < 5; face++) { // per face
            v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            w = vertices[faces[face][2]].MinusPoint(vertices[faces[face][0]]);
            normal = v.cross(w).Normal();
            GL11.glNormal3f(normal.x, normal.y, normal.z);
            GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);
            GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);
            GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);
        } // per face
        GL11.glEnd();
    }
}
