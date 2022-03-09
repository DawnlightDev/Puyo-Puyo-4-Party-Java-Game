/* Decompiler 13ms, total 76ms, lines 103 */
import java.util.Date;

public class Calc {
   private static boolean setFunction;
   private static int[] Sin;
   private static int[] Atan;
   private int second;
   private long nextTime;

   int getTimer() {
      return this.second;
   }

   int getSin(int var1) {
      var1 &= 65535;
      var1 >>>= 4;
      switch(var1 & 3072) {
      case 0:
         return Sin[var1];
      case 1024:
         return Sin[2048 - var1];
      case 2048:
         return -Sin[var1 - 2048];
      case 3072:
         return -Sin[4096 - var1];
      default:
         return 0;
      }
   }

   int[] getRandomArray_2(int var1, int var2) {
      int[] var3 = new int[var1];

      for(int var4 = 0; var4 < var1; ++var4) {
         var3[var4] = this.getRandom(var2);
      }

      return var3;
   }

   boolean checkTimer() {
      if (this.nextTime < (new Date()).getTime()) {
         ++this.second;
         this.nextTime += 1000L;
         return true;
      } else {
         return false;
      }
   }

   void initTimer() {
      this.second = 0;
      this.nextTime = (new Date()).getTime() + 1000L;
   }

   int getRandom(int var1) {
      return (int)(Math.random() * (double)var1);
   }

   int[] getRandomArray_1(int var1, int var2) {
      int[] var3 = new int[var1];

      for(int var4 = 0; var4 < var1; ++var4) {
         int var5 = this.getRandom(var2);
         boolean var6 = true;

         for(int var7 = 0; var7 < var4; ++var7) {
            if (var3[var7] == var5) {
               var6 = false;
            }
         }

         if (var6) {
            var3[var4] = var5;
         } else {
            --var4;
         }
      }

      return var3;
   }

   public Calc() {
      if (!setFunction) {
         Sin = new int[1025];
         Atan = new int[1025];
         int var1 = 0;

         do {
            Sin[var1] = (int)(Math.sin(3.141592653589793D * (double)var1 / 2048.0D) * 65536.0D);
            Atan[var1] = (int)(Math.atan(3.141592653589793D * (double)var1 / 2048.0D) * 65536.0D);
            ++var1;
         } while(var1 <= 1024);

         setFunction = true;
      }
   }

   int getCos(int var1) {
      return this.getSin(var1 + 16384);
   }
}
