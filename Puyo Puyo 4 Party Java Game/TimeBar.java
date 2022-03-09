/* Decompiler 11ms, total 70ms, lines 55 */
import java.awt.Color;
import java.awt.Graphics;

class TimeBar extends Act {
   private int SPEED;
   private boolean timer;

   public TimeBar(DoubleBuffer var1) {
      super(var1);
   }

   void setTimeBar(boolean var1) {
      this.timer = var1;
   }

   boolean checkTime() {
      if (!this.timer) {
         return true;
      } else {
         if (++super.cnt_loop >= this.SPEED) {
            super.db.addRedrawArea(this.getActSize());
            int[] var10000 = super.Pos;
            var10000[1] += 65536;
            --super.size_y;
            super.cnt_loop = 0;
         }

         return super.size_y != 0;
      }
   }

   void drawImage() {
      Graphics var1 = super.db.getBGGraphics();
      var1.setColor(Color.green);
      var1.fillRect(super.Pos[0] >> 16, super.Pos[1] >> 16, super.size_x, super.size_y);
   }

   void setSpeed(int var1) {
      this.SPEED = var1;
   }

   void initTime(int var1, int var2) {
      super.ID = 16384;
      super.priority = 2;
      super.Pos[0] = 524288;
      super.Pos[1] = 1048576;
      super.Pos[2] = 1;
      super.size_x = 24;
      super.size_y = (var1 - 96) / var2 * var2;
      super.cnt_loop = 0;
      this.timer = true;
      super.db.addRedrawArea(this.getActSize());
   }
}
