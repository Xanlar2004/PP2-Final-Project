import java.io.*;
import java.text.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        String source = "mymoviedb.csv";
        ArrayList<Movie> user = CSVReadandWrite.CSVReader(source);
        String input;

        System.out.println("\n\n\t\t\t\t\t\tWelcome to Movies Dataset!\n\n\n");
        
        while(true) {
            if(user == null || user.isEmpty()) {
                System.out.println("Your final list is empty! Do you still want to continue?");
                input = sc.nextLine().replaceAll(" ", "").toLowerCase();
                if(!input.equals("y") && !input.equals("yes"))
                    break;
            }
            System.out.println("What would you like to do?");
            System.out.println("\t1 -> List films by parameter (List)");
            System.out.println("\t2 -> Sort films (Sort)");
            System.out.println("\t3 -> Search films by parameters (Search)");
            System.out.println("\t4 -> Filter films by parameters (Filter)");
            System.out.println("Please, enter only the words inside brackets or numbers corresponding to the operation you would like to do");
            input = sc.nextLine().replaceAll(" ", "").toLowerCase();
            if (input.equals("1") || input.equals("list")) {
                List.listInterface(user);
            }
            else if (input.equals("2") || input.equals("sort")) {
                user = Sort.sortInterface(user);
                List.listInterface(user);
            }
            else if (input.equals("3") || input.equals("search")) {
                user = Search.searchInterface(user);
                List.listInterface(user);
            }
            else if (input.equals("4") || input.equals("filter")) {
                user = Filter.filterInterface(user);
                List.listInterface(user);
            }
            else {
                System.out.println("Wrong operation specified! Please, next time enter only the words inside brackets or numbers corresponding to the operation you would like to do");
            }
            System.out.println("Do you want to continue with other operation? yes or no?");
            String continueSpecifier = sc.nextLine().replaceAll(" ", "").toLowerCase();
            if (continueSpecifier.equals("yes") || continueSpecifier.equals("y"))
                continue;
            else { 
                System.out.println("Do you want to save your result to new file? yes or no?");
                String newFileSpecifier = sc.nextLine().toLowerCase();
                if (newFileSpecifier.equals("yes") || newFileSpecifier.equals("y")) {
                    System.out.println("Please indicate location of the file:");
                    String fileLocation = sc.nextLine();
                    if(fileLocation.isBlank())
                        fileLocation = "dest";
                    CSVReadandWrite.CSVWriter(user, fileLocation);
                }
                break;
            }
        }
    }

}