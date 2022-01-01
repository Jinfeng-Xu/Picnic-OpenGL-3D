package objects3D;

import GraphicsObjects.Point4f;
import GraphicsObjects.Vector4f;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class TexCube {


    public TexCube() {

    }

    // Implement using notes  and looking at TexSphere
    public void DrawTexCube() {

        Point4f vertices[] = {  new Point4f(-1.0f, -1.0f, -1.0f, 0.0f),
                new Point4f(-1.0f, -1.0f, 1.0f, 0.0f),
                new Point4f(-1.0f, 1.0f, -1.0f, 0.0f),
                new Point4f(-1.0f, 1.0f, 1.0f, 0.0f),
                new Point4f(1.0f, -1.0f, -1.0f, 0.0f),
                new Point4f(1.0f, -1.0f, 1.0f, 0.0f),
                new Point4f(1.0f, 1.0f, -1.0f, 0.0f),
                new Point4f(1.0f, 1.0f, 1.0f, 0.0f) };

        int faces[][] = { 	{ 0, 4, 5, 1 },
                { 0, 2, 6, 4 },
                { 0, 1, 3, 2 },
                { 4, 6, 7, 5 },
                { 1, 5, 7, 3 },
                { 2, 3, 7, 6 } };


        GL11.glBegin(GL11.GL_QUADS);

        /*
         * here the texture picture is painted onto the surfaces of this cube
         */
        for (int face = 0; face < 6; face++) { // per face
            Vector4f v = vertices[faces[face][1]].MinusPoint(vertices[faces[face][0]]);
            Vector4f w = vertices[faces[face][3]].MinusPoint(vertices[faces[face][0]]);
            Vector4f normal = v.cross(w).Normal();
            GL11.glNormal3f(normal.x, normal.y, normal.z);

            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(vertices[faces[face][0]].x, vertices[faces[face][0]].y, vertices[faces[face][0]].z);

            GL11.glTexCoord2f(0, 1);
            GL11.glVertex3f(vertices[faces[face][1]].x, vertices[faces[face][1]].y, vertices[faces[face][1]].z);

            GL11.glTexCoord2f(1, 1);
            GL11.glVertex3f(vertices[faces[face][2]].x, vertices[faces[face][2]].y, vertices[faces[face][2]].z);

            GL11.glTexCoord2f(1, 0);
            GL11.glVertex3f(vertices[faces[face][3]].x, vertices[faces[face][3]].y, vertices[faces[face][3]].z);
        } // per face

        GL11.glEnd();
    }


}



	 