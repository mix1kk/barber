package com.mycompany.barber.Repository;

import com.mycompany.barber.Models.HttpMessages.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LineRepository extends JpaRepository<Line, Integer> {
    List<Line> findByUserId(int userId);
}
