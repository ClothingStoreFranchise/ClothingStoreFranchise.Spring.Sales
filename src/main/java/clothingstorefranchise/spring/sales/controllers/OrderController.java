package clothingstorefranchise.spring.sales.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clothingstorefranchise.spring.sales.dtos.OrderDto;
import clothingstorefranchise.spring.sales.dtos.OrderWithOrderProductsDto;
import clothingstorefranchise.spring.sales.facade.IOrderService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/orders")
@Api(value = "Endpoints to manage Orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<OrderDto>> loadAll() {
		return ResponseEntity.ok(orderService.loadAll());
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<List<OrderWithOrderProductsDto>> loadByCustomerId(@PathVariable Long customerId) {
		return ResponseEntity.ok(orderService.loadByCustomerId(customerId));
	}
	
	@PostMapping
	public ResponseEntity<OrderWithOrderProductsDto> create(@Valid @RequestBody OrderWithOrderProductsDto orderDto) {
		return new ResponseEntity<>(orderService.create(orderDto), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<OrderDto> update(@Valid @RequestBody OrderDto orderDto) {
		return ResponseEntity.ok(orderService.update(orderDto));
	}
}
