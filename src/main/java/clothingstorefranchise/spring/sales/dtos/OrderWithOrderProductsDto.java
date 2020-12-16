package clothingstorefranchise.spring.sales.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderWithOrderProductsDto extends OrderDto {
	
	private List<OrderProductDto> orderProducts;
}
