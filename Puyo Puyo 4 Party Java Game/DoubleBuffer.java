/* Decompiler 86ms, total 155ms, lines 413 */
import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class DoubleBuffer extends Canvas {
   private static final boolean Debug = false;
   private ImageRead ir;
   private EventManagement em;
   protected ActTable head;
   protected ActTable tail;
   protected Applet applet;
   protected Rectangle DisplayArea;
   protected Rectangle RedrawArea;
   protected Image BG_image;
   protected Image old_BG_image;
   protected Graphics BG_graphics;
   protected Graphics old_BG_graphics;
   protected Image[][] Img;
   private boolean m_bMouse;
   private boolean m_bKey;
   protected boolean double_buffer;
   private int num_Act;

   void clearAct(Act var1) {
      ActTable var2 = this.searchAct(var1);
      if (var2 != null) {
         if (var2.prev == null) {
            this.head = var2.next;
         } else {
            var2.prev.next = var2.next;
         }

         if (var2.next == null) {
            this.tail = var2.prev;
         } else {
            var2.next.prev = var2.prev;
         }

         this.addRedrawArea(var2.act.getActSize());
         this.num_Act += -1;
      }
   }

   ActTable partition_priority(ActTable var1, ActTable var2) {
      ActTable var3 = var2;

      while(true) {
         while(var1.act.priority < var3.act.priority) {
            var1 = var1.next;
         }

         do {
            var2 = var2.prev;
         } while(this.checkActPosition(var1, var2) && var3.act.priority < var2.act.priority);

         if (!this.checkActPosition(var1, var2)) {
            this.swapAct(var3, var1);
            return var1;
         }

         this.swapAct(var1, var2);
      }
   }

   void removeEventClient(Callback var1, int var2, Rectangle var3, int var4) {
      this.em.removeEventClient(var1, var2, var3, var4);
   }

   void removeEventClient(Callback var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.em.removeEventClient(var1, var2, new Rectangle(var3, var4, var5, var6), var7);
   }

   void RedrawAll() {
      this.addRedrawArea(this.DisplayArea);
   }

   private void moveAct(Act var1) {
      Rectangle var2 = var1.getActSize();
      int var3 = var1.ID;
      int var4 = var1.img_seat;
      int var5 = var1.img_index;
      var1.actionAct();
      if (!var2.equals(var1.getActSize()) || var4 != var1.img_seat || var5 != var1.img_index || var3 != var1.ID) {
         this.addRedrawArea(var2.union(var1.getActSize()));
      }
   }

   public synchronized void update(Graphics var1) {
      this.initGraphics();
      this.i_sort_priority();

      for(ActTable var2 = this.head; var2 != null; var2 = var2.next) {
         this.moveAct(var2.act);
      }

      this.redrawBGArea(this.RedrawArea);
      if (!this.RedrawArea.isEmpty()) {
         var1.clipRect(this.RedrawArea.x, this.RedrawArea.y, this.RedrawArea.width, this.RedrawArea.height);
         if (this.BG_image != null) {
            var1.drawImage(this.BG_image, 0, 0, (ImageObserver)null);
         }

         this.RedrawArea.reshape(0, 0, 0, 0);
      }
   }

   void drawImage(int var1, int var2, int var3, int var4) {
      this.BG_graphics.drawImage(this.Img[var1][var2], var3, var4, (ImageObserver)null);
   }

   void drawImage(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.BG_graphics.drawImage(this.Img[var1][var2], var3, var4, var5, var6, (ImageObserver)null);
   }

   void enableKey(boolean var1) {
      this.m_bKey = var1;
   }

   boolean getEnableKey() {
      return this.m_bKey;
   }

   private void q_sort_priority() {
      new ActTable();
      new ActTable();
      new ActTable();
      ActTable[] var4 = new ActTable[20];
      ActTable[] var5 = new ActTable[20];
      int var6 = 0;

      do {
         var4[var6] = new ActTable();
         var5[var6] = new ActTable();
         ++var6;
      } while(var6 < 20);

      var4[0] = this.head;
      var5[0] = this.tail;
      var6 = 1;

      while(var6 > 0) {
         --var6;
         ActTable var1 = var4[var6];
         ActTable var3 = var5[var6];
         if (!var1.equals(var3)) {
            ActTable var2 = this.partition_priority(var1, var3);
            System.out.println("left " + var1.toString());
            System.out.println("pivot " + var2.toString());
            System.out.println("right " + var3.toString());
            System.out.println("");
            if (var2.next != null) {
               var4[var6] = var2.next;
               var5[var6++] = var3;
            }

            if (var2.prev != null) {
               var4[var6] = var1;
               var5[var6++] = var2.prev;
            }
         }
      }

      System.out.println("End");
   }

   private void i_sort_posz() {
      for(ActTable var2 = this.head.next; var2 != null; var2 = var2.next) {
         for(ActTable var1 = var2; var1 != this.head && var1.prev.act.Pos[2] > var1.act.Pos[2]; var1 = var1.prev) {
            this.swapAct(var1.prev, var1);
         }
      }

   }

   void addRedrawArea(Rectangle var1) {
      if (this.RedrawArea.isEmpty()) {
         this.RedrawArea.reshape(var1.x, var1.y, var1.width, var1.height);
      } else {
         this.RedrawArea.add(var1);
      }
   }

   void setAct(Act var1) {
      ActTable var2 = new ActTable();
      var2.act = var1;
      if (this.tail != null) {
         this.tail.next = var2;
      }

      var2.next = null;
      var2.prev = this.tail;
      this.tail = var2;
      if (this.head == null) {
         this.head = var2;
      }

      ++this.num_Act;
   }

   private void swapAct(ActTable var1, ActTable var2) {
      Act var3 = var1.act;
      var1.act = var2.act;
      var2.act = var3;
      if (var2.prev != null) {
         var2.prev.next = var2;
      }

      if (var2.next != null) {
         var2.next.prev = var2;
      }

      if (var1.prev != null) {
         var1.prev.next = var1;
      }

      if (var1.next != null) {
         var1.next.prev = var1;
      }

   }

   public synchronized void paint(Graphics var1) {
      this.addRedrawArea(this.DisplayArea);
      this.update(var1);
   }

   void addEventClient(Callback var1, int var2, Rectangle var3, int var4) {
      this.em.addEventClient(var1, var2, var3, var4);
   }

   void addEventClient(Callback var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.em.addEventClient(var1, var2, new Rectangle(var3, var4, var5, var6), var7);
   }

   private void initGraphics() {
      if (this.double_buffer) {
         if (this.BG_graphics != null) {
            return;
         }

         this.BG_image = this.applet.createImage(this.DisplayArea.width, this.DisplayArea.height);
         if (this.BG_image == null) {
            return;
         }

         this.BG_graphics = this.BG_image.getGraphics();
      } else {
         this.BG_graphics = this.getGraphics();
      }

      this.old_BG_image = this.BG_image;
      this.old_BG_graphics = this.BG_graphics;
      this.addRedrawArea(this.DisplayArea);
   }

   public DoubleBuffer(Applet var1, int var2, int var3) {
      this.ir = new ImageRead(var1);
      this.em = new EventManagement();
      this.applet = var1;
      this.DisplayArea = new Rectangle(0, 0, var2, var3);
      this.RedrawArea = new Rectangle(0, 0, 0, 0);
      this.setBackground(Color.black);
      this.resize(var2, var3);
      this.m_bMouse = true;
      this.m_bKey = true;
      this.double_buffer = true;
      this.num_Act = 0;
      this.requestFocus();
   }

   void setImageSize(int var1) {
      this.Img = new Image[var1][];
      this.applet.showStatus("Now Loading Image");
   }

   private void i_sort_priority() {
      for(ActTable var2 = this.head.next; var2 != null; var2 = var2.next) {
         for(ActTable var1 = var2; var1 != this.head && var1.prev.act.priority > var1.act.priority; var1 = var1.prev) {
            this.swapAct(var1.prev, var1);
         }
      }

   }

   void grp_loadmono(int var1, String var2, int var3, int var4, int var5) {
      Image var6 = this.ir.loadImage(var2, var3);
      int var7 = var6.getWidth((ImageObserver)null);
      int var8 = var6.getHeight((ImageObserver)null);
      Rectangle var9 = new Rectangle(0, 0, var4, var5);
      this.Img[var1] = new Image[var7 / var4 * (var8 / var5)];
      int var10 = 0;

      for(var9.y = 0; var9.y + var5 <= var8; var9.y += var5) {
         for(var9.x = 0; var9.x + var4 <= var7; var9.x += var4) {
            this.Img[var1][var10] = this.ir.disintegrate(var6, var9.x, var9.y, var9.width, var9.height);
            ++var10;
         }
      }

      System.gc();
   }

   void enableMouse(boolean var1) {
      this.m_bMouse = var1;
   }

   boolean getEnableMouse() {
      return this.m_bMouse;
   }

   void gameEnd() {
      for(ActTable var1 = this.head; var1 != null; var1 = var1.next) {
         if (var1.prev == null) {
            this.head = var1.next;
         } else {
            var1.prev.next = var1.next;
         }

         if (var1.next == null) {
            this.tail = var1.prev;
         } else {
            var1.next.prev = var1.prev;
         }

         this.num_Act += -1;
      }

   }

   Graphics getBGGraphics() {
      return this.BG_graphics;
   }

   private void setBuffering(boolean var1) {
      if (this.double_buffer != var1) {
         this.double_buffer = var1;
      }
   }

   private ActTable searchAct(Act var1) {
      ActTable var2;
      for(var2 = this.head; var2 != null && var1 != var2.act; var2 = var2.next) {
      }

      return var2;
   }

   private void redrawBGArea(Rectangle var1) {
      if (!var1.isEmpty()) {
         this.BG_graphics.setColor(this.getBackground());
         this.BG_graphics.fillRect(var1.x, var1.y, var1.width, var1.height);
         this.i_sort_posz();

         for(ActTable var2 = this.head; var2 != null; var2 = var2.next) {
            Act var3 = var2.act;
            Rectangle var4 = var3.getActSize();
            if (var3 != null && (var3.ID & 16384) != 0 && var1.intersects(var4) && (var3.ID & 4096) == 0 && var3.img_seat >= 0 && var3.img_index >= 0) {
               if ((var3.chr_atr & 8192) != 0) {
                  var3.drawImageStretch();
               } else {
                  var3.drawImage();
               }
            }
         }

      }
   }

   void grp_loadfree(int var1, String var2, int var3, int[][] var4) {
      Image var5 = this.ir.loadImage(var2, var3);
      int var6 = var4.length;
      this.Img[var1] = new Image[var6];

      for(int var7 = 0; var7 < var6; ++var7) {
         this.Img[var1][var7] = this.ir.disintegrate(var5, var4[var7][0], var4[var7][1], var4[var7][2], var4[var7][3]);
      }

      System.gc();
   }

   public boolean handleEvent(Event var1) {
      if (this.m_bMouse) {
         this.em.getEvent(var1);
      }

      return true;
   }

   public Event getLastEvent() {
      return this.em.getLastEvent();
   }

   String loadTxt(String var1) {
      FileRead var2 = new FileRead(this.applet);
      return new String(var2.dataRead(var1), 0);
   }

   boolean checkActPosition(ActTable var1, ActTable var2) {
      for(ActTable var3 = var1; var3 != null; var3 = var3.next) {
         if (var3.next == var2) {
            return true;
         }
      }

      return false;
   }
}
