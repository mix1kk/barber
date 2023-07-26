package com.mycompany.barber.Utils.Mappers;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.Models.Line;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LineMapper {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static LineDTO mapToLineDTO(Line line) {
        LineDTO lineDTO = new LineDTO();
        lineDTO.setLineId(line.getLineId());
        lineDTO.setUserId(line.getUserId());
        lineDTO.setDate(line.getDate().format(FORMATTER).toString());
        lineDTO.setTime(line.getTime());
        lineDTO.setClientName(line.getClientName());
        lineDTO.setProcedureName(line.getProcedureName());
        lineDTO.setProcedureCost(line.getProcedureCost());
        lineDTO.setProcedureDiscount(line.getProcedureDiscount());
        lineDTO.setComment(line.getComment());
        return lineDTO;
    }
    public static Line mapToLine(LineDTO lineDTO) {
        Line line = new Line();
        line.setLineId(lineDTO.getLineId());
        line.setUserId(lineDTO.getUserId());
        line.setDate(LocalDate.parse(lineDTO.getDate(), FORMATTER));
        line.setTime(lineDTO.getTime());
        line.setClientName(lineDTO.getClientName());
        line.setProcedureName(lineDTO.getProcedureName());
        line.setProcedureCost(lineDTO.getProcedureCost());
        line.setProcedureDiscount(lineDTO.getProcedureDiscount());
        line.setComment(lineDTO.getComment());
        return line;
    }
}
