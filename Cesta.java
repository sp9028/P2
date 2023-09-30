/* Decompiler 5ms, total 372ms, lines 32 */
class Cesta {
   private Vozlisce v1;
   private Vozlisce v2;
   private int ndh;

   public Cesta(Vozlisce v1, Vozlisce v2, int ndh) {
      this.v1 = v1;
      this.v2 = v2;
      this.ndh = ndh;
   }

   public int getNdh() {
      return this.ndh;
   }

   double getDolzina() {
      return Math.sqrt(Math.pow((this.v1.getX() - this.v2.getX()) * 111.12D, 2.0D) + Math.pow((this.v1.getY() - this.v2.getY()) * 77.4D, 2.0D));
   }

   public Vozlisce getV1() {
      return this.v1;
   }

   public Vozlisce getV2() {
      return this.v2;
   }

   void toString1() {
      System.out.printf("Cesta(%d,%d): dolzina=%.2f km, omejitev=%d km/h\n", this.v1.getId(), this.v2.getId(), this.getDolzina(), this.getNdh());
   }
}
