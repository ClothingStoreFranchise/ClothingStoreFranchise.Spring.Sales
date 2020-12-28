package clothingstorefranchise.spring.sales.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductWithOrderDto extends OrderProductDto {
	private OrderDto order;
}
