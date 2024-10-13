package com.devteria.identityservice.service;

import com.devteria.identityservice.entity.Table;
import com.devteria.identityservice.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;
    public List<Table> getTableAvailable() {
        return tableRepository.findTableIsAvailable(true);
    }
    public Table bookTable(Long tableId, LocalDateTime reservationTime) {
        Table table = tableRepository.findById(tableId).orElseThrow(()
                -> new IllegalStateException("Table not found"));
        if(table.isAvailable()){
            table.setAvailable(false);
            table.setReservationTime(reservationTime);
            return tableRepository.save(table);
        }else{
            throw new IllegalStateException("Table is already booked");
        }
    }

    public void cancelReservation(Long tableId) {
        Table table = tableRepository.findById(tableId).orElseThrow(()
                -> new IllegalStateException("Table not found"));
        table.setAvailable(true);
        table.setReservationTime(null);
        tableRepository.save(table);
    }
}
