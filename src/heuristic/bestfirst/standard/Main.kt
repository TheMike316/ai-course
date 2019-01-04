package heuristic.bestfirst.standard


fun main(args: Array<String>) {
    val rootNode = Node("A").apply {
        addConnection(10.0, Node("B").apply {
            addConnection(1.5, Node("D"))
            addConnection(3.4, Node("E"))
        })
        addConnection(255.0, Node("C").apply {
            addConnection(8.1, Node("F"))
            addConnection(6.3, Node("G"))
        })
    }

    BestFirst.bestFirst(rootNode)
}