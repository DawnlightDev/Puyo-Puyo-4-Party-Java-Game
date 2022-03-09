/* Decompiler 40ms, total 106ms, lines 203 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.net.URL;

public class SimpleDisplay extends Canvas implements Runnable {
   public SimpleBG m_sBG;
   private Dimension m_dimOff;
   private boolean m_fClip;
   public static final int GMP = 0;
   public static final int CNS = 1;
   public static final int CNX = 2;
   public static final int BMP = 3;
   public static final int GIF = 4;
   public static final int IMG_MAX = 256;
   public static final int START_IMGS = 0;
   public static Image[] m_imgTbl;
   public static boolean[] m_fImgTrans;
   public boolean m_fLoading;
   private int m_nReadMode;
   private Thread m_thrLoadGrp;
   private ImageLib m_ilib;
   public SimpleGameMain m_gm;
   public SimpleApplet m_app;
   private KeyInput m_keyMain;

   public boolean mouseEnter(Event var1, int var2, int var3) {
      this.requestFocus();
      return true;
   }

   public void paint(Graphics var1) {
      if (this.m_sBG != null) {
         if (this.m_fClip) {
            Rectangle var2 = this.m_sBG.getUpdateRect();
            var1.clipRect(var2.x, var2.y, var2.width, var2.height);
         }

         this.m_sBG.drawImage(var1, 0, 0, this);
      }

   }

   public boolean mouseUp(Event var1, int var2, int var3) {
      this.m_keyMain.mouseUp(var1.modifiers);
      return true;
   }

   public int disintegrateImage(int var1, Image var2, int var3, int var4) {
      Image[] var6 = ImageLib.disintegrateCombi(var2, var3, var4);

      int var5;
      for(var5 = 0; var5 < var6.length; ++var5) {
         if (var1 + var5 >= m_imgTbl.length) {
            return var1 + var5;
         }

         m_imgTbl[var1 + var5] = this.createImage(var6[var5].getWidth((ImageObserver)null), var6[var5].getHeight((ImageObserver)null));
         Graphics var7 = m_imgTbl[var1 + var5].getGraphics();
         var7.drawImage(var6[var5], 0, 0, (ImageObserver)null);
         m_fImgTrans[var1] = false;
      }

      return var1 + var5;
   }

   public SimpleDisplay(int var1, int var2, URL var3, KeyInput var4, SimpleGameMain var5, SimpleApplet var6) {
      this(var1, var2, Color.black, var3, var4, var5, var6);
   }

   public SimpleDisplay(int var1, int var2, Color var3, URL var4, KeyInput var5, SimpleGameMain var6, SimpleApplet var7) {
      this.resize(var1, var2);
      this.m_ilib = new ImageLib(var4, this.getToolkit());
      m_imgTbl = new Image[256];
      m_fImgTrans = new boolean[256];
      this.setBackground(var3);
      this.m_app = var7;
      this.m_gm = var6;
      this.m_gm.setDisplay(this);
      this.m_keyMain = var5;
   }

   public synchronized void update(Graphics var1) {
      this.resizeImage();
      if (SimpleApplet.m_fDrawRegInt && !this.m_fLoading) {
         this.m_gm.mainProcess();
      }

      if (this.m_sBG.isUpdated()) {
         this.m_fClip = true;
         this.paint(var1);
         this.m_fClip = false;
      }

      this.m_sBG.clearUpdateRect();
   }

   public boolean mouseDown(Event var1, int var2, int var3) {
      this.m_keyMain.mouseDown(var2, var3, var1.modifiers);
      return true;
   }

   public void resizeImage() {
      Dimension var1 = this.size();
      int var2 = var1.width;
      int var3 = var1.height;
      if (this.m_dimOff == null) {
         this.m_sBG = new SimpleBG(this, var2, var3);
      } else {
         if (this.m_dimOff.width == var2 && this.m_dimOff.height == var3) {
            return;
         }

         this.m_sBG.init(var2, var3);
      }

      this.m_dimOff = new Dimension(var2, var3);
   }

   public void run() {
      this.readGraphicsSub();
      this.m_fLoading = false;
   }

   public int setImageGIFFree(int var1, String var2, int var3, int var4, int[][] var5) {
      Image var7 = this.m_ilib.loadCNX(var2, var3, var4);
      Image[] var8 = ImageLib.disintegrateFree(var7, var5);

      int var6;
      for(var6 = 0; var6 < var8.length; ++var6) {
         if (var1 + var6 >= m_imgTbl.length) {
            return var1 + var6;
         }

         if (var3 != -1 && var4 != 255) {
            m_imgTbl[var1 + var6] = var8[var6];
         } else {
            m_imgTbl[var1 + var6] = this.createImage(var8[var6].getWidth((ImageObserver)null), var8[var6].getHeight((ImageObserver)null));
            Graphics var9 = m_imgTbl[var1 + var6].getGraphics();
            var9.drawImage(var8[var6], 0, 0, (ImageObserver)null);
         }
      }

      return var1 + var6;
   }

   protected void readGraphicsSub() {
      byte var1 = 0;

      for(int var2 = 0; var2 < m_imgTbl.length; ++var2) {
         if (m_imgTbl[var2] != null) {
            m_imgTbl[var2].flush();
            m_imgTbl[var2] = null;
         }
      }

      switch(this.m_nReadMode) {
      case 0:
         int[][] var5 = new int[][]{{0, 0, 32, 32}, {32, 0, 32, 32}, {64, 0, 32, 32}, {96, 0, 32, 32}, {128, 0, 32, 32}, {160, 0, 32, 32}, {192, 0, 32, 32}, {224, 0, 32, 32}, {256, 0, 32, 32}, {288, 0, 32, 32}, {320, 0, 160, 32}, {413, 0, 32, 32}, {480, 0, 96, 32}, {0, 32, 384, 32}, {384, 32, 160, 32}};
         int[][] var3 = new int[][]{{0, 0, 90, 90}, {90, 0, 90, 90}, {180, 0, 90, 90}, {270, 0, 90, 90}, {0, 90, 90, 90}, {90, 90, 90, 90}, {180, 90, 90, 90}, {270, 90, 90, 90}, {0, 180, 90, 90}, {90, 180, 90, 90}, {180, 180, 90, 90}, {270, 180, 90, 90}, {0, 270, 90, 90}, {90, 270, 90, 90}, {180, 270, 90, 90}, {270, 270, 90, 90}};
         int var4 = this.setImageGIFFree(var1, "img/moji.cnx", 1, 0, var5);
         this.setImageGIFFree(var4 + 1, "img/puz15.cnx", -1, 255, var3);
         return;
      default:
      }
   }

   public boolean mouseDrag(Event var1, int var2, int var3) {
      this.m_keyMain.mouseMove(var2, var3, var1.modifiers);
      return true;
   }

   public void readGraphics(int var1) {
      this.m_fLoading = true;
      this.m_nReadMode = var1;
      this.m_thrLoadGrp = new Thread(this);
      this.m_thrLoadGrp.start();
   }

   public int setImageGIF(int var1, String var2, int var3, int var4) {
      Image var5 = this.m_ilib.loadCNX(var2, var3, var4);
      if (var3 != -1 && var4 != 255) {
         m_imgTbl[var1] = var5;
      } else {
         m_imgTbl[var1] = this.createImage(var5.getWidth((ImageObserver)null), var5.getHeight((ImageObserver)null));
         Graphics var6 = m_imgTbl[var1].getGraphics();
         var6.drawImage(var5, 0, 0, (ImageObserver)null);
      }

      return var1 + 1;
   }

   public boolean mouseMove(Event var1, int var2, int var3) {
      this.m_keyMain.mouseMove(var2, var3, 0);
      return true;
   }
}
