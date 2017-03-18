package com.perepelitsya.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Andriu on 18.03.2017.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Transactional
    void deleteByDone(boolean done);
}
