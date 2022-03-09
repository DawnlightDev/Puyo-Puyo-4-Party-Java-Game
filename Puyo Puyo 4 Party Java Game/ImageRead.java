/* Decompiler 97ms, total 167ms, lines 320 */
import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageRead {
   private static final int CNX_FORMAT_VER = 2;
   private static final int CM_LEN = 2;
   private static final int CM_CNT = 4;
   private static final int CM_MASK = 3;
   private static final int CM_END = 0;
   private static final int CM_NOP = 0;
   private static final int CM_BT1 = 1;
   private static final int CM_CPY = 2;
   private static final int CM_BTS = 3;
   FileRead fr;
   Applet applet;
   URL BaseURL;

   private Image loadGmp2(byte[] var1, int var2) {
      int var3 = var1[9] << 8 & '\uff00' | var1[8] & 255;
      int var4 = var1[13] << 8 & '\uff00' | var1[12] & 255;
      int var5 = var1[21] << 8 & '\uff00' | var1[20] & 255;
      int var6 = var1[25] << 8 & '\uff00' | var1[24] & 255;
      int var7 = var1[29] << 8 & '\uff00' | var1[28] & 255;
      int var8 = var1[31] << 8 & '\uff00' | var1[30] & 255;
      int var10 = 0;
      if (var8 == 4) {
         var10 = var4 / 8 * 8;
         if (var4 % 8 != 0) {
            var10 += 8;
         }
      } else if (var8 == 8) {
         var10 = var4 / 4 * 4;
         if (var4 % 4 != 0) {
            var10 += 4;
         }
      }

      int var9 = var10 * var3;
      int[] var12 = new int[var9];
      int[] var13 = new int[var9];
      int[] var11 = new int[var7];
      int var14 = var5;

      int var15;
      for(var15 = 0; var14 < var6; ++var15) {
         var11[var15] = ~var1[var14 + 3] << 24 & -16777216 | var1[var14 + 2] << 16 & 16711680 | var1[var14 + 1] << 8 & '\uff00' | var1[var14] & 255;
         if (var2 == var15) {
            var11[var15] &= 16777215;
         }

         var14 += 4;
      }

      if (var8 == 4) {
         for(var14 = 0; var14 < var9 / 2; ++var14) {
            var12[var14 * 2 + 0] = var11[var1[var6 + var14] >>> 4 & 15];
            var12[var14 * 2 + 1] = var11[var1[var6 + var14] & 15];
         }
      } else if (var8 == 8) {
         for(var14 = 0; var14 < var9; ++var14) {
            var12[var14] = var11[var1[var6 + var14] & 255];
         }
      }

      for(var14 = 0; var14 < var3; ++var14) {
         for(var15 = 0; var15 < var10; ++var15) {
            var13[(var3 - var14 - 1) * var10 + var15] = var12[var14 * var10 + var15];
         }
      }

      return this.applet.createImage(new MemoryImageSource(var4, var3, var13, 0, var10));
   }

   private int checkGMPversion(byte[] var1) {
      if ((new String(var1, 0, 0, 7)).equals("GMP-200")) {
         return 2;
      } else {
         return var1[0] == 0 && var1[1] == 0 ? 1 : 0;
      }
   }

   private byte[] meltCNS(byte[] var1) {
      int var2 = 0;
      int var3 = 0;
      byte[] var4 = new byte[var1.length];

      while(true) {
         int var7;
         label48:
         while((var7 = var1[var3++]) != 0) {
            int var5;
            int var6;
            int var8;
            switch(var7 & 240) {
            case 0:
               var6 = (var7 & 15) + 2;
               var5 = var1[var3] & 255;
               ++var3;
               break;
            case 16:
               var6 = (var7 & 15) + 2;
               var5 = var1[var3 + 1] << 8 & '\uff00' | var1[var3] & 255;
               var3 += 2;
               break;
            case 32:
               var6 = var7 << 8 & 3840 | var1[var3] & 255;
               var5 = var1[var3 + 1] & 255;
               var3 += 2;
               break;
            case 48:
               var6 = var7 << 8 & 3840 | var1[var3] & 255;
               var5 = var1[var3 + 2] << 8 & '\uff00' | var1[var3 + 1] & 255;
               var3 += 3;
               break;
            case 64:
            case 80:
               var6 = var7 & 31;
               var8 = 0;

               while(true) {
                  if (var8 >= var6) {
                     continue label48;
                  }

                  var4[var2++] = var1[var3++];
                  ++var8;
               }
            case 96:
            case 112:
               var6 = var7 << 8 & 7936 | var1[var3++] & 255;

               for(var8 = 0; var8 < var6; ++var8) {
                  var4[var2++] = var1[var3++];
               }
               continue;
            default:
               var6 = var7 >>> 4 & 7;

               for(var8 = 0; var8 < var6; ++var8) {
                  var4[var2++] = var1[var3++];
               }

               var5 = var1[var3++] & 255;
               var6 = (var7 & 15) + 2;
            }

            for(var8 = 0; var8 < var6; ++var8) {
               var4[var2] = var4[var2 - var5];
               ++var2;
            }
         }

         return var4;
      }
   }

   public ImageRead(Applet var1) {
      this.fr = new FileRead(var1);
      this.applet = var1;

      try {
         this.BaseURL = new URL(var1.getCodeBase(), "img/");
      } catch (MalformedURLException var2) {
         System.out.println("Malformed URL Exception");
      }
   }

   Image loadImage(String var1, int var2) {
      if (!var1.endsWith(".gif") && !var1.endsWith(".jpg")) {
         byte[] var3;
         if (var1.endsWith(".gmp")) {
            var3 = this.fr.dataRead(var1);
            switch(this.checkGMPversion(var3)) {
            case 0:
            default:
               break;
            case 1:
               return this.loadGmp2(var3, var2);
            case 2:
               return this.loadGmp2(var3, var2);
            }
         } else if (var1.endsWith(".cnx")) {
            var3 = this.fr.dataRead(var1);
            if (this.checkCNXversion(var3)) {
               return this.loadGmp2(this.meltCNX(var3), var2);
            }
         } else if (var1.endsWith(".cns")) {
            return this.loadGmp2(this.meltCNS(this.fr.dataRead(var1)), var2);
         }

         return null;
      } else {
         return this.loadGif(var1);
      }
   }

   Image disintegrate(Image var1, int var2, int var3, int var4, int var5) {
      if (var2 < 0) {
         var2 = 0;
      }

      if (var3 < 0) {
         var3 = 0;
      }

      if (var1.getWidth((ImageObserver)null) < var2 + var4) {
         var4 = var1.getWidth((ImageObserver)null) - var2;
      }

      if (var1.getHeight((ImageObserver)null) < var3 + var5) {
         var5 = var1.getHeight((ImageObserver)null) - var3;
      }

      CropImageFilter var6 = new CropImageFilter(var2, var3, var4, var5);
      FilteredImageSource var7 = new FilteredImageSource(var1.getSource(), var6);
      Image var8 = this.applet.createImage(var7);
      MediaTracker var9 = new MediaTracker(this.applet);
      var9.addImage(var8, 0);
      this.waitMediaTracker(var9);
      return var8;
   }

   void waitMediaTracker(MediaTracker var1) {
      try {
         var1.waitForID(0);
      } catch (InterruptedException var2) {
         System.out.println("Fault");
      }
   }

   private boolean checkCNXversion(byte[] var1) {
      return (new String(var1, 0, 0, 3)).equals("CNX") && var1[3] == 2;
   }

   private byte[] meltCNX(byte[] var1) {
      int var2 = var1[12] << 24 & -16777216 | var1[13] << 16 & 16711680 | var1[14] << 8 & '\uff00' | var1[15] & 255;
      byte[] var3 = new byte[var2];
      byte var4 = var1[7];
      int var5 = 0;
      int var6 = var1[7];

      int var11;
      for(int var7 = 0; var5 < var2; var5 = var7 - var11) {
         var11 = var7;
         int var12 = var6;
         int var13 = var1[var6];
         int var14 = 0;
         ++var6;

         while(true) {
            int var10;
            switch(var13 & 3) {
            case 0:
               var6 += var1[var6];
               ++var6;
               var14 = 4;
               break;
            case 1:
               var3[var7] = var1[var6];
               ++var7;
               ++var6;
               break;
            case 2:
               int var8 = var1[var6] << 8 & '\uff00' | var1[var6 + 1] & 255;
               var6 += 2;
               var10 = (var8 & 31) + 4;
               int var9 = (var8 >>> 5 & 2047) + 1;

               for(int var15 = 0; var15 < var10; ++var15) {
                  var3[var7 + var15] = var3[var7 - var9 + var15];
               }

               var7 += var10;
               break;
            case 3:
               var10 = var1[var6] & 255;
               ++var6;

               while(var10 > 0) {
                  var3[var7] = var1[var6];
                  ++var7;
                  ++var6;
                  --var10;
               }
            }

            ++var14;
            if (var14 >= 4) {
               var13 = var1[var6];
               var14 = 0;
               ++var6;
               if (var13 == 0) {
                  int var10000 = var6 - var12;
                  break;
               }
            } else {
               var13 >>>= 2;
            }
         }
      }

      return var3;
   }

   private Image loadGif(String var1) {
      Image var2 = this.applet.getImage(this.BaseURL, var1);
      MediaTracker var3 = new MediaTracker(this.applet);
      var3.addImage(var2, 0);
      this.waitMediaTracker(var3);
      return var2;
   }
}
