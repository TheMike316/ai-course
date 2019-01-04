package blind.ids.standard

fun main(args: Array<String>) {

    val rootNode = Node("A").apply {
        addNeighbor(Node("B").apply {
            addNeighbor(Node("D"))
            addNeighbor(Node("E"))
        })
        addNeighbor(Node("C").apply {
            addNeighbor(Node("F"))
            addNeighbor(Node("G"))
        })
    }

    val graph = Graph(listOf(rootNode))

    IDS.ids(graph, "E")

}