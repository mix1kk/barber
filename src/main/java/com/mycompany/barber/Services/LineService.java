package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Line;
import com.mycompany.barber.Models.User;
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
    private final UserService userService;

    @Autowired
    public LineService(LineRepository lineRepository, UserService userService) {
        this.lineRepository = lineRepository;
        this.userService = userService;
    }
    public List<Line> findAllForUser(int userId) {
        return lineRepository.findByUserId(userId);
    }

    public Line findById(Integer lineId) {
        return lineRepository.findById(lineId).orElseThrow(LineNotFoundException::new);
    }

    public void save(Line line) {
        line.setUserCompany(userService.findById(line.getUserId()).getUserCompany());
        fillLine(line);
        lineRepository.save(line);
    }

    public void update(int id,Line line) {
        line.setLineId(id);
        lineRepository.save(line);
    }

    public void delete(int id) {
        lineRepository.deleteById(id);
    }
    private void fillLine(Line line){
        line.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        line.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        line.setUpdatedBy("User");//TODO: сделать запись имени того, кто изменил поле
        line.setSpare1("spare1");
        line.setSpare2("spare2");
    }
}
