package meta.genetic

class Population(chromosomeNo: Int, geneNo: Int) {
    val chromosomes: Array<Chromosome>

    init {
        val chromosomeList = mutableListOf<Chromosome>()

        for (i in 0 until chromosomeNo) {
            chromosomeList.add(i, Chromosome(geneNo))
        }

        chromosomes = chromosomeList.toTypedArray()
    }

    fun initialize(p: Double) {
        chromosomes.forEach { it.initialize(p) }
    }

    fun getFittest() = chromosomes.minBy { it.fitness } ?: throw RuntimeException("Population has not been initialized")

    fun getSingleChromosome(i: Int) = chromosomes[i]

    fun setSingleChromosome(i: Int, chromosome: Chromosome) {
        chromosomes[i] = chromosome
        chromosome.fitness = evaluateFitness(chromosome.genes)
    }
}