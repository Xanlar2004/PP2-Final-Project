import java.util.*;
import java.lang.reflect.*;
import java.util.stream.*;

public class Sort {

    public static ArrayList<Movie> sortInterface(ArrayList<Movie> user) {

        System.out.println("Please, enter the field you would like to sort");
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
        ArrayList<Movie> sorted = user;
        String sortingParameter, sortingOrder;
        sortingParameter = sc.nextLine().replaceAll(" ", "").toUpperCase();
        if (Movie.moviefields.containsKey(sortingParameter))
            sortingParameter = Movie.moviefields.get(sortingParameter);
        if (sortingParameter.equals("ID") ||
                sortingParameter.equals("POPULARITY") ||
                sortingParameter.equals("VOTECOUNT") ||
                sortingParameter.equals("VOTEAVERAGE")) {
            System.out.println("In what order do you want to sort?");
            System.out.println("\tASC/asc -> Ascending\n\tDESC/desc -> Descending");
            sortingOrder = sc.nextLine().replaceAll(" ", "").toUpperCase();
            sorted = sortByDouble(user, sortingParameter, sortingOrder);
        } else if (sortingParameter.equals("TITLE") ||
                sortingParameter.equals("OVERVIEW") ||
                sortingParameter.equals("ORIGINALLANGUAGE") ||
                sortingParameter.equals("GENRE") ||
                sortingParameter.equals("POSTERURL")) {
            System.out.println("In what order do you want to sort?");
            System.out.println("\tASC/asc -> Ascending\n\tDESC/desc -> Descending");
            sortingOrder = sc.nextLine().replaceAll(" ", "").toUpperCase();
            sorted = sortByString(user, sortingParameter, sortingOrder);
        } else if (sortingParameter.equals("RELEASEDATE")) {
            System.out.println("In what order do you want to sort?");
            System.out.println("\tASC/asc -> Ascending\n\tDESC/desc -> Descending");
            sortingOrder = sc.nextLine().replaceAll(" ", "").toUpperCase();
            sorted = sortByDate(user, sortingParameter, sortingOrder);
        } else {
            System.out.println(
                    "Wrong field specified! Please, next time enter only the given names or the number corresponding to them!");
        }
        return sorted;

    }

    public static ArrayList<Movie> sortByDouble(ArrayList<Movie> movie, String type, String style) {

        if (style.equals("ASC")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().sorted(Comparator.comparing(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return (Double) field.get(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return 0.0;
            })).collect(Collectors.toList());
        }
        else if (style.equals("DESC")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().sorted(Comparator.comparing(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return (Double) field.get(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return 0.0;
            }).reversed()).collect(Collectors.toList());
        }
        else {
            System.out.println("Wrong order specified! Please, next time enter only \"ASC/asc\" or \"DESC/desc\"");
            return movie;
        }

    }

    public static ArrayList<Movie> sortByString(ArrayList<Movie> movie, String type, String style) {

        if (style.equals("ASC")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().sorted(Comparator.comparing(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return (String) field.get(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return type;
            })).collect(Collectors.toList());
        }
        else if (style.equals("DESC")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().sorted(Comparator.comparing(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return (String) field.get(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return type;
            }).reversed()).collect(Collectors.toList());
        }
        else {
            System.out.println("Wrong order specified! Please, next time enter only \"ASC/asc\" or \"DESC/desc\"");
            return movie;
        }

    }

    public static ArrayList<Movie> sortByDate(ArrayList<Movie> movie, String type, String style) {

        if (style.equals("ASC")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().sorted(Comparator.comparing(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return (Date) field.get(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            })).collect(Collectors.toList());
        }
        else if (style.equals("DESC")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().sorted(Comparator.comparing(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return (Date) field.get(s);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }).reversed()).collect(Collectors.toList());
        }
        else {
            System.out.println("Wrong order specified! Please, next time enter only \"ASC/asc\" or \"DESC/desc\"");
            return movie;
        }

    }

}