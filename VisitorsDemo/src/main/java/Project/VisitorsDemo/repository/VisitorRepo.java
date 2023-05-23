package Project.VisitorsDemo.repository;

import Project.VisitorsDemo.model.Visitors;

import java.util.Optional;

public interface VisitorRepo {
    public default Iterable<Object> findAll() {
        return null;
    }

    public default Optional<Visitors> findById(int id) {
        return null;
    }

    public default Visitors save(Visitors visitors) {
        return null;
    }

    public default void deleteById(int id) {
    }
}
