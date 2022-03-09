import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.36
// 

public abstract class Act
{
    public static final int WP = 65536;
    public static final int ID_PAUSE = 32768;
    public static final int ID_DISP = 16384;
    public static final int ID_ANIME = 8192;
    public static final int ID_MESSEQ = 4096;
    public static final int CHRATR_CENTER = 4096;
    public static final int CHRATR_STRETCH = 8192;
    public static final int _X = 0;
    public static final int _Y = 1;
    public static final int _Z = 2;
    public static final int _XYZ = 3;
    public static final int MOVE_XY = 24576;
    public static final int MOVE_X = 16384;
    public static final int MOVE_Y = 8192;
    public static final int MOVE_Z = 4096;
    DoubleBuffer db;
    int ID;
    int prog_mode;
    int flg_status;
    int priority;
    int img_seat;
    int img_index;
    int[] Pos;
    int[] vector;
    int[] accele;
    int[] center;
    int[] angle;
    int[] radian;
    int[][] anime;
    Rectangle collision;
    int size_x;
    int size_y;
    int cnt_wait;
    int cnt_loop;
    int cnt_animeindex;
    int cnt_animeloop;
    int chr_atr;
    int scale_x;
    int scale_y;
    
    protected void setCollision() {
        this.setCollision(this.Pos[0] >> 16, this.Pos[1] >> 16, this.size_x, this.size_y);
    }
    
    protected void setCollision(final int x, final int y, final int width, final int height) {
        this.collision.reshape(x, y, width, height);
    }
    
    public String toString() {
        return "Act : " + this.Pos[0] / 65536 + "," + this.Pos[1] / 65536;
    }
    
    protected void actionAct() {
        if ((this.ID & 0x8000) != 0x0 && this.cnt_wait > 0) {
            --this.cnt_wait;
        }
        if ((this.ID & 0x2000) != 0x0 && this.cnt_animeloop != 0 && --this.cnt_animeloop == 0) {
            if (++this.cnt_animeindex == this.anime.length) {
                this.cnt_animeindex = 0;
            }
            this.img_index = this.anime[this.cnt_animeindex][0];
            this.cnt_animeloop = this.anime[this.cnt_animeindex][1];
        }
    }
    
    void setAnimeTable(final int[][] array) {
        this.anime = new int[array.length][];
        for (int i = 0; i < array.length; ++i) {
            this.anime[i] = new int[array[i].length];
            for (int j = 0; j < array[i].length; ++j) {
                this.anime[i][j] = array[i][j];
            }
        }
        this.cnt_animeindex = 0;
        this.img_index = this.anime[this.cnt_animeindex][0];
        this.cnt_animeloop = this.anime[this.cnt_animeindex][1];
        this.db.addRedrawArea(this.getActSize());
    }
    
    Rectangle getActSize() {
        final Rectangle rectangle = new Rectangle();
        if ((this.chr_atr & 0x2000) != 0x0) {
            rectangle.resize(this.size_x * this.scale_x >> 16, this.size_y * this.scale_y >> 16);
        }
        else {
            rectangle.resize(this.size_x, this.size_y);
        }
        if ((this.chr_atr & 0x1000) != 0x0) {
            rectangle.move((this.Pos[0] >> 16) - rectangle.width / 2, (this.Pos[1] >> 16) - rectangle.height / 2);
        }
        else {
            rectangle.move(this.Pos[0] >> 16, this.Pos[1] >> 16);
        }
        return rectangle;
    }
    
    void drawImage() {
        final Rectangle actSize = this.getActSize();
        this.db.drawImage(this.img_seat, this.img_index, actSize.x, actSize.y);
    }
    
    protected void clear() {
        this.db.clearAct(this);
        this.db = null;
    }
    
    void drawImageStretch() {
        final Rectangle actSize = this.getActSize();
        this.db.drawImage(this.img_seat, this.img_index, actSize.x, actSize.y, actSize.width, actSize.height);
    }
    
    public Act(final DoubleBuffer db) {
        (this.db = db).setAct(this);
        this.Pos = new int[3];
        this.vector = new int[3];
        this.accele = new int[3];
        this.center = new int[3];
        this.angle = new int[3];
        this.radian = new int[3];
        this.collision = new Rectangle();
    }
}
