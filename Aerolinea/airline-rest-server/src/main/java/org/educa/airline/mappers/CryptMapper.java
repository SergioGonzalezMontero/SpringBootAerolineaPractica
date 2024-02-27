package org.educa.airline.mappers;


import org.educa.airline.dto.CryptDTO;
import org.educa.airline.entity.CryptEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CryptMapper {
    public CryptEntity toEntity(CryptDTO cryptDTO){
        CryptEntity product = new CryptEntity();
        product.setMessage(cryptDTO.getMessage());
        return product;
    }

    public CryptDTO toDTO(CryptEntity cryptEntity){
        CryptDTO cryptDTO = new CryptDTO();
        cryptDTO.setMessage(cryptEntity.getMessage());
        return cryptDTO;
    }

    public List<CryptDTO> toDTOs(List<CryptEntity> productEntities){
        List<CryptDTO> cryptDTOS = new ArrayList<>();
        for (CryptEntity crypt: productEntities) {
            CryptDTO cryptDTO = toDTO(crypt);
            cryptDTOS.add(cryptDTO);
        }
        return cryptDTOS;
    }

}
