package heuristic.astar.maze

import java.util.*

object AStar {

    fun aStar(graph: Graph, startNodeIndex: Int, targetPredicate: (Node) -> Boolean): List<Node> {
        val visitedNodes = mutableListOf<Node>()

        val priorityQueue = PriorityQueue<Node> { n1, n2 ->
            when {
                n1.getFValue() < n2.getFValue() -> -1
                n1.getFValue() > n2.getFValue() -> 1
                else -> 0
            }
        }

        val root = graph.nodes[startNodeIndex]

        priorityQueue.add(root)

        while (!priorityQueue.isEmpty()) {
            val currentNode = priorityQueue.poll() ?: throw IllegalStateException()
            visitedNodes.add(currentNode)

            println("Current node: ${currentNode.element}")

            if (targetPredicate.invoke(currentNode))
                break

            currentNode.neighbors.asSequence()
                .filter { !it.visited && !visitedNodes.map(Node::element).contains(it.element) }
                .forEach {
                    //increasing the G Value by 1 in all directions is sufficient for our case
                    it.gValue = currentNode.gValue + 1
                    priorityQueue.add(it)
                }
        }

        return visitedNodes
    }
}