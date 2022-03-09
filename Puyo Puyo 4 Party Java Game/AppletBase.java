/* Decompiler 5ms, total 67ms, lines 36 */
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.StringTokenizer;

public class AppletBase extends Applet {
   DoubleBuffer db;

   public String getParameterString(String var1) {
      return super.getParameter(var1);
   }

   public int getParameterInteger(String var1) {
      return Integer.parseInt(super.getParameter(var1));
   }

   public void init() {
      this.setLayout(new BorderLayout());
      this.db = new DoubleBuffer(this, this.size().width, this.size().height);
      this.add("Center", this.db);
      Font var1 = new Font("Courier", 1, 16);
      this.setFont(var1);
   }

   public int[] getParameterIntegerArray(String var1) {
      StringTokenizer var2 = new StringTokenizer(this.getParameterString(var1), ",");
      int[] var3 = new int[var2.countTokens()];

      for(int var4 = 0; var4 < var3.length; ++var4) {
         var3[var4] = Integer.parseInt(var2.nextToken());
      }

      return var3;
   }
}
