package fr.hackinghealth.repository;

import fr.hackinghealth.domain.Stock;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Stock entity.
 */
public interface StockRepository extends JpaRepository<Stock,Long> {

}
