/* Decompiler 7ms, total 73ms, lines 54 */
import java.applet.Applet;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FileRead {
   Applet applet;
   URL BaseURL;

   public FileRead(Applet var1) {
      this.applet = var1;

      try {
         this.BaseURL = new URL(var1.getCodeBase(), "img/");
      } catch (MalformedURLException var2) {
         System.out.println("Malformed URL Exception");
      }
   }

   synchronized byte[] dataRead(String var1) {
      URL var2;
      try {
         var2 = new URL(this.BaseURL, var1);
      } catch (MalformedURLException var3) {
         System.out.println("Malformed URL Exception");
         return null;
      }

      return this.dataRead(var2);
   }

   synchronized byte[] dataRead(URL var1) {
      byte[] var4 = new byte[524288];

      try {
         DataInputStream var3 = new DataInputStream(new BufferedInputStream(var1.openStream()));

         try {
            var3.readFully(var4);
         } catch (EOFException var10) {
         } finally {
            var3.close();
         }
      } catch (IOException var12) {
         System.out.println("IO Exception in " + var1.getFile());
      }

      return var4;
   }
}
