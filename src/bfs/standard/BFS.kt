package bfs.standard

import java.util.*

class BFS {

    private val queue: Queue<Node> = LinkedList()

    fun bfs(graph: Graph) {
        graph.nodes.asSequence().filter { !it.visited }.forEach(::bfsInQueue)
    }

    private fun bfsInQueue(node: Node) {
        node.visited = true
        queue.add(node)

        while (!queue.isEmpty()) {
            val currentNode = queue.remove()
            println("current node: $currentNode")

            currentNode.neighbors.forEach {
                it.visited = true
                queue.add(it)
            }
        }
    }
}