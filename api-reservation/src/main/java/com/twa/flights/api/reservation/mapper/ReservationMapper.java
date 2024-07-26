package com.twa.flights.api.reservation.mapper;

import com.twa.flights.api.reservation.dto.ReservationDTO;
import com.twa.flights.api.reservation.model.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends Converter<Reservation, ReservationDTO> {

    @Override
    ReservationDTO convert(Reservation source);

}