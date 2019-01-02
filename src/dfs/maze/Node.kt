package dfs.maze

class Node(var element: Any, var visited: Boolean = false, var neighbors: MutableList<Node> = mutableListOf()) {

    override fun toString(): String {
        return element.toString()
    }

    fun addNeighbor(node: Node) {
        neighbors.add(node)
    }

    fun getNextUnvisitedNeighborOrNull() = neighbors.firstOrNull { !it.visited }
}