package meta.genetic

class Chromosome(geneNo: Int) {
    val genes: Array<Int?> = arrayOfNulls(geneNo)

    var fitness: Int = 0

    fun getSingleGene(idx: Int) = genes[idx] ?: throw RuntimeException("Chromosome has not been initialized")

    fun setSingleGene(idx: Int, gene: Int) {
        genes[idx] = gene
    }

    fun getGeneNo() = genes.size

    fun initialize(p: Double) {
        for (i in 0 until getGeneNo()) {
            genes[i] = if (Math.random() < p) 0 else 1
        }

        fitness = evaluateFitness(genes)
    }
}