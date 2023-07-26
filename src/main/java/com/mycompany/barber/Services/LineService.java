package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Line;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Repository.LineRepository;
import com.mycompany.barber.Utils.Line.LineNotDeletedException;
import com.mycompany.barber.Utils.Line.LineNotFoundException;
import com.mycompany.barber.Utils.User.UserNotDeletedException;
import com.mycompany.barber.Utils.User.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Line> findByUserIdAndDateBetween(int userId, LocalDate fromDate, LocalDate toDate) {
        return lineRepository.findByUserIdAndDateBetween(userId, fromDate,toDate);
    }
    public Line findById(Integer lineId) {
        return lineRepository.findById(lineId).orElseThrow();
    }
    public void save(Line line) {
        fillLine(line);
        line.setUserCompany(userService.findById(line.getUserId()).getUserCompany());
        lineRepository.save(line);
    }

    public void update(int id, Line line) {
        String createdAt = lineRepository.findById(id).orElseThrow().getCreatedAt();
        fillLine(line);
        line.setCreatedAt(createdAt);
        line.setLineId(id);
        lineRepository.save(line);
    }

    public void delete(int id) {
        if (!lineRepository.existsById(id)) {
            throw new LineNotDeletedException("Не существует записи");
        }
        lineRepository.deleteById(id);
        if (lineRepository.existsById(id)) {
            throw new LineNotDeletedException("Не удалось удалить запись");
        }
        lineRepository.deleteById(id);
    }

    private void fillLine(Line line) {
        line.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        line.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        line.setUpdatedBy("User");//TODO: сделать запись имени того, кто изменил поле
        line.setSpare1("spare1");
        line.setSpare2("spare2");
    }
}
