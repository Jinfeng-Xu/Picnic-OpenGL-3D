
import java.io.IOException;
import java.nio.FloatBuffer;

import javafx.scene.control.Alert;
import objects3D.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import GraphicsObjects.Arcball;
import GraphicsObjects.Utils;

//Main windows class controls and creates the 3D virtual world , please do not change this class but edit the other classes to complete the assignment. 
// Main window is built upon the standard Helloworld LWJGL class which I have heavily modified to use as your standard openGL environment. 
// 

// Do not touch this class, I will be making a version of it for your 3rd Assignment 
public class MainWindow {

    private boolean MouseOnepressed = true;
    private boolean dragMode = false;
    private boolean BadAnimation = true;
    private boolean Earth = false;
    private boolean SwingStatus = false;
    private boolean TentStatus = false;
    private boolean ShipStatus = false;
    private boolean BrandStatus = false;
    private boolean ObstacleStatus = false;
    private boolean MillStatus = false;
    private int DancingStatus = 0;
    private int DancingStatusTmp = 0;
    private int CountFlag = 0;
    private boolean bufferFlag = true;
    private float birdStart = 2000;
    private boolean birdFlag = true;
    private float birdTmp = 0;
    private int windInfluenceTime = 0;
    private int windInfluenceCount = 0;
    private boolean lemonDown = false;
    private boolean SwingTmpFlag = false;
    private boolean birdChange = false;
    private int lemonDownFinished = 0;
    private int swingInt = 0;
    /**
     * position of pointer
     */
    float x = 0, y = 0, tmpX = 0, tmpY = 0;
    /**
     * angle of rotation
     */
    float rotation = 0;
    /**
     * time at last frame
     */
    long lastFrame;
    /**
     * frames per second
     */
    int fps;
    /**
     * last fps time
     */
    long lastFPS;
    boolean flag = false, flagObstacle = false;

    long myDelta = 0; //to use for animation
    float Alpha = 0; //to use for animation
    long StartTime; // beginAnimiation

    Arcball MyArcball = new Arcball();

    boolean DRAWGRID = false;
    boolean waitForKeyrelease = true;
    /**
     * Mouse movement
     */
    int LastMouseX = -1;
    int LastMouseY = -1;

    boolean watching = false;

    float deltaTmp = 0;
    float deltaShipTmp = 0;

    float pullX = 0.0f; // arc ball  X cord.
    float pullY = 0.0f; // arc ball  Y cord.

    int move = 0;
    int direction = 0;
    int tmpDirection = 0;
    int jump = 0;
    int limit = 0;
//    long bus = 0;
//    long trash = 0;
//    long btime;
//    long ttime;

    int OrthoNumber = 3200; // using this for screen size, making a window of 1200 x 800 so aspect ratio 3:2 // do not change this for assignment 3 but you can change everything for your project

    // basic colours
    static float black[] = {0.0f, 0.0f, 0.0f, 1.0f};
    static float white[] = {1.0f, 1.0f, 1.0f, 1.0f};

    static float grey[] = {0.5f, 0.5f, 0.5f, 1.0f};
    static float spot[] = {0.1f, 0.1f, 0.1f, 0.5f};

    // primary colours
    static float red[] = {1.0f, 0.0f, 0.0f, 1.0f};
    static float green[] = {0.0f, 1.0f, 0.0f, 1.0f};
    static float blue[] = {0.0f, 0.0f, 1.0f, 1.0f};

    // secondary colours
    static float yellow[] = {1.0f, 1.0f, 0.0f, 1.0f};
    static float magenta[] = {1.0f, 0.0f, 1.0f, 1.0f};
    static float cyan[] = {0.0f, 1.0f, 1.0f, 1.0f};

    // other colours
    static float orange[] = {1.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float brown[] = {0.5f, 0.25f, 0.0f, 1.0f, 1.0f};
    static float dkgreen[] = {0.0f, 0.5f, 0.0f, 1.0f, 1.0f};
    static float pink[] = {1.0f, 0.6f, 0.6f, 1.0f, 1.0f};

    // static GLfloat light_position[] = {0.0, 100.0, 100.0, 0.0};

    //support method to aid in converting a java float array into a Floatbuffer which is faster for the opengl layer to process


    public void start() {

        StartTime = getTime();
        try {
            Display.setDisplayMode(new DisplayMode(1200, 800));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL(); // init OpenGL
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        while (!Display.isCloseRequested()) {
            int delta = getDelta();
            update(delta);
            renderGL();
            Display.update();
            Display.sync(120); // cap fps to 120fps
        }

        Display.destroy();
    }

    public void update(int delta) {

        int MouseX = Mouse.getX();
        int MouseY = Mouse.getY();
        int WheelPostion = Mouse.getDWheel();

        boolean MouseButonPressed = Mouse.isButtonDown(0);

        if (MouseButonPressed && !MouseOnepressed) {
            MouseOnepressed = true;
            //  System.out.println("Mouse drag mode");
            MyArcball.startBall(MouseX, MouseY, 1200, 800);
            dragMode = true;

        } else if (!MouseButonPressed) {
            // System.out.println("Mouse drag mode end ");
            MouseOnepressed = false;
            dragMode = false;
        }

        // Set the line-of-sight range to 0 to 610
        if (WheelPostion > 0) {
            OrthoNumber += 10;
        }
        if (WheelPostion < 0) {
            OrthoNumber -= 10;
            if (OrthoNumber < 610) {
                OrthoNumber = 610;
            }
        }

        // Keyboard A,S,D,W to control the movement of the man
        if (Keyboard.isKeyDown(Keyboard.KEY_A) && !SwingStatus && !TentStatus && !watching) {
            x -= 0.35f * delta;
            direction = 0;
            move = 1;
            if(DancingStatus == 2)
                x -= 0.15f * delta;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D) && !SwingStatus && !TentStatus && !watching) {
            x += 0.35f * delta;
            direction = 1;
            move = 2;
            if(DancingStatus == 2)
                x += 0.15f * delta;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_W) && !SwingStatus && !TentStatus && !watching) {
            y += 0.35f * delta;
            direction = 2;
            move = 3;
            if(DancingStatus == 2)
                y += 0.15f * delta;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S) && !SwingStatus && !TentStatus && !watching) {
            y -= 0.35f * delta;
            direction = 3;
            move = 4;
            if(DancingStatus == 2)
                y -= 0.15f * delta;
        } else {
            move = 0;
        }

        //On the swings
        if (Keyboard.isKeyDown(Keyboard.KEY_1) && (x <= -50 && x >= -400) && (y <= 50 && y >= -150)) {
            SwingStatus = true;
            SwingTmpFlag = true;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        SwingStatus = false;
                    }
                }
            };
            thread.start();
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lemonDownFinished = 1;
                        Thread thread3 = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    sleep(800);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } finally {
                                    lemonDownFinished = 2;
                                }
                            }
                        };
                        thread3.start();
                    }
                }
            };
            if(!lemonDown)
                thread2.start();
            lemonDown = true;

        }

        //Start the ship
        if (Keyboard.isKeyDown(Keyboard.KEY_2) && x >= 1130 && x <= 1870 && y >= 420 && y <= 520 && CountFlag == 0) {
            ShipStatus = true;
            ObstacleStatus = true;
            watching = true;
//            birdChange = true;
            CountFlag = 1;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        watching = false;
                        CountFlag = 3;
                        flagObstacle = false;
                    }
                }
            };
            thread.start();
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(22000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        ObstacleStatus = false;
                        CountFlag = 2;
                        Thread thread3 = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    sleep(4000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } finally {
                                    CountFlag = 4;
                                }
                            }
                        };
                        thread3.start();
                    }
                }
            };
            thread2.start();
        }

        //Start the Brand
        if (Keyboard.isKeyDown(Keyboard.KEY_3) && x >= -985 && x <= -615 && y >= -700 && y <= -510 && direction == 2) {
            if(!BrandStatus) DancingStatusTmp = DancingStatus;
            BrandStatus = true;
            DancingStatus = 1;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        BrandStatus = false;
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        DancingStatus = DancingStatusTmp;
                        DancingStatusTmp = 0;
                    }
                }
            };
            thread.start();
            thread2.start();
        }

        //Change walking style
        if (Mouse.isButtonDown(0) && bufferFlag) {
            if(DancingStatus == 0)
                DancingStatus = 2;
            else if(DancingStatus == 2)
                DancingStatus = 0;
            bufferFlag = false;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        bufferFlag = true;
                    }
                }
            };
            thread.start();
        }

        //Open the tent
        if (Keyboard.isKeyDown(Keyboard.KEY_4) && x <= 2400 && x >= 1600 && y >= -1000 && y <= -725) {
            TentStatus = true;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        TentStatus = false;
                        flag = false;
                    }
                }
            };
            thread.start();
        }


        // Keyboard Space to make the man jump
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !SwingStatus && !TentStatus && !watching) {
//            System.out.println("x: " + x);
//            System.out.println("y: " + y);
            jump += 0.5f * delta;
            if (jump > 80) {
                limit = 1;
                jump = 80;
            }
            if (limit == 1) {
                jump -= 1f * delta;
            }
            if (jump <= 0) {
                jump = 0;
                limit = 0;
            }
        }

        if (!Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump -= 0.5f * delta;
            if (jump <= 0) {
                jump = 0;
                limit = 0;
            }
        }
        if (waitForKeyrelease) // check done to see if key is released
        {
            if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
                DRAWGRID = !DRAWGRID;
                Keyboard.next();
                if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
                    waitForKeyrelease = true;
                } else {
                    waitForKeyrelease = false;
                }
            }
        }

        /** to check if key is released */
        if (Keyboard.isKeyDown(Keyboard.KEY_G) == false) {
            waitForKeyrelease = true;
        } else {
            waitForKeyrelease = false;
        }

        // keep quad on the screen
        if (x < -2000)
            x = -2000;
        if (x > 2400)
            x = 2400;
        if (y < -1540)
            y = -1540;
        if (y > 520)
            y = 520;
        // Collision detection of bulletin board
        if (x < -715 && x > -915 && y < -390 && y > -510) {
            if (direction == 0) x = -715;
            else if (direction == 1) x = -915;
            else if (direction == 2) y = -510;
            else if (direction == 3) y = -390;
        }

        // Collision detection of swing
        if (x < 50 && x > -480 && y < 150 && y > 0) {
            if (direction == 0) x = 50;
            else if (direction == 1) x = -480;
            else if (direction == 2) y = 0;
            else if (direction == 3) y = 150;
        }

        // Collision detection of tree
        if (x < -65 && x > -345 && y < 240 && y > 100) {
            if (direction == 0) x = -65;
            else if (direction == 1) x = -345;
            else if (direction == 2) y = 100;
            else if (direction == 3) y = 240;
        }

        // Collision detection of Tent
        if (x < 2450 && x > 1450 && y < -290 && y > -730) {
            if (direction == 0) x = 2400;
            else if (direction == 1) x = 1450;
            else if (direction == 2) y = -730;
            else if (direction == 3) y = -290;
        }

        // Collision detection of Left Light
        if (x < 1100 && x > 900 && y < 520 && y > 440) {
            if (direction == 0) x = 1100;
            else if (direction == 1) x = 900;
            else if (direction == 2) y = 440;
            else if (direction == 3) y = 520;
        }

        // Collision detection of Right Light
        if (x < 1785 && x > 1585 && y < 520 && y > 440) {
            if (direction == 0) x = 1785;
            else if (direction == 1) x = 1585;
            else if (direction == 2) y = 440;
            else if (direction == 3) y = 520;
        }

        // Collision detection of Obstacle
        if (x < 65 && x > -285 && y > 430) {
            if (direction == 0) x = 65;
            else if (direction == 1) x = -285;
            else if (direction == 2) y = 430;
        }

        // Collision detection of Windmill
        if (x < -1900 && x >= -2000 && y <= 520 && y > 390) {
            if (direction == 0) x = -1900;
            else if (direction == 1) x = -2000;
            else if (direction == 2) y = 390;
            else if (direction == 3) y = 520;
        }
        updateFPS(); // update FPS Counter
        LastMouseX = MouseX;
        LastMouseY = MouseY;
    }

    /**
     * Calculate how many milliseconds have passed since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void initGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        changeOrth();
        MyArcball.startBall(0, 0, 1200, 800);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        //road light
        FloatBuffer lightPos1 = BufferUtils.createFloatBuffer(4);
        lightPos1.put(1870).put(600).put(975).put(1).flip();

        FloatBuffer lightPos2 = BufferUtils.createFloatBuffer(4);
        lightPos2.put(1170).put(600).put(975).put(1).flip();
//
//        FloatBuffer lightPos3 = BufferUtils.createFloatBuffer(4);
//        lightPos3.put(-600).put(1900).put(-1300).put(0).flip();

        // the light Pos from sky
        FloatBuffer lightPos4 = BufferUtils.createFloatBuffer(4);
        lightPos4.put(-400).put(2000).put(-800).put(0).flip();

        // the light Pos from sun
        FloatBuffer lightPos6 = BufferUtils.createFloatBuffer(4);
        lightPos6.put(-750).put(2000).put(-1300).put(1f).flip();
        FloatBuffer lightPos7 = BufferUtils.createFloatBuffer(4);
        lightPos7.put(-550).put(2100).put(550).put(1f).flip();
        FloatBuffer lightPos8 = BufferUtils.createFloatBuffer(4);
        lightPos8.put(-100).put(2100).put(100).put(1f).flip();
        FloatBuffer lightPos9 = BufferUtils.createFloatBuffer(4);
        lightPos9.put(-900).put(2100).put(-100).put(1f).flip();

//
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPos1);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, Utils.ConvertForGL(pink));

        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPos2);
        GL11.glEnable(GL11.GL_LIGHT1); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, Utils.ConvertForGL(pink));
////
//        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_POSITION, lightPos3);
//        GL11.glEnable(GL11.GL_LIGHT2); // switch light #0 on
//        GL11.glLight(GL11.GL_LIGHT2, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
////

        // Grey direction lights mimic the grey glow of the sky
        GL11.glLight(GL11.GL_LIGHT3, GL11.GL_POSITION, lightPos4);
        GL11.glEnable(GL11.GL_LIGHT3); // switch light #0 on
        GL11.glLight(GL11.GL_LIGHT3, GL11.GL_DIFFUSE, Utils.ConvertForGL(grey));
        GL11.glLight(GL11.GL_LIGHT4, GL11.GL_POSITION, lightPos6);
        // Multiple directional lights were used to simulate the brightness of the sun
        GL11.glEnable(GL11.GL_LIGHT4);
        GL11.glLight(GL11.GL_LIGHT4, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
        GL11.glLight(GL11.GL_LIGHT5, GL11.GL_POSITION, lightPos7);
        GL11.glEnable(GL11.GL_LIGHT5);
        GL11.glLight(GL11.GL_LIGHT5, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
        GL11.glLight(GL11.GL_LIGHT6, GL11.GL_POSITION, lightPos8);
        GL11.glEnable(GL11.GL_LIGHT6);
        GL11.glLight(GL11.GL_LIGHT6, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));
        GL11.glLight(GL11.GL_LIGHT7, GL11.GL_POSITION, lightPos9);
        GL11.glEnable(GL11.GL_LIGHT7);
        GL11.glLight(GL11.GL_LIGHT7, GL11.GL_DIFFUSE, Utils.ConvertForGL(white));


        GL11.glEnable(GL11.GL_LIGHTING); // switch lighting on
        GL11.glEnable(GL11.GL_DEPTH_TEST); // make sure depth buffer is switched
        // on
        GL11.glEnable(GL11.GL_NORMALIZE); // normalize normal vectors for safety
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        try {
            init();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } //load in texture
    }


    public void changeOrth() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(1200 - OrthoNumber, OrthoNumber, (800 - (OrthoNumber * 0.66f)), (OrthoNumber * 0.66f), 100000, -100000);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        FloatBuffer CurrentMatrix = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, CurrentMatrix);

        //	if(MouseOnepressed)
        //	{

        MyArcball.getMatrix(CurrentMatrix);
        //	}

        GL11.glLoadMatrix(CurrentMatrix);

    }

    /*
     * You can edit this method to add in your own objects /  remember to load in textures in the INIT method as they take time to load
     *
     */
    public void renderGL() {
        changeOrth();
        // The camera Angle follows the man
        GLU.gluLookAt(0, -200, 1500, x, 350, -800 + y, 0, 1, 0);
//        GLU.gluLookAt(0, 200, y, x, 350, -800 + y, 0, 1, 0);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor3f(0.5f, 0.5f, 1.0f);

        myDelta = getTime() - StartTime;
        float delta = ((float) myDelta) / 10000;

        // code to aid in animation
        float theta = (float) (delta * 2 * Math.PI);
        float thetaDeg = delta * 360;
        float posn_x = (float) Math.cos(theta); // same as your circle code in your notes
        float posn_y = (float) Math.sin(theta);

        // human character is drawed here
        GL11.glPushMatrix();
//        System.out.println("x: " + x);
//        System.out.println("y: " + y);
        Human MyHuman = new Human();
        if (!SwingStatus && !watching && !TentStatus) {
            GL11.glTranslatef(200 + x, 200 + jump, -500 + 3 * y);
            GL11.glScalef(90f, 90f, 90f);
            GL11.glRotatef(90, 0, 1.0f, 0);
        } else if (SwingStatus) {
            GL11.glTranslatef(0, 220, -400);
            GL11.glScalef(90f, 90f, 90f);
            GL11.glRotatef(90, 0, 1.0f, 0);
            direction = 3;
            float thetaTmp = (float) (delta * 10 * Math.PI);
//            System.out.println("HUMANTEHTA: " + thetaTmp);
            float LimbRotation = (float) Math.cos(thetaTmp) * 45;
//            System.out.println("HUMAN: " + LimbRotation);
            GL11.glRotatef(-LimbRotation / 2, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(0, 0.0f, LimbRotation / 11);
        } else if (ShipStatus) {
            GL11.glTranslatef(200 + x, 200 + jump, -500 + 3 * y);
            GL11.glScalef(90f, 90f, 90f);
            GL11.glRotatef(90, 0, 1.0f, 0);
            direction = 2;
        } else if (TentStatus) {
            GL11.glTranslatef(200 + x, 200 + jump, -500 + 3 * y);
            GL11.glScalef(90f, 90f, 90f);
            GL11.glRotatef(90, 0, 1.0f, 0);
            direction = 2;
        }
        // different move status
        if (direction == 0) {
            GL11.glRotatef(0, 0, 1.0f, 0);
        }
        if (direction == 1) {
            GL11.glRotatef(180, 0, 1.0f, 0);
        }
        if (direction == 2) {
            GL11.glRotatef(90f, 0, 1.0f, 0);
        }
        if (direction == 3) {
            GL11.glRotatef(-90, 0, 1.0f, 0);
        }
        Boolean moveStatus = true;
        if (move == 0) {
            moveStatus = false;
        }
        MyHuman.DrawHuman(delta * 10, moveStatus, SwingStatus, watching, DancingStatus, texture, texture5); // give a delta for the Human object ot be animated


        // to draw human's shadow
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            HumanShadow shadow = new HumanShadow();
            if (!SwingStatus && !watching && !TentStatus) {
                GL11.glTranslatef(200 + x, 10, -700 + 3 * y - jump);
                GL11.glScalef(90f, 5f, 90f);
                GL11.glRotatef(180, 0, 1.0f, 0);
                GL11.glRotatef(90, 1.0f, 0, 0);
            } else if (SwingStatus) {
                GL11.glTranslatef(0, 10, -850);
                GL11.glScalef(90f, 5f, 90f);
                GL11.glRotatef(180, 0, 1.0f, 0);
                GL11.glRotatef(90, 1.0f, 0, 0);
//                direction = 3;
                float thetaTmp = (float) (delta * 10 * Math.PI);
                float LimbRotation = (float) Math.cos(thetaTmp) * 45;
                GL11.glRotatef(LimbRotation / 2, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(-LimbRotation / 14, 0.0f, 0);
            } else if (ShipStatus && watching) {
                GL11.glTranslatef(200 + x, 10, -700 + 3 * y - jump);
                GL11.glScalef(90f, 5f, 90f);
                GL11.glRotatef(180, 0, 1.0f, 0);
                GL11.glRotatef(90, 1.0f, 0, 0);
                direction = 2;
            } else if (TentStatus) {
                GL11.glTranslatef(200 + x, 10, -700 + 3 * y - jump);
                GL11.glScalef(90f, 5f, 90f);
                GL11.glRotatef(180, 0, 1.0f, 0);
                GL11.glRotatef(90, 1.0f, 0, 0);
                direction = 2;
            }

            if (direction == 0) {
                GL11.glRotatef(-90, 0, 1.0f, 0);
            }
            if (direction == 1) {
                GL11.glRotatef(90, 0, 1.0f, 0);
            }
            if (direction == 2) {
                GL11.glRotatef(0f, 0, 1.0f, 0);
            }
            if (direction == 3) {
                GL11.glRotatef(180, 0, 1.0f, 0);
            }
            GL11.glColor3f(black[0], black[1], black[2]);
            shadow.DrawHuman(delta * 10, moveStatus, SwingStatus, watching, DancingStatus);
            GL11.glPopMatrix();
        }

        //Ship
        GL11.glPushMatrix();
        {
            Ship ship = new Ship();
            if (!ShipStatus) {
                deltaShipTmp = delta;
            }
            GL11.glTranslatef(1450 - (delta - deltaShipTmp) * 1000, 0, 2000);
            GL11.glScalef(200, 200, 200);
            ship.DrawShip(delta * 5, true, texture9);
            GL11.glPopMatrix();
        }

        //Brand
        GL11.glPushMatrix();
        {
            Brand brand = new Brand();
            GL11.glTranslatef(-600, 400, -1800);
            GL11.glScalef(100, 100, 100);
            brand.DrawBrand(delta * 225, BrandStatus, gifTexture, texture9, texture10);
            GL11.glPopMatrix();
        }

        //Tent
        GL11.glPushMatrix();
        {
            Tent tent = new Tent();
            GL11.glTranslatef(2200, 500, -2000);
            GL11.glScalef(200, 200, 200);
            if (!flag && TentStatus) {
                deltaTmp = delta;
                flag = true;
            }
            tent.DrawTent((delta - deltaTmp) * 2, TentStatus, texture9);
            GL11.glPopMatrix();
        }

        //Swing
        GL11.glPushMatrix();
        {
            Swing swing = new Swing();
            GL11.glTranslatef(0, 0, 0);
            GL11.glScalef(100, 80, 100);
            if (SwingStatus && SwingTmpFlag) {
                deltaTmp = delta;
                SwingTmpFlag = false;
            }
            swing.DrawSwing(delta * 5, (delta-deltaTmp)*5, SwingStatus, lemonDown, lemonDownFinished, texture6, texture7, texture8, texture14);
            GL11.glPopMatrix();
        }

        //Obstacle
        GL11.glPushMatrix();
        {
            Obstacle obstacle = new Obstacle();
            GL11.glTranslatef(100, 30, 1000);
            GL11.glScalef(1, 1, 1);
            if (!flagObstacle && (CountFlag == 1 || CountFlag == 2)) {
                deltaTmp = delta;
                flagObstacle = true;
            }
            obstacle.DrawObstacle((delta - deltaTmp) * 5.92f , ObstacleStatus, CountFlag, texture11);
            GL11.glPopMatrix();
        }


        //Ground
        GL11.glPushMatrix();
        {
            TexCube cube2 = new TexCube();
            GL11.glTranslatef(600, -20, -6600);
            GL11.glScalef(7200f, 10f, 8000f);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            texture3.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            cube2.DrawTexCube();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }

        //background
        GL11.glPushMatrix();
        {
            TexCube back = new TexCube();
            GL11.glTranslatef(600, 2000, 2700);
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0);
            GL11.glRotatef(180.0f, 1.0f, 0, 0.0f);
            GL11.glScalef(10f, 2000f, 7200f);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            texture2.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

            back.DrawTexCube();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }

        //Windmill
        GL11.glPushMatrix();
        {
            Windmill windmill = new Windmill();
            GL11.glTranslatef(-1800, 30, 800);
            GL11.glScalef(1, 1, 1);
            if (ShipStatus && !MillStatus) {
                deltaTmp = delta;
                MillStatus = true;
                swingInt = 0;
            }
            windmill.DrawWindmill((delta - deltaTmp), swingInt++, !ShipStatus, texture);
            GL11.glPopMatrix();
        }
        //River
        GL11.glPushMatrix();
        {
            TexCube road = new TexCube();
            GL11.glTranslatef(600, -20, 1800);
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0);
            GL11.glScalef(1000f, 10f, 7000f);
            GL11.glTexParameteri(
                    GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                    GL11.GL_CLAMP);
            Color.white.bind();
            texture4.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
            GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            road.DrawTexCube();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }

        //Sun
        GL11.glPushMatrix();
        {
            GL11.glColor4f(orange[0], orange[1], orange[2], 1f);
            GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT_AND_DIFFUSE, Utils.ConvertForGL(yellow));
            Sphere sphere = new Sphere();
            Tetrahedron tetrahedron = new Tetrahedron();
            GL11.glTranslatef(-600, 1800, 1800);
            GL11.glScalef(1, 1, 1);
            sphere.DrawSphere(100, 32, 32);
            GL11.glPopMatrix();
        }

        //Light1
        GL11.glPushMatrix();
        {
            Light light = new Light();
            GL11.glTranslatef(1870, 600, 975);
            GL11.glScalef(1, 1, 1);
            light.DrawLight(delta, watching);
            GL11.glPopMatrix();
        }

        //Light2
        GL11.glPushMatrix();
        {
            Light light = new Light();
            GL11.glTranslatef(1170, 600, 975);
            GL11.glScalef(1, 1, 1);
            light.DrawLight(delta, watching);
            GL11.glPopMatrix();
        }

        //Flowers
        for (int i = 0; i < 5; i++) {
            GL11.glPushMatrix();
            {
                Flower flower = new Flower();
                GL11.glTranslatef(-1500 - (i * 100), 0, -1000 - (i * 100));
                GL11.glScalef(1, 1, 1);
                flower.DrawFlower(delta, ShipStatus);
            } GL11.glPopMatrix();
        }

        //Bird
        GL11.glPushMatrix();
        {
            Bird bird = new Bird();
//            System.out.println((delta-birdTmp));
            if(birdFlag) {
                if (!birdChange)
                    GL11.glTranslatef(birdStart - ((delta - birdTmp) * 3000), 1800, -1000);
                else {
                    GL11.glTranslatef(birdStart - ((delta - birdTmp) * 3000) + (windInfluenceCount/150), 1800, -1000);
//                    GL11.glTranslatef(-windInfluenceTime*2, 0, 0);
                    windInfluenceCount += windInfluenceTime*2;
                    windInfluenceTime ++;
//                    System.out.println("to " + windInfluenceCount);
                }
            }
            else {
                if (!birdChange)
                    GL11.glTranslatef(birdStart + ((delta - birdTmp) * 3000), 1800, -1000);
                else {
                    GL11.glTranslatef(birdStart + ((delta - birdTmp) * 4500) - (windInfluenceCount/200), 1800, -1000);
//                    GL11.glTranslatef(400 - windInfluenceTime,0,0);
                    windInfluenceCount += windInfluenceTime*2;
                    windInfluenceTime ++;
//                    System.out.println(windInfluenceCount);
                }
            }
            if(birdChange && ShipStatus) {
                if (birdStart - ((delta - birdTmp) * 3000) + (windInfluenceCount / 150) <= -2500 && birdFlag) {
                    birdTmp = delta;
                    birdFlag = false;
                    birdStart = -2500;
                    windInfluenceTime = 0;
                    windInfluenceCount = 0;
                }
                else if (birdStart + ((delta - birdTmp) * 4500) - (windInfluenceCount / 200) >= 2500 && !birdFlag) {
                    birdTmp = delta;
                    birdFlag = true;
                    birdStart = 2500;
                    windInfluenceTime = 0;
                    windInfluenceCount = 0;
                }
            }
            else {
                if (birdStart - ((delta - birdTmp) * 3000) <= -2500 && birdFlag) {
                    birdTmp = delta;
                    birdFlag = false;
                    if(ShipStatus)
                        birdChange = true;
                    birdStart = -2500;
                    windInfluenceTime = 0;
                    windInfluenceCount = 0;
                } else if (birdStart + ((delta - birdTmp) * 3000) >= 2500 && !birdFlag) {
                    birdTmp = delta;
                    birdFlag = true;
                    if(ShipStatus)
                        birdChange = true;
                    birdStart = 2500;
                    windInfluenceTime = 0;
                    windInfluenceCount = 0;
                }

            }
            GL11.glScalef(1, 1, 1);
            bird.DrawBird(delta, birdFlag, texture12, texture13);
        } GL11.glPopMatrix();
    }

    public static void main(String[] argv) {
        MainWindow hello = new MainWindow();
        hello.start();
    }

    Texture texture, texture2, texture3, texture4, texture5,
            texture6, texture7, texture8, texture9, texture10,
            texture11, texture12, texture13, texture14;
    Texture[] gifTexture = new Texture[11];


    /*
     * Any additional textures for your assignment should be written in here.
     * Make a new texture variable for each one so they can be loaded in at the beginning
     */
    public void init() throws IOException {
        // load the resourse
        texture = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/aliens.jpeg"));
        texture2 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/2021.jpg"));
        texture3 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/grass.jpeg"));
        texture4 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/water.jpeg"));
        texture5 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/aliens2.jpeg"));
        texture6 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/trunk.jpeg"));
        texture7 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/Tree.jpeg"));
        texture8 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/wooden.jpeg"));
        texture9 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/Ship.jpeg"));
        texture10 = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/load_dog.jpg"));
        texture11 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/texture_wooden.jpeg"));
        texture12 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/feather.jpeg"));
        texture13 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/feather2.jpeg"));
        texture14 = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("res/lemon.jpeg"));
        for (int i = 0; i < 11; i++) {
            gifTexture[i] = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/Dog" + (i + 1) + ".jpg"));
        }
        System.out.println("Texture loaded okay ");
    }
}
