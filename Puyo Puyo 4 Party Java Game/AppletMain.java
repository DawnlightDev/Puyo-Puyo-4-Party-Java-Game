/* Decompiler 21ms, total 88ms, lines 165 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;

public class AppletMain extends Applet implements Runnable {
   public Button m_btnDebug;
   public static final int BTN_DEBUG = 0;
   public static final int BTN_RESTART = 1;
   public static final int BTN_OK = 2;
   public static final int PNL_NULL = 0;
   public static final int PNL_SOUTH = 1;
   public static final int PNL_NORTH = 2;
   public static final int PNL_EAST = 3;
   public static final int PNL_WEST = 4;
   public static final int PNL_PLACE = 0;
   public static final int PNL_WIDTH = 64;
   public static final int PNL_HEIGHT = 64;
   public static final boolean m_fDebug = false;
   public static boolean m_fDrawRegInt = true;
   public static String[] m_strFace = new String[]{"Debug", "Retry", "OK", "", "", "", "", "", "", "", "", "", "", "", "", ""};
   public Dimension m_dimScr;
   public Thread m_thrMain;
   public Display m_dspMain;
   public GameMain m_gmMain;
   public KeyInput m_keyMain;
   public Panel m_pnlMain;
   public static int m_nWait;
   public MediaTracker m_mt;

   public void stop() {
      if (this.m_thrMain != null) {
         this.m_thrMain.stop();
         this.m_thrMain = null;
      }

   }

   public boolean mouseEnter(Event var1, int var2, int var3) {
      this.requestFocus();
      return false;
   }

   public boolean mouseExit(Event var1, int var2, int var3) {
      return true;
   }

   public void destroy() {
   }

   protected void getParam() {
      try {
         m_nWait = Integer.parseInt(this.getParameter("WAIT"));
      } catch (NumberFormatException var1) {
         m_nWait = 0;
      }
   }

   public boolean keyUp(Event var1, int var2) {
      if (var1.target instanceof Display) {
         this.m_keyMain.keyUp(var2);
         return true;
      } else {
         return false;
      }
   }

   public void start() {
      if (this.m_thrMain == null) {
         this.m_thrMain = new Thread(this);
         this.m_thrMain.start();
      }

   }

   public String getAppletInfo() {
      return "Copyright (C) 1997 COMPILE Corporation. All Rights Reserved.\r\n" + "Name: GameSys\r\n" + "Program: MSY\r\n" + "This Program is made by Microsoft Visual J++ Version 1.0";
   }

   public boolean action(Event var1, Object var2) {
      return this.m_keyMain.action(var1, var2);
   }

   public Image setImage(String var1) {
      Image var2 = this.getImage(this.getCodeBase(), var1);
      this.m_mt.addImage(var2, 0);

      try {
         this.m_mt.waitForID(0);
         return var2;
      } catch (InterruptedException var3) {
         return null;
      }
   }

   public void run() {
      this.m_dspMain.resizeImage();

      while(true) {
         if (!m_fDrawRegInt && !this.m_dspMain.m_fLoading) {
            this.m_gmMain.mainProcess();
         }

         this.m_dspMain.repaint();
         if (m_fDrawRegInt) {
            try {
               Thread.currentThread();
               Thread.sleep((long)m_nWait);
            } catch (InterruptedException var1) {
               System.out.println("Interrupted Exception in GameSys.run()");
               this.stop();
            }
         }
      }
   }

   public void init() {
      this.m_mt = new MediaTracker(this);
      this.getParam();
      this.m_dimScr = this.size();
      this.m_keyMain = new KeyInput();
      this.m_gmMain = new GameMain(this.m_keyMain, this);
      this.m_dspMain = new Display(this.m_dimScr.width - 0, this.m_dimScr.height - 0, Color.white, this.getDocumentBase(), this.m_keyMain, this.m_gmMain, this);
      this.m_pnlMain = new Panel();
      this.setLayout(new BorderLayout());
      this.add("Center", this.m_dspMain);
      this.requestFocus();
   }

   public static void debugInfo(int[] var0) {
      System.out.print("(");

      for(int var1 = 0; var1 < var0.length; ++var1) {
         if (var1 != 0) {
            System.out.print(",");
         }

         System.out.print(var0[var1]);
      }

      System.out.println(")");
   }

   public static void debugInfo(int[][] var0) {
      for(int var1 = 0; var1 < var0.length; ++var1) {
         debugInfo(var0[var1]);
      }

   }

   public boolean keyDown(Event var1, int var2) {
      if (var1.target instanceof Display) {
         this.m_keyMain.keyDown(var2);
         return true;
      } else {
         return false;
      }
   }
}
