import shop.ShoppingCart

data class Order(
    val shoppingCart: ShoppingCart,
    val address: Address
)