package clothingstorefranchise.spring.sales.definitions;

public final class OrderState {
	public static final int PENDING = 0;
	public static final int CONFIRMED = 1;
	public static final int PREPARED = 2;
	public static final int ON_THE_WAY = 3;
	public static final int DELIVERED = 4;
	public static final int CANCELLED = 5;
	
	private OrderState() { }
}
