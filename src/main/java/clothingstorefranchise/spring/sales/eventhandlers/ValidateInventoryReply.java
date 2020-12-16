package clothingstorefranchise.spring.sales.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clothingstorefranchise.spring.sales.RabbitMqConfig;
import clothingstorefranchise.spring.sales.dtos.events.ValidateInventoryEvent;
import clothingstorefranchise.spring.sales.facade.IOrderProductService;

@Service
public class ValidateInventoryReply {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ValidateInventoryReply.class);

	@Autowired
	private IOrderProductService orderProductService;
	
	@RabbitListener(queues = RabbitMqConfig.QUEUE, concurrency = "10")
	 public void validateInventory(ValidateInventoryEvent validateInventoryEvent, Message message) {
			orderProductService.update(validateInventoryEvent.getOrderProducts());
	}
}
