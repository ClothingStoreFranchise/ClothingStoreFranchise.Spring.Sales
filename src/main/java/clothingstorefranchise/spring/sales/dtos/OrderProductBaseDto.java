package clothingstorefranchise.spring.sales.dtos;

import clothingstorefranchise.spring.common.types.IEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductBaseDto implements IEntityDto<Long> {
	
	private Long id;
	
	private Long productId;
	
	private int size;

	private int quantity;
	
	private int state;
	
	private Long warehouseId;

	@Override
	public Long key() {
		return id;
	}
}
