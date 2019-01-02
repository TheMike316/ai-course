package dfs.maze

import java.util.*

class DFS {

    private val stack: Stack<Node> = Stack()
    private val visitedNodes: MutableList<Node> = mutableListOf()

    private val startNodeIndex: Int = 0
    private val targetNodeIndex: Int = 0
    private var targetFound: Boolean = false

    fun dfs(graph: Graph) {
        graph.nodes.asSequence().filter { !it.visited }.forEach(::dfsInStack)
    }

    private fun dfsInStack(node: Node) {
        stack.push(node)

        while (!stack.isEmpty()) {
            val currentNode = stack.peek()
            currentNode.visited = true
            println("current node: $currentNode")

            currentNode.getNextUnvisitedNeighborOrNull()
                ?.let(stack::push) ?: stack.pop()
        }
    }
}