package com.twa.flights.api.reservation.controller;

import com.twa.flights.api.reservation.controller.resource.ReservationResource;
import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.dto.SearchReservationCriteriaDTO;
import com.twa.flights.api.reservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class ReservationController implements ReservationResource {

    private Logger lo = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations(SearchReservationCriteriaDTO criteria) {
        lo.info("Obtain all the reservations");
        List<ReservationDTO> response = service.getReservations(criteria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        lo.info("Obtain information from a reservation with {}", id);
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservation) {
        lo.info("Saving new reservation");
        ReservationDTO response = service.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        lo.info("Updating a reservation with {}", id);
        ReservationDTO response = service.update(id, reservation);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lo.info("Deleting a reservation with {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
