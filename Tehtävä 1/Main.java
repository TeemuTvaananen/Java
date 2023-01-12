import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner lukija = new Scanner(System.in);
        System.out.println("Syötä etu ja sukunimesi ohjelmoija");
        String nimi = lukija.nextLine();
        System.out.println("Tervetuloa Java-ohjelmoinnin maailmaan " +nimi);
        System.out.print("Paina enter lopettaaksesi");
        lukija.nextLine();
        lukija.close();
    }

}