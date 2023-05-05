import java.util.Random;
import java.util.stream.IntStream;


class Individual {

    int fitness = 0;
    static final int GENE_LENGTH = 3;
    char[] genes = new char[GENE_LENGTH];


    public Individual() {
        Random rn = new Random();
        //Set genes randomly for each individual
        IntStream.range(0, genes.length)
                .forEach(index -> genes[index] = (char) (rn.nextInt(26) + 'a'));
    }

    public void calcFitness(String word) {
        fitness = 0;
        IntStream.range(0, GENE_LENGTH)
                .forEach(index -> {
                    if (word.contains("" + genes[index] + "")) {
                        int incrementationNumber = word.indexOf(genes[index]) == index ? 2 : 1;
                        fitness += incrementationNumber;
                    }
                });
    }

}


