package fr.hackinghealth.web.rest.mapper;

import fr.hackinghealth.domain.*;
import fr.hackinghealth.web.rest.dto.MedicineChestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MedicineChest and its DTO MedicineChestDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicineChestMapper {

    MedicineChestDTO medicineChestToMedicineChestDTO(MedicineChest medicineChest);

    MedicineChest medicineChestDTOToMedicineChest(MedicineChestDTO medicineChestDTO);
}
