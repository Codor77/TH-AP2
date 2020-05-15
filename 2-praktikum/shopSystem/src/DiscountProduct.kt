class DiscountProduct (productName : String, basePrice : Double, description : String, private val pSalesPrice : Double, val discount : DiscountType) : Product (productName, basePrice, description, pSalesPrice) {

    override val salesPrice: Double
        get() = pSalesPrice - (pSalesPrice * discount.discountFactor)
}