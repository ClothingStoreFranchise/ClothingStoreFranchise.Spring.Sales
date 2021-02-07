package clothingstorefranchise.spring.sales.facade.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clothingstorefranchise.spring.common.exceptions.InvalidDataException;
import clothingstorefranchise.spring.sales.RabbitMqConfig;
import clothingstorefranchise.spring.sales.dtos.OrderDto;
import clothingstorefranchise.spring.sales.dtos.OrderWithOrderProductsDto;
import clothingstorefranchise.spring.sales.dtos.events.ValidateInventoryEvent;
import clothingstorefranchise.spring.sales.facade.IOrderService;
import clothingstorefranchise.spring.sales.model.Order;
import clothingstorefranchise.spring.sales.model.OrderProduct;
import clothingstorefranchise.spring.sales.repositories.IOrderRepository;
import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

@Service
public class OrderService extends BaseService<Order, Long, IOrderRepository, OrderWithOrderProductsDto> implements IOrderService {
	public static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private IntegrationEventLogService integrationEventService;
	
	public OrderService(IOrderRepository orderRepository) {
		super(Order.class, orderRepository);
	}
	
	@Transactional
	public OrderWithOrderProductsDto create(OrderWithOrderProductsDto orderDto) {
		orderDto.setDate(LocalDateTime.now());
		
		validationActions(orderDto);
		
		Order order = map(orderDto, Order.class);
		for(OrderProduct product : order.getOrderProducts()) {
			product.setOrder(order);
		}
		Order orderCreated = repository.save(order);
		
		ValidateInventoryEvent validateInventoryEvent = map(orderCreated, ValidateInventoryEvent.class);
		integrationEventService.saveEvent(validateInventoryEvent);
		sendMessage(validateInventoryEvent);
		return map(order, OrderWithOrderProductsDto.class);
	}
	
	public OrderDto update(OrderDto orderDto) {
		Order order = super.updateBase(orderDto);
		return map(order, OrderDto.class);
	}
	
	public List<OrderDto> loadAll() {
		List<Order> orders = super.loadAllBase();
		return mapList(orders, OrderDto.class);
	}
	
	public OrderDto load(Long id) {
		Order order = super.loadBase(id);
		return map(order, OrderDto.class);
	}
	
	public List<OrderWithOrderProductsDto> loadByCustomerId(Long customerId) {
		List<Order> orders = repository.findByCustomerId(customerId);
		return mapList(orders, OrderWithOrderProductsDto.class);
	}
	
	private void sendMessage(ValidateInventoryEvent validateInventoryEvent) {
        UUID correlationId = UUID.randomUUID();
        
    	MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setReplyTo(RabbitMqConfig.QUEUE);
            messageProperties.setCorrelationId(correlationId.toString());
            return message;
        };
        
        template.convertAndSend(RabbitMqConfig.EXCHANGE_NAME,
        		ValidateInventoryEvent.class.getSimpleName(),
                validateInventoryEvent,
                messagePostProcessor);
	}

	protected boolean isValid(OrderWithOrderProductsDto dto) {
		return nullValidation(dto);
	}
	
	@Override
	protected void validationActions(OrderWithOrderProductsDto dto) {
		if(!isValid(dto)) 
			throw new InvalidDataException("Invalid data");
	}
	
	private static boolean nullValidation(OrderWithOrderProductsDto dto) {
		return dto != null
			&& dto.getCustomerId() != null
			&& !dto.getOrderProducts().isEmpty()
			&& !StringUtils.isAnyBlank(dto.getCard())
			&& !StringUtils.isAnyBlank(dto.getAddress());
	}
}
