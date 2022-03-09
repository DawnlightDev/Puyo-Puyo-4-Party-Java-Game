/* Decompiler 489ms, total 581ms, lines 692 */
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class GameMain {
   protected static final int WP = 65536;
   protected static final int SYS_READ_GRAPHICS = 1;
   protected static final int SYS_WAIT = 2;
   protected static final int SYS_WAIT_R = 3;
   private int m_nMode;
   private int m_nModeCnt;
   private int m_nNextMode;
   private boolean m_fArg0;
   private Display DISP;
   private BackGround BG;
   private KeyInput KEY;
   private AppletMain MAIN;
   private int m_nScrWidth;
   private int m_nScrHeight;
   private boolean m_fCont;
   public static final int FONT_WIDTH = 16;
   public static final int FONT_HEIGHT = 32;
   public static final int MARU_WIDTH = 32;
   public static final int MARU_HEIGHT = 32;
   public static final int BAKU_WIDTH = 44;
   public static final int BAKU_HEIGHT = 63;
   public static final int LOGO_WIDTH = 128;
   public static final int LOGO_HEIGHT = 32;
   public static final int LOGO_BIG_WIDTH = 320;
   public static final int LOGO_BIG_HEIGHT = 80;
   public static final int SCREEN_WIDTH = 408;
   public static final int SCREEN_HEIGHT = 272;
   public static final int FIELD_X = 8;
   public static final int FIELD_Y = 8;
   public static final int FIELD_WIDTH = 256;
   public static final int FIELD_HEIGHT = 256;
   public static final int CELL_MAX_X = 4;
   public static final int CELL_MAX_Y = 4;
   public static final int CELL_WIDTH = 64;
   public static final int CELL_HEIGHT = 64;
   public static final int INFO_X = 272;
   public static final int INFO_Y = 8;
   public static final int INFO_WIDTH = 128;
   public static final int INFO_HEIGHT = 256;
   public static final int PASSWORD_WIDTH = 288;
   public static final int PASSWORD_HEIGHT = 72;
   public static final int PACKAGE_X = 39;
   public static final int PACKAGE_Y = 142;
   public static final int BAKU_BLINK_COUNT = 4;
   public static final int STR_BLINK_COUNT = 10;
   public static final int FRAME_PER_SECOND = 18;
   public static final int LIMIT_TIME = 30;
   public static final int STAGE_MAX = 5;
   public static final int CHANCE_MAX = 8;
   public static final int MISS_COUNT = 3;
   public static final int PASSWORD_STAGE = 1;
   private int[] pattern_range = new int[]{0, 9, 10, 19, 20, 29, 30, 38, 39, 46};
   private int[][] pattern_tbl = new int[][]{{0, 5, 9, 2, 5, 9, 4, 5, 9, 6, 5, 9, 8, 5, 9, 10, 5, 9, 12, 5, 9, 14, 5, 9}, {15, 5, 9, 13, 5, 9, 11, 5, 9, 9, 5, 9, 7, 5, 9, 5, 5, 9, 3, 5, 9, 1, 5, 9}, {0, 5, 9, 8, 5, 9, 1, 5, 9, 9, 5, 9, 2, 5, 9, 10, 5, 9, 3, 5, 9, 11, 5, 9}, {12, 5, 9, 4, 5, 9, 13, 5, 9, 5, 5, 9, 14, 5, 9, 6, 5, 9, 15, 5, 9, 7, 5, 9}, {0, 5, 9, 3, 5, 9, 15, 5, 9, 12, 5, 9, 5, 5, 9, 6, 5, 9, 10, 5, 9, 9, 5, 9}, {1, 5, 9, 7, 5, 9, 14, 5, 9, 8, 5, 9, 13, 5, 9, 11, 5, 9, 2, 5, 9, 4, 5, 9}, {0, 5, 9, 5, 5, 9, 10, 5, 9, 15, 5, 9, 3, 5, 9, 6, 5, 9, 9, 5, 9, 12, 5, 9}, {12, 5, 9, 1, 5, 9, 14, 5, 9, 3, 5, 9, 11, 5, 9, 6, 5, 9, 9, 5, 9, 4, 5, 9}, {0, 3, 2, 2, 3, 2, 4, 4, 2, 6, 4, 2, 8, 5, 2, 10, 5, 2, 12, 6, 2, 14, 6, 58}, {1, 3, 2, 3, 3, 2, 5, 4, 2, 7, 4, 2, 9, 5, 2, 11, 5, 2, 13, 6, 2, 15, 6, 58}, {0, 3, 0, 2, 4, 0, 4, 5, 0, 6, 6, 30, 8, 3, 0, 10, 4, 0, 12, 5, 0, 14, 6, 30}, {15, 3, 0, 13, 4, 0, 11, 5, 0, 9, 6, 30, 7, 3, 0, 5, 4, 0, 3, 5, 0, 1, 6, 30}, {0, 3, 0, 8, 4, 0, 1, 5, 0, 9, 6, 30, 2, 3, 0, 10, 4, 0, 3, 5, 0, 11, 6, 30}, {12, 3, 0, 4, 4, 0, 13, 5, 0, 5, 6, 30, 14, 3, 0, 6, 4, 0, 15, 5, 0, 7, 6, 30}, {0, 3, 0, 3, 4, 0, 15, 5, 0, 12, 6, 30, 5, 3, 0, 6, 4, 0, 10, 5, 0, 9, 6, 30}, {1, 3, 0, 7, 4, 0, 14, 5, 0, 8, 6, 30, 13, 3, 0, 11, 4, 0, 2, 5, 0, 4, 6, 30}, {0, 3, 0, 5, 4, 0, 10, 5, 0, 15, 6, 30, 3, 3, 0, 6, 4, 0, 9, 5, 0, 12, 6, 30}, {12, 3, 0, 1, 4, 0, 14, 5, 0, 3, 6, 30, 11, 3, 0, 6, 4, 0, 9, 5, 0, 4, 6, 30}, {0, 3, 2, 8, 3, 2, 2, 4, 2, 10, 4, 2, 4, 5, 2, 12, 5, 2, 6, 6, 2, 14, 6, 46}, {1, 3, 2, 9, 3, 2, 3, 4, 2, 11, 4, 2, 5, 5, 2, 13, 5, 2, 7, 6, 2, 15, 6, 46}, {0, 5, 6, 2, 5, 6, 4, 5, 6, 6, 5, 6, 8, 5, 6, 10, 5, 6, 12, 5, 6, 14, 5, 6}, {15, 5, 6, 13, 5, 6, 11, 5, 6, 9, 5, 6, 7, 5, 6, 5, 5, 6, 3, 5, 6, 1, 5, 6}, {0, 5, 6, 8, 5, 6, 1, 5, 6, 9, 5, 6, 2, 5, 6, 10, 5, 6, 3, 5, 6, 11, 5, 6}, {12, 5, 6, 4, 5, 6, 13, 5, 6, 5, 5, 6, 14, 5, 6, 6, 5, 6, 15, 5, 6, 7, 5, 6}, {0, 5, 6, 3, 5, 6, 15, 5, 6, 12, 5, 6, 5, 5, 6, 6, 5, 6, 10, 5, 6, 9, 5, 6}, {1, 5, 6, 7, 5, 6, 14, 5, 6, 8, 5, 6, 13, 5, 6, 11, 5, 6, 2, 5, 6, 4, 5, 6}, {0, 5, 6, 5, 5, 6, 10, 5, 6, 15, 5, 6, 3, 5, 6, 6, 5, 6, 9, 5, 6, 12, 5, 6}, {12, 5, 6, 1, 5, 6, 14, 5, 6, 3, 5, 6, 11, 5, 6, 6, 5, 6, 9, 5, 6, 4, 5, 6}, {0, 3, 1, 2, 3, 1, 4, 4, 1, 6, 4, 1, 8, 5, 1, 10, 5, 1, 12, 6, 1, 14, 6, 41}, {1, 3, 1, 3, 3, 1, 5, 4, 1, 7, 4, 1, 9, 5, 1, 11, 5, 1, 13, 6, 1, 15, 6, 41}, {0, 3, 5, 1, 3, 5, 2, 3, 5, 3, 3, 5, 7, 3, 5, 6, 3, 5, 5, 3, 5, 4, 3, 5}, {8, 3, 5, 9, 3, 5, 10, 3, 5, 11, 3, 5, 15, 3, 5, 14, 3, 5, 13, 3, 5, 12, 3, 5}, {0, 3, 5, 4, 3, 5, 8, 3, 5, 12, 3, 5, 13, 3, 5, 9, 3, 5, 5, 3, 5, 1, 3, 5}, {2, 3, 5, 6, 3, 5, 10, 3, 5, 14, 3, 5, 15, 3, 5, 11, 3, 5, 7, 3, 5, 3, 3, 5}, {0, 3, 5, 1, 3, 5, 2, 3, 5, 6, 3, 5, 10, 3, 5, 9, 3, 5, 8, 3, 5, 4, 3, 5}, {3, 3, 5, 7, 3, 5, 11, 3, 5, 10, 3, 5, 9, 3, 5, 5, 3, 5, 1, 3, 5, 2, 3, 5}, {15, 3, 5, 14, 3, 5, 13, 3, 5, 9, 3, 5, 5, 3, 5, 6, 3, 5, 7, 3, 5, 11, 3, 5}, {12, 3, 5, 8, 3, 5, 4, 3, 5, 5, 3, 5, 6, 3, 5, 10, 3, 5, 14, 3, 5, 13, 3, 5}, {0, 3, 1, 1, 3, 1, 2, 3, 1, 3, 3, 1, 7, 4, 1, 11, 4, 1, 15, 4, 1, 14, 4, 1, 13, 5, 1, 12, 5, 1, 8, 5, 1, 4, 5, 1, 5, 6, 1, 6, 6, 1, 10, 6, 1, 9, 6, 65}, {0, 3, 5, 2, 3, 6, 10, 3, 5, 8, 3, 6, 5, 3, 5, 7, 3, 6, 15, 3, 5, 13, 3, 6}, {3, 3, 5, 1, 3, 6, 9, 3, 5, 11, 3, 6, 6, 3, 5, 4, 3, 6, 12, 3, 5, 14, 3, 6}, {0, 3, 5, 3, 3, 6, 15, 3, 5, 12, 3, 6, 5, 3, 5, 6, 3, 6, 10, 3, 5, 9, 3, 6}, {1, 3, 5, 7, 3, 6, 14, 3, 5, 8, 3, 6, 13, 3, 5, 11, 3, 6, 2, 3, 5, 4, 3, 6}, {0, 3, 5, 5, 3, 6, 10, 3, 5, 15, 3, 6, 3, 3, 5, 6, 3, 6, 9, 3, 5, 12, 3, 6}, {12, 3, 5, 1, 3, 6, 14, 3, 5, 3, 3, 6, 11, 3, 5, 6, 3, 6, 9, 3, 5, 4, 3, 6}, {4, 3, 5, 1, 3, 6, 2, 3, 5, 7, 3, 6, 11, 3, 5, 6, 3, 6, 5, 3, 5, 8, 3, 6, 12, 3, 5, 9, 3, 6, 10, 3, 5, 15, 3, 6, 3, 3, 5, 14, 3, 6, 13, 3, 5, 0, 3, 6}, {0, 3, 1, 2, 3, 1, 10, 3, 1, 8, 3, 1, 5, 4, 1, 7, 4, 1, 15, 4, 1, 13, 4, 1, 3, 5, 1, 1, 5, 1, 9, 5, 1, 11, 5, 1, 6, 6, 1, 4, 6, 1, 12, 6, 1, 14, 6, 73}};
   private Rectangle raField = new Rectangle(8, 8, 256, 256);
   private static final int GINITGAME = 100;
   private static final int GINITGAME2 = 101;
   private static final int GTITLE = 102;
   private static final int GSTART = 109;
   private static final int GMAIN = 103;
   private static final int GCLEAR = 104;
   private static final int GOVER = 105;
   private static final int GOVERMISS = 107;
   private static final int GPASSWORD = 108;
   private static final int GENDING = 106;
   public static final int IMG_BAKU_0 = 0;
   public static final int IMG_BAKU_1 = 1;
   public static final int IMG_LOGO = 2;
   public static final int IMG_FONT_0 = 3;
   public static final int IMG_FONT_1 = 4;
   public static final int IMG_FONT_2 = 5;
   public static final int IMG_FONT_3 = 6;
   public static final int IMG_FONT_4 = 7;
   public static final int IMG_FONT_5 = 8;
   public static final int IMG_FONT_6 = 9;
   public static final int IMG_FONT_7 = 10;
   public static final int IMG_FONT_8 = 11;
   public static final int IMG_FONT_9 = 12;
   public static final int IMG_FONT_A = 13;
   public static final int IMG_FONT_B = 14;
   public static final int IMG_FONT_C = 15;
   public static final int IMG_FONT_D = 16;
   public static final int IMG_FONT_E = 17;
   public static final int IMG_FONT_F = 18;
   public static final int IMG_FONT_G = 19;
   public static final int IMG_FONT_H = 20;
   public static final int IMG_FONT_I = 21;
   public static final int IMG_FONT_J = 22;
   public static final int IMG_FONT_K = 23;
   public static final int IMG_FONT_L = 24;
   public static final int IMG_FONT_M = 25;
   public static final int IMG_FONT_N = 26;
   public static final int IMG_FONT_O = 27;
   public static final int IMG_FONT_P = 28;
   public static final int IMG_FONT_Q = 29;
   public static final int IMG_FONT_R = 30;
   public static final int IMG_FONT_S = 31;
   public static final int IMG_FONT_T = 32;
   public static final int IMG_FONT_U = 33;
   public static final int IMG_FONT_V = 34;
   public static final int IMG_FONT_W = 35;
   public static final int IMG_FONT_X = 36;
   public static final int IMG_FONT_Y = 37;
   public static final int IMG_FONT_Z = 38;
   public static final int IMG_PASSWORD = 39;
   public static final int IMG_LOGO_BIG = 40;
   public static final int IMG_MARU = 41;
   public static final int IMG_BATU = 42;
   public static final int IMG_ENDING_BG = 43;
   public static final int IMG_PACKAGE = 44;
   private static final Color[] m_colTbl;
   private FontMetrics m_fmBG;
   private int tmp;
   private int tmp2;
   private Point p;
   private String s;
   private int stage;
   private int time;
   private long stime;
   private int score;
   private int hiscore;
   private int chance;
   private int[] waiting;
   private int[] field;
   private int[] oldfield;
   private int fieldstate;
   private int ptn_no;
   private int ptn_ptr;
   private int count_next;
   private boolean no_miss;
   private int pass = 100000;
   private int hi_pass = 100000;

   private void readGraphics(int var1, int var2, String var3, boolean var4) {
      this.DISP.m_sBG.setColor(Color.black);
      this.DISP.m_sBG.drawString(var3, 0, 0);
      this.m_nModeCnt = var1;
      this.m_nNextMode = var2;
      this.m_fArg0 = var4;
      this.m_nMode = 1;
   }

   private void dispColorStringCenter(String var1, int var2) {
      this.dispColorStringCenter(var1, (this.m_nScrHeight - this.m_fmBG.getHeight()) / 2, var2);
   }

   private void dispColorStringCenter(String var1, int var2, int var3) {
      int var4 = var1.length();
      int var5 = (this.m_nScrWidth - this.m_fmBG.stringWidth(var1)) / 2;

      for(int var7 = 0; var7 < var4; ++var7) {
         String var6 = var1.substring(var7, var7 + 1);
         this.BG.setColor(m_colTbl[(var3 + var7) % m_colTbl.length]);
         this.BG.drawString(var6, var5, var2);
         var5 += this.m_fmBG.stringWidth(var6);
      }

   }

   private void drawGStringFieldCenter(String var1, int var2) {
      this.drawGString(var1, (256 - var1.length() * 16) / 2 + 8, var2, true);
   }

   private void drawScoreInfo() {
      this.drawGString(Integer.toString(this.score), 272, 136);
   }

   public void setDisplay(Display var1) {
      this.DISP = var1;
      this.m_nScrWidth = 408;
      this.m_nScrHeight = 272;
   }

   private void drawChanceInfo() {
      short var1 = 200;
      int var2 = 0;

      do {
         if (var2 < this.chance) {
            this.BG.drawImage(41, 272 + var2 * 32, var1, (ImageObserver)null);
         } else {
            this.BG.drawImage(42, 272 + var2 * 32, var1, (ImageObserver)null);
         }

         ++var2;
      } while(var2 < 4);

      int var3 = var1 + 32;
      var2 = 4;

      do {
         if (var2 < this.chance) {
            this.BG.drawImage(41, 272 + (var2 - 4) * 32, var3, (ImageObserver)null);
         } else {
            this.BG.drawImage(42, 272 + (var2 - 4) * 32, var3, (ImageObserver)null);
         }

         ++var2;
      } while(var2 < 8);

   }

   private void initInfo() {
      byte var1 = 8;
      this.BG.drawImage(2, 272, var1, (ImageObserver)null);
      int var3 = var1 + 32;
      this.drawGString("STAGE " + Integer.toString(this.stage + 1), 272, var3);
      var3 += 32;
      this.drawGString("TIME " + Integer.toString(this.time) + " ", 272, var3);
      var3 += 32;
      this.drawGString("SCORE", 272, var3);
      var3 += 32;
      this.drawGString(Integer.toString(this.score), 272, var3);
      var3 += 32;
      this.drawGString("CHANCE", 272, var3);
      var3 += 32;
      int var2 = 0;

      do {
         if (var2 < this.chance) {
            this.BG.drawImage(41, 272 + var2 * 32, var3, (ImageObserver)null);
         } else {
            this.BG.drawImage(42, 272 + var2 * 32, var3, (ImageObserver)null);
         }

         ++var2;
      } while(var2 < 4);

      var3 += 32;
      var2 = 4;

      do {
         if (var2 < this.chance) {
            this.BG.drawImage(41, 272 + (var2 - 4) * 32, var3, (ImageObserver)null);
         } else {
            this.BG.drawImage(42, 272 + (var2 - 4) * 32, var3, (ImageObserver)null);
         }

         ++var2;
      } while(var2 < 8);

   }

   public void mainProcess() {
      this.m_fCont = true;

      while(true) {
         label354:
         do {
            while(this.m_fCont) {
               this.m_fCont = false;
               int var1;
               switch(this.m_nMode) {
               case 1:
                  this.DISP.readGraphics(this.m_nModeCnt, this.m_fArg0);
                  this.m_nMode = this.m_nNextMode;
                  break;
               case 2:
                  if (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0) {
                     this.m_nMode = this.m_nNextMode;
                  }
                  break;
               case 3:
                  if (this.KEY.m_nFlgKeyR != 0 || this.KEY.m_nFlgMouseR != 0) {
                     this.m_nMode = this.m_nNextMode;
                  }
                  break;
               case 100:
                  this.BG = this.DISP.m_sBG;
                  this.setBGFont(0);
                  this.readGraphics(0, 101, "Now loading...");
                  break;
               case 101:
                  this.BG.setBGMode(1);
                  this.BG.setColor(Color.black);
                  this.switchMode(102, true);
                  this.setBGFont(4);
                  break;
               case 102:
                  if (this.m_nModeCnt == 0) {
                     if (this.score > this.hiscore) {
                        this.hiscore = this.score;
                     }

                     this.chance = 8;
                     this.stage = 0;
                     this.BG.clearImage();
                     this.BG.drawImage(40, 22, 16, (ImageObserver)null);
                     this.drawGStringCenter("SCORE " + this.score, 96);
                     this.drawGStringCenter("HISCORE " + this.hiscore, 160);
                     this.BG.setColor(Color.white);
                  }

                  if (this.m_nModeCnt % 4 == 0) {
                     if (this.m_nModeCnt / 4 % 2 == 0) {
                        this.BG.drawImage(0, 342, 32, (ImageObserver)null);
                     } else {
                        this.BG.drawImage(1, 342, 32, (ImageObserver)null);
                     }
                  }

                  if (this.m_nModeCnt % 10 == 0) {
                     if (this.m_nModeCnt / 10 % 2 == 0) {
                        this.drawGString("CLICK TO START", 92, 228);
                     } else {
                        this.BG.fillRect(92, 224, 228, 32);
                     }
                  }

                  ++this.m_nModeCnt;
                  if (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0) {
                     this.score = 0;
                     this.switchMode(109);
                  }
                  break;
               case 103:
                  if (this.m_nModeCnt == 0) {
                     this.BG.setColor(Color.white);
                     this.BG.fillRect(8, 8, 256, 256);
                     this.BG.setColor(Color.red);
                     var1 = 8;

                     do {
                        int var2 = 8;

                        do {
                           this.BG.drawRect(var1, var2, 64, 64);
                           var2 += 64;
                        } while(var2 < 264);

                        var1 += 64;
                     } while(var1 < 264);
                  }

                  for(var1 = 0; var1 < this.waiting.length; ++var1) {
                     if (this.waiting[var1] != 0 && this.field[var1] == 0) {
                        this.field[var1] = this.waiting[var1];
                        this.fieldstate |= 1 << var1;
                        this.waiting[var1] = 0;
                     }
                  }

                  if (this.KEY.m_nFlgMouse != 0) {
                     Point var3 = this.KEY.getMousePos();
                     if (this.raField.inside(var3.x, var3.y)) {
                        this.tmp = (var3.y - 8) / 64 * 4 + (var3.x - 8) / 64;
                        if (this.field[this.tmp] <= 0) {
                           this.chance += -1;
                           this.drawChanceInfo();
                           this.field[this.tmp] = -3;
                           if (this.chance == 0) {
                              this.switchMode(107);
                              break;
                           }
                        } else {
                           this.score += this.field[this.tmp];
                           this.drawScoreInfo();
                           this.field[this.tmp] = 0;
                           this.fieldstate &= ~(1 << this.tmp);
                        }
                     }
                  }

                  this.tmp = 0;

                  for(var1 = 0; var1 < this.field.length; ++var1) {
                     if (this.field[var1] > 0) {
                        int[] var10000 = this.field;
                        var10000[var1] += -1;
                        if (this.field[var1] == 0) {
                           this.tmp = var1 + 1;
                        }
                     } else if (this.field[var1] < 0) {
                        int var10002 = this.field[var1]++;
                     }
                  }

                  if (this.tmp != 0) {
                     this.switchMode(105);
                  } else {
                     if (this.time > 0 && (this.count_next == 0 || this.fieldstate == 0)) {
                        do {
                           if (this.ptn_no < 0 || this.ptn_ptr * 3 >= this.pattern_tbl[this.ptn_no].length) {
                              this.ptn_no = this.pattern_range[this.stage * 2] + this.getRandom(this.pattern_range[this.stage * 2 + 1] - this.pattern_range[this.stage * 2] + 1);
                              this.ptn_ptr = 0;
                           }

                           this.tmp = this.pattern_tbl[this.ptn_no][this.ptn_ptr * 3];
                           if (this.field[this.tmp] != 0) {
                              this.waiting[this.tmp] = this.pattern_tbl[this.ptn_no][this.ptn_ptr * 3 + 1] * 18;
                           } else {
                              this.field[this.tmp] = this.pattern_tbl[this.ptn_no][this.ptn_ptr * 3 + 1] * 18;
                              this.fieldstate |= 1 << this.tmp;
                           }

                           this.count_next = this.pattern_tbl[this.ptn_no][this.ptn_ptr * 3 + 2];
                           ++this.ptn_ptr;
                        } while(this.count_next == 0);
                     }

                     this.count_next += -1;
                     this.tmp = 0;
                     if (this.m_nModeCnt / 4 % 2 != (this.m_nModeCnt - 1) / 4 % 2) {
                        this.tmp = 1;
                     }

                     for(var1 = 0; var1 < this.field.length; ++var1) {
                        if (this.field[var1] == 0) {
                           if (this.oldfield[var1] != 0) {
                              this.BG.setColor(Color.white);
                              this.BG.fillRect(8 + 64 * (var1 % 4) + 1, 8 + 64 * (var1 / 4) + 1, 63, 63);
                           }
                        } else {
                           if (this.field[var1] > 0 && (this.tmp == 1 || this.oldfield[var1] == 0)) {
                              this.BG.drawImage(0 + this.m_nModeCnt / 4 % 2, 8 + 64 * (var1 % 4) + 1, 8 + 64 * (var1 / 4) + 1, (ImageObserver)null);
                           }

                           if (this.field[var1] < 0 && this.oldfield[var1] == 0) {
                              this.BG.drawImage(42, 8 + 64 * (var1 % 4) + 16, 8 + 64 * (var1 / 4) + 16, (ImageObserver)null);
                           }

                           if ((this.field[var1] - 1) / 18 + 1 != (this.oldfield[var1] - 1) / 18 + 1) {
                              this.BG.drawImage(3 + (this.field[var1] - 1) / 18 + 1, 8 + 64 * (var1 % 4) + 44 + 1, 8 + 64 * (var1 / 4) + 1, (ImageObserver)null);
                           }
                        }
                     }

                     this.tmp = (int)((System.currentTimeMillis() - this.stime) / 1000L);
                     if (this.tmp <= 30 && 30 - this.tmp < this.time) {
                        this.time = 30 - this.tmp;
                        this.drawTimeInfo();
                     }

                     if (this.time == 0 && this.fieldstate == 0) {
                        this.switchMode(104);
                     } else {
                        for(var1 = 0; var1 < this.field.length; ++var1) {
                           this.oldfield[var1] = this.field[var1];
                        }

                        ++this.m_nModeCnt;
                     }
                  }
                  break;
               case 104:
                  if (this.m_nModeCnt == 0) {
                     if (this.stage == 4) {
                        this.drawGStringFieldCenter("STAGE " + (this.stage + 1) + " CLEAR", 64);
                     } else {
                        this.drawGStringFieldCenter("STAGE " + (this.stage + 1) + " CLEAR", 120);
                     }
                  }

                  if (this.stage == 4 && this.m_nModeCnt == 10) {
                     this.drawGStringFieldCenter("CHANCE BONUS", 128);
                     this.drawGStringFieldCenter(Integer.toString(this.chance * 100), 160);
                     this.score += this.chance * 100;
                  }

                  if (this.stage == 4 && this.m_nModeCnt == 20) {
                     this.drawScoreInfo();
                  }

                  ++this.m_nModeCnt;
                  if (this.m_nModeCnt > 30 && (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0)) {
                     ++this.stage;
                     if (this.stage == 5) {
                        this.switchMode(106);
                     } else {
                        this.switchMode(109);
                     }
                  }
                  break;
               case 105:
                  this.BG.setColor(Color.red);
                  if (this.m_nModeCnt == 0) {
                     this.tmp += -1;
                     this.BG.drawImage(3, 8 + 64 * (this.tmp % 4) + 44 + 1, 8 + 64 * (this.tmp / 4) + 1, (ImageObserver)null);
                     this.BG.fillOval(8 + this.tmp % 4 * 64 + 20, 8 + this.tmp / 4 * 64 + 40, 64, 2);
                  }

                  if (this.m_nModeCnt == 1) {
                     this.BG.fillOval(8 + this.tmp % 4 * 64 + 20 - 64, 8 + this.tmp / 4 * 64 + 40 - 1, 192, 3);
                  }

                  if (this.m_nModeCnt == 2) {
                     this.BG.fillOval(8 + this.tmp % 4 * 64 + 20 - 408, 8 + this.tmp / 4 * 64 + 40 - 2, 816, 5);
                  }

                  if (this.m_nModeCnt > 2 && this.m_nModeCnt < 30) {
                     this.tmp2 = (int)Math.sqrt((double)((this.m_nModeCnt - 2) * 300));
                     this.BG.fillOval(8 + this.tmp % 4 * 64 + 20 - this.tmp2 / 2, 8 + this.tmp / 4 * 64 + 40 - this.tmp2 / 2, this.tmp2, this.tmp2);
                  }

                  if (this.m_nModeCnt == 30) {
                     this.drawGString("GAME OVER", 56, 120, true);
                  }

                  ++this.m_nModeCnt;
                  if (this.m_nModeCnt > 30 && (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0)) {
                     this.switchMode(102);
                  }
                  break;
               case 106:
                  if (this.m_nModeCnt == 0) {
                     this.BG.drawImage(43, 0, 0, (ImageObserver)null);
                     this.BG.drawImage(44, 39, 142, (ImageObserver)null);
                  }

                  ++this.m_nModeCnt;
                  if (this.m_nModeCnt > 10 && (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0)) {
                     this.switchMode(102);
                  }
                  break;
               case 107:
                  if (this.m_nModeCnt == 0) {
                     this.drawGString("GAME OVER", 56, 128, true);
                  }

                  ++this.m_nModeCnt;
                  if (this.m_nModeCnt > 10 && (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0)) {
                     this.switchMode(102);
                  }
                  break;
               case 108:
                  if (this.m_nModeCnt == 0) {
                     this.BG.clearImage();
                     this.drawGString("PASSWORD", 126, 64);
                     this.BG.drawImage(39, 60, 100, (ImageObserver)null);
                  }

                  ++this.m_nModeCnt;
                  if (this.m_nModeCnt > 10 && (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0)) {
                     this.switchMode(109);
                  }
                  break;
               case 109:
                  if (this.m_nModeCnt == 0) {
                     this.BG.clearImage();
                     this.ptn_no = -1;
                     this.ptn_ptr = -1;
                     this.count_next = 0;
                     this.time = 30;
                     this.stime = System.currentTimeMillis();

                     for(var1 = 0; var1 < this.waiting.length; ++var1) {
                        this.waiting[var1] = 0;
                     }

                     for(var1 = 0; var1 < this.field.length; ++var1) {
                        this.field[var1] = 0;
                     }

                     for(var1 = 0; var1 < this.oldfield.length; ++var1) {
                        this.oldfield[var1] = 0;
                     }

                     this.fieldstate = 0;
                     this.initInfo();
                     this.drawGString("STAGE " + (this.stage + 1), 72, 96, true);
                     this.drawGString("CLICK TO START", 16, 128, true);
                  }
                  continue label354;
               }
            }

            this.KEY.clearKey();
            return;
         } while(this.KEY.m_nFlgKey == 0 && this.KEY.m_nFlgMouse == 0);

         this.switchMode(103);
      }
   }

   private void drawTimeInfo() {
      this.drawGString(Integer.toString(this.time) + " ", 352, 72);
   }

   private void dispStringCenter(String var1, int var2) {
      this.dispStringCenter(var1, var2, true);
   }

   private void dispStringCenter(String var1, boolean var2) {
      this.dispStringCenter(var1, (this.m_nScrHeight - this.m_fmBG.getHeight()) / 2, var2);
   }

   private void dispStringCenter(String var1) {
      this.dispStringCenter(var1, (this.m_nScrHeight - this.m_fmBG.getHeight()) / 2, true);
   }

   private void dispStringCenter(String var1, int var2, boolean var3) {
      this.BG.drawString(var1, (this.m_nScrWidth - this.m_fmBG.stringWidth(var1)) / 2, var2, var3);
   }

   private void setBGFont(int var1) {
      this.m_fmBG = this.BG.setFont(var1);
   }

   private void drawGString(String var1, int var2, int var3) {
      this.drawGString(var1, var2, var3, false);
   }

   private void drawGString(String var1, int var2, int var3, boolean var4) {
      if (var4) {
         var1 = " " + var1 + " ";
         var2 -= 16;
      }

      char[] var5 = var1.toCharArray();
      int var7 = var2;

      for(int var8 = 0; var8 < var5.length; ++var8) {
         int var6;
         if (var5[var8] >= '0' && var5[var8] <= '9') {
            var6 = 3 + var5[var8] - 48;
         } else {
            if (var5[var8] < 'A' || var5[var8] > 'Z') {
               this.BG.setColor(Color.white);
               this.BG.fillRect(var7, var3, 16, 32);
               var7 += 16;
               continue;
            }

            var6 = 13 + var5[var8] - 65;
         }

         this.BG.drawImage(var6, var7, var3, (ImageObserver)null);
         var7 += 16;
      }

   }

   static {
      m_colTbl = new Color[]{Color.cyan, Color.green, Color.yellow, Color.orange, Color.red, Color.magenta, Color.red, Color.orange, Color.yellow, Color.green};
   }

   private void drawStageInfo() {
      this.drawGString(Integer.toString(this.stage + 1), 368, 40);
   }

   private int makePassword(int var1) {
      int var2 = var1 * 1103515245 + 12345;
      return (var2 >>> 16) % 100000;
   }

   private void drawGStringCenter(String var1, int var2) {
      this.drawGString(var1, (408 - var1.length() * 16) / 2, var2, false);
   }

   private int getRandom(int var1) {
      double var2 = (double)var1 - 0.001D;
      return (new Double(Math.random() * var2)).intValue();
   }

   public GameMain(KeyInput var1, AppletMain var2) {
      this.KEY = var1;
      this.MAIN = var2;
      this.m_nMode = 100;
      this.field = new int[16];
      this.oldfield = new int[16];
      this.waiting = new int[16];
   }

   private void switchMode(int var1) {
      this.switchMode(var1, false);
   }

   private void switchMode(int var1, boolean var2) {
      this.m_nMode = var1;
      this.m_nModeCnt = 0;
      this.m_fCont = var2;
   }

   private void readGraphics(int var1, int var2) {
      this.readGraphics(var1, var2, "Now Loading...", true);
   }

   private void readGraphics(int var1, int var2, String var3) {
      this.readGraphics(var1, var2, var3, true);
   }

   private void readGraphics(int var1, int var2, boolean var3) {
      this.readGraphics(var1, var2, "Now Loading...", var3);
   }
}
