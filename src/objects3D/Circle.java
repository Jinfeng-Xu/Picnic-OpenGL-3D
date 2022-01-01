package objects3D;
import org.lwjgl.opengl.GL11;

public class Circle {


    public Circle() {

    }
    // Implement using notes and examine Tetrahedron to aid in the coding  look at lecture  7 , 7b and 8
    // 7b should be your primary source, we will cover more about circles in later lectures to understand why the code works
    public void DrawCircle(float radius,float nSlices,float nSegments) {
        float inctheta = (float) (2 * Math.PI / nSlices);
        float incphi = (float) (Math.PI / nSegments);

        /**
         * 	Draw Principle:
         * 			1. It is the same as drawing a cylinder.
         * 			2. To draw a sphere, divide the surface into small squares.
         * 			3. When drawing a sphere, only draw a small square around a point.
         * 			4. Repeat the drawing several times, the small square has completely covered the surface of the sphere, and a sphere has been created successfully. sphere and a sphere has been created successfully.
         */
        GL11.glBegin(GL11.GL_QUADS);
        for (float theta = (float) - Math.PI; theta < Math.PI; theta += inctheta) {
            for (float phi = (float) (- (Math.PI) / 2); phi < Math.PI / 2; phi += incphi) {
                //first point
                float y = 0;
                float x = (float) ((Math.cos(phi) * radius) * Math.cos(theta));
//                float y = (float) ((Math.cos(phi) * radius) * Math.sin(theta));
                float z = (float) (Math.sin(phi) * radius);
                GL11.glNormal3f(x, y, z);
                GL11.glVertex3f(x, y, z);
                //second point
                x = (float) ((Math.cos(phi + incphi) * radius) * Math.cos(theta));
//                y = (float) ((Math.cos(phi + incphi) * radius) * Math.sin(theta));
                z = (float) (Math.sin(phi + incphi) * radius);
                GL11.glNormal3f(x, y, z);
                GL11.glVertex3f(x, y, z);
                //third point
                x = (float) ((Math.cos(phi + incphi) * radius) * Math.cos(theta + inctheta));
//                y = (float) ((Math.cos(phi + incphi) * radius) * Math.sin(theta + inctheta));
                z = (float) (Math.sin(phi + incphi) * radius);
                GL11.glNormal3f(x, y, z);
                GL11.glVertex3f(x, y, z);
                //forth point
                x = (float) ((Math.cos(phi) * radius) * Math.cos(theta + inctheta));
//                y = (float) ((Math.cos(phi) * radius) * Math.sin(theta + inctheta));
                z = (float) (Math.sin(phi) * radius);
                GL11.glNormal3f(x, y, z);
                GL11.glVertex3f(x, y, z);
            }
        }
        GL11.glEnd();

    }
}

