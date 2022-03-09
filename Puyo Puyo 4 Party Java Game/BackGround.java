/* Decompiler 22ms, total 93ms, lines 166 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class BackGround {
   public static final int BGMODE_PUT = 0;
   public static final int BGMODE_FILLSCREEN = 1;
   public static final int BGMODE_FULLSIZE = 2;
   private Image m_imgBG;
   private int m_nBGMode;
   private int m_nScrWidth;
   private int m_nScrHeight;
   private Graphics m_gBG;
   private FontMetrics m_fmBG;
   private int m_nBase;
   private int m_nHeight;
   private Color m_colStr;
   private Rectangle m_raUpdate;
   private Display m_dspMain;
   private Font[] m_fntTbl;

   public void clearUpdateRect() {
      this.m_raUpdate.width = this.m_raUpdate.height = 0;
   }

   public void fillRect(int var1, int var2, int var3, int var4) {
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.fillRect(var1, var2, var3, var4);
      this.setUpdateRect(var1, var2, var3, var4);
   }

   public Rectangle getUpdateRect() {
      return this.m_raUpdate;
   }

   private void setUpdateRect(int var1, int var2, int var3, int var4) {
      if (var3 != 0 && var4 != 0) {
         if (this.m_raUpdate.isEmpty()) {
            this.m_raUpdate.reshape(var1, var2, var3, var4);
         } else {
            this.m_raUpdate.add(new Rectangle(var1, var2, var3, var4));
         }
      }
   }

   public void draw(Graphics var1, int var2, int var3, ImageObserver var4) {
      var1.drawImage(this.m_imgBG, var2, var3, var4);
   }

   public BackGround(Display var1, int var2, int var3) {
      this.m_colStr = Color.white;
      this.m_raUpdate = new Rectangle(0, 0, 0, 0);
      this.m_fntTbl = new Font[]{new Font("Dialog", 0, 16), new Font("Courier", 1, 24), new Font("TimesRoman", 1, 48), new Font("TimesRoman", 3, 24), new Font("TimesRoman", 1, 24), new Font("TimesRoman", 1, 16)};
      this.m_dspMain = var1;
      this.init(var2, var3);
   }

   public void setBGMode(int var1) {
      if (var1 >= 0 || var1 <= 2) {
         this.m_nBGMode = var1;
      }

   }

   public void setColor(Color var1) {
      this.m_colStr = var1;
   }

   public void drawString(String var1, int var2, int var3) {
      this.drawString(var1, var2, var3, true);
   }

   public void drawString(String var1, int var2, int var3, Color var4) {
      int var5 = this.m_fmBG.stringWidth(var1);
      this.m_gBG.setColor(var4);
      this.m_gBG.fillRect(var2, var3, var5, this.m_nHeight);
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.drawString(var1, var2, var3 + this.m_nBase);
      this.setUpdateRect(var2, var3, var5, this.m_nHeight);
   }

   public void drawString(String var1, int var2, int var3, boolean var4) {
      int var5 = this.m_fmBG.stringWidth(var1);
      if (var4) {
         this.m_gBG.setColor(this.m_dspMain.getBackground());
         this.m_gBG.fillRect(var2, var3, var5, this.m_nHeight);
      }

      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.drawString(var1, var2, var3 + this.m_nBase);
      this.setUpdateRect(var2, var3, var5, this.m_nHeight);
   }

   public boolean isUpdated() {
      return !this.m_raUpdate.isEmpty();
   }

   public void draw3DRect(int var1, int var2, int var3, int var4, boolean var5) {
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.draw3DRect(var1, var2, var3, var4, var5);
      this.setUpdateRect(var1, var2, var3, var4);
   }

   public void clearImage() {
      this.m_gBG.setColor(this.m_dspMain.getBackground());
      this.m_gBG.fillRect(0, 0, this.m_nScrWidth, this.m_nScrHeight);
      this.setUpdateRect(0, 0, this.m_nScrWidth, this.m_nScrHeight);
   }

   public FontMetrics setFont(int var1) {
      if (var1 < this.m_fntTbl.length) {
         this.m_gBG.setFont(this.m_fntTbl[var1]);
         this.m_fmBG = this.m_gBG.getFontMetrics();
         this.m_nBase = this.m_fmBG.getLeading() + this.m_fmBG.getAscent();
         this.m_nHeight = this.m_fmBG.getHeight();
         return this.m_fmBG;
      } else {
         return null;
      }
   }

   public void fillOval(int var1, int var2, int var3, int var4) {
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.fillOval(var1, var2, var3, var4);
      this.setUpdateRect(var1, var2, var3, var4);
   }

   public FontMetrics getFontMetrics() {
      return this.m_fmBG;
   }

   public void drawImage(int var1, int var2, int var3, ImageObserver var4) {
      if (Display.m_imgTbl[var1] != null) {
         this.m_gBG.drawImage(Display.m_imgTbl[var1], var2, var3, var4);
         this.setUpdateRect(var2, var3, Display.m_imgTbl[var1].getWidth(var4), Display.m_imgTbl[var1].getHeight(var4));
      }

   }

   public void init(int var1, int var2) {
      this.m_imgBG = this.m_dspMain.createImage(var1, var2);
      this.m_nScrWidth = var1;
      this.m_nScrHeight = var2;
      this.m_gBG = this.m_imgBG.getGraphics();
      this.m_fmBG = this.m_gBG.getFontMetrics();
      this.m_nBase = this.m_fmBG.getLeading() + this.m_fmBG.getAscent();
      this.m_nHeight = this.m_fmBG.getHeight();
      this.clearImage();
      this.setUpdateRect(0, 0, this.m_nScrWidth, this.m_nScrHeight);
   }

   public boolean isEmpty() {
      return this.m_imgBG == null || this.m_gBG == null;
   }

   public void drawRect(int var1, int var2, int var3, int var4) {
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.drawRect(var1, var2, var3, var4);
      this.setUpdateRect(var1, var2, var3 + 1, var4 + 1);
   }
}
