package clothingstorefranchise.spring.sales.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import clothingstorefranchise.spring.sales.model.OrderProduct;

public interface IOrderProductRepository extends JpaRepository<OrderProduct, Long> {
	List<OrderProduct> findByState(int state);
	
	List<OrderProduct> findByWarehouseId(Long warehouseId);
	
	List<OrderProduct> findByWarehouseIdAndState(Long warehouseId, int state);
}
