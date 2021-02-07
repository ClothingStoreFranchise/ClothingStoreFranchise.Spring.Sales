package clothingstorefranchise.spring.sales.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import clothingstorefranchise.spring.sales.model.IntegrationEventLog;

public interface IIntegrationEventRepository extends JpaRepository<IntegrationEventLog, UUID>{
	List<IntegrationEventLog> findByEventIdAndStateOrderByCreationTimeDesc(UUID eventId, int state);
}
