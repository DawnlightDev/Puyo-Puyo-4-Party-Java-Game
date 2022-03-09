/* Decompiler 14ms, total 96ms, lines 45 */
import java.awt.Color;
import java.awt.Graphics;

class PointBar extends Act {
   private int DY;
   private int MarkY;

   public PointBar(DoubleBuffer var1) {
      super(var1);
   }

   void initPoint(int var1, int var2) {
      super.ID = 16384;
      super.priority = 2;
      super.Pos[0] = 2621440;
      super.Pos[1] = 1048576;
      super.Pos[2] = 1;
      super.size_x = 24;
      super.size_y = var1 - 16;
      this.DY = (var1 - 96) / var2;
      this.MarkY = 24 + this.DY * var2;
      super.db.addRedrawArea(this.getActSize());
      super.size_y = this.DY * var2;
   }

   boolean getPoint() {
      super.db.addRedrawArea(this.getActSize());
      int[] var10000 = super.Pos;
      var10000[1] += this.DY * 65536;
      super.size_y -= this.DY;
      return super.size_y <= 0;
   }

   void drawImage() {
      Graphics var1 = super.db.getBGGraphics();
      var1.setColor(Color.pink);

      for(int var2 = 0; var2 < super.size_y; var2 += this.DY) {
         var1.fillRect(super.Pos[0] >> 16, (super.Pos[1] >> 16) + var2, super.size_x, this.DY - 1);
      }

      super.db.drawImage(5, 0, 8, this.MarkY);
   }
}
