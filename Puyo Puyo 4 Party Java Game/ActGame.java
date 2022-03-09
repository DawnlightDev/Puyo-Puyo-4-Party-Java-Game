/* Decompiler 19ms, total 391ms, lines 157 */
class ActGame extends Act implements Callback {
   private static final int TITLE = 0;
   private static final int INIT = 10;
   private static final int GAME = 1;
   private static final int GAMEEND = 2;
   private Calc calc = new Calc();
   private MainBG mbg;
   private TimeBar tb;
   private PointBar pb;
   private Parts[] p;
   private int[][] imgTbl;
   private int pwd_stage;
   private int now_stage = 1;
   private int[][] num_fault_tbl;
   private int NUM_FAULT = 10;

   void initNextBG(int var1, int var2, int var3, int var4) {
      int[] var5 = this.calc.getRandomArray_1(this.NUM_FAULT, this.num_fault_tbl[this.now_stage - 1][1] - this.num_fault_tbl[this.now_stage - 1][0] + 1);

      for(int var6 = 0; var6 < var5.length; ++var6) {
         var5[var6] += this.num_fault_tbl[this.now_stage - 1][0];
         this.p[var5[var6]].initAct(this.imgTbl[var5[var6]][0] + var1, this.imgTbl[var5[var6]][1] + var2, this.imgTbl[var5[var6]][2], this.imgTbl[var5[var6]][3], var4);
      }

      this.tb.initTime(super.size_y, this.NUM_FAULT);
      this.pb.initPoint(super.size_y, this.NUM_FAULT);
      this.mbg.initAct(0, 72, 0, super.size_x, super.size_y);
   }

   public ActGame(DoubleBuffer var1, int[][] var2, int[][] var3, int var4, int var5) {
      super(var1);
      this.tb = new TimeBar(var1);
      this.pb = new PointBar(var1);
      this.mbg = new MainBG(var1);
      this.p = new Parts[var2.length];

      for(int var6 = 0; var6 < var2.length; ++var6) {
         this.p[var6] = new Parts(var1, var6);
      }

      this.NUM_FAULT = var5;
      this.num_fault_tbl = var3;
      this.pwd_stage = var4;
      this.imgTbl = var2;
   }

   public void actionAct() {
      switch(super.prog_mode) {
      case 1:
         int var1;
         if (this.tb.checkTime()) {
            for(var1 = 0; var1 < this.imgTbl.length; ++var1) {
               if (this.p[var1].checkParts() && this.pb.getPoint()) {
                  if (this.now_stage == this.pwd_stage) {
                     this.mbg.initAct(3, 72, 0, super.size_x, super.size_y);
                  } else if (this.now_stage == this.num_fault_tbl.length) {
                     this.mbg.initAct(7, 72, 0, super.size_x, super.size_y);
                  } else {
                     this.mbg.initAct(8, 72, 0, super.size_x, super.size_y);
                  }

                  this.tb.setTimeBar(false);
                  super.prog_mode = 2;
                  super.db.enableMouse(false);
                  return;
               }
            }

            return;
         }

         this.mbg.initAct(6, 72, 0, super.size_x, super.size_y);
         this.tb.setTimeBar(false);
         super.prog_mode = 2;

         for(var1 = 0; var1 < this.imgTbl.length; ++var1) {
            this.p[var1].clear();
         }

         super.db.enableMouse(false);
         return;
      case 10:
         if (super.db.getEnableMouse()) {
            super.prog_mode = 1;
            super.img_seat = 0;
            super.db.addRedrawArea(this.getActSize());
            return;
         }
      case 0:
      default:
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

   private boolean MouseDown() {
      switch(super.prog_mode) {
      case 0:
         this.now_stage = 1;
         this.initNextBG(super.Pos[0] >> 16, super.Pos[1] >> 16, super.size_x, super.size_y);
         super.db.enableMouse(false);
         super.prog_mode = 10;
         break;
      case 1:
         Mark var1 = new Mark(super.db);
         var1.initMark(false, super.db.getLastEvent().x, super.db.getLastEvent().y);
         break;
      case 2:
         this.mbg.ID = 0;

         for(int var2 = 0; var2 < this.imgTbl.length; ++var2) {
            this.p[var2].ID = 0;
         }

         if (this.mbg.img_seat != 6 && this.now_stage != this.num_fault_tbl.length) {
            super.img_seat = this.mbg.img_seat;
            ++this.now_stage;
            this.initNextBG(super.Pos[0] >> 16, super.Pos[1] >> 16, super.size_x, super.size_y);
            super.db.enableMouse(false);
            super.prog_mode = 10;
         } else {
            super.prog_mode = 0;
            super.db.addRedrawArea(this.getActSize());
            super.img_seat = 4;
         }
      }

      return true;
   }

   void setSpeed(int var1) {
      this.tb.setSpeed(var1);
   }

   void initAct(int var1, int var2, int var3, int var4) {
      super.ID = 16384;
      super.img_seat = 4;
      super.img_index = 0;
      super.priority = 2;
      super.Pos[0] = var1 * 65536;
      super.Pos[1] = var2 * 65536;
      super.Pos[2] = 0;
      super.size_x = var3;
      super.size_y = var4;
      super.prog_mode = 0;
      super.db.addRedrawArea(this.getActSize());
      super.db.addEventClient(this, 1, this.getActSize(), 1);
   }
}
