/* Decompiler 2ms, total 188ms, lines 19 */
class Crpalka extends Vozlisce {
   private double cena_95;
   private double cena_dizel;

   public Crpalka(double x, double y, int id, double cena_95, double cena_dizel) {
      super(x, y, id);
      this.cena_95 = cena_95;
      this.cena_dizel = cena_dizel;
   }

   public double get95() {
      return this.cena_95;
   }

   public double getDizel() {
      return this.cena_dizel;
   }
}
