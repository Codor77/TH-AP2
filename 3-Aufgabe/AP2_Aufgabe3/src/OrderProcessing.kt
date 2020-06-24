import products.Product
import java.lang.IllegalStateException

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
        } else
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
                if (lastTotalPrice < node.order.totalPrice)
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
        } else {
            last!!.next = OrderNode(order, null)
            last = last!!.next
        }
    }

    // Sortiert die Bestellung ein. Details siehe Aufgabentext
    fun insertBeforeSmallerVolumes(order: Order) {
        if (!isEmpty) {
            var node: OrderNode = first!!
            if (node.order.totalPrice < order.totalPrice) {
                first = OrderNode(order, first)
            } else {
                while (node.next != null && node.next!!.order.totalPrice > order.totalPrice) {
                    node = node.next!!
                }
                node.next = OrderNode(order, node.next)
                if (node.next!!.next == null)
                    last = node.next
            }
        } else
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
        if (!isEmpty) {
            first!!.order.shoppingCart.buyEverything()
            first = first!!.next
        }
    }

    // Vearbeitet die Bestellung mit dem höchsten Auftragsvolumen
    // und entfernt diese aus der Liste
    fun processHighest() {
        if (!isEmpty) {
            var node = first!!
            var nodeBeforeHighest: OrderNode? = null
            var highestVolume = node.order.totalPrice

            while (node.next != null) {
                if (highestVolume < node.next!!.order.totalPrice) {
                    nodeBeforeHighest = node
                    highestVolume = node.next!!.order.totalPrice
                } else
                    node = node.next!!
            }
            if (nodeBeforeHighest == null)
                processFirst()
            else {
                nodeBeforeHighest.next!!.order.shoppingCart.buyEverything()
                nodeBeforeHighest.next = nodeBeforeHighest.next!!.next
            }
        }
    }

    // Verarbeitet alle Aufträge für die Stadt in einem Rutsch
    // und entfernt diese aus der Lite
    fun processAllFor(city: String) {
        if (!isEmpty) {
            while (first!!.order.address.city.equals(city)) {
                processFirst()
            }
            var node = first!!
            var processingNode: OrderNode

            while (node.next != null) {
                processingNode = node.next!!
                if (processingNode.order.address.city.equals(city)) {
                    processingNode.order.shoppingCart.buyEverything()
                    node.next = processingNode.next
                } else
                    node = node.next!!
            }
        }
    }

    // Verarbeite alle Bestellungen. Die Liste ist danach leer.
    fun processAll() {
        for (order in this){
            order.shoppingCart.buyEverything()
        }
        first = null
    }

    // ** Funktionen zum Analysieren**

    // Analysiert alle order mit der analyzer Funktion
    fun analyzeAll(analyzer: (Order) -> String): String {
        return "test"
    }


    // Prüft, ob für ein Produkt einer der Bestellungen
    // die predicate Funktion erfüllt wird
    fun anyProduct(predicate: (Product) -> Boolean): Boolean = false // TODO

    // Erzeugt ein neues OrderProcessing Objekt, in dem nur noch
    // Order enthalten, für die die predicate Funktion true liefert
    fun filter(predicate: (Order) -> Boolean): OrderProcessing = this // TODO


    operator fun iterator(): MutableIterator<Order> {
        return OrderProcessingIterator(this)
    }

    class OrderProcessingIterator(private val parent: OrderProcessing) : MutableIterator<Order> {
        private var beforeReturned: OrderNode? = null
        private var lastReturned: OrderNode? = null
        private var current = parent.first

        override fun hasNext(): Boolean {
            return current != null
        }

        override fun next(): Order {
            val out = current ?: throw NoSuchElementException()
            if (lastReturned != null)
                beforeReturned = lastReturned
            lastReturned = current
            current = current!!.next
            return out.order
        }

        override fun remove() {
            if (lastReturned != null) {
                if (beforeReturned != null)
                    beforeReturned!!.next = current
                else
                    parent.first = current
                lastReturned = null
            } else
                throw IllegalStateException("Can't remove the latest element before any have been requested")
        }

    }
}