package dfs.standard

import java.util.*

class DFS {

    private val stack: Stack<Node> = Stack()

    fun dfs(graph: Graph) {
        graph.nodes.asSequence().filter { !it.visited }.forEach(::dfsInStack)
    }

    private fun dfsInStack(node: Node) {
        stack.push(node)

        while (!stack.isEmpty()) {
            val currentNode = stack.peek()
            currentNode.visited = true
            println("current node: $currentNode")

            currentNode.getNextUnvisitedChildOrNull()
                ?.let(stack::push) ?: stack.pop()
        }
    }
}