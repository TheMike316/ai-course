package heuristic.astar.maze

class Node(
    var element: Any,
    val hValue: Double,
    var visited: Boolean = false,
    var neighbors: MutableList<Node> = mutableListOf()
) {

    var gValue: Int = 0


    override fun toString(): String {
        return element.toString()
    }

    fun getFValue() = hValue + gValue

    fun addNeighbor(node: Node) {
        neighbors.add(node)
    }

    fun getNextUnvisitedNeighborOrNull() = neighbors.firstOrNull { !it.visited }
}