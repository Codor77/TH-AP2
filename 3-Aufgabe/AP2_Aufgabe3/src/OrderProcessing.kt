import products.Product

class OrderProcessing {
    /*** Basisstruktur für verkettete Liste ***/
    // Erstes Element der verketten Liste
    var first: OrderNode? = null
    var last: OrderNode? = null

    // Ein Knoten der verketteten Liste
    data class OrderNode(val order: Order, var next: OrderNode?)

    /*** Custom ***/
    fun print() {
        if (!isEmpty) {
            var node: OrderNode = first!!
            var counter: Int = 1
            println("${counter}: ${node.order.totalPrice}")
            while (node.next != null) {
                counter += 1
                node = node.next!!
                println("${counter}: ${node.order.totalPrice}")
            }
        }
        else
            println("empty")
    }

    /*** Eigenschaften ***/
    // ist die Liste leer?
    val isEmpty: Boolean
        get() = first == null

    // Sind die Items absteigend sortiert?
    fun isSorted(): Boolean {
        if (!isEmpty) {
            var node: OrderNode = first!!
            var lastTotalPrice: Double = node.order.totalPrice
            while (node.next != null) {
                node = node.next!!
                if(lastTotalPrice < node.order.totalPrice)
                    return false
            }
            return true
        }
        return true
    }

    // Berechnet den Gesamtwert aller Bestellungen
    val totalVolume: Double
        get() {
            if (!isEmpty) {
                var node: OrderNode = first!!
                var returnValue: Double = node.order.totalPrice
                while (node.next != null) {
                    node = node.next!!
                    returnValue += node.order.totalPrice
                }
                return returnValue
            }
            return 0.0
        }

    // Anzahl der Bestellungen
    val size: Int
        get() {
            if (!isEmpty) {
                var node: OrderNode = first!!
                var returnValue: Int = 1
                while (node.next != null) {
                    returnValue += 1
                    node = node.next!!
                }
                return returnValue
            }
            return 0
        }

    // ** Funktionen zum Einfügen **

    // Bestellung hinten anhängen
    fun append(order: Order) {
        if (isEmpty) {
            first = OrderNode(order, null)
            last = first
        }
        else {
            last!!.next = OrderNode(order, null)
            last = last!!.next
        }
    }

    // Sortiert die Bestellung ein. Details siehe Aufgabentext
    fun insertBeforeSmallerVolumes(order: Order) {
        if (!isEmpty) {
            var node: OrderNode = first!!
            if (node.order.totalPrice < order.totalPrice){
                first = OrderNode(order, first)
            }
            else {
                while (node.next != null && node.next!!.order.totalPrice > order.totalPrice) {
                    node = node.next!!
                }
                node.next = OrderNode(order, node.next)
                if (node.next!!.next == null)
                    last = node.next
            }
        }
        else
            append(order)
    }

    // Sortiert nach Auftragsvolumen
    fun sortyByVolume() {
        if (!isEmpty) {
            var oldFirst: OrderNode = first!!
            first = null
            last = null
            var node: OrderNode = oldFirst!!
            append(node.order)
            while (node.next != null) {
                node = node.next!!
                insertBeforeSmallerVolumes(node.order)
            }
        }
    }

    // Funktionen zum Verarbeiten der Liste

    // Verarbeitet die erste Bestellung und entfernt diese aus der Liste
    fun processFirst() {
        // TODO
    }

    // Vearbeitet die Bestellung mit dem höchsten Auftragsvolumen
    // und entfernt diese aus der Liste
    fun processHighest() {
        // TODO
    }

    // Verarbeitet alle Aufträge für die Stadt in einem Rutsch
    // und entfernt diese aus der Lite
    fun processAllFor(city: String) {
        // TODO
    }

    // Verarbeite alle Bestellungen. Die Liste ist danach leer.
    fun processAll() {
        // TODO
    }

    // ** Funktionen zum Analysieren**

    // Analysiert alle order mit der analyzer Funktion
    fun analyzeAll(analyzer: (Order) -> String): String = "" // TODO

    // Prüft, ob für ein Produkt einer der Bestellungen
    // die predicate Funktion erfüllt wird
    fun anyProduct(predicate: (Product) -> Boolean): Boolean = false // TODO

    // Erzeugt ein neues OrderProcessing Objekt, in dem nur noch
    // Order enthalten, für die die predicate Funktion true liefert
    fun filter(predicate: (Order) -> Boolean): OrderProcessing = this // TODO
}