package GraphicsObjects;


public class Vector4f {

    public float x = 0;
    public float y = 0;
    public float z = 0;
    public float a = 0;

    public Vector4f() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        a = 0.0f;
    }

    public Vector4f(float x, float y, float z, float a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
    }

    //implement Vector plus a Vector
    //The method to get a new Vector is by adding x, y, z, a of the old Vector to x, y, z, a of the other Vector
    public Vector4f PlusVector(Vector4f Additonal) {
        return new Vector4f(this.x + Additonal.x, this.y + Additonal.y, this.z + Additonal.z, this.a + Additonal.a);
    }

    //implement Vector minus a Vector
    //The method to get a new Vector is by minus x, y, z, a of the old Vector to x, y, z, a of the other Vector
    public Vector4f MinusVector(Vector4f Minus) {
        return new Vector4f(this.x - Minus.x, this.y - Minus.y, this.z - Minus.z, this.a - Minus.a);
    }

    //implement Vector plus a Point
    //The method to get a new Point is by adding x, y, z, a of the old Vector to x, y, z, a of the other Point
    public Point4f PlusPoint(Point4f Additonal) {
        return new Point4f(this.x + Additonal.x, this.y + Additonal.y, this.z + Additonal.z, this.a - Additonal.a);
    }
    //Do not implement Vector minus a Point as it is undefined

    //Implement a Vector * Scalar
    //Vector plus a Point equals a Point, the same as a point plus a vector.
    public Vector4f byScalar(float scale) {
        return new Vector4f(this.x * scale, this.y * scale, this.z * scale, this.a * scale);
    }

    //implement returning the negative of a  Vector
    public Vector4f NegateVector() {
        return new Vector4f(0 - this.x, 0 - this.y, 0 - this.z, 0 - this.a);
    }

    //implement getting the length of a Vector
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + a * a);
    }

    //Just to avoid confusion here is getting the Normal  of a Vector
    public Vector4f Normal() {
        float LengthOfTheVector = this.length();
        return this.byScalar(1.0f / LengthOfTheVector);
    }

    //implement getting the dot product of Vector.Vector

    public float dot(Vector4f v) {
        return (this.x * v.x + this.y * v.y + this.z * v.z + this.a * v.a);
    }

    // Implemented this for you to avoid confusion
    // as we will not normally  be using 4 float vector
    public Vector4f cross(Vector4f v) {
        float u0 = (this.y * v.z - z * v.y);
        float u1 = (z * v.x - x * v.z);
        float u2 = (x * v.y - y * v.x);
        float u3 = 0; //ignoring this for now
        return new Vector4f(u0, u1, u2, u3);
    }

}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                                                       MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */