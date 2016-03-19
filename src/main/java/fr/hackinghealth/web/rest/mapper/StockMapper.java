package fr.hackinghealth.web.rest.mapper;

import fr.hackinghealth.domain.*;
import fr.hackinghealth.web.rest.dto.StockDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Stock and its DTO StockDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StockMapper {

    @Mapping(source = "medicine.id", target = "medicineId")
    StockDTO stockToStockDTO(Stock stock);

    @Mapping(source = "medicineId", target = "medicine")
    Stock stockDTOToStock(StockDTO stockDTO);

    default Medicine medicineFromId(Long id) {
        if (id == null) {
            return null;
        }
        Medicine medicine = new Medicine();
        medicine.setId(id);
        return medicine;
    }
}
