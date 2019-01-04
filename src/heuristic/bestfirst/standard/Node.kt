package heuristic.bestfirst.standard

class Node(
    val element: Any,
    private val connections: MutableList<Connection> = mutableListOf()
) {

    fun getNextNodeOrNull(): Node? {
        if (connections.isEmpty())
            return null

        var min = connections[0].weight
        var target = connections[0].targetNode
        for (i in 1 until connections.size) {
            val con = connections[i]
            if (con.weight < min) {
                target = con.targetNode
                min = con.weight
            }
        }
        return target
    }

    fun addConnection(weight: Double, node: Node) = connections.add(Connection(node, weight))
}