package clothingstorefranchise.spring.sales.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import clothingstorefranchise.spring.sales.definitions.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="order_products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderProduct {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
    @Column(insertable = true)
	private Long id;
	
	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String pictureUrl;
	
	@Column(nullable = false)
	private double unitPrice;
	
	@Column(nullable = false)
	private int size;
	
	@Column(nullable = false)
	private int clothingSizeType;
	
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	@Builder.Default
	private int state = OrderState.PENDING;
	
	@Column
	private Long warehouseId;
    
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable=false, updatable=false)
    private Order order;
	/*
    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName="id")
	private Order order;
	*/
}