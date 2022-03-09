/* Decompiler 25ms, total 82ms, lines 81 */
public class match2 extends AppletBase implements Runnable {
   Thread thread;

   public void stop() {
      if (this.thread != null) {
         this.thread.stop();
         this.thread = null;
      }

   }

   public void destroy() {
      super.db.gameEnd();
      System.gc();
   }

   public void start() {
      if (this.thread == null) {
         this.thread = new Thread(this);
         this.thread.start();
      }

   }

   public void run() {
      while(true) {
         try {
            Thread.sleep(50L);
         } catch (InterruptedException var1) {
            this.stop();
         }

         super.db.repaint();
      }
   }

   public void init() {
      int[] var1 = this.getParameterIntegerArray("bg_size");
      int var2 = var1[0];
      int var3 = var1[1];
      int[][] var4 = new int[][]{{341, 18, 31, 43}, {20, 11, 35, 30}, {60, 382, 93, 49}, {202, 330, 52, 99}, {166, 159, 42, 34}, {262, 41, 49, 61}, {263, 102, 22, 20}, {61, 139, 39, 38}, {67, 42, 9, 10}, {16, 349, 25, 16}, {262, 169, 38, 15}, {67, 70, 11, 12}, {64, 87, 28, 15}, {75, 329, 30, 28}, {301, 307, 310, 327}, {147, 10, 9, 13}, {221, 126, 36, 11}, {225, 27, 18, 36}, {30, 188, 46, 23}, {103, 126, 23, 12}, {229, 208, 15, 10}, {51, 262, 11, 10}, {248, 299, 29, 9}, {334, 160, 346, 166}, {129, 253, 11, 6}, {301, 135, 9, 7}, {167, 104, 7, 6}, {228, 95, 8, 8}, {180, 127, 8, 17}, {0, 290, 11, 13}};
      super.init();
      int var5 = this.getParameterInteger("stage_max");
      int[][] var6 = new int[var5][2];

      int var7;
      for(var7 = 0; var7 < var6.length; ++var7) {
         var6[var7] = this.getParameterIntegerArray("stage" + var7);
      }

      var7 = this.getParameterInteger("pwd_stage");
      int var8 = this.getParameterInteger("num_fault");
      super.db.setImageSize(9);
      super.db.grp_loadmono(0, this.getParameterString("bg"), -1, var2, var3);
      super.db.grp_loadfree(1, this.getParameterString("fault"), -1, var4);
      super.db.grp_loadmono(2, "mark.cnx", 1, 32, 32);
      if (var7 > 0 && var7 <= var5) {
         super.db.grp_loadmono(3, this.getParameterString("pass"), -1, var2, var3);
      }

      super.db.grp_loadmono(4, this.getParameterString("title"), -1, var2, var3);
      super.db.grp_loadmono(5, "code.cnx", -1, 56, 64);
      super.db.grp_loadmono(6, this.getParameterString("end"), -1, var2, var3);
      if (var7 != var5) {
         super.db.grp_loadmono(7, "ending.cnx", -1, var2, var3);
      }

      for(int var9 = 1; var9 <= var5; ++var9) {
         if (var9 != var5 && var9 != var7) {
            super.db.grp_loadmono(8, "clear.cnx", -1, var2, var3);
            break;
         }
      }

      ActGame var10 = new ActGame(super.db, var4, var6, var7, var8);
      var10.initAct(72, 0, var2, var3);
      var10.setSpeed(this.getParameterInteger("timespeed"));
      this.requestFocus();
   }
}
