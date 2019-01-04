package heuristic.bestfirst.standard

object BestFirst {

    fun bestFirst(root: Node) {

        var currentNode: Node? = root

        while (currentNode != null) {
            println("Current node: ${currentNode.element}")
            currentNode = currentNode.getNextNodeOrNull()
        }

        println("Leaf found")
    }
}