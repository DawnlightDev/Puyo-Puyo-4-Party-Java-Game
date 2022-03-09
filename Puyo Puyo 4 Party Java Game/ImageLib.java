/* Decompiler 127ms, total 201ms, lines 426 */
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLib {
   private static final int GMP_HEADER_SIZE = 8;
   private static final int GMP2_HEADER_SIZE = 32;
   private static final int GMP_HEAD_SIZEX = 2;
   private static final int GMP_HEAD_SIZEY = 4;
   private static final int GMP_HEAD_ENTRY = 6;
   private static final int GMP2_HEAD_SIZEY = 8;
   private static final int GMP2_HEAD_SIZEX = 12;
   private static final int GMP2_HEAD_FLAGS = 16;
   private static final int GMP2_HEAD_PALLETE = 20;
   private static final int GMP2_HEAD_DIB = 24;
   private static final int GMP2_HEAD_ENTRY = 28;
   private static final int GMP2_HEAD_PIXEL = 30;
   private static final int CNX_HEAD_ID = 0;
   private static final int CNX_HEAD_VER = 3;
   private static final int CNX_HEAD_FID = 4;
   private static final int CNX_HEAD_HSIZE = 7;
   private static final int CNX_HEAD_PSIZE = 8;
   private static final int CNX_HEAD_SSIZE = 12;
   private static final int CNX_FORMAT_VER = 2;
   private static final int CNX_CMCPY_BIT = 5;
   private static final int CNX_CM_LEN = 2;
   private static final int CNX_CM_CNT = 4;
   private static final int CNX_CM_MASK = 3;
   private static final int CNX_CM_NOP = 0;
   private static final int CNX_CM_BT1 = 1;
   private static final int CNX_CM_CPY = 2;
   private static final int CNX_CM_BTS = 3;
   public static Toolkit m_tk;
   private URL m_urlBase;

   private Image convGmpToImage(byte[] var1, int var2, int var3) {
      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;
      int var9 = 0;
      boolean var10 = false;
      if (var1 == null) {
         return null;
      } else {
         boolean var11;
         int var16;
         int var17;
         int var18;
         byte var19;
         if ((new String(var1, 0, 0, 7)).equals("GMP-200")) {
            var11 = true;
            var17 = var1[9] << 8 & '\uff00' | var1[8] & 255;
            var16 = var1[13] << 8 & '\uff00' | var1[12] & 255;
            var18 = var1[29] << 8 & '\uff00' | var1[28] & 255;
            var19 = 32;
         } else {
            var11 = true;
            var16 = var1[3] << 8 & '\uff00' | var1[2] & 255;
            var17 = var1[5] << 8 & '\uff00' | var1[4] & 255;
            var18 = var1[7] << 8 & '\uff00' | (var1[6] & 255) + 1;
            var19 = 8;
         }

         if (var18 <= 16) {
            var9 = (var16 + 7) / 8 * 8;
         } else if (var18 <= 256) {
            var9 = (var16 + 3) / 4 * 4;
         }

         int[] var12 = new int[var9 * var17 + 1];
         int[] var13 = new int[var9 * var17 + 1];
         int[] var5;
         if (var18 <= 16) {
            var5 = new int[16];
         } else {
            if (var18 > 256) {
               return null;
            }

            var5 = new int[256];
         }

         int var14;
         for(var14 = 0; var14 < var18; ++var14) {
            if (var2 == var14) {
               var5[var14] = (var3 & 255) << 24 & -16777216 | var1[var19 + 2 + var14 * 4] << 16 & 16711680 | var1[var19 + 1 + var14 * 4] << 8 & '\uff00' | var1[var19 + var14 * 4] & 255;
            } else {
               var5[var14] = ~var1[var19 + 3 + var14 * 4] << 24 & -16777216 | var1[var19 + 2 + var14 * 4] << 16 & 16711680 | var1[var19 + 1 + var14 * 4] << 8 & '\uff00' | var1[var19 + var14 * 4] & 255;
            }
         }

         if (var18 <= 16) {
            for(var14 = 0; var14 < var9 * var17 / 2; ++var14) {
               var12[var14 * 2 + 0] = var5[var1[var19 + var18 * 4 + var14] >>> 4 & 15];
               var12[var14 * 2 + 1] = var5[var1[var19 + var18 * 4 + var14] & 15];
            }
         } else if (var18 <= 256) {
            for(var14 = 0; var14 < var9 * var17; ++var14) {
               var12[var14] = var5[var1[var19 + var18 * 4 + var14] & 255];
            }
         }

         for(var14 = 0; var14 < var17; ++var14) {
            for(int var15 = 0; var15 < var9; ++var15) {
               var13[(var17 - var14 - 1) * var9 + var15] = var12[var14 * var9 + var15];
            }
         }

         Image var4 = m_tk.createImage(new MemoryImageSource(var16, var17, var13, 0, var9));
         Object var20 = null;
         return var4;
      }
   }

   public Image loadGMP(String var1) {
      return this.convGmpToImage(FileLib.readFile(this.m_urlBase, var1), -1, 255);
   }

   public Image loadGMP(String var1, int var2, int var3) {
      return this.convGmpToImage(FileLib.readFile(this.m_urlBase, var1), var2, var3);
   }

   private byte[] unpressCNS(byte[] var1) {
      int var2 = 0;
      int var3 = 0;
      byte[] var5 = new byte[var1.length];

      while(true) {
         int var6;
         label48:
         while((var6 = var1[var3++]) != 0) {
            int var4;
            int var7;
            int var8;
            switch(var6 & 240) {
            case 0:
               var7 = (var6 & 15) + 2;
               var4 = var1[var3++] & 255;
               break;
            case 16:
               var7 = (var6 & 15) + 2;
               var4 = var1[var3 + 1] << 8 & '\uff00' | var1[var3] & 255;
               var3 += 2;
               break;
            case 32:
               var7 = var6 << 8 & 3840 | var1[var3++] & 255;
               var4 = var1[var3++] & 255;
               break;
            case 48:
               var7 = var6 << 8 & 3840 | var1[var3++] & 255;
               var4 = var1[var3 + 1] << 8 & '\uff00' | var1[var3] & 255;
               var3 += 2;
               break;
            case 64:
            case 80:
               var7 = var6 & 31;
               var8 = 0;

               while(true) {
                  if (var8 >= var7) {
                     continue label48;
                  }

                  var5[var2++] = var1[var3++];
                  ++var8;
               }
            case 96:
            case 112:
               var7 = var6 << 8 & 7936 | var1[var3++] & 255;

               for(var8 = 0; var8 < var7; ++var8) {
                  var5[var2++] = var1[var3++];
               }
               continue;
            default:
               var7 = var6 >>> 4 & 7;

               for(var8 = 0; var8 < var7; ++var8) {
                  var5[var2++] = var1[var3++];
               }

               var4 = var1[var3++] & 255;
               var7 = (var6 & 15) + 2;
            }

            for(var8 = 0; var8 < var7; ++var8) {
               var5[var2] = var5[var2 - var4];
               ++var2;
            }
         }

         return var5;
      }
   }

   public static Image[] disintegrateFree(Image var0, int[][] var1) {
      Image[] var2 = new Image[var1.length];

      for(int var3 = 0; var3 < var1.length; ++var3) {
         var2[var3] = disintegrate(var0, var1[var3][0], var1[var3][1], var1[var3][2], var1[var3][3]);
      }

      return var2;
   }

   public Image loadCNS(String var1) {
      return this.convGmpToImage(this.unpressCNS(FileLib.readFile(this.m_urlBase, var1)), -1, 255);
   }

   public Image loadCNS(String var1, int var2, int var3) {
      return this.convGmpToImage(this.unpressCNS(FileLib.readFile(this.m_urlBase, var1)), var2, var3);
   }

   public static Image disintegrate(Image var0, int var1, int var2, int var3, int var4) {
      int var5 = var0.getWidth((ImageObserver)null);
      int var6 = var0.getHeight((ImageObserver)null);
      if (var1 < 0) {
         var3 += var1;
         var1 = 0;
      }

      if (var2 < 0) {
         var4 += var2;
         var2 = 0;
      }

      if (var5 < var1 + var3) {
         var3 = var5 - var1;
      }

      if (var6 < var2 + var4) {
         var4 = var6 - var2;
      }

      int[] var7 = new int[var3 * var4];
      PixelGrabber var8 = new PixelGrabber(var0, var1, var2, var3, var4, var7, 0, var3);

      try {
         var8.grabPixels();
      } catch (InterruptedException var9) {
         System.out.println("Interrupted Exception in file.disintegrate() : ");
         return null;
      }

      return m_tk.createImage(new MemoryImageSource(var3, var4, var7, 0, var3));
   }

   public ImageLib(URL var1, Toolkit var2) {
      try {
         this.m_urlBase = new URL(var1, "");
      } catch (MalformedURLException var3) {
         System.out.println("Malformed URL Exception in constructor of class LoadImageFile");
         return;
      }

      if (m_tk == null) {
         m_tk = var2;
      }

   }

   public static Image copy(Image var0) {
      return disintegrate(var0, 0, 0, var0.getWidth((ImageObserver)null), var0.getHeight((ImageObserver)null));
   }

   public static Image changeAlpha(Image var0, Color var1, int var2) {
      int var3 = var0.getWidth((ImageObserver)null);
      int var4 = var0.getHeight((ImageObserver)null);
      int[] var5 = new int[var3 * var4];
      int var6 = (var2 & 255) << 24 & -16777216 | var1.getRed() << 16 & 16711680 | var1.getGreen() << 8 & '\uff00' | var1.getBlue() & 255;
      PixelGrabber var7 = new PixelGrabber(var0, 0, 0, var3, var4, var5, 0, var3);

      try {
         var7.grabPixels();
      } catch (InterruptedException var9) {
         System.out.println("Interrupted Exception");
         return null;
      }

      for(int var8 = 0; var8 < var5.length; ++var8) {
         if ((var5[var8] & 16777215) == (var6 & 16777215)) {
            var5[var8] = var6;
         }
      }

      return m_tk.createImage(new MemoryImageSource(var3, var4, var5, 0, var3));
   }

   public static Image reverseImageLR(Image var0) {
      int var1 = var0.getWidth((ImageObserver)null);
      int var2 = var0.getHeight((ImageObserver)null);
      int[] var3 = new int[var1 * var2];
      PixelGrabber var5 = new PixelGrabber(var0, 0, 0, var1, var2, var3, 0, var1);

      try {
         var5.grabPixels();
      } catch (InterruptedException var8) {
         System.out.println("Interrupted Exception in file.disintegrate() : ");
         return null;
      }

      for(int var6 = 0; var6 < var2; ++var6) {
         for(int var7 = 0; var7 < var1 / 2; ++var7) {
            int var4 = var3[var6 * var1 + var7];
            var3[var6 * var1 + var7] = var3[(var6 + 1) * var1 - var7 - 1];
            var3[(var6 + 1) * var1 - var7 - 1] = var4;
         }
      }

      return m_tk.createImage(new MemoryImageSource(var1, var2, var3, 0, var1));
   }

   public static Image makeFilter(int var0, int var1, Color var2, int var3) {
      int[] var4 = new int[var0 * var1];

      for(int var5 = 0; var5 < var4.length; ++var5) {
         var4[var5] = (var3 & 255) << 24 | (var2.getRed() & 255) << 16 | (var2.getGreen() & 255) << 8 | var2.getBlue() & 255;
      }

      return m_tk.createImage(new MemoryImageSource(var0, var1, var4, 0, var0));
   }

   private byte[] unpressCNX(byte[] var1) {
      if (var1[0] == 67 && var1[1] == 78 && var1[2] == 88 && var1[3] == 2) {
         int var9 = var1[12] << 24 & -16777216 | var1[13] << 16 & 16711680 | var1[14] << 8 & '\uff00' | var1[15] & 255;
         byte[] var10 = new byte[var9];
         byte var11 = var1[7];
         int var12 = 0;
         int var13 = var1[7];

         int var5;
         for(int var14 = 0; var12 < var9; var12 = var14 - var5) {
            var5 = var14;
            int var6 = var13;
            int var7 = var1[var13];
            int var8 = 0;
            ++var13;

            while(true) {
               int var4;
               switch(var7 & 3) {
               case 0:
                  var13 += var1[var13];
                  ++var13;
                  var8 = 4;
                  break;
               case 1:
                  var10[var14] = var1[var13];
                  ++var14;
                  ++var13;
                  break;
               case 2:
                  int var2 = var1[var13] << 8 & '\uff00';
                  ++var13;
                  var2 |= var1[var13] & 255;
                  ++var13;
                  var4 = (var2 & 31) + 4;
                  int var3 = (var2 >>> 5 & 2047) + 1;

                  for(int var15 = 0; var15 < var4; ++var15) {
                     var10[var14 + var15] = var10[var14 - var3 + var15];
                  }

                  var14 += var4;
                  break;
               case 3:
                  var4 = var1[var13] & 255;
                  ++var13;

                  while(var4 > 0) {
                     var10[var14] = var1[var13];
                     ++var14;
                     ++var13;
                     --var4;
                  }
               }

               ++var8;
               if (var8 >= 4) {
                  var7 = var1[var13];
                  var8 = 0;
                  ++var13;
                  if (var7 == 0) {
                     int var10000 = var13 - var6;
                     break;
                  }
               } else {
                  var7 >>>= 2;
               }
            }
         }

         return var10;
      } else {
         return null;
      }
   }

   public Image loadCNX(String var1) {
      return this.convGmpToImage(this.unpressCNX(FileLib.readFile(this.m_urlBase, var1)), -1, 255);
   }

   public Image loadCNX(String var1, int var2, int var3) {
      return this.convGmpToImage(this.unpressCNX(FileLib.readFile(this.m_urlBase, var1)), var2, var3);
   }

   public static Image[] disintegrateCombi(Image var0, int var1, int var2) {
      int var3 = var0.getWidth((ImageObserver)null);
      int var4 = var0.getHeight((ImageObserver)null);
      Image[] var5 = new Image[var3 / var1 * (var4 / var2)];

      for(int var6 = 0; var6 * var2 < var4; ++var6) {
         for(int var7 = 0; var7 * var1 < var3; ++var7) {
            var5[var6 * (var3 / var1) + var7] = disintegrate(var0, var7 * var1, var6 * var2, var1, var2);
         }
      }

      return var5;
   }
}
