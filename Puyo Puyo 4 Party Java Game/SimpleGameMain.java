/* Decompiler 108ms, total 226ms, lines 597 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.util.Random;

public class SimpleGameMain {
   protected static final int WP = 65536;
   protected static final int PIC_SIZX = 360;
   protected static final int PIC_SIZY = 360;
   protected static final int FIELD_X = 16;
   protected static final int FIELD_Y = 32;
   protected static final int STATUS_X = 16;
   protected static final int STATUS_Y = 0;
   protected static final int COMPLT_X = 148;
   protected static final int COMPLT_Y = 196;
   protected static final int MIN_PIECE = 3;
   protected static final int MAX_PIECE = 5;
   protected static final int UPSIDE = 0;
   protected static final int LEFT = 1;
   protected static final int DOWNSIDE = 2;
   protected static final int RIGHT = 3;
   protected static final int MAX_DIR = 4;
   protected static final int DRAWMODE_TITLE = 0;
   protected static final int DRAWMODE_GAME = 1;
   protected static final int SYS_READ_GRAPHICS = 1;
   protected static final int SYS_WAIT = 2;
   protected static final int SYS_WAIT_NR = 3;
   protected static final int GINITGAME = 100;
   protected static final int GINITGAME2 = 101;
   protected static final int GINITGAME3 = 108;
   protected static final int GMAIN = 102;
   protected static final int MAKE_PROBLEM = 103;
   protected static final int GMAIN1 = 104;
   protected static final int GMAIN2 = 105;
   protected static final int GCLEAR = 106;
   protected static final int CHANGEMODE = 107;
   public static final int IMG_FONT_0 = 0;
   public static final int IMG_MES_MOVECNT = 10;
   public static final int IMG_MES_HAND = 11;
   public static final int IMG_MES_COMPLT = 12;
   public static final int IMG_MES_CONGRA = 13;
   public static final int IMG_MES_PASSWD = 14;
   public static final int[] IMG_PIECE = new int[]{16, 16, 16};
   private static final Color[] m_colTbl;
   public boolean debug;
   private int m_nMode;
   private int m_nSubMode;
   private int m_nNextMode;
   private SimpleDisplay DISP;
   private SimpleBG BG;
   private KeyInput KEY;
   private SimpleApplet MAIN;
   private int m_nScrWidth;
   private int m_nScrHeight;
   private FontMetrics m_fmBG;
   private int m_nCounter;
   private int m_nWaitCnt;
   private int m_nWaitTime;
   private int[] m_nPieceTbl;
   private int m_nBlankPiece;
   private int m_nColorCnt;
   private int m_nMoveCnt;
   private int m_nFocusIndex;
   private int m_nFocusIndex_P;
   private Random m_rnd;
   private int m_nMoveBase;
   private int m_nMoveDir;
   private int m_nMoveSize;
   private int m_nMoveFrame;
   private boolean m_fMoving;
   private boolean m_fHelp;
   private int tmp;
   private Point p;
   public static int m_nPieceNum;
   public static int m_nBlankImageNo;
   protected static int m_nPieceWidth;
   protected static int m_nPieceHeight;

   private void drawPiece(int var1, int var2, boolean var3) {
      if (var1 >= 0 && var1 < this.m_nPieceTbl.length) {
         if (var3 && var1 == this.m_nBlankPiece) {
            this.BG.setColor(Color.lightGray);
            this.BG.fillRect(16 + var1 % m_nPieceNum * m_nPieceWidth, 32 + var1 / m_nPieceNum * m_nPieceHeight, m_nPieceWidth, m_nPieceHeight);
            return;
         }

         this.BG.drawImage(IMG_PIECE[m_nPieceNum - 3] + this.m_nPieceTbl[var1], 16 + var1 % m_nPieceNum * m_nPieceWidth, 32 + var1 / m_nPieceNum * m_nPieceHeight, (ImageObserver)null);
      }

   }

   private int movePiece(int var1) {
      int var2 = 0;
      if (var1 >= 0 && var1 < this.m_nPieceTbl.length && this.m_nPieceTbl[var1] != m_nBlankImageNo) {
         this.m_nMoveBase = var1;
         int var3;
         if (var1 % m_nPieceNum == this.m_nBlankPiece % m_nPieceNum) {
            if (var1 > this.m_nBlankPiece) {
               this.m_nMoveDir = 0;

               for(var3 = this.m_nBlankPiece; var3 < var1; var3 += m_nPieceNum) {
                  this.m_nPieceTbl[var3] = this.m_nPieceTbl[var3 + m_nPieceNum];
                  ++var2;
               }
            } else {
               this.m_nMoveDir = 2;

               for(var3 = this.m_nBlankPiece; var3 > var1; var3 -= m_nPieceNum) {
                  this.m_nPieceTbl[var3] = this.m_nPieceTbl[var3 - m_nPieceNum];
                  ++var2;
               }
            }
         } else {
            if (var1 / m_nPieceNum != this.m_nBlankPiece / m_nPieceNum) {
               return 0;
            }

            if (var1 > this.m_nBlankPiece) {
               this.m_nMoveDir = 1;

               for(var3 = this.m_nBlankPiece; var3 < var1; ++var3) {
                  this.m_nPieceTbl[var3] = this.m_nPieceTbl[var3 + 1];
                  ++var2;
               }
            } else {
               this.m_nMoveDir = 3;

               for(var3 = this.m_nBlankPiece; var3 > var1; --var3) {
                  this.m_nPieceTbl[var3] = this.m_nPieceTbl[var3 - 1];
                  ++var2;
               }
            }
         }

         this.m_nPieceTbl[var1] = m_nBlankImageNo;
         this.m_nBlankPiece = var1;
         this.m_nMoveSize = var2;
         return var2;
      } else {
         return 0;
      }
   }

   public void setDisplay(SimpleDisplay var1) {
      this.DISP = var1;
      Dimension var2 = var1.size();
      this.m_nScrWidth = var2.width;
      this.m_nScrHeight = var2.height;
   }

   private void drawMoveCnt() {
      this.BG.setColor(this.MAIN.bgColor);
      this.BG.fillRect(208, 0, 128, 32);
      int var1 = this.m_nMoveCnt;
      if (var1 < 0 || var1 > 9999) {
         var1 = 9999;
      }

      int var2 = 304;
      int var3 = 3;

      do {
         this.colorString(0 + var1 % 10, var2, 0, Color.black);
         var1 /= 10;
         var2 -= 32;
         --var3;
      } while(var3 >= 0);

   }

   private void colorLineString(int var1, int var2, int var3, Color[] var4, int var5) {
      int var6 = var4.length;
      int var7 = SimpleDisplay.m_imgTbl[var1].getWidth((ImageObserver)null);
      int var8 = var3 + 1;
      int var9 = 0;

      do {
         this.BG.setColor(var4[(var9 + var5) % var6]);
         this.BG.fillRect(var2, var8, var7, 6);
         var8 += 6;
         ++var9;
      } while(var9 < 5);

      this.BG.drawImage(var1, var2, var3, (ImageObserver)null);
   }

   public SimpleGameMain(KeyInput var1, SimpleApplet var2) {
      this.KEY = var1;
      this.MAIN = var2;
      this.m_nMode = 100;
   }

   private void colorString(int var1, int var2, int var3, int var4, Color var5) {
      int var6 = SimpleDisplay.m_imgTbl[var1].getWidth((ImageObserver)null) * var4;
      int var7 = SimpleDisplay.m_imgTbl[var1].getHeight((ImageObserver)null) * var4;
      this.BG.setColor(var5);
      this.BG.fillRect(var2, var3 + var4, var6, 31 * var4);
      this.BG.drawImage(var1, var2, var3, var6, var7, (ImageObserver)null);
   }

   private void colorString(int var1, int var2, int var3, Color var4) {
      this.BG.setColor(var4);
      this.BG.fillRect(var2, var3 + 1, SimpleDisplay.m_imgTbl[var1].getWidth((ImageObserver)null), 31);
      this.BG.drawImage(var1, var2, var3, (ImageObserver)null);
   }

   public void mainProcess() {
      switch(this.m_nMode) {
      case 1:
         this.DISP.readGraphics(this.m_nSubMode);
         this.m_nMode = this.m_nNextMode;
         break;
      case 2:
         if (this.KEY.m_nFlgKeyR != 0 || this.KEY.m_nFlgMouseR != 0) {
            this.m_nMode = this.m_nNextMode;
         }
         break;
      case 3:
         if (this.KEY.m_nFlgKey != 0 || this.KEY.m_nFlgMouse != 0) {
            this.m_nMode = this.m_nNextMode;
         }
         break;
      case 100:
         this.BG = this.DISP.m_sBG;
         this.m_rnd = new Random();
         this.readGraphics(0, 101);
         break;
      case 101:
         this.BG.setBGMode(1);
         this.BG.setColor(Color.black);
         this.BG.setFont(new Font("Courier", 1, 24));
         this.m_fmBG = this.BG.getFontMetrics();
         this.m_fHelp = false;
      case 108:
         m_nPieceNum = 4;
         m_nBlankImageNo = m_nPieceNum * m_nPieceNum - 1;
         m_nPieceWidth = 360 / m_nPieceNum;
         m_nPieceHeight = 360 / m_nPieceNum;
         this.m_nPieceTbl = new int[m_nPieceNum * m_nPieceNum];
         this.initPiece();
         this.m_nMoveFrame = 2;
         this.m_nCounter = 0;
         this.m_nWaitCnt = 0;
         this.m_nMode = 102;
         break;
      case 102:
         if (this.m_nCounter == 0 && this.m_nWaitCnt == 0) {
            this.BG.clearImage();
            this.m_nWaitTime = 15;
            this.m_nColorCnt = 0;
            this.shufflePieces(300);
            this.drawAllPieces(0, false);
            this.BG.setColor(Color.black);
            this.dispStringCenter("PANEL de \"BAKU-PUYO\"!!", 0);
            this.BG.setColor(Color.red);
            this.BG.setColor(m_colTbl[this.m_nColorCnt]);
            this.m_nColorCnt = (this.m_nColorCnt + 1) % m_colTbl.length;
            this.dispStringCenter("" + m_nPieceNum * m_nPieceNum + "Pieces. Click to start.", 392);
            ++this.m_nWaitCnt;
         } else if (this.KEY.m_nFlgKey == 0 && this.KEY.m_nFlgMouse == 0) {
            if (this.m_nWaitCnt == this.m_nWaitTime) {
               this.m_nCounter = 1;
               this.m_nWaitCnt = 0;
               this.initPiece();
               this.shufflePieces(1000);
               this.drawAllPieces(0, false);
               this.BG.setColor(m_colTbl[this.m_nColorCnt]);
               this.m_nColorCnt = (this.m_nColorCnt + 1) % m_colTbl.length;
               this.dispStringCenter("" + m_nPieceNum * m_nPieceNum + "Pieces. Click to start.", 392);
            } else {
               this.BG.setColor(m_colTbl[this.m_nColorCnt]);
               this.m_nColorCnt = (this.m_nColorCnt + 1) % m_colTbl.length;
               this.dispStringCenter("" + m_nPieceNum * m_nPieceNum + "Pieces. Click to start.", 392);
               ++this.m_nWaitCnt;
            }
         } else {
            this.m_fMoving = false;
            this.m_nMoveCnt = 0;
            this.BG.clearImage();

            do {
               this.shufflePieces(1000);
               this.moveBlankPiece();
            } while(this.checkShuffleState() == 0);

            this.drawAllPieces(1, true, this.m_fHelp);
            this.colorString(10, 16, 0, Color.black);
            this.drawMoveCnt();
            this.colorString(11, 336, 0, Color.black);
            this.m_nMode = 104;
         }
      case 103:
      default:
         break;
      case 104:
         if (this.m_fMoving) {
            this.drawMovingPieces(this.m_nWaitCnt++, this.m_fHelp);
            if (this.m_nWaitCnt > this.m_nMoveFrame) {
               this.m_fMoving = false;
               if (this.clearCheck()) {
                  if (this.m_fHelp) {
                     this.drawAllPieces(1, false, false);
                  } else {
                     this.drawPiece(m_nBlankImageNo, 1, false);
                  }

                  this.m_nCounter = 0;
                  this.m_nWaitCnt = 0;
                  this.m_nMode = 105;
               }
            }
         } else {
            if (this.KEY.getMouse(0)) {
               this.m_fHelp = false;
               this.p = this.KEY.getMousePosTrg(0);
               if (this.p.x < 16 || this.p.x >= 16 + m_nPieceNum * m_nPieceWidth || this.p.y < 32 || this.p.y >= 32 + m_nPieceNum * m_nPieceHeight) {
                  break;
               }

               this.tmp = (this.p.y - 32) / m_nPieceHeight * m_nPieceNum + (this.p.x - 16) / m_nPieceWidth;
               if (this.tmp != this.m_nFocusIndex) {
                  this.m_nFocusIndex_P = this.m_nFocusIndex;
                  this.m_nFocusIndex = this.tmp;
               }

               if ((this.tmp = this.movePiece(this.m_nFocusIndex)) == 0) {
                  break;
               }

               this.m_nWaitCnt = 0;
               this.m_nMoveCnt += this.tmp;
               this.drawMoveCnt();
               this.m_fMoving = true;
               this.drawMovingPieces(this.m_nWaitCnt++, this.m_fHelp);
            }

            if (this.KEY.getMouse(1)) {
               this.m_fHelp = !this.m_fHelp;
               this.drawAllPieces(1, true, this.m_fHelp);
            }

            if (this.KEY.getSpecialEvent(0)) {
               this.p = this.KEY.getMousePos();
               this.tmp = (this.p.y - 32) / m_nPieceHeight * m_nPieceNum + (this.p.x - 16) / m_nPieceWidth;
               if (this.tmp != this.m_nFocusIndex) {
                  this.m_nFocusIndex_P = this.m_nFocusIndex;
                  this.m_nFocusIndex = this.tmp;
               }
            }
         }
         break;
      case 105:
         if (this.m_nCounter == 0 && this.m_nWaitCnt == 0) {
            this.m_nWaitTime = 2;
            this.colorLineString(12, 148, 196, m_colTbl, this.m_nCounter);
         }

         if (this.KEY.m_nFlgKey == 0 && this.KEY.m_nFlgMouse == 0) {
            if (this.m_nWaitCnt == this.m_nWaitTime) {
               ++this.m_nCounter;
               this.m_nWaitCnt = 0;
               this.colorLineString(12, 148, 196, m_colTbl, this.m_nCounter);
            } else {
               ++this.m_nWaitCnt;
            }
         } else {
            this.m_nCounter = 0;
            this.m_nWaitCnt = 0;
            this.m_nMode = 106;
         }
         break;
      case 106:
         if (this.m_nCounter == 0 && this.m_nWaitCnt == 0) {
            this.m_nWaitTime = 2;
            this.BG.clearImage();
            this.colorLineString(13, 4, 144, m_colTbl, 0);
         }

         if (this.KEY.m_nFlgKey == 0 && this.KEY.m_nFlgMouse == 0) {
            if (this.m_nWaitCnt == this.m_nWaitTime) {
               this.m_nWaitCnt = 0;
               ++this.m_nCounter;
               this.colorLineString(13, 4, 144, m_colTbl, this.m_nCounter);
            } else {
               ++this.m_nWaitCnt;
            }
         } else {
            this.m_nMode = 108;
         }
      }

      this.KEY.clearKey();
   }

   private void dispStringCenter(String var1, boolean var2) {
      this.BG.drawString(var1, (this.m_nScrWidth - this.m_fmBG.stringWidth(var1)) / 2, (this.m_nScrHeight - this.m_fmBG.getHeight()) / 2, var2);
   }

   private void dispStringCenter(String var1) {
      this.BG.drawString(var1, (this.m_nScrWidth - this.m_fmBG.stringWidth(var1)) / 2, (this.m_nScrHeight - this.m_fmBG.getHeight()) / 2);
   }

   private void dispStringCenter(String var1, int var2) {
      this.BG.drawString(var1, (this.m_nScrWidth - this.m_fmBG.stringWidth(var1)) / 2, var2);
   }

   static {
      m_colTbl = new Color[]{Color.cyan, Color.green, Color.yellow, Color.orange, Color.red, Color.magenta, Color.red, Color.orange, Color.yellow, Color.green};
      m_nPieceNum = 3;
      m_nPieceWidth = 360 / m_nPieceNum;
      m_nPieceHeight = 360 / m_nPieceNum;
   }

   private int moveIndex(int var1, int var2) {
      switch(var2) {
      case 0:
         if (var1 / m_nPieceNum == 0) {
            return -1;
         }

         var1 -= m_nPieceNum;
         break;
      case 1:
         if (var1 % m_nPieceNum == 0) {
            return -1;
         }

         --var1;
         break;
      case 2:
         if (var1 / m_nPieceNum == m_nPieceNum - 1) {
            return -1;
         }

         var1 += m_nPieceNum;
         break;
      case 3:
         if (var1 % m_nPieceNum == m_nPieceNum - 1) {
            return -1;
         }

         ++var1;
      }

      return var1;
   }

   private void shufflePieces(int var1) {
      int var5 = -1;

      for(int var6 = 0; var6 < var1 || this.checkShuffleState() == 0; ++var6) {
         int var4 = this.m_rnd.nextInt() % 4;

         int var2;
         for(var2 = this.moveIndex(this.m_nBlankPiece, var4); var2 == -1 || var2 == var5; var2 = this.moveIndex(this.m_nBlankPiece, var4)) {
            var4 = (var4 + 1) % 4;
         }

         int var3 = this.m_nPieceTbl[this.m_nBlankPiece];
         this.m_nPieceTbl[this.m_nBlankPiece] = this.m_nPieceTbl[var2];
         this.m_nPieceTbl[var2] = var3;
         var5 = this.m_nBlankPiece;
         this.m_nBlankPiece = var2;
      }

   }

   private void initPiece() {
      for(int var1 = 0; var1 < this.m_nPieceTbl.length; this.m_nPieceTbl[var1] = var1++) {
      }

      this.m_nBlankPiece = m_nBlankImageNo;
   }

   private boolean clearCheck() {
      for(int var1 = 0; var1 < this.m_nPieceTbl.length; ++var1) {
         if (this.m_nPieceTbl[var1] != var1) {
            return false;
         }
      }

      return true;
   }

   private void drawAllPieces(int var1, boolean var2) {
      this.drawAllPieces(var1, var2, false);
   }

   private void drawAllPieces(int var1, boolean var2, boolean var3) {
      for(int var4 = 0; var4 < this.m_nPieceTbl.length; ++var4) {
         if (var2 && var4 == this.m_nBlankPiece) {
            this.BG.setColor(Color.lightGray);
            this.BG.fillRect(16 + var4 % m_nPieceNum * m_nPieceWidth, 32 + var4 / m_nPieceNum * m_nPieceHeight, m_nPieceWidth, m_nPieceHeight);
         } else {
            this.BG.drawImage(IMG_PIECE[m_nPieceNum - 3] + this.m_nPieceTbl[var4], 16 + var4 % m_nPieceNum * m_nPieceWidth, 32 + var4 / m_nPieceNum * m_nPieceHeight, (ImageObserver)null);
            if (var3) {
               this.BG.setColor(Color.black);
               this.BG.drawString("" + (this.m_nPieceTbl[var4] + 1), 16 + var4 % m_nPieceNum * m_nPieceWidth, 32 + var4 / m_nPieceNum * m_nPieceHeight);
            }
         }
      }

   }

   private void drawMovingPieces(int var1) {
      this.drawMovingPieces(var1, false);
   }

   private void drawMovingPieces(int var1, boolean var2) {
      if (var1 >= this.m_nMoveFrame) {
         this.drawAllPieces(1, true, var2);
      } else {
         this.BG.setColor(Color.lightGray);
         int var5 = this.moveIndex(this.m_nMoveBase, this.m_nMoveDir);
         this.BG.fillRect(16 + this.m_nMoveBase % m_nPieceNum * m_nPieceWidth, 32 + this.m_nMoveBase / m_nPieceNum * m_nPieceHeight, m_nPieceWidth, m_nPieceHeight);

         for(int var6 = 0; var6 < this.m_nMoveSize; ++var6) {
            int var3;
            int var4;
            switch(this.m_nMoveDir) {
            case 0:
               var3 = 0;
               var4 = m_nPieceHeight / this.m_nMoveFrame * (this.m_nMoveFrame - var1 - 1);
               break;
            case 1:
               var4 = 0;
               var3 = m_nPieceWidth / this.m_nMoveFrame * (this.m_nMoveFrame - var1 - 1);
               break;
            case 2:
               var3 = 0;
               var4 = -(m_nPieceHeight / this.m_nMoveFrame * (this.m_nMoveFrame - var1 - 1));
               break;
            case 3:
               var4 = 0;
               var3 = -(m_nPieceWidth / this.m_nMoveFrame * (this.m_nMoveFrame - var1 - 1));
               break;
            default:
               return;
            }

            this.BG.drawImage(IMG_PIECE[m_nPieceNum - 3] + this.m_nPieceTbl[var5], 16 + var5 % m_nPieceNum * m_nPieceWidth + var3, 32 + var5 / m_nPieceNum * m_nPieceHeight + var4, (ImageObserver)null);
            if (var2) {
               this.BG.setColor(Color.black);
               this.BG.drawString("" + (this.m_nPieceTbl[var5] + 1), 16 + var5 % m_nPieceNum * m_nPieceWidth + var3, 32 + var5 / m_nPieceNum * m_nPieceHeight + var4);
            }

            var5 = this.moveIndex(var5, this.m_nMoveDir);
         }

      }
   }

   private int checkShuffleState() {
      int var1 = 0;

      for(int var3 = 0; var3 < m_nPieceNum * m_nPieceNum; ++var3) {
         int var2 = this.m_nPieceTbl[var3] % m_nPieceNum - var3 % m_nPieceNum;
         var2 = var2 < 0 ? -var2 : var2;
         var1 += var2;
         var2 = this.m_nPieceTbl[var3] / m_nPieceNum - var3 / m_nPieceNum;
         var2 = var2 < 0 ? -var2 : var2;
         var1 += var2;
      }

      return var1;
   }

   private void readGraphics(int var1, int var2) {
      this.DISP.m_sBG.setColor(Color.black);
      this.DISP.m_sBG.drawString("Now Loading...", 0, 0);
      this.m_nMode = 1;
      this.m_nSubMode = var1;
      this.m_nNextMode = var2;
   }

   private void moveBlankPiece() {
      int var1;
      while(this.m_nBlankPiece / m_nPieceNum != m_nPieceNum - 1) {
         var1 = this.m_nPieceTbl[this.m_nBlankPiece];
         this.m_nPieceTbl[this.m_nBlankPiece] = this.m_nPieceTbl[this.m_nBlankPiece + m_nPieceNum];
         this.m_nPieceTbl[this.m_nBlankPiece + m_nPieceNum] = var1;
         this.m_nBlankPiece += m_nPieceNum;
      }

      while(this.m_nBlankPiece != m_nBlankImageNo) {
         var1 = this.m_nPieceTbl[this.m_nBlankPiece];
         this.m_nPieceTbl[this.m_nBlankPiece] = this.m_nPieceTbl[this.m_nBlankPiece + 1];
         this.m_nPieceTbl[this.m_nBlankPiece + 1] = var1;
         ++this.m_nBlankPiece;
      }

   }
}
