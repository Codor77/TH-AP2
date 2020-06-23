package products

class DiscountProduct (productName : String, basePrice : Double, description : String, private val pSalesPrice : Double, val discount : DiscountType) : Product(productName, basePrice, pSalesPrice, description) {

    override val salesPrice: Double
        get() = pSalesPrice - (pSalesPrice * discount.discountFactor)
}