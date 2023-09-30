/* Decompiler 2ms, total 181ms, lines 13 */
class Kraj extends Vozlisce {
   private String kraj;

   public Kraj(double x, double y, int id, String kraj) {
      super(x, y, id);
      this.kraj = kraj;
   }

   public String getKraj() {
      return this.kraj;
   }
}
