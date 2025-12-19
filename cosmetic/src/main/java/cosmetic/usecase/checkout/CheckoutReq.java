package cosmetic.usecase.checkout;

public class CheckoutReq {
	private Long userId; 
	private String address; 
	private String phone; 
	private String paymentMethod;
	
	public CheckoutReq(Long userId, String address, String phone, String paymentMethod) { 
		this.userId = userId; 
		this.address = address; 
		this.phone = phone; 
		this.paymentMethod = paymentMethod;
		
}
		
		public Long getUserId() { return userId; } 
		public String getAddress() { return address; } 
		public String getPhone() { return phone; } 
		public String getPaymentMethod() { return paymentMethod; }
	
	

}
