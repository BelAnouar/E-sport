package org.esport.Repository.base;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract   class BaseRepository<T> implements GenericRepository<T, Integer> {
    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    @Transactional
    public T save(T entity) {

            entityManager.persist(entity);
            return entity;

    }

    @Override
    public Optional<T> findById(Integer id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }



    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }


    @Override
    @Transactional
    public T update(T entity) {
       return entityManager.merge(entity);
    }
}
