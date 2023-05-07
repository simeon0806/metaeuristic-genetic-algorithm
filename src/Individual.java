import java.util.Random;
import java.util.stream.IntStream;

class Individual {

    int fitness = 0;
    static int GENE_LENGTH;
    char[] genes;

    public Individual(int size) {
        GENE_LENGTH = size;
        Random rn = new Random();

        genes = new char[size];
        // Set genes randomly for each individual
        IntStream.range(0, genes.length)
                .forEach(index -> genes[index] = (char) (rn.nextInt(26) + 'a'));
    }

    public void calcFitness(String word) {
        fitness = 0;
        IntStream.range(0, genes.length)
                .forEach(index -> {
                    if (word.contains("" + genes[index] + "")) {
                        int incrementationNumber = word.indexOf(genes[index]) == index ? 2 : 1;
                        fitness += incrementationNumber;
                    }
                });
    }

}


