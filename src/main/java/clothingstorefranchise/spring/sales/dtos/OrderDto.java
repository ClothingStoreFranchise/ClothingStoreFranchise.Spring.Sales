package clothingstorefranchise.spring.sales.dtos;

import java.time.LocalDateTime;

import clothingstorefranchise.spring.common.types.IEntityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements IEntityDto<Long> {
	private Long id;
	
	private LocalDateTime date;
	
	private Long customerId;
	
	private String address;
	
	private String card;

	@Override
	public Long key() {
		return id;
	}
}
