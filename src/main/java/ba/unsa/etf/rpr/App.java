package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CardManager;
import ba.unsa.etf.rpr.business.ProfileManager;
import ba.unsa.etf.rpr.domain.Card;
import ba.unsa.etf.rpr.domain.CardType;
import ba.unsa.etf.rpr.domain.Profile;
import ba.unsa.etf.rpr.exceptions.AppException;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws AppException {
        System.out.println("Dobrodošli u aplikaciju Javnog prevoza Kantona Sarajevo. Unesite 1 za prijavu u račun ili 2 za kreiranje računa: ");
        Scanner scanner = new Scanner(System.in);
        ProfileManager profileManager = new ProfileManager();
        Profile loggedUser;
        int choice;
        while (true){
            if(scanner.hasNextInt()){
                System.out.println("Unesite 1 za prijavu u račun ili 2 za kreiranje računa: ");
                int input = scanner.nextInt();
                if(input == 1 || input == 2){
                    choice = input;
                    break;
                }
                else{
                    System.out.println("Neispravan unos.");
                    scanner.nextLine();
                }
            }
            else{
                System.out.println("Neispravan unos.");
                scanner.nextLine();
            }
        }
        if (choice != 1) {
            register();
        }
        loggedUser = logIn();
        System.out.println("Dobrodošli nazad, " + loggedUser.getName());
        while (true){
            System.out.println("Unesite 1 za pregled SMART kartica, 2 za pregled linija vožnji, 3 za pregled linija od interesa, te 4 za odjavu: ");
            if(scanner.hasNextInt()){
                int input = scanner.nextInt();
                if(input == 1 || input == 2 || input == 3 || input == 4){
                    switch(input){
                        case 1:
                            smartCardsInterface(loggedUser);
                    }
                }
                else{
                    System.out.println("Neispravan unos.");
                    scanner.nextLine();
                }
            }
            else{
                System.out.println("Neispravan unos.");
                scanner.nextLine();
            }
        }
    }

    private static Profile logIn(){
        String email;
        String password;
        Scanner scanner = new Scanner(System.in);
        ProfileManager profileManager = new ProfileManager();
        System.out.println("Prijavite se u vaš račun.");
        while (true){
            System.out.println("Unesite email: ");
            email = scanner.nextLine();
            System.out.println("Unesite password: ");
            password = scanner.nextLine();
            if(!profileManager.validateLogin(email, password)){
                System.out.println("Neispravna email adresa/password. Pokušajte ponovo.");
                continue;
            }
            else{
                return profileManager.getProfileByEmail(email);
            }
        }
    }

    private static void register() throws AppException {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{5,}$";
        Scanner scanner = new Scanner(System.in);
        ProfileManager profileManager = new ProfileManager();
        String email;
        while (true){
            System.out.println("Unesite email: ");
            email = scanner.nextLine();
            if(!email.matches(emailRegex)){
                System.out.println("Neispravna email adresa.");
                continue;
            }
            else{
                break;
            }
        }
        String name;
        System.out.println("Unesite ime: ");
        name = scanner.nextLine();
        String surname;
        System.out.println("Unesite prezime: ");
        surname = scanner.nextLine();
        String password;
        while (true){
            System.out.println("Unesite password: ");
            password = scanner.nextLine();
            if(!password.matches(passwordRegex)){
                System.out.println("Neispravan password. Password mora sadržavati najmanje 5 karaktera, barem jednu cifru, barem jedan posebni karakter, barem jedno veliko slovo i barem jedno malo slovo!");
                continue;
            }
            else{
                break;
            }
        }
        System.out.println("Uspješna registracija!");
        profileManager.addToDatabase(name, surname, password, email);
    }

    private static void smartCardsInterface(Profile loggedUser) throws AppException {
        while (true){
            boolean exit = false;
            System.out.println("Vaše SMART kartice: ");
            CardManager cardManager = new CardManager();
            List<Card> cards = cardManager.getUserCards(loggedUser.getId());
            for(Card card: cards){
                System.out.println(card);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Unesite 1 za dodavanje SMART kartice, 2 za uklanjanje SMART kartice, 3 za dopunjavanje SMART kartice, 4 za aktivaciju mjesečnog kupona, te 5 za povratak nazad: ");
            if(scanner.hasNextInt()){
                int input = scanner.nextInt();
                scanner.nextLine();
                if(input == 1 || input == 2 || input == 3 || input == 4 || input == 5){
                    switch(input){
                        case 1:
                            String serialNoRegex = "\\d{0,9}";
                            int serialNumber;
                            while(true){
                                System.out.println("Unesite serijski broj kartice: ");
                                String serialnr = scanner.nextLine();
                                if(!serialnr.matches(serialNoRegex)){
                                    System.out.println("Serijski broj je 9 - cifren broj. Pokušajte ponovo.");
                                    continue;
                                }
                                serialNumber = Integer.parseInt(serialnr);
                                System.out.println("Unesite vrstu kartice ('Studentska', 'Srednja škola', 'Osnovna škola', 'Radnička', 'Penzionerska', 'Ostali'): ");
                                CardType cardType = determineCategory(scanner.nextLine());
                                if(cardType == null){
                                    System.out.println("Pogrešan unos. Pokušajte ponovo.");
                                    continue;
                                }
                 //               cardManager.addCard(new Card(-1, serialNumber, cardType, 0.0, false, loggedUser));
                                break;
                            }
                            break;
                        case 2:
                            while(true) {
                                System.out.println("Unesite id kartice za brisanje: ");
                                if(scanner.hasNextInt()){
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean exists = false;
                                    for(Card card: cards){
                                        if(card.getId() == id){
                                            exists = true;
                                            break;
                                        }
                                    }
                                    if(!exists){
                                        System.out.println("Neispravan unos.");
                                        scanner.nextLine();
                                    }
                                    cardManager.deleteCard(id);
                                    break;
                                }
                                else{
                                    System.out.println("Neispravan unos.");
                                    scanner.nextLine();
                                }
                            }
                            break;
                        case 3:
                            while(true){
                                int id;
                                int serialnr = 0;
                                while(true) {
                                    System.out.println("Unesite id kartice za dopunu: ");
                                    if (scanner.hasNextInt()) {
                                        id = scanner.nextInt();
                                        scanner.nextLine();
                                        boolean exists = false;
                                        for (Card card : cards) {
                                            if (card.getId() == id) {
                                                exists = true;
                                                serialnr = card.getSerialNumber();
                                                break;
                                            }
                                        }
                                        if (!exists) {
                                            System.out.println("Neispravan id.");
                                            scanner.nextLine();
                                        }
                                        System.out.println("id je " + id);
                                        cardManager.deleteCard(id);
                                        break;
                                    } else {
                                        System.out.println("Neispravan id.");
                                        scanner.nextLine();
                                    }
                                }
                                System.out.println("Unesite iznos u KM za dopunu:");
                                try{
                                    double newBalance = Double.parseDouble(scanner.nextLine());
                                    if(newBalance < 0) {
                                        throw new NumberFormatException();
                                    }
                                    Card card = cardManager.getCard(serialnr);
                                    card.setBalance(card.getBalance() + newBalance);
                                    cardManager.updateCard(card);
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Unijeli ste neispravnu vrijednost. Molimo unesite nenegativan numerički iznos u KM.");
                                }
                                break;
                            }
                        case 4:
                            int id;
                            int serialnr = 0;
                            while(true) {
                                System.out.println("Želite li aktivirati mjesečni kupon? Iznos će biti preuzet sa trenutnog stanja SMART kartice." +
                                                                           "Studenti: 20 KM\n Srednja škola: 16 KM\nOsnovna škola: 16KM\nRadnička: 25 KM\nPenzionerska: 20 KM\nOstali: 23 KM\nUnesite id kartice za aktivaciju mjesečnog kupona: ");
                                if (scanner.hasNextInt()) {
                                    id = scanner.nextInt();
                                    scanner.nextLine();
                                    boolean exists = false;
                                    for (Card card : cards) {
                                        if (card.getId() == id) {
                                            exists = true;
                                            serialnr = card.getSerialNumber();
                                            break;
                                        }
                                    }
                                    if (!exists) {
                                        System.out.println("Neispravan id.");
                                        scanner.nextLine();
                                    }
                                    cardManager.deleteCard(id);
                                    break;
                                } else {
                                    System.out.println("Neispravan id.");
                                    scanner.nextLine();
                                }
                            }
                            Card card = cardManager.getCard(serialnr);
                            if(!balanceNegative(card.getCardType(), card.getBalance())){
                                card.setBalance(newBalance(card.getCardType(), card.getBalance()));
                                card.setMonthlyCoupon(true);
                                cardManager.updateCard(card);
                            }
                            else{
                                System.out.println("Nedovoljan iznos na kartici za aktivaciju!");
                            }
                            break;
                        case 5:
                            exit = true;
                            break;
                    }
                }
                else{
                    System.out.println("Neispravan unos.");
                    scanner.nextLine();
                }
            }
            else{
                System.out.println("Neispravan unos.");
                scanner.nextLine();
            }
            if(exit){
                break;
            }
        }
    }

    private static double newBalance(CardType type, double balance){
        switch(type){
            case STUDENT:
            case PENSIONER:
                return balance - 20;
            case HIGH_SCHOOL:
            case ELEMENTARY:
                return balance - 16;
            case WORKER:
                return balance - 25;
        }
        return balance - 23;
    }

    private static boolean balanceNegative(CardType type, double balance){
        switch(type){
            case STUDENT:
            case PENSIONER:
                return (balance - 20) < 0;
            case HIGH_SCHOOL:
            case ELEMENTARY:
                return (balance - 16) < 0;
            case WORKER:
                return (balance - 25) < 0;
        }
        return (balance - 23) < 0;
    }

    private static CardType determineCategory(String category){
        switch(category){
            case "Studentska":
                return CardType.valueOf("STUDENT");
            case "Srednja škola":
                return CardType.valueOf("HIGH_SCHOOL");
            case "Osnovna škola":
                return CardType.valueOf("ELEMENTARY");
            case "Radnička":
                return CardType.valueOf("WORKER");
            case "Penzionerska":
                return CardType.valueOf("PENSIONER");
            case "Ostali":
                return CardType.valueOf("OTHER");
        }
        return null;
    }

}
