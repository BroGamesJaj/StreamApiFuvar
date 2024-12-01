package hu.petrik.StreamApiFuvar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static List<Fuvar> fuvarok = new ArrayList<Fuvar>();
    public static void main(String[] args) {
        try{
            readFile("fuvar.csv");

            System.out.println("Utak száma: " + f1());
            f2();
            System.out.println("Megtett mérföldek száma: " + f3());
            System.out.println("Leghosszabb fuvar: " + f4());
            System.out.println("Legbőkezűbb borravaló: " + f5());
            System.out.println("4261-es taxi megtett távolsága: " + f6() + "km");
            f7();
            System.out.println("Létezik a 1452-es taxi? " + f8());
            System.out.println("3 legrövidebb fuvar: " + f9());
            System.out.println("December 24.-ei fuvarok: " + f10());
            System.out.println("Borravoló százaléka december 31.-én: " + f11() + "%");


        } catch (IOException e) {
            System.err.println(e);
        }


    }

    public static int f1(){
        return fuvarok.size();
    }

    public static void f2(){
        double bevetel = fuvarok.stream().filter(fuvar -> fuvar.getTaxi_id() == 6185).mapToDouble(fuvar -> fuvar.getViteldij() + fuvar.getBorravalo()).sum();

        long fuvarok_szama = fuvarok.stream().filter(fuvar -> fuvar.getTaxi_id() == 6185).count();

        System.out.println("Taxi 6185 bevétele: $" + bevetel + " fuvarok száma: " + fuvarok_szama);
    }

    public static double f3(){
        return fuvarok.stream().mapToDouble(Fuvar::getTavolsag).sum();
    }

    public static Fuvar f4(){
        return fuvarok.stream().max((f1,f2) -> Integer.compare(f1.getIdotartam(), f2.getIdotartam())).orElse(null);
    }

    public static Fuvar f5(){
        return fuvarok.stream().max((f1,f2) -> Double.compare(f1.getBorravalo() / f1.getViteldij(), f2.getBorravalo() / f2.getViteldij())).orElse(null);
    }

    public static double f6(){
        return fuvarok.stream().filter(fuvar -> fuvar.getTaxi_id() == 4261).mapToDouble(Fuvar::getTavolsag).sum() * 1.6;
    }

    public static void f7(){
        List<Fuvar> hibas = fuvarok.stream().filter(fuvar -> fuvar.getIdotartam() > 0 && fuvar.getViteldij() > 0 && fuvar.getTavolsag() == 0).toList();
        System.out.println("hibás fuvarok száma: " + hibas.size());
        System.out.println("Összes időtartam: " + hibas.stream().mapToInt(Fuvar::getIdotartam).sum());
        System.out.println("Teljes bevétel: " + hibas.stream().mapToDouble(f -> f.getViteldij() + f.getBorravalo()).sum());
    }

    public static boolean f8(){
        return fuvarok.stream().anyMatch(fuvar -> fuvar.getTaxi_id() == 1452);
    }

    public static List<Fuvar> f9(){
        return fuvarok.stream().filter(fuvar -> fuvar.getIdotartam() > 0).sorted(Comparator.comparingInt(Fuvar::getIdotartam)).limit(3).collect(Collectors.toList());
    }

    public static long f10(){
        return  fuvarok.stream().filter(fuvar -> fuvar.getIndulas().startsWith("2016-12-24")).count();
    }

    public static double f11(){
        List<Fuvar> dec31 = fuvarok.stream().filter(fuvar -> fuvar.getIndulas().startsWith("2016-12-31")).toList();

        return (dec31.size() / dec31.stream().filter(fuvar -> fuvar.getBorravalo() > 0).count()) * 100;
    }

    private static void readFile( String fileName ) throws IOException {
        BufferedReader br = new BufferedReader( new FileReader( fileName ) );
        br.readLine();
        String line = br.readLine();
        while ( line != null && !line.isEmpty() ) {
            fuvarok.add( new Fuvar( line ) );
            line = br.readLine();
        }
        br.close();
    }
}