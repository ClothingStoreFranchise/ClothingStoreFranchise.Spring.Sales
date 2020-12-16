package clothingstorefranchise.spring.sales.facade;

import java.util.List;

import clothingstorefranchise.spring.sales.dtos.OrderProductBaseDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductWithOrderDto;

public interface IOrderProductService {
	
	void update(List<OrderProductBaseDto> dtos);
	
	List<OrderProductWithOrderDto> loadByState(int state);
	
	List<OrderProductDto> loadByWarehouseId(Long warehouseId);
	
	List<OrderProductDto> loadByWarehouseIdAndState(Long warehouseId, int state);
}
