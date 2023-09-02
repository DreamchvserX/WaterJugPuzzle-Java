import java.util.Scanner;
class DieHardWasser {

    //funktion die den größtenDivisor ermittelt
    public static int GrößterDivisor(int x, int y) {
        if (y == 0) {
            return x;
        }

        return GrößterDivisor(y, x % y);
    }

    // Methode mit 3 parta,metern, bucket a, bucket b und d

    public static int WasserTausch(int BucketACapacity, int BucketBCapacity, int d) {

       // variablen mit momentanem wassrstand
        int bucketA = BucketACapacity;
        int bucketB = 0;

        // benötigte schritte
        int reqStep = 0;

        // While-Schleife, um die Menge d Liter zu füllen, und brechen wenn einer der beiden Krüge die Menge d Liter hat
        while (bucketA != d && bucketB != d) {

            // maximum Liter
            int maximum = Math.min(bucketA, BucketBCapacity - bucketB);

            // maximum von a in b schütten
            bucketB = bucketB + maximum;
            bucketA = bucketA - maximum;

            // schritte erhöhen
            reqStep++;

            // check wie viel liter vrhanden sind in den buckets
            if (bucketA == d || bucketB == d)
                break;

            // füllen wenn a leer ist
            if (bucketA == 0) {
                bucketA = BucketACapacity;
                reqStep++;
            }

            //b leeren wenn er voll ist
            if (bucketB == BucketBCapacity) {
                bucketB = 0;
                reqStep++;
            }
        }
        // totatal benötigte schritte
        return reqStep;
    }

    // minimale schriotte benötigt errechnen
    public static int MinSteps(int i, int j, int d) {

        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }

        // wir können das Wasser nicht mit den bucvkets messen, wenn d > j
        if (d > j) {
            System.out.println("Nicht moeglich");
        }
        // Wir können das nicht lösen, wenn GrößterDivisor von j und i nicht durch d geteilt werden kann
        if ((d % GrößterDivisor(j, i)) != 0)
            System.out.println("Nicht moeglich");

        // Wasser aus j-Liter-Krug wird in i-Liter-Krug gegossen

        return Math.min(WasserTausch(j, i, d), // j to i
                WasserTausch(i, j, d)); // i to j
    }


    public static void main(String[] args) {

        // Variablen für die Größe der Krüge und die Wassermenge, die wir messen möchten
        int i, j, d;

        // scanner für input
        Scanner sc = new Scanner(System.in);

        System.out.println("Groesse bucket a");
        i = sc.nextInt();

        System.out.println("Groesse bucket b");
        j = sc.nextInt();

        System.out.println("Wie viel wollen sie abmessen? :");
        d = sc.nextInt();

        sc.close();

        System.out.println("benoetigte schritte sind " + MinSteps(i, j, d));
    }
}  