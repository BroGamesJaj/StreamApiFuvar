package hu.petrik.StreamApiFuvar;

public class Fuvar {
    private int taxi_id;
    private String indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetes_modja;

    public Fuvar(int taxi_id, String indulas, int idotartam, double tavolsag, double viteldij, double borravalo, String fizetes_modja) {
        this.taxi_id = taxi_id;
        this.indulas = indulas;
        this.idotartam = idotartam;
        this.tavolsag = tavolsag;
        this.viteldij = viteldij;
        this.borravalo = borravalo;
        this.fizetes_modja = fizetes_modja;
    }

    public Fuvar(String sor){
        String[] data = sor.split(";");

        this.taxi_id = Integer.parseInt(data[0]);
        this.indulas = data[1];
        this.idotartam = Integer.parseInt(data[2]);
        this.tavolsag = Double.parseDouble(data[3].replace(",","."));
        this.viteldij = Double.parseDouble(data[4].replace(",","."));
        this.borravalo = Double.parseDouble(data[5].replace(",","."));
        this.fizetes_modja = data[6];
    }

    public int getTaxi_id() {
        return taxi_id;
    }

    public String getIndulas() {
        return indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public String getFizetes_modja() {
        return fizetes_modja;
    }

    @Override
    public String toString() {
        return taxi_id + ", " + indulas + ", " + idotartam + ", " + tavolsag + ", " + viteldij + ", " + borravalo + ", " + fizetes_modja;
    }
}
