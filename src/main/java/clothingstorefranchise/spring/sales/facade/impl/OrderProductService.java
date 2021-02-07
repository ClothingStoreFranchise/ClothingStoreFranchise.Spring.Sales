package clothingstorefranchise.spring.sales.facade.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import clothingstorefranchise.spring.common.exceptions.InvalidDataException;
import clothingstorefranchise.spring.sales.dtos.OrderProductBaseDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductWithOrderDto;
import clothingstorefranchise.spring.sales.facade.IOrderProductService;
import clothingstorefranchise.spring.sales.model.OrderProduct;
import clothingstorefranchise.spring.sales.repositories.IOrderProductRepository;

@Service
public class OrderProductService extends BaseService<OrderProduct, Long, IOrderProductRepository, OrderProductBaseDto> implements IOrderProductService {
	
	public OrderProductService(IOrderProductRepository orderProductRepository) {
		super(OrderProduct.class, orderProductRepository);
	}
	
	public void update(List<OrderProductBaseDto> dtos) {		
		super.updateBase(dtos);
	}
	
	public OrderProductWithOrderDto update(OrderProductWithOrderDto orderProductDto) {
		validationActions(orderProductDto);
		
		OrderProduct orderProduct = super.updateBase(orderProductDto);
		return map(orderProduct, OrderProductWithOrderDto.class);
	}
	
	public List<OrderProductWithOrderDto> loadByState(int state){
		List<OrderProduct> orderProducts = repository.findByState(state);
		return mapList(orderProducts, OrderProductWithOrderDto.class);
	}
	
	public List<OrderProductWithOrderDto> loadByWarehouseId(Long warehouseId){
		List<OrderProduct> orderProducts = repository.findByWarehouseId(warehouseId);
		return mapList(orderProducts, OrderProductWithOrderDto.class);
	}
	
	public List<OrderProductDto> loadByWarehouseIdAndState(Long warehouseId, int state){
		List<OrderProduct> orderProducts = repository.findByWarehouseIdAndState(warehouseId, state);
		return mapList(orderProducts, OrderProductDto.class);
	}

	protected boolean isValid(OrderProductBaseDto dto) {
		return nullValidation(dto) && numericValidation(dto);
	}
	
	@Override
	protected void validationActions(OrderProductBaseDto dto) {
		if(!isValid(dto)) 
			throw new InvalidDataException("Invalid data");
	}
	
	private static boolean nullValidation(OrderProductBaseDto dto) {
		return dto != null
			&& dto.getId() != null
			&& dto.getProductId() != null;
	}
	
	private static boolean numericValidation(OrderProductBaseDto dto) {
		return dto.getQuantity() > 0;
	}
}
