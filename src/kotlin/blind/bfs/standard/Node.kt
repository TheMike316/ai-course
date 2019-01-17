package blind.bfs.standard

class Node(var element: Any, var visited: Boolean = false, var neighbors: MutableList<Node> = mutableListOf()) {

    fun addNeighbor(node: Node) {
        neighbors.add(node)
    }

    override fun toString(): String {
        return element.toString()
    }
}