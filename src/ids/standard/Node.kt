package ids.standard

class Node(
    val element: Any,
    var depthLevel: Int = 0,
    val neighbors: MutableList<Node> = mutableListOf()
) {
    fun addNeighbor(node: Node) {
        neighbors.add(node)
    }
}