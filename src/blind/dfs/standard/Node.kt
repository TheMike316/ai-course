package blind.dfs.standard

class Node(var element: Any, var visited: Boolean = false, var children: MutableList<Node> = mutableListOf()) {

    override fun toString(): String {
        return element.toString()
    }

    fun addChild(node: Node) {
        children.add(node)
    }

    fun getNextUnvisitedChildOrNull() = children.firstOrNull { !it.visited }
}