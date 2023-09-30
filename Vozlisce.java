/* Decompiler 4ms, total 524ms, lines 42 */
import java.util.ArrayList;
import java.util.List;

class Vozlisce {
   private double x;
   private double y;
   private List<Cesta> ceste = new ArrayList();
   private int id;

   public Vozlisce(double x, double y, int id) {
      this.x = x;
      this.y = y;
      this.id = id;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public int getId() {
      return this.id;
   }

   public List<Cesta> getCeste() {
      return this.ceste;
   }

   public void setXY(double x, double y, int id) {
      this.x = x;
      this.y = y;
      this.id = id;
   }

   void dodajCesto(Cesta cesta) {
      this.ceste.add(cesta);
   }
}
