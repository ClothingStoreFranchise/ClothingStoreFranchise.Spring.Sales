package clothingstorefranchise.spring.sales.facade;

import java.util.List;

import clothingstorefranchise.spring.sales.dtos.OrderDto;
import clothingstorefranchise.spring.sales.dtos.OrderWithOrderProductsDto;

public interface IOrderService {
		
	OrderWithOrderProductsDto create(OrderWithOrderProductsDto orderDto);
	
	OrderDto update(OrderDto orderDto);
	
	OrderDto load(Long id);
	
	List<OrderWithOrderProductsDto> loadByCustomerId(Long customerId);
	
	List<OrderDto> loadAll();
}
