import java.util.stream.IntStream;

class Population {

    int popSize = 10;
    Individual[] individuals;
    int fittest = 0;

    public Population(int size) {
        individuals = IntStream.range(0, popSize).mapToObj(i -> new Individual(size)).toArray(Individual[]::new);
    }

    public Individual getFittest() {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (maxFit <= individuals[i].fitness) {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].fitness;
        try {
            return (Individual) individuals[maxFitIndex].clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Individual getSecondFittest() {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i].fitness > individuals[maxFit1].fitness) {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } else if (individuals[i].fitness > individuals[maxFit2].fitness) {
                maxFit2 = i;
            }
        }
        try {
            return (Individual) individuals[maxFit2].clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLeastFittestIndex() {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) {
            if (minFitVal >= individuals[i].fitness) {
                minFitVal = individuals[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    public void calculateFitness(String word) {
        for (Individual individual : individuals) {
            individual.calcFitness(word);
        }
        getFittest();
    }

}


