package com.mc.mc1.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface McMessageRepository extends JpaRepository<McMessageEntity, Long> {
}
