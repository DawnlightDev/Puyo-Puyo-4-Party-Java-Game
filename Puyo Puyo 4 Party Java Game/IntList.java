/* Decompiler 62ms, total 135ms, lines 152 */
public class IntList {
   protected IntList m_lstPrev;
   protected IntList m_lstNext;
   protected int m_nElem;

   public IntList search(int var1) {
      IntList var2;
      for(var2 = this; var2 != null && var1 != var2.m_nElem; var2 = var2.m_lstNext) {
      }

      return var2;
   }

   public int getElement() {
      return this.m_nElem;
   }

   public String toString() {
      return this.m_lstNext == null ? "(" + this.m_nElem + ")->null" : "(" + this.m_nElem + ")->" + this.m_lstNext;
   }

   public int getIndexElement(int var1) {
      IntList var2 = this.getNext();

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var2 != null) {
            var2 = var2.getNext();
         }
      }

      if (var2 == null) {
         return -1;
      } else {
         return var2.m_nElem;
      }
   }

   public IntList add(IntList var1) {
      IntList var2;
      for(var2 = this; var2.m_lstNext != null; var2 = var2.m_lstNext) {
      }

      var2.m_lstNext = var1;
      var1.m_lstPrev = var2;
      var1.m_lstNext = null;
      return var1;
   }

   public IntList addSort(IntList var1) {
      IntList var2;
      for(var2 = this; var2.m_nElem < var1.m_nElem; var2 = var2.m_lstNext) {
         if (var2.m_lstNext == null) {
            var2.m_lstNext = var1;
            var1.m_lstPrev = var2;
            var1.m_lstNext = null;
            return var1;
         }
      }

      if (var2 == this) {
         return null;
      } else {
         var1.m_lstNext = var2;
         var1.m_lstPrev = var2.m_lstPrev;
         var1.m_lstNext.m_lstPrev = var1;
         var1.m_lstPrev.m_lstNext = var1;
         return var1;
      }
   }

   public IntList getNext() {
      return this.m_lstNext;
   }

   public void setNext(IntList var1) {
      this.m_lstNext = var1;
   }

   public IntList() {
   }

   public IntList(int var1) {
      this.m_nElem = var1;
   }

   public IntList getIndexList(int var1) {
      IntList var2 = this.getNext();

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var2 != null) {
            var2 = var2.getNext();
         }
      }

      return var2;
   }

   public IntList getPrev() {
      return this.m_lstPrev;
   }

   public IntList removeIndexList(int var1) {
      IntList var2 = this.getNext();

      for(int var3 = 0; var3 < var1; ++var3) {
         if (var2 != null) {
            var2 = var2.getNext();
         }
      }

      if (var2 != null) {
         var2.remove();
      }

      return var2;
   }

   public int length() {
      IntList var1 = this.getNext();

      int var2;
      for(var2 = 0; var1 != null; ++var2) {
         var1 = var1.getNext();
      }

      return var2;
   }

   public int searchIndex(int var1) {
      int var2 = -1;

      IntList var3;
      for(var3 = this; var3 != null && var1 != var3.m_nElem; ++var2) {
         var3 = var3.m_lstNext;
      }

      return var3 == null ? -1 : var2;
   }

   public void remove() {
      if (this.m_lstNext != null) {
         this.m_lstNext.m_lstPrev = this.m_lstPrev;
      }

      if (this.m_lstPrev != null) {
         this.m_lstPrev.m_lstNext = this.m_lstNext;
      }

      this.m_lstNext = this.m_lstPrev = null;
   }
}
