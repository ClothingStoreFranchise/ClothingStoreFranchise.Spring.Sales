package clothingstorefranchise.spring.sales.dtos.events;

import java.util.List;

import clothingstorefranchise.spring.sales.dtos.OrderProductBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateInventoryEvent {
	
	private List<OrderProductBaseDto> orderProducts;
}
