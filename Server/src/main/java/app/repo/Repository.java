package app.repo;


import app.model.Entity;

import java.util.List;

public interface Repository<ID, E extends Entity> {
    void add(E elem);
    List<E> findAll();
}
