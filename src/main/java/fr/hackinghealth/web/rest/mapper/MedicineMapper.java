package fr.hackinghealth.web.rest.mapper;

import fr.hackinghealth.domain.*;
import fr.hackinghealth.web.rest.dto.MedicineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Medicine and its DTO MedicineDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicineMapper {

    @Mapping(source = "medicineChest.id", target = "medicineChestId")
    MedicineDTO medicineToMedicineDTO(Medicine medicine);

    @Mapping(source = "medicineChestId", target = "medicineChest")
    Medicine medicineDTOToMedicine(MedicineDTO medicineDTO);

    default MedicineChest medicineChestFromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicineChest medicineChest = new MedicineChest();
        medicineChest.setId(id);
        return medicineChest;
    }
}
