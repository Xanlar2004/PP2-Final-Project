import java.text.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.stream.*;

public class Search {

    public static ArrayList<Movie> searchInterface(ArrayList<Movie> user) {

        System.out.println("Please, enter the field by which you want to search");
        System.out.println("\t1 -> Id");
        System.out.println("\t2 -> Release Date");
        System.out.println("\t3 -> Title");
        System.out.println("\t4 -> Overview");
        System.out.println("\t5 -> Popularity");
        System.out.println("\t6 -> Vote Count");
        System.out.println("\t7 -> Vote Average");
        System.out.println("\t8 -> Original Language");
        System.out.println("\t9 -> Genre");
        System.out.println("\t10 -> Poster Url");
        Scanner sc = new Scanner(System.in);
        ArrayList<Movie> searched = user;
        String searchParameter, searchInput;
        Double searchDouble;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date searchDate;
        searchParameter = sc.nextLine().replaceAll(" ", "").toUpperCase();
        if (Movie.moviefields.containsKey(searchParameter))
            searchParameter = Movie.moviefields.get(searchParameter);
        if (searchParameter.equals("ID") ||
                searchParameter.equals("POPULARITY") ||
                searchParameter.equals("VOTECOUNT") ||
                searchParameter.equals("VOTEAVERAGE")) {
            System.out.println("Please, enter the " + Movie.headers.get(searchParameter) + " of the film you want to search");
            searchInput = sc.nextLine().replaceAll(" ", "");
            try {
                searchDouble = Double.valueOf(searchInput);
                searched = searchByDouble(user, searchParameter, searchDouble);
            } catch (Exception ex) {
                System.out.println("Wrong input specified! Please, next time enter only numeric value!");
            }
        } else if (searchParameter.equals("TITLE") ||
                searchParameter.equals("OVERVIEW") ||
                searchParameter.equals("ORIGINALLANGUAGE") ||
                searchParameter.equals("POSTERURL")) {
            System.out.println("Please, enter the " + Movie.headers.get(searchParameter) + " of the film you want to search");
            searchInput = sc.nextLine();
            searched = searchByString(user, searchParameter, searchInput);
        } else if (searchParameter.equals("GENRE")) {
            System.out.println("Please, enter the " + Movie.headers.get(searchParameter) + " of the film you want to search");
            System.out.println("Genres:");
            System.out.println("\t>Drama");
            System.out.println("\t>Action");
            System.out.println("\t>Comedy");
            System.out.println("\t>History");
            System.out.println("\t>War");
            System.out.println("\t>ScienceFiction");
            System.out.println("\t>Horror");
            System.out.println("\t>Thriller");
            System.out.println("\t>Music");
            System.out.println("\t>Mystery");
            System.out.println("\t>Crime");
            System.out.println("\t>Family");
            System.out.println("\t>Adventure");
            System.out.println("\t>Fantasy");
            System.out.println("\t>Romance");
            System.out.println("\t>Animation");
            System.out.println("\t>TV Movie");
            System.out.println("\t>Documentary");
            searchInput = sc.nextLine();
            searched = searchByString(user, searchParameter, searchInput);
        } else if (searchParameter.equals("RELEASEDATE")) {
            System.out.println("Please, enter the " + Movie.headers.get(searchParameter) + " of the film you want to search in the format of MM/DD/YYYY");
            searchInput = sc.nextLine().replaceAll(" ", "");
            try {
                searchDate = df.parse(searchInput);
                searched = searchByDate(user, searchParameter, searchDate);
            } catch (Exception ex) {
                System.out.println(
                        "Wrong input specified! Please, next time enter only numeric values in the format of MM/DD/YYYY!");
            }
        } else {
            System.out.println(
                    "Wrong field specified! Please, next time enter only the given names or the number corresponding to them!");
        }
        return searched;

    }

    public static ArrayList<Movie> searchByDouble(ArrayList<Movie> movie, String type, Double input) {

        System.out.println("Operation succesfully done!");

        return (ArrayList<Movie>) movie.stream().filter(s -> {
            try {
                Field field = Movie.class.getDeclaredField(type);
                field.setAccessible(true);
                return ((Double) field.get(s)).equals(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }).collect(Collectors.toList());

    }

    public static ArrayList<Movie> searchByString(ArrayList<Movie> movie, String type, String input) {
        
        System.out.println("Operation succesfully done!");

        return (ArrayList<Movie>) movie.stream().filter(s -> {
            try {
                Field field = Movie.class.getDeclaredField(type);
                field.setAccessible(true);
                return ((String) field.get(s)).contains(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }).collect(Collectors.toList());

    }

    public static ArrayList<Movie> searchByDate(ArrayList<Movie> movie, String type, Date input) {
        
        System.out.println("Operation succesfully done!");

        return (ArrayList<Movie>) movie.stream().filter(s -> {
            try {
                Field field = Movie.class.getDeclaredField(type);
                field.setAccessible(true);
                return ((Date) field.get(s)).equals(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return false;
        }).collect(Collectors.toList());

    }

}
