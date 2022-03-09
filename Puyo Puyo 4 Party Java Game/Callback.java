/* Decompiler 0ms, total 56ms, lines 14 */
interface Callback {
   int MOUSE_DOWN_LEFT = 1;
   int MOUSE_DOWN_RIGHT = 2;
   int MOUSE_UP_LEFT = 16;
   int MOUSE_UP_RIGHT = 32;
   int MOUSE_ENTER = 256;
   int MOUSE_EXIT = 512;
   int MOUSE_MOVE = 1024;
   int KEY_PRESS = 4096;
   int KEY_RELEASE = 8192;

   boolean EventCallback(int var1);
}
