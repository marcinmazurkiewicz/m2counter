package dev.mazurkiewicz;

public interface EntityMapper<T, U, W> {

    T mapRequestToEntity(U request);
    W mapEntityToResponse(T entity);

}
