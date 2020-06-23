import shop.ShoppingCart

data class Order(val shoppingCart: ShoppingCart, val address: Address) {
    val totalPrice: Double
        get() = shoppingCart.totalPrice
}