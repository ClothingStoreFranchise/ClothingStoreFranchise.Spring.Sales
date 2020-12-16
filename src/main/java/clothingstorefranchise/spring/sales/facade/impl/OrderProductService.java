package clothingstorefranchise.spring.sales.facade.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import clothingstorefranchise.spring.sales.dtos.OrderProductBaseDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductWithOrderDto;
import clothingstorefranchise.spring.sales.facade.IOrderProductService;
import clothingstorefranchise.spring.sales.model.OrderProduct;
import clothingstorefranchise.spring.sales.repositories.IOrderProductRepository;

@Service
public class OrderProductService extends BaseService<OrderProduct, Long, IOrderProductRepository> implements IOrderProductService {
	
	public OrderProductService(IOrderProductRepository orderProductRepository) {
		super(OrderProduct.class, orderProductRepository);
	}
	
	public void update(List<OrderProductBaseDto> dtos) {
		super.updateBase(dtos);
	}
	
	public List<OrderProductWithOrderDto> loadByState(int state){
		List<OrderProduct> orderProducts = repository.findByState(state);
		return mapList(orderProducts, OrderProductWithOrderDto.class);
	}
	
	public List<OrderProductDto> loadByWarehouseId(Long warehouseId){
		List<OrderProduct> orderProducts = repository.findByWarehouseId(warehouseId);
		return mapList(orderProducts, OrderProductDto.class);
	}
	
	public List<OrderProductDto> loadByWarehouseIdAndState(Long warehouseId, int state){
		List<OrderProduct> orderProducts = repository.findByWarehouseIdAndState(warehouseId, state);
		return mapList(orderProducts, OrderProductDto.class);
	}
}
