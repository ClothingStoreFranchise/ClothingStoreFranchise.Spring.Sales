package clothingstorefranchise.spring.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import clothingstorefranchise.spring.sales.model.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomerId(Long id);
}
