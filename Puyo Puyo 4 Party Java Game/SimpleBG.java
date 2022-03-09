/* Decompiler 44ms, total 125ms, lines 224 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class SimpleBG {
   public static final int BGMODE_PUT = 0;
   public static final int BGMODE_FILLSCREEN = 1;
   public static final int BGMODE_FULLSIZE = 2;
   private Rectangle m_raUpdate = new Rectangle(0, 0, 0, 0);
   private int m_nImgNo = -1;
   private int m_nScrWidth;
   private int m_nScrHeight;
   private int m_nImgWidth;
   private int m_nImgHeight;
   private Image m_imgBG;
   private Graphics m_gBG;
   private FontMetrics m_fmBG;
   private int m_nBase;
   private int m_nHeight;
   private Color m_colStr;
   private int m_nBGMode;
   private SimpleDisplay m_dspMain;

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

   public void setBGMode(int var1) {
      if (var1 >= 0 || var1 <= 2) {
         this.m_nBGMode = var1;
      }

   }

   public void setColor(Color var1) {
      this.m_colStr = var1;
   }

   public boolean isUpdated() {
      return !this.m_raUpdate.isEmpty();
   }

   public void drawString(String var1, int var2, int var3) {
      this.drawString(var1, var2, var3, true);
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

   public void draw3DRect(int var1, int var2, int var3, int var4, boolean var5) {
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.draw3DRect(var1, var2, var3, var4, var5);
      this.setUpdateRect(var1, var2, var3, var4);
   }

   public void clearImage() {
      this.m_gBG.setColor(this.m_dspMain.getBackground());
      this.m_gBG.fillRect(0, 0, this.m_nScrWidth, this.m_nScrHeight);
      if (this.m_nImgNo >= 0) {
         label36:
         switch(this.m_nBGMode) {
         case 0:
            this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[this.m_nImgNo], 0, 0, (ImageObserver)null);
            break;
         case 1:
            int var1 = 0;

            while(true) {
               if (var1 >= this.m_nScrWidth) {
                  break label36;
               }

               for(int var2 = 0; var2 < this.m_nScrHeight; var2 += this.m_nImgHeight) {
                  if (var1 == 0 && var2 == 0) {
                     this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[this.m_nImgNo], 0, 0, (ImageObserver)null);
                  } else {
                     this.m_gBG.copyArea(0, 0, this.m_nImgWidth, this.m_nImgHeight, var1, var2);
                  }
               }

               var1 += this.m_nImgWidth;
            }
         case 2:
            this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[this.m_nImgNo], 0, 0, this.m_nScrWidth, this.m_nScrHeight, (ImageObserver)null);
         }
      }

      this.setUpdateRect(0, 0, this.m_nScrWidth, this.m_nScrHeight);
   }

   public void setFont(Font var1) {
      this.m_gBG.setFont(var1);
      this.m_fmBG = this.m_gBG.getFontMetrics();
      this.m_nBase = this.m_fmBG.getLeading() + this.m_fmBG.getAscent();
      this.m_nHeight = this.m_fmBG.getHeight();
   }

   public FontMetrics getFontMetrics() {
      return this.m_fmBG;
   }

   public void setImage(int var1) {
      if (!this.isEmpty() && var1 >= 0 && var1 <= SimpleDisplay.m_imgTbl.length) {
         if (this.m_nImgNo < 0 || this.m_nImgNo != var1) {
            this.setUpdateRect(0, 0, this.m_nScrWidth, this.m_nScrHeight);
         }

         this.m_nImgNo = var1;
         this.m_nImgWidth = SimpleDisplay.m_imgTbl[this.m_nImgNo].getWidth((ImageObserver)null);
         this.m_nImgHeight = SimpleDisplay.m_imgTbl[this.m_nImgNo].getHeight((ImageObserver)null);
         this.clearImage();
      }
   }

   public void resetImage() {
      this.m_nImgNo = -1;
      this.m_nImgWidth = 0;
      this.m_nImgHeight = 0;
      this.clearImage();
   }

   public void drawImage(int var1, int var2, int var3, ImageObserver var4) {
      if (SimpleDisplay.m_imgTbl[var1] != null) {
         this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[var1], var2, var3, var4);
         this.setUpdateRect(var2, var3, SimpleDisplay.m_imgTbl[var1].getWidth(var4), SimpleDisplay.m_imgTbl[var1].getHeight(var4));
      }

   }

   public void drawImage(int var1, int var2, int var3, Color var4, ImageObserver var5) {
      if (SimpleDisplay.m_imgTbl[var1] != null) {
         this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[var1], var2, var3, var4, var5);
         this.setUpdateRect(var2, var3, SimpleDisplay.m_imgTbl[var1].getWidth(var5), SimpleDisplay.m_imgTbl[var1].getHeight(var5));
      }

   }

   public void drawImage(int var1, int var2, int var3, int var4, int var5, ImageObserver var6) {
      if (SimpleDisplay.m_imgTbl[var1] != null) {
         this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[var1], var2, var3, var4, var5, var6);
         this.setUpdateRect(var2, var3, var4, var5);
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

   public void drawImage(int var1, int var2, int var3, int var4, int var5, Color var6, ImageObserver var7) {
      if (SimpleDisplay.m_imgTbl[var1] != null) {
         this.m_gBG.drawImage(SimpleDisplay.m_imgTbl[var1], var2, var3, var4, var5, var6, var7);
         this.setUpdateRect(var2, var3, var4, var5);
      }

   }

   public void copyArea(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.m_gBG.copyArea(var1, var2, var3, var4, var5, var6);
      this.setUpdateRect(var1 + var5, var2 + var6, var3, var4);
   }

   public void drawImage(Graphics var1, int var2, int var3, ImageObserver var4) {
      var1.drawImage(this.m_imgBG, var2, var3, var4);
   }

   public SimpleBG(SimpleDisplay var1, int var2, int var3) {
      this.m_colStr = Color.white;
      this.m_dspMain = var1;
      this.init(var2, var3);
   }

   public void drawRect(int var1, int var2, int var3, int var4) {
      this.m_gBG.setColor(this.m_colStr);
      this.m_gBG.drawRect(var1, var2, var3, var4);
      this.setUpdateRect(var1, var2, var3, var4);
   }
}
