class StockUnit (var quantity : Int, var daysBeforeExpiration : Int ) {
    val isExpired = daysBeforeExpiration <= 0
    val isExpiringSoon = daysBeforeExpiration <= 5 && !isExpired
}