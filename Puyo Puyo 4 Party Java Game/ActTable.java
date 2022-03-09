/* Decompiler 2ms, total 75ms, lines 14 */
public class ActTable {
   Act act;
   ActTable prev;
   ActTable next;

   public String toString() {
      if (this.prev == null) {
         return "prev = null , this = " + this.act.toString() + ", next = " + this.next.act.toString();
      } else {
         return this.next == null ? "prev = " + this.prev.act.toString() + ", this = " + this.act.toString() + ", next = null" : "prev = " + this.prev.act.toString() + ", this = " + this.act.toString() + ", next = " + this.next.act.toString();
      }
   }
}
