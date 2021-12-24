package app.server.model;

public class Sala extends Entity<Integer> {

    private int nr_locuri;

    public Sala() {}

    public Sala(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nr_locuri='" + nr_locuri +
                '}';
    }
}
