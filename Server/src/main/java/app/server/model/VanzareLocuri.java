package app.server.model;


public class VanzareLocuri extends Entity<Integer> {

    private int nr_locuri;

    private int vanzare_id;

    public VanzareLocuri() {}

    public VanzareLocuri(int nr_locuri, int vanzare_id) {
        this.nr_locuri = nr_locuri;
        this.vanzare_id = vanzare_id;
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public int getVanzare_id() {
        return vanzare_id;
    }

    public void setVanzare_id(int vanzare_id) {
        this.vanzare_id = vanzare_id;
    }

    @Override
    public String toString() {
        return "VanzareLocuri{" +
                "nr_locuri='" + nr_locuri +
                "vanzarea='" + vanzare_id +
                '}';
    }
}
