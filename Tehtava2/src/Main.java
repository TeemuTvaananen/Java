import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int input, total = 0, summa = 0, keskiarvo, pienin_luku = Integer.MAX_VALUE, suurin_luku = Integer.MIN_VALUE;

        Scanner lukija = new Scanner(System.in);

        do {
            System.out.print("Syötä positiivinen kokonaisluku > ");
            input = lukija.nextInt();
           if(input > 0){
               total++;
               summa += input;
               if(input > suurin_luku){
                   suurin_luku = input;
               }
               if(input < pienin_luku ){
                   pienin_luku = input;
               }
           }
           else{
               break; }

        } while(true);
        keskiarvo = summa / total;
        System.out.println("\nSyötit yhteensä näin monta lukua > " + total);
        System.out.println("Lukujen summa on > " + summa);
        System.out.println("Lukujen keskiarvo on > "+ keskiarvo);
        System.out.println("Suurin syötetty luku on > " + suurin_luku);
        System.out.println("Pienin syötetty luku on > " + pienin_luku);
    }
}