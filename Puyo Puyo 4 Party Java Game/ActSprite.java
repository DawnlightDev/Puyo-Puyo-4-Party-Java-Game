/* Decompiler 19ms, total 80ms, lines 178 */
import java.awt.Point;

public class ActSprite extends Act {
   Calc calc = new Calc();

   void rotate_abs(int var1) {
      if ((var1 & 16384) != 0) {
         super.Pos[0] = super.center[0] + this.calc.getSin(super.angle[0]) * super.radian[0];
      }

      if ((var1 & 8192) != 0) {
         super.Pos[1] = super.center[1] + this.calc.getCos(super.angle[1]) * super.radian[1];
      }

      if ((var1 & 4096) != 0) {
         super.Pos[2] = super.center[2] + this.calc.getCos(super.angle[2]) * super.radian[2];
      }

   }

   public String toString() {
      return "ActSprite : " + super.Pos[0] / 65536 + "," + super.Pos[1] / 65536;
   }

   void chr_move(int var1) {
      int[] var10000;
      if ((var1 & 16384) != 0) {
         if (super.center[0] > super.Pos[0]) {
            var10000 = super.vector;
            var10000[0] += super.accele[0];
         }

         if (super.center[0] < super.Pos[0]) {
            var10000 = super.vector;
            var10000[0] -= super.accele[0];
         }

         var10000 = super.Pos;
         var10000[0] += super.vector[0];
      }

      if ((var1 & 8192) != 0) {
         if (super.center[1] > super.Pos[1]) {
            var10000 = super.vector;
            var10000[1] += super.accele[1];
         }

         if (super.center[1] < super.Pos[1]) {
            var10000 = super.vector;
            var10000[1] -= super.accele[1];
         }

         var10000 = super.Pos;
         var10000[1] += super.vector[1];
      }

      if ((var1 & 4096) != 0) {
         if (super.center[2] > super.Pos[2]) {
            var10000 = super.vector;
            var10000[2] += super.accele[2];
         }

         if (super.center[2] < super.Pos[2]) {
            var10000 = super.vector;
            var10000[2] -= super.accele[2];
         }

         var10000 = super.Pos;
         var10000[2] += super.vector[2];
      }

   }

   protected void actionAct() {
      super.actionAct();
      this.chr_move(24576);
   }

   public ActSprite(DoubleBuffer var1) {
      super(var1);
   }

   void sin_rel(int var1) {
      int[] var10000;
      if ((var1 & 16384) != 0) {
         var10000 = super.Pos;
         var10000[0] += this.calc.getSin(super.angle[0]) * super.radian[0];
      }

      if ((var1 & 8192) != 0) {
         var10000 = super.Pos;
         var10000[1] += this.calc.getSin(super.angle[1]) * super.radian[1];
      }

      if ((var1 & 4096) != 0) {
         var10000 = super.Pos;
         var10000[2] += this.calc.getSin(super.angle[2]) * super.radian[2];
      }

   }

   void cos_rel(int var1) {
      int[] var10000;
      if ((var1 & 16384) != 0) {
         var10000 = super.Pos;
         var10000[0] += this.calc.getCos(super.angle[0]) * super.radian[0];
      }

      if ((var1 & 8192) != 0) {
         var10000 = super.Pos;
         var10000[1] += this.calc.getCos(super.angle[1]) * super.radian[1];
      }

      if ((var1 & 4096) != 0) {
         var10000 = super.Pos;
         var10000[2] += this.calc.getCos(super.angle[2]) * super.radian[2];
      }

   }

   protected boolean clsn_spr(Act var1) {
      return super.collision.intersects(var1.collision);
   }

   void rotate_rel(int var1) {
      int[] var10000;
      if ((var1 & 16384) != 0) {
         var10000 = super.Pos;
         var10000[0] += this.calc.getSin(super.angle[0]) * super.radian[0];
      }

      if ((var1 & 8192) != 0) {
         var10000 = super.Pos;
         var10000[1] += this.calc.getCos(super.angle[1]) * super.radian[1];
      }

      if ((var1 & 4096) != 0) {
         var10000 = super.Pos;
         var10000[2] += this.calc.getCos(super.angle[2]) * super.radian[2];
      }

   }

   void sin_abs(int var1) {
      if ((var1 & 16384) != 0) {
         super.Pos[0] = super.center[0] + this.calc.getSin(super.angle[0]) * super.radian[0];
      }

      if ((var1 & 8192) != 0) {
         super.Pos[1] = super.center[1] + this.calc.getSin(super.angle[1]) * super.radian[1];
      }

      if ((var1 & 4096) != 0) {
         super.Pos[2] = super.center[2] + this.calc.getSin(super.angle[2]) * super.radian[2];
      }

   }

   void cos_abs(int var1) {
      if ((var1 & 16384) != 0) {
         super.Pos[0] = super.center[0] + this.calc.getCos(super.angle[0]) * super.radian[0];
      }

      if ((var1 & 8192) != 0) {
         super.Pos[1] = super.center[1] + this.calc.getCos(super.angle[1]) * super.radian[1];
      }

      if ((var1 & 4096) != 0) {
         super.Pos[2] = super.center[2] + this.calc.getCos(super.angle[2]) * super.radian[2];
      }

   }

   protected boolean clsn_pos(Point var1) {
      return super.collision.inside(var1.x, var1.y);
   }
}
