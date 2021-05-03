package io.github.ksamodol.oglasnikbackend.repository;

import io.github.ksamodol.oglasnikbackend.entity.location.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
    public List<County> findAll();
}
