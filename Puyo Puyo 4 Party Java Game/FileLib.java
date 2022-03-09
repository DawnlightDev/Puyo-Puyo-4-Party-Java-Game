/* Decompiler 8ms, total 76ms, lines 41 */
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FileLib {
   public static byte[] readFile(URL var0, String var1) {
      URL var2;
      try {
         var2 = new URL(var0, var1);
      } catch (MalformedURLException var3) {
         System.out.println("Malformed URL Exception in readFile() : " + var1);
         return null;
      }

      return readFile(var2);
   }

   public static byte[] readFile(URL var0) {
      byte[] var4 = new byte[327680];

      try {
         InputStream var1 = var0.openStream();
         DataInputStream var3 = new DataInputStream(new BufferedInputStream(var1));

         try {
            var3.readFully(var4);
         } catch (EOFException var10) {
         } finally {
            var3.close();
         }
      } catch (IOException var12) {
      }

      return var4;
   }
}
