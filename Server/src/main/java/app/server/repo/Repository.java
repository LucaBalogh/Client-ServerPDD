package app.server.repo;


import app.server.model.Entity;

import java.util.List;

public interface Repository<ID, E extends Entity<ID>> {
    void add(E elem);
    List<E> findAll();
}
