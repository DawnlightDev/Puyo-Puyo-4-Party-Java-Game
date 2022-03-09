/* Decompiler 32ms, total 91ms, lines 205 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;

public class SimpleApplet extends Applet implements Runnable {
   public static final int PNL_WIDTH = 0;
   public Dimension m_dimScr;
   public Thread m_thrMain;
   public SimpleDisplay m_dspMain;
   public SimpleGameMain m_gmMain;
   public KeyInput m_keyMain;
   public Panel m_pnlMain;
   public static boolean m_fDrawRegInt = true;
   public static int m_nWait;
   public MediaTracker m_mt;
   public static final boolean m_fDebug = false;
   public Color bgColor;

   public static void debugInfo(boolean var0) {
   }

   public static void debugInfo(Object var0) {
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

   public void stop() {
      if (this.m_thrMain != null) {
         this.m_thrMain.stop();
         this.m_thrMain = null;
      }

   }

   public boolean keyDown(Event var1, int var2) {
      if (var1.target instanceof SimpleDisplay) {
         this.m_keyMain.keyDown(var2);
         return true;
      } else {
         return false;
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
      } catch (NumberFormatException var5) {
         m_nWait = 0;
      }

      try {
         int var1 = Integer.parseInt(this.getParameter("BGCOLOR_R"));
         int var2 = Integer.parseInt(this.getParameter("BGCOLOR_G"));
         int var3 = Integer.parseInt(this.getParameter("BGCOLOR_B"));
         this.bgColor = new Color(var1, var2, var3);
      } catch (NumberFormatException var4) {
         this.bgColor = Color.white;
      }
   }

   public boolean keyUp(Event var1, int var2) {
      if (var1.target instanceof SimpleDisplay) {
         this.m_keyMain.keyUp(var2);
         return true;
      } else {
         return false;
      }
   }

   public static void debugInfoHex(int[] var0) {
      System.out.print("(");

      for(int var1 = 0; var1 < var0.length; ++var1) {
         if (var1 != 0) {
            System.out.print(",");
         }

         System.out.print(Integer.toHexString(var0[var1]));
      }

      System.out.println(")");
   }

   public static void debugInfoHex(int[][] var0) {
      for(int var1 = 0; var1 < var0.length; ++var1) {
         debugInfoHex(var0[var1]);
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
      this.m_gmMain = new SimpleGameMain(this.m_keyMain, this);
      this.m_dspMain = new SimpleDisplay(this.m_dimScr.width - 0, this.m_dimScr.height, this.bgColor, this.getDocumentBase(), this.m_keyMain, this.m_gmMain, this);
      if (this.m_pnlMain != null) {
         this.m_pnlMain.setBackground(Color.lightGray);
         this.setLayout(new BorderLayout(0, 0));
         this.add("Center", this.m_pnlMain);
         this.add("East", this.m_dspMain);
      } else {
         this.setLayout(new BorderLayout());
         this.add("Center", this.m_dspMain);
      }

      this.requestFocus();
   }

   public static void debugInfo(byte var0) {
   }

   public static void debugInfo(int var0) {
   }

   public static void debugInfo(long var0) {
   }
}
