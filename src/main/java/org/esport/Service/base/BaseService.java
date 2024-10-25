package org.esport.Service.base;

import org.esport.Repository.base.GenericRepository;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.ValidationException;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    protected final GenericRepository<T,Integer> repository;

    protected BaseService(GenericRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<T> findById(Integer id) {
        try {

            return repository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error finding entity with id: {}", id, e);
            throw new ServiceException("Error finding entity", e);
        }
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error finding all entities", e);
            throw new ServiceException("Error retrieving all entities", e);
        }
    }

    @Transactional
    public void save(T entity) {
        try {

            repository.save(entity);
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error saving entity: {}", entity, e);
            throw new ServiceException("Error saving entity", e);
        }
    }
    @Transactional
    public void update(T entity) {
        try {

            repository.update(entity);
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Error updating entity: {}", entity, e);
            throw new ServiceException("Error updating entity", e);
        }
    }


    @Transactional
    public void delete(Integer id) {
        try {

            if (!repository.existsById(id)) {
                throw new ServiceException("Entity with id " + id + " not found");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Error deleting entity with id: {}", id, e);
            throw new ServiceException("Error deleting entity", e);
        }
    }

    @Transactional(readOnly = true)
    public boolean exists(Integer id) {
        try {

            return repository.existsById(id);
        } catch (Exception e) {
            LOGGER.error("Error checking existence of entity with id: {}", id, e);
            throw new ServiceException("Error checking entity existence", e);
        }
    }

    @Transactional(readOnly = true)
    public long count() {
        try {
            return repository.count();
        } catch (Exception e) {
            LOGGER.error("Error counting entities", e);
            throw new ServiceException("Error counting entities", e);
        }
    }


}
