import java.util.Iterator;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;

// 
// Decompiled by Procyon v0.5.36
// 

class CestnoOmrezje
{
    private Vozlisce[] vozlisca;
    private Cesta[] ceste;
    
    static CestnoOmrezje CestnoOmrezje_izDatoteke(final String imeDatoteke) throws FileNotFoundException {
        final CestnoOmrezje co1 = new CestnoOmrezje();
        final Scanner sc = new Scanner(new File(imeDatoteke));
        String[] vrstica = sc.nextLine().split(" ");
        final int st_v = Integer.parseInt(vrstica[0]);
        final int st_c = Integer.parseInt(vrstica[1]);
        co1.vozlisca = new Vozlisce[st_v];
        co1.ceste = new Cesta[st_c];
        int stevec = 0;
        int stevec2 = 0;
        int id = 0;
        while (sc.hasNextLine()) {
            vrstica = sc.nextLine().split(" ");
            if (Objects.equals(vrstica[0], "vozlisce") || Objects.equals(vrstica[0], "kraj") || Objects.equals(vrstica[0], "crpalka")) {
                if (Objects.equals(vrstica[0], "vozlisce")) {
                    final Vozlisce v1 = new Vozlisce(Double.parseDouble(vrstica[1]), Double.parseDouble(vrstica[2]), id);
                    co1.vozlisca[stevec] = v1;
                }
                if (Objects.equals(vrstica[0], "kraj")) {
                    String kraj = "";
                    for (int i = 3; i < vrstica.length; ++i) {
                        kraj = invokedynamic(makeConcatWithConstants:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;, kraj, vrstica[i]);
                    }
                    final Kraj v2 = new Kraj(Double.parseDouble(vrstica[1]), Double.parseDouble(vrstica[2]), id, kraj);
                    co1.vozlisca[stevec] = (Vozlisce)v2;
                }
                if (Objects.equals(vrstica[0], "crpalka")) {
                    final Crpalka v3 = new Crpalka(Double.parseDouble(vrstica[1]), Double.parseDouble(vrstica[2]), id, Double.parseDouble(vrstica[3]), Double.parseDouble(vrstica[4]));
                    co1.vozlisca[stevec] = (Vozlisce)v3;
                }
                ++stevec;
                ++id;
            }
            else {
                Vozlisce v1 = null;
                Vozlisce v4 = null;
                for (int j = 0; j < co1.vozlisca.length; ++j) {
                    if (co1.vozlisca[j].getId() == Integer.parseInt(vrstica[0])) {
                        v1 = co1.vozlisca[j];
                    }
                    else if (co1.vozlisca[j].getId() == Integer.parseInt(vrstica[1])) {
                        v4 = co1.vozlisca[j];
                    }
                }
                final Cesta c1 = new Cesta(v1, v4, Integer.parseInt(vrstica[2]));
                v1.dodajCesto(c1);
                v4.dodajCesto(c1);
                co1.ceste[stevec2] = c1;
                ++stevec2;
            }
        }
        return co1;
    }
    
    public Cesta[] getCeste() {
        return this.ceste;
    }
    
    public Vozlisce[] getVozlisca() {
        return this.vozlisca;
    }
    
    void dolzinaPoti(final int[] pot) {
        double skupna_dolzina = 0.0;
        double cas = 0.0;
        this.ceste = this.getCeste();
        for (int i = 0; i < pot.length && i != pot.length - 1; ++i) {
            final int v1 = pot[i];
            final int v2 = pot[i + 1];
            for (int j = 0; j < this.ceste.length; ++j) {
                if ((v1 == this.ceste[j].getV1().getId() && v2 == this.ceste[j].getV2().getId()) || (v1 == this.ceste[j].getV2().getId() && v2 == this.ceste[j].getV1().getId())) {
                    skupna_dolzina += this.ceste[j].getDolzina();
                    cas += this.ceste[j].getDolzina() / this.ceste[j].getNdh();
                }
            }
        }
        final double h = cas;
        final double m = cas * 60.0 % 60.0;
        System.out.print("Pot: ");
        for (int k = 0; k < pot.length; ++k) {
            if (k != pot.length - 1) {
                System.out.print(invokedynamic(makeConcatWithConstants:(I)Ljava/lang/String;, pot[k]));
            }
            else {
                System.out.print(pot[k]);
            }
        }
        System.out.println();
        System.out.printf("Skupna dolzina: %.2f km\n", skupna_dolzina);
        System.out.printf("Predviden cas voznje: %.0fh %.0fmin", h, m);
    }
    
    void crpalkeObPoti(final int[] vozlisca) {
        this.vozlisca = this.getVozlisca();
        final List<String> crpalke = new ArrayList<String>();
        this.ceste = this.getCeste();
        final double do_crpalke = 0.0;
        for (int i = 0; i < vozlisca.length && i != vozlisca.length - 1; ++i) {
            final int v1 = vozlisca[i];
            final int v2 = vozlisca[i + 1];
            for (int j = 0; j < this.ceste.length; ++j) {
                if (v1 == this.ceste[j].getV1().getId() && this.ceste[j].getV2() instanceof Crpalka) {
                    if (!crpalke.contains(String.valueOf(this.ceste[j].getV2().getId()))) {
                        if (this.ceste[j].getV2().getId() == v2) {
                            System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: na poti\n", this.ceste[j].getV2().getId(), ((Crpalka)this.ceste[j].getV2()).get95(), ((Crpalka)this.ceste[j].getV2()).getDizel());
                            crpalke.add(String.valueOf(this.ceste[j].getV2().getId()));
                        }
                        else {
                            System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: %.2f km s poti\n", this.ceste[j].getV2().getId(), ((Crpalka)this.ceste[j].getV2()).get95(), ((Crpalka)this.ceste[j].getV2()).getDizel(), this.ceste[j].getDolzina());
                            crpalke.add(String.valueOf(this.ceste[j].getV2().getId()));
                        }
                    }
                }
                else if (v2 == this.ceste[j].getV2().getId() && this.ceste[j].getV1() instanceof Crpalka && !crpalke.contains(String.valueOf(this.ceste[j].getV1().getId()))) {
                    if (this.ceste[j].getV1().getId() == v1) {
                        System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: na poti\n", this.ceste[j].getV1().getId(), ((Crpalka)this.ceste[j].getV1()).get95(), ((Crpalka)this.ceste[j].getV1()).getDizel());
                        crpalke.add(String.valueOf(this.ceste[j].getV1().getId()));
                    }
                    else {
                        System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: %.2f km s poti\n", this.ceste[j].getV1().getId(), ((Crpalka)this.ceste[j].getV1()).get95(), ((Crpalka)this.ceste[j].getV1()).getDizel(), this.ceste[j].getDolzina());
                        crpalke.add(String.valueOf(this.ceste[j].getV1().getId()));
                    }
                }
            }
        }
    }
    
    void obremenjeneCeste(final String datoteka) throws FileNotFoundException {
        final Cesta[] ceste1 = new Cesta[this.ceste.length];
        final double[] poti = new double[this.ceste.length];
        this.ceste = this.getCeste();
        final Scanner sc = new Scanner(new File(datoteka));
        String[] vrstica = sc.nextLine().split(" ");
        final int vse = Integer.parseInt(vrstica[0]);
        int index = 0;
        int st = 0;
        for (int j = 0; j < this.ceste.length; ++j) {
            ceste1[index] = this.ceste[j];
            st = 0;
            final Scanner sc2 = new Scanner(new File(datoteka));
            while (sc2.hasNextLine()) {
                vrstica = sc2.nextLine().split(" ");
                for (int i = 0; i < vrstica.length && i != vrstica.length - 1; ++i) {
                    final int v1 = Integer.parseInt(vrstica[i]);
                    final int v2 = Integer.parseInt(vrstica[i + 1]);
                    if ((this.ceste[j].getV1().getId() == v1 && this.ceste[j].getV2().getId() == v2) || (this.ceste[j].getV1().getId() == v2 && this.ceste[j].getV2().getId() == v1)) {
                        ++st;
                    }
                }
            }
            poti[index] = st;
            ++index;
        }
        final String s = "%";
        for (int k = 0; k < ceste1.length; ++k) {
            final double p = poti[k] / vse * 100.0;
            System.out.printf("Cesta(%d,%d): %.0f poti (%.1f%s)\n", ceste1[k].getV1().getId(), ceste1[k].getV2().getId(), poti[k], p, s);
        }
    }
    
    void Izrisi() {
        final List<Double> X = new ArrayList<Double>();
        final List<Double> Y = new ArrayList<Double>();
        for (int i = 0; i < this.vozlisca.length; ++i) {
            X.add(this.vozlisca[i].getX());
            Y.add(this.vozlisca[i].getY());
        }
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        for (final double d : X) {
            if (d > maxX) {
                maxX = d;
            }
            if (d < minX) {
                minX = d;
            }
        }
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (final double d2 : Y) {
            if (d2 > maxY) {
                maxY = d2;
            }
            if (d2 < minY) {
                minY = d2;
            }
        }
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setYscale(minX - minX / 1000.0, maxX + maxX / 1000.0);
        StdDraw.setXscale(minY - minY / 600.0, maxY + maxY / 600.0);
        final Font font = new Font("Arial", 0, 12);
        StdDraw.setFont(font);
        this.vozlisca = this.getVozlisca();
        this.ceste = this.getCeste();
        for (int j = 0; j < this.ceste.length; ++j) {
            if (this.ceste[j].getNdh() < 90) {
                StdDraw.setPenColor(Color.black);
                StdDraw.setPenRadius(0.004);
                StdDraw.line(this.ceste[j].getV1().getY(), this.ceste[j].getV1().getX(), this.ceste[j].getV2().getY(), this.ceste[j].getV2().getX());
            }
            else if (this.ceste[j].getNdh() >= 90 && this.ceste[j].getNdh() < 110) {
                StdDraw.setPenColor(Color.yellow);
                StdDraw.setPenRadius(0.008);
                StdDraw.line(this.ceste[j].getV1().getY(), this.ceste[j].getV1().getX(), this.ceste[j].getV2().getY(), this.ceste[j].getV2().getX());
            }
            else if (this.ceste[j].getNdh() >= 110 && this.ceste[j].getNdh() < 130) {
                StdDraw.setPenColor(Color.blue);
                StdDraw.setPenRadius(0.012);
                StdDraw.line(this.ceste[j].getV1().getY(), this.ceste[j].getV1().getX(), this.ceste[j].getV2().getY(), this.ceste[j].getV2().getX());
            }
            else if (this.ceste[j].getNdh() >= 130) {
                StdDraw.setPenColor(Color.green);
                StdDraw.setPenRadius(0.016);
                StdDraw.line(this.ceste[j].getV1().getY(), this.ceste[j].getV1().getX(), this.ceste[j].getV2().getY(), this.ceste[j].getV2().getX());
            }
        }
        for (int j = 0; j < this.vozlisca.length; ++j) {
            if (this.vozlisca[j] instanceof Kraj) {
                StdDraw.setPenColor(Color.yellow);
                StdDraw.setPenRadius(0.02);
                StdDraw.point(this.vozlisca[j].getY(), this.vozlisca[j].getX());
                StdDraw.setPenColor(Color.black);
                StdDraw.setPenRadius(0.015);
                StdDraw.text(this.vozlisca[j].getY(), this.vozlisca[j].getX() + 0.01, ((Kraj)this.vozlisca[j]).getKraj());
                StdDraw.text(this.vozlisca[j].getY(), this.vozlisca[j].getX() - 0.01, String.valueOf(this.vozlisca[j].getId()));
            }
            else if (this.vozlisca[j] instanceof Crpalka) {
                StdDraw.setPenColor(Color.white);
                StdDraw.setPenRadius(0.005);
                StdDraw.filledRectangle(this.vozlisca[j].getY(), this.vozlisca[j].getX(), 0.0012, 0.002);
                StdDraw.setPenColor(Color.red);
                StdDraw.setPenRadius(0.005);
                StdDraw.rectangle(this.vozlisca[j].getY(), this.vozlisca[j].getX(), 0.0012, 0.002);
                StdDraw.setPenColor(Color.black);
                StdDraw.text(this.vozlisca[j].getY(), this.vozlisca[j].getX() - 0.01, String.valueOf(this.vozlisca[j].getId()));
            }
            else {
                StdDraw.setPenColor(Color.black);
                StdDraw.setPenRadius(0.01);
                StdDraw.point(this.vozlisca[j].getY(), this.vozlisca[j].getX());
                StdDraw.text(this.vozlisca[j].getY(), this.vozlisca[j].getX() - 0.01, String.valueOf(this.vozlisca[j].getId()));
            }
        }
    }
}
