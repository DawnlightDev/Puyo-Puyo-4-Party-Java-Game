/* Decompiler 59ms, total 121ms, lines 316 */
import java.awt.Event;
import java.awt.Rectangle;

class EventManagement {
   EventQueue MOUSE_DOWN_LEFT_QUEUE;
   EventQueue MOUSE_DOWN_RIGHT_QUEUE;
   EventQueue MOUSE_UP_LEFT_QUEUE;
   EventQueue MOUSE_UP_RIGHT_QUEUE;
   EventQueue MOUSE_ENTER_QUEUE;
   EventQueue MOUSE_EXIT_QUEUE;
   EventQueue MOUSE_MOVE_QUEUE;
   EventQueue KEY_PRESS_QUEUE;
   EventQueue KEY_RELEASE_QUEUE;
   private Event last_evt;
   private int old_x;
   private int old_y;

   synchronized void removeEventClient(Callback var1, int var2, Rectangle var3, int var4) {
      EventQueue var5;
      if ((var4 & 1) != 0) {
         var5 = this.MOUSE_DOWN_LEFT_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_DOWN_LEFT_QUEUE = this.MOUSE_DOWN_LEFT_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 2) != 0) {
         var5 = this.MOUSE_DOWN_RIGHT_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_DOWN_RIGHT_QUEUE = this.MOUSE_DOWN_RIGHT_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 16) != 0) {
         var5 = this.MOUSE_UP_LEFT_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_UP_LEFT_QUEUE = this.MOUSE_UP_LEFT_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 32) != 0) {
         var5 = this.MOUSE_UP_RIGHT_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_UP_RIGHT_QUEUE = this.MOUSE_UP_RIGHT_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 256) != 0) {
         var5 = this.MOUSE_ENTER_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_ENTER_QUEUE = this.MOUSE_ENTER_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 512) != 0) {
         var5 = this.MOUSE_EXIT_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_EXIT_QUEUE = this.MOUSE_EXIT_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 1024) != 0) {
         var5 = this.MOUSE_MOVE_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.MOUSE_MOVE_QUEUE = this.MOUSE_MOVE_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 4096) != 0) {
         var5 = this.KEY_PRESS_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.KEY_PRESS_QUEUE = this.KEY_PRESS_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      } else if ((var4 & 8192) != 0) {
         var5 = this.KEY_RELEASE_QUEUE;
         if (var5 != null) {
            if (var5.client == var1 && var5.raArea.equals(var3)) {
               this.KEY_RELEASE_QUEUE = this.KEY_RELEASE_QUEUE.next;
               return;
            }

            while(var5.next != null && var5 != null) {
               if (var5.next.client == var1 && var5.next.raArea.equals(var3)) {
                  var5.next = var5.next.next;
                  return;
               }

               var5 = var5.next;
            }
         }

      }
   }

   private EventQueue searchEventQueue(EventQueue var1, Callback var2, Rectangle var3) {
      if (var1 == null) {
         return null;
      } else {
         return var1.client == var2 && var1.raArea.equals(var3) ? var1 : null;
      }
   }

   synchronized void addEventClient(Callback var1, int var2, Rectangle var3, int var4) {
      EventQueue var5 = new EventQueue(var1, var2, var3);
      if ((var4 & 1) != 0) {
         var5.next = this.MOUSE_DOWN_LEFT_QUEUE;
         this.MOUSE_DOWN_LEFT_QUEUE = var5;
      }

      if ((var4 & 2) != 0) {
         var5.next = this.MOUSE_DOWN_RIGHT_QUEUE;
         this.MOUSE_DOWN_RIGHT_QUEUE = var5;
      }

      if ((var4 & 16) != 0) {
         var5.next = this.MOUSE_UP_LEFT_QUEUE;
         this.MOUSE_UP_LEFT_QUEUE = var5;
      }

      if ((var4 & 32) != 0) {
         var5.next = this.MOUSE_UP_RIGHT_QUEUE;
         this.MOUSE_UP_RIGHT_QUEUE = var5;
      }

      if ((var4 & 256) != 0) {
         var5.next = this.MOUSE_ENTER_QUEUE;
         this.MOUSE_ENTER_QUEUE = var5;
      }

      if ((var4 & 512) != 0) {
         var5.next = this.MOUSE_EXIT_QUEUE;
         this.MOUSE_EXIT_QUEUE = var5;
      }

      if ((var4 & 1024) != 0) {
         var5.next = this.MOUSE_MOVE_QUEUE;
         this.MOUSE_MOVE_QUEUE = var5;
      }

      if ((var4 & 4096) != 0) {
         var5.next = this.KEY_PRESS_QUEUE;
         this.KEY_PRESS_QUEUE = var5;
      }

      if ((var4 & 8192) != 0) {
         var5.next = this.KEY_RELEASE_QUEUE;
         this.KEY_RELEASE_QUEUE = var5;
      }

   }

   public EventManagement() {
   }

   Event getLastEvent() {
      return this.last_evt;
   }

   void getEvent(Event var1) {
      if (var1 != null) {
         EventQueue var2 = null;
         int var3 = var1.id;
         this.last_evt = var1;
         if (var3 == 503 || var3 == 506) {
            for(var2 = this.MOUSE_ENTER_QUEUE; var2 != null && (var2.raArea == null || var2.raArea.inside(this.old_x, this.old_y) || !var2.raArea.inside(var1.x, var1.y) || !var2.client.EventCallback(var2.method_number)); var2 = var2.next) {
            }

            for(var2 = this.MOUSE_EXIT_QUEUE; var2 != null && (var2.raArea == null || !var2.raArea.inside(this.old_x, this.old_y) || var2.raArea.inside(var1.x, var1.y) || !var2.client.EventCallback(var2.method_number)); var2 = var2.next) {
            }
         }

         this.old_x = var1.x;
         this.old_y = var1.y;
         switch(var3) {
         case 401:
         case 403:
            var2 = this.KEY_PRESS_QUEUE;
            break;
         case 402:
         case 404:
            var2 = this.KEY_RELEASE_QUEUE;
            break;
         case 501:
            if ((var1.modifiers & 4) != 0) {
               var2 = this.MOUSE_DOWN_RIGHT_QUEUE;
            } else {
               var2 = this.MOUSE_DOWN_LEFT_QUEUE;
            }
            break;
         case 502:
            if ((var1.modifiers & 4) != 0) {
               var2 = this.MOUSE_UP_RIGHT_QUEUE;
            } else {
               var2 = this.MOUSE_UP_LEFT_QUEUE;
            }
            break;
         case 503:
         case 506:
            var2 = this.MOUSE_MOVE_QUEUE;
            break;
         case 504:
            var2 = this.MOUSE_ENTER_QUEUE;
            break;
         case 505:
            var2 = this.MOUSE_EXIT_QUEUE;
         }

         this.drawEvent(var2, var1);
      }
   }

   private void drawEvent(EventQueue var1, Event var2) {
      while(var1 != null) {
         if (var1.raArea.inside(var2.x, var2.y)) {
            if (var1.client.EventCallback(var1.method_number)) {
            }

            return;
         }

         var1 = var1.next;
      }

   }
}
