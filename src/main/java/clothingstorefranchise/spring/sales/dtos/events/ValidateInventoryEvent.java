package clothingstorefranchise.spring.sales.dtos.events;

import java.util.List;

import clothingstorefranchise.spring.common.event.IntegrationEvent;
import clothingstorefranchise.spring.sales.dtos.OrderProductBaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateInventoryEvent extends IntegrationEvent {
	
	private List<OrderProductBaseDto> orderProducts;
}
