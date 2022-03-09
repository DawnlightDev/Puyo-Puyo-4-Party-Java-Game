/* Decompiler 42ms, total 118ms, lines 208 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.net.URL;

public class Display extends Canvas implements Runnable {
   public static final int IMG_MAX = 256;
   public static final int START_IMGS = 0;
   public static final int START_IMGS2 = 1;
   public static final int START_IMGS3 = 2;
   public BackGround m_sBG;
   private Dimension m_dimOff;
   private boolean m_fClip;
   public static Image[] m_imgTbl;
   public boolean m_fLoading;
   private int m_nReadMode;
   private Thread m_thrLoadGrp;
   private ImageLib m_ilib;
   private boolean m_fInit;
   public GameMain m_gm;
   public AppletMain m_app;
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

         this.m_sBG.draw(var1, 0, 0, this);
      }

   }

   public boolean mouseUp(Event var1, int var2, int var3) {
      this.m_keyMain.mouseUp();
      return true;
   }

   public void update(Graphics var1) {
      this.resizeImage();
      if (AppletMain.m_fDrawRegInt && !this.m_fLoading) {
         this.m_gm.mainProcess();
      }

      if (this.m_sBG.isUpdated()) {
         this.m_fClip = true;
         this.paint(var1);
         this.m_fClip = false;
      }

      this.m_sBG.clearUpdateRect();
   }

   public int setImageGIFCombi(int var1, String var2, int var3, int var4, int var5, int var6) {
      Image var8 = this.m_ilib.loadCNX(var2, var3, var4);
      Image[] var9 = ImageLib.disintegrateCombi(var8, var5, var6);

      int var7;
      for(var7 = 0; var7 < var9.length; ++var7) {
         if (var1 + var7 >= m_imgTbl.length) {
            return var1 + var7;
         }

         if (var3 != -1 && var4 != 255) {
            m_imgTbl[var1 + var7] = var9[var7];
         } else {
            m_imgTbl[var1 + var7] = this.createImage(var9[var7].getWidth((ImageObserver)null), var9[var7].getHeight((ImageObserver)null));
            Graphics var10 = m_imgTbl[var1 + var7].getGraphics();
            var10.drawImage(var9[var7], 0, 0, (ImageObserver)null);
         }
      }

      return var1 + var7;
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
         this.m_sBG = new BackGround(this, var2, var3);
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

   public Display(int var1, int var2, URL var3, KeyInput var4, GameMain var5, AppletMain var6) {
      this(var1, var2, Color.black, var3, var4, var5, var6);
   }

   public Display(int var1, int var2, Color var3, URL var4, KeyInput var5, GameMain var6, AppletMain var7) {
      this.resize(var1, var2);
      this.m_ilib = new ImageLib(var4, this.getToolkit());
      m_imgTbl = new Image[256];
      this.setBackground(var3);
      this.m_app = var7;
      this.m_gm = var6;
      this.m_gm.setDisplay(this);
      this.m_keyMain = var5;
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

   public boolean mouseDrag(Event var1, int var2, int var3) {
      this.m_keyMain.mouseMove(var2, var3);
      return true;
   }

   protected void readGraphicsSub() {
      if (this.m_fInit) {
         for(int var1 = 0; var1 < m_imgTbl.length; ++var1) {
            if (m_imgTbl[var1] != null) {
               m_imgTbl[var1].flush();
               m_imgTbl[var1] = null;
            }
         }
      }

      switch(this.m_nReadMode) {
      case 0:
         this.setImageGIFCombi(0, "img/bomb.cnx", -1, 255, 44, 63);
         this.setImageGIF(2, "img/logo.cnx", -1, 255);
         this.setImageGIFCombi(3, "img/font.cnx", -1, 255, 16, 32);
         this.setImageGIF(39, "img/pass.cnx", 2, 0);
         this.setImageGIF(40, "img/logob.cnx", -1, 255);
         this.setImageGIFCombi(41, "img/marubatu.cnx", -1, 255, 32, 32);
         this.setImageGIF(43, "img/ending.cnx", -1, 255);
         this.setImageGIF(44, "img/package.cnx", -1, 255);
         return;
      default:
      }
   }

   public void readGraphics(int var1, boolean var2) {
      this.m_fInit = var2;
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
      this.m_keyMain.mouseMove(var2, var3);
      return true;
   }
}
