/* Decompiler 46ms, total 108ms, lines 220 */
import java.awt.Event;
import java.awt.Point;

public class KeyInput {
   public static final byte _NOT = 0;
   public static final byte _X = 1;
   public static final byte _Y = 2;
   public static final byte _XY = 3;
   public static final int MOUSE_LBTN = 0;
   public static final int MOUSE_RBTN = 1;
   public static final int MOUSE_CBTN = 2;
   public static final int MOUSE_EVT_MAX = 3;
   public static final int MSK_CURSOR = 15;
   public static final int KEY_UP = 0;
   public static final int KEY_DOWN = 1;
   public static final int KEY_LEFT = 2;
   public static final int KEY_RIGHT = 3;
   public static final int KEY_TRG1 = 4;
   public static final int KEY_TRG2 = 5;
   public static final int KEY_EVT_MAX = 32;
   public static final int SPC_MOUSE_MOVE = 0;
   public static final int SPC_SYS_RESERVED1 = 1;
   public static final int SPC_SYS_RESERVED2 = 2;
   public static final int SPC_SYS_RESERVED3 = 3;
   public static final int SPC_SYS_RESERVED4 = 4;
   public static final int SPC_SYS_RESERVED5 = 5;
   public static final int SPC_SYS_RESERVED6 = 6;
   public static final int SPC_SYS_RESERVED7 = 7;
   public int m_nOldKey;
   public int m_nOldTrg = -1;
   public int m_nFlgKey;
   public int m_nFlgKeyR;
   public int m_nFlgMouse;
   public int m_nFlgMouseR;
   public int m_nFlgSpcEvt;
   public Point m_ptMouse = new Point(0, 0);
   public Point[] m_ptMouseEvtPos = new Point[3];
   public Point[] m_ptMouseEvtPosR = new Point[3];

   public void keyDown(int var1) {
      boolean var2 = true;
      if (this.m_nOldKey != var1) {
         this.m_nOldKey = var1;
         int var3 = this.getKeyID(var1);
         this.m_nFlgKey |= 1 << var3;
         this.m_nFlgKeyR |= 1 << var3;
      }
   }

   private int getSpcEvtID(Event var1, Object var2) {
      return -1;
   }

   public boolean getKeyRep(int var1) {
      return (this.m_nFlgKeyR & 1 << var1) != 0;
   }

   public KeyInput() {
      int var1 = 0;

      do {
         this.m_ptMouseEvtPos[var1] = new Point(-1, -1);
         this.m_ptMouseEvtPosR[var1] = new Point(-1, -1);
         ++var1;
      } while(var1 < 3);

   }

   public int getMousePosTrgRepY(int var1) {
      return this.m_ptMouseEvtPosR[var1].y;
   }

   public int getMousePosY() {
      return this.m_ptMouse.y;
   }

   public void mouseUp(int var1) {
      int var2 = this.getTrgID(var1);
      this.m_nOldTrg = -1;
      this.m_nFlgMouseR &= ~(1 << var2);
      this.m_ptMouseEvtPosR[var2].move(-1, -1);
   }

   public Point getMousePos() {
      return this.m_ptMouse;
   }

   public int getMousePosTrgX(int var1) {
      return this.m_ptMouseEvtPos[var1].x;
   }

   public void clearKey() {
      this.m_nFlgKey = 0;
      this.m_nFlgMouse = 0;
      int var1 = 0;

      do {
         this.m_ptMouseEvtPos[var1].move(-1, -1);
         ++var1;
      } while(var1 < 3);

   }

   public boolean getMouse(int var1) {
      return (this.m_nFlgMouse & 1 << var1) != 0;
   }

   public Point getMousePosTrgRep(int var1) {
      return this.m_ptMouseEvtPosR[var1];
   }

   public void keyUp(int var1) {
      this.m_nOldKey = 0;
      int var2;
      if ((var2 = this.getKeyID(var1)) >= 0) {
         this.m_nFlgKeyR &= ~(1 << var2);
      }

   }

   public int getMousePosTrgRepX(int var1) {
      return this.m_ptMouseEvtPosR[var1].x;
   }

   public int getMousePosX() {
      return this.m_ptMouse.x;
   }

   public Point getMousePosTrg(int var1) {
      return this.m_ptMouseEvtPos[var1];
   }

   public void mouseDown(int var1, int var2, int var3) {
      if (this.m_nOldTrg != var3) {
         this.m_nOldTrg = var3;
         int var4 = this.getTrgID(var3);
         this.m_nFlgMouse |= 1 << var4;
         this.m_nFlgMouseR |= 1 << var4;
         this.m_ptMouseEvtPos[var4].move(var1, var2);
         this.m_ptMouseEvtPosR[var4].move(var1, var2);
      }
   }

   public boolean getKey(int var1) {
      return (this.m_nFlgKey & 1 << var1) != 0;
   }

   public boolean action(Event var1, Object var2) {
      int var3;
      if ((var3 = this.getSpcEvtID(var1, var2)) >= 0) {
         this.m_nFlgSpcEvt |= 1 << var3;
         return true;
      } else {
         return false;
      }
   }

   private int getKeyID(int var1) {
      switch(var1) {
      case 32:
      case 90:
      case 122:
         return 4;
      case 50:
      case 1005:
         return 1;
      case 52:
      case 1006:
         return 2;
      case 54:
      case 1007:
         return 3;
      case 56:
      case 1004:
         return 0;
      case 88:
      case 120:
         return 5;
      default:
         return -1;
      }
   }

   public boolean getMouseRep(int var1) {
      return (this.m_nFlgMouseR & 1 << var1) != 0;
   }

   private int getTrgID(int var1) {
      switch(var1) {
      case 4:
         return 1;
      case 5:
      case 6:
      case 7:
      default:
         return 0;
      case 8:
         return 2;
      }
   }

   public boolean getSpecialEvent(int var1) {
      if ((this.m_nFlgSpcEvt & 1 << var1) != 0) {
         this.m_nFlgSpcEvt &= ~(1 << var1);
         return true;
      } else {
         return false;
      }
   }

   public int getMousePosTrgY(int var1) {
      return this.m_ptMouseEvtPos[var1].y;
   }

   public void mouseMove(int var1, int var2, int var3) {
      this.m_ptMouse.move(var1, var2);
      this.m_nFlgSpcEvt |= 1;
   }
}
