package clothingstorefranchise.spring.sales.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDto extends OrderProductBaseDto {

	private String name;
	
	private String pictureUrl;
	
	private double unitPrice;
	
	private int clothingSizeType;
}
