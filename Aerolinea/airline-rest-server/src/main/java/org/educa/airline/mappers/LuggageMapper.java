package org.educa.airline.mappers;

import org.educa.airline.dto.FlightDTO;
import org.educa.airline.dto.LuggageDTO;
import org.educa.airline.entity.Luggage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LuggageMapper {

    public Luggage toEntity(LuggageDTO luggageDTO){
        Luggage luggage = new Luggage();
        luggage.setId(luggageDTO.getId());
        luggage.setNif(luggageDTO.getNif());
        luggage.setFlightId(luggageDTO.getFlightId());
        luggage.setDescription(luggageDTO.getDescription());
        return luggage;
    }

    public LuggageDTO toDTO(Luggage luggage){
        LuggageDTO luggageDTO = new LuggageDTO();
        luggageDTO.setId(luggage.getId());
        luggageDTO.setNif(luggage.getNif());
        luggageDTO.setFlightId(luggage.getFlightId());
        luggageDTO.setDescription(luggage.getDescription());
        return luggageDTO;
    }

    public List<LuggageDTO> toDTOs(List<Luggage> luggageList){
        List<LuggageDTO> luggageDTOs = new ArrayList<>();
        for(Luggage luggage: luggageList){
            LuggageDTO luggageDTO = toDTO(luggage);
            luggageDTOs.add(luggageDTO);
        }
        return luggageDTOs;
    }

}
