package clothingstorefranchise.spring.sales.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clothingstorefranchise.spring.sales.dtos.OrderProductDto;
import clothingstorefranchise.spring.sales.dtos.OrderProductWithOrderDto;
import clothingstorefranchise.spring.sales.facade.IOrderProductService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/order-products")
@Api(value = "Endpoints to manage OrderProduct")
public class OrderProductController {
	
	@Autowired
	private IOrderProductService orderProductService;
	
	@PutMapping
	public ResponseEntity<OrderProductWithOrderDto> update(@Valid @RequestBody OrderProductWithOrderDto orderDto) {
		return ResponseEntity.ok(orderProductService.update(orderDto));
	}
	
	@GetMapping("/state/{stateId}")
	public ResponseEntity<List<OrderProductWithOrderDto>> loadByState(@PathVariable int stateId) {
		return ResponseEntity.ok(orderProductService.loadByState(stateId));
	}
	
	@GetMapping("/warehouses/{warehouseId}")
	public ResponseEntity<List<OrderProductWithOrderDto>> loadByWarehouseId(@PathVariable Long warehouseId) {
		return ResponseEntity.ok(orderProductService.loadByWarehouseId(warehouseId));
	}
	
	@GetMapping("/warehouses/{warehouseId}/state/{stateId}")
	public ResponseEntity<List<OrderProductDto>> loadByWarehouseIdAndState(@PathVariable Long warehouseId, @PathVariable int stateId) {
		return ResponseEntity.ok(orderProductService.loadByWarehouseIdAndState(warehouseId, stateId));
	}
}
