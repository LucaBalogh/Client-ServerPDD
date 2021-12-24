package app.server.model;
import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
