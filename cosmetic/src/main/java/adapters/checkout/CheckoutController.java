package adapters.checkout;

import cosmetic.entities.Order;
import cosmetic.entities.User;
import cosmetic.usecase.checkout.CheckoutUseCase;

public class CheckoutController {
	private final CheckoutUseCase checkoutUseCase; 
	private final CheckoutPresenter presenter;
	
	public CheckoutController(CheckoutUseCase checkoutUseCase, CheckoutPresenter presenter) {
		this.checkoutUseCase = checkoutUseCase; 
		this.presenter = presenter;
	}
	
	public CheckoutViewModel handle(Long userId, User user, String address, String phone, String paymentMethod) {
		try {
			Order order = checkoutUseCase.execute(userId, user, address, phone, paymentMethod);
			presenter.present(order);
		} catch (Exception e) {
			presenter.presentError(e.getMessage());
		}
		return presenter.getViewModel();
	}
}
