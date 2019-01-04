package dfs.maze

import java.util.*

class DFS {


    fun dfs(graph: Graph, startNodeIndex: Int, targetPredicate: (Node) -> Boolean): List<Node> {
        return dfsInStack(graph.nodes[startNodeIndex], targetPredicate)
    }

    private fun dfsInStack(node: Node, targetPredicate: (Node) -> Boolean): List<Node> {
        val stack = Stack<Node>()

        stack.push(node)

        val visitedNodes = mutableListOf<Node>()
        while (!stack.isEmpty()) {
            val currentNode = stack.peek()
            currentNode.visited = true
            visitedNodes.add(currentNode)
            println("current node: $currentNode")

            currentNode.getNextUnvisitedNeighborOrNull()
                ?.let(stack::push) ?: stack.pop()

            if (targetPredicate.invoke(currentNode))
                break
        }

        return visitedNodes
    }
}