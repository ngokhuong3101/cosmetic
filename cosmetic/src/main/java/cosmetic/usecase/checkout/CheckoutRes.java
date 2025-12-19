package cosmetic.usecase.checkout;

public class CheckoutRes {

	    private Long orderId;
	    private double totalAmount;
	    private String status;

	    public CheckoutRes(Long orderId, double totalAmount, String status) {
	        this.orderId = orderId;
	        this.totalAmount = totalAmount;
	        this.status = status;
	    }

	    public Long getOrderId() { return orderId; }
	    public double getTotalAmount() { return totalAmount; }
	    public String getStatus() { return status; }
	    
}




