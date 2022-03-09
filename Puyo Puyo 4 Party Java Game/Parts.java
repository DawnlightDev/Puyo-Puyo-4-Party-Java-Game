/* Decompiler 7ms, total 101ms, lines 84 */
class Parts extends ActSprite implements Callback {
   private static final int INIT = 0;
   private static final int DISP = 1;
   private static final int FLASH = 2;
   private static final int ERASE = 3;
   private static final int END = 4;
   private static final int FLASHTIME = 16;

   public void actionAct() {
      switch(super.prog_mode) {
      case 0:
         this.chr_move(8192);
         if (super.Pos[1] >= super.center[1]) {
            super.Pos[1] = super.center[1];
            super.vector[1] = 0;
            super.prog_mode = 1;
            super.db.addEventClient(this, 1, this.getActSize(), 1);
            return;
         }
      case 1:
      default:
         return;
      case 2:
         if ((super.cnt_loop += -1) <= 0) {
            super.prog_mode = 3;
            super.ID = 0;
            Mark var1 = new Mark(super.db);
            var1.initMark(true, (super.Pos[0] >> 16) + super.size_x / 2, (super.Pos[1] >> 16) + super.size_y / 2);
         } else {
            super.ID ^= 16384;
         }
      }
   }

   public boolean EventCallback(int var1) {
      switch(var1) {
      case 1:
         return this.MouseDown();
      default:
         return false;
      }
   }

   boolean checkParts() {
      if (super.prog_mode == 3) {
         super.prog_mode = 4;
         return true;
      } else {
         return false;
      }
   }

   private boolean MouseDown() {
      super.db.removeEventClient(this, 1, this.getActSize(), 1);
      super.prog_mode = 2;
      super.cnt_loop = 16;
      return true;
   }

   protected void clear() {
      super.db.removeEventClient(this, 1, this.getActSize(), 1);
   }

   public Parts(DoubleBuffer var1, int var2) {
      super(var1);
      super.img_seat = 1;
      super.img_index = var2;
      super.priority = 1;
   }

   void initAct(int var1, int var2, int var3, int var4, int var5) {
      super.ID = 16384;
      super.Pos[0] = var1 * 65536;
      super.Pos[1] = (var2 - var5) * 65536;
      super.Pos[2] = 2;
      super.size_x = var3;
      super.size_y = var4;
      super.center[1] = var2 * 65536;
      super.accele[1] = 65536;
      super.prog_mode = 0;
      super.db.addRedrawArea(this.getActSize());
   }
}
