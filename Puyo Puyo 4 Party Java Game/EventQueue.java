/* Decompiler 9ms, total 79ms, lines 20 */
import java.awt.Rectangle;

class EventQueue {
   Callback client;
   int method_number;
   Rectangle raArea;
   EventQueue next;

   public String toString() {
      return this.raArea.toString();
   }

   public EventQueue(Callback var1, int var2, Rectangle var3) {
      this.client = var1;
      this.method_number = var2;
      this.raArea = var3;
      this.next = this.next;
   }
}
