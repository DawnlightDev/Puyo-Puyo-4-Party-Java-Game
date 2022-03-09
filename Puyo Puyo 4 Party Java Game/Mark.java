/* Decompiler 7ms, total 67ms, lines 30 */
class Mark extends Act {
   private static final int DISPTIME = 20;

   public Mark(DoubleBuffer var1) {
      super(var1);
   }

   void initMark(boolean var1, int var2, int var3) {
      super.ID = 16384;
      super.img_seat = 2;
      super.img_index = var1 ? 0 : 1;
      super.priority = 2;
      super.Pos[0] = var2 * 65536;
      super.Pos[1] = var3 * 65536;
      super.Pos[2] = 3;
      super.size_x = 32;
      super.size_y = 32;
      super.chr_atr = 4096;
      super.cnt_loop = 20;
      super.db.addRedrawArea(this.getActSize());
   }

   public void actionAct() {
      if ((super.cnt_loop += -1) <= 0) {
         this.clear();
      }

   }
}
