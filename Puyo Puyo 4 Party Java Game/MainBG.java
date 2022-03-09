/* Decompiler 3ms, total 93ms, lines 35 */
class MainBG extends ActSprite {
   public MainBG(DoubleBuffer var1) {
      super(var1);
   }

   public void actionAct() {
      this.chr_move(8192);
      if (super.Pos[1] >= super.center[1]) {
         super.Pos[1] = super.center[1];
         super.vector[1] = 0;
         if (super.img_seat == 0) {
            super.ID = 0;
         }

         super.db.enableMouse(true);
      }

   }

   void initAct(int var1, int var2, int var3, int var4, int var5) {
      super.ID = 16384;
      super.img_seat = var1;
      super.img_index = 0;
      super.priority = 1;
      super.Pos[0] = var2 * 65536;
      super.Pos[1] = -var5 * 65536;
      super.Pos[2] = super.img_seat == 0 ? 1 : 10;
      super.size_x = var4;
      super.size_y = var5;
      super.center[1] = var3 * 65536;
      super.accele[1] = 65536;
      super.db.addRedrawArea(this.getActSize());
   }
}
