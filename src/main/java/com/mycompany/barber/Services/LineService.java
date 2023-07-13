package com.mycompany.barber.Services;

import com.mycompany.barber.Models.HttpMessages.Line;
import com.mycompany.barber.Repository.LineRepository;
import com.mycompany.barber.Utils.Line.LineNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LineService {
    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }
    public List<Line> findAll(int userId) {
        return lineRepository.findByUserId(userId);
    }

    public Line findById(Integer lineId) {
        return lineRepository.findById(lineId).orElseThrow(LineNotFoundException::new);
    }

    public void save(Line line) {
        lineRepository.save(line);
    }

    public void update(int id,Line line) {
        line.setLineId(id);
        lineRepository.save(line);
    }

    public void delete(int id) {
        lineRepository.deleteById(id);
    }
}
