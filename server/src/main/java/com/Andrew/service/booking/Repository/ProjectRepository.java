package com.Andrew.service.booking.Repository;


import com.Andrew.service.booking.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, ListCrudRepository<Project, Long> {
   List<Project> findAll();
   Optional<Project> findById(long id);
}

