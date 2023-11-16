package com.backcomerideal.repository;

import com.backcomerideal.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, String> {
   // List<Local> findAll();
}
