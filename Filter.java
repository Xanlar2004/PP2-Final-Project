import java.text.*;
import java.util.*;
import java.lang.reflect.*;
import java.util.stream.*;

public class Filter {

    public static ArrayList<Movie> filterInterface(ArrayList<Movie> user) {

        System.out.println("Please, enter the field by which you want to filter");
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
        ArrayList<Movie> filtered = user;
        String filterParameter, opInput, filterInput1, filterInput2, filterInput[];
        Double filterDouble1, filterDouble2;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date filterDate1, filterDate2, temp;
        Integer datecheck;
        filterParameter = sc.nextLine().replaceAll(" ", "").toUpperCase();
        if (Movie.moviefields.containsKey(filterParameter))
            filterParameter = Movie.moviefields.get(filterParameter);
        if (filterParameter.equals("ID") ||
                filterParameter.equals("POPULARITY") ||
                filterParameter.equals("VOTECOUNT") ||
                filterParameter.equals("VOTEAVERAGE")) {
            System.out.println("Please, enter the parameter by which you want to filter");
            System.out.println("\t> Equal");
            System.out.println("\t> Greater");
            System.out.println("\t> Less");
            System.out.println("\t> Greater Equal");
            System.out.println("\t> Less Equal");
            System.out.println("\t> Between");
            System.out.println("\t> Null (For Missed Values)");
            opInput = sc.nextLine().replaceAll(" ", "").toLowerCase();
            if (opInput.equals("null")) {
                filtered = filterByDouble(user, filterParameter, opInput, null, null);
            } else if (opInput.equals("between")) {
                System.out.println("Please, enter the 2 values according to which you want to filter");
                filterInput = sc.nextLine().split(" ");
                if (filterInput.length == 1) {
                    filterInput1 = filterInput[0];
                    filterInput2 = sc.nextLine().replaceAll(" ", "");
                } else {
                    filterInput1 = filterInput[0];
                    filterInput2 = filterInput[1];
                }
                try {
                    filterDouble1 = Double.valueOf(filterInput1);
                    filterDouble2 = Double.valueOf(filterInput2);
                    filtered = filterByDouble(user, filterParameter, opInput, Math.min(filterDouble1, filterDouble2),
                            Math.max(filterDouble1, filterDouble2));
                } catch (Exception ex) {
                    System.out.println("Wrong input specified! Please, next time enter only numeric value!");
                }
            } else {
                System.out.println("Please, enter the value according to which you want to filter");
                filterInput1 = sc.nextLine().replaceAll(" ", "");
                try {
                    filterDouble1 = Double.valueOf(filterInput1);
                    filtered = filterByDouble(user, filterParameter, opInput, filterDouble1, null);
                } catch (Exception ex) {
                    System.out.println("Wrong input specified! Please, next time enter only numeric value!");
                }
            }
        } else if (filterParameter.equals("RELEASEDATE")) {
            System.out.println("Please, enter the parameter you by which you want to filter");
            System.out.println("\t> Equal");
            System.out.println("\t> Greater");
            System.out.println("\t> Less");
            System.out.println("\t> Greater Equal");
            System.out.println("\t> Less Equal");
            System.out.println("\t> Between");
            System.out.println("\t> Specific Year");
            System.out.println("\t> Specific Month");
            System.out.println("\t> Specific Day");
            System.out.println("\t> Null (For Missed Values)");
            opInput = sc.nextLine().replaceAll(" ", "").toLowerCase();
            if (opInput.equals("null")) {
                filtered = filterByDate(user, filterParameter, opInput, null, null);
            } else if (opInput.equals("between")) {
                System.out.println("Please, enter the 2 values according to which you want to filter in the format of MM/DD/YYYY");
                filterInput = sc.nextLine().split(" ");
                if (filterInput.length == 1) {
                    filterInput1 = filterInput[0];
                    filterInput2 = sc.nextLine().replaceAll(" ", "");
                } else {
                    filterInput1 = filterInput[0];
                    filterInput2 = filterInput[1];
                }
                try {
                    filterDate1 = df.parse(filterInput1);
                    filterDate2 = df.parse(filterInput2);
                    if (filterDate2.compareTo(filterDate1) < 0) {
                        temp = filterDate1;
                        filterDate1 = filterDate2;
                        filterDate2 = temp;
                    }
                    filtered = filterByDate(user, filterParameter, opInput, filterDate1, filterDate2);
                } catch (Exception ex) {
                    System.out.println(
                            "Wrong input specified! Please, next time enter only numeric values in the format of MM/DD/YYYY!");
                }
            } else if (opInput.equals("specificyear")) {
                System.out.println("Please, enter the year according to which you want to filter");
                filterInput1 = "01/01/" + sc.nextLine().replaceAll(" ", "");
                try {
                    filterDate1 = df.parse(filterInput1);
                    filtered = filterByDate(user, filterParameter, opInput, filterDate1, null);
                } catch (Exception ex) {
                    System.out.println("Wrong input specified! Please, next time enter only year of the film you want to filter!");
                }
            } else if (opInput.equals("specificmonth")) {
                System.out.println("Please, enter the month according to which you want to filter");
                filterInput1 = sc.nextLine().replaceAll(" ", "") ;
                try {
                    datecheck = Integer.valueOf(filterInput1);
                    if (datecheck < 1 || datecheck > 12) {
                        System.out.println("Wrong input specified! Please, next time enter only month of the film you want to filter in range 1 - 12!");
                    }
                    else {
                        filterInput1 = filterInput1 + "/01/1000";
                        filterDate1 = df.parse(filterInput1);
                        filtered = filterByDate(user, filterParameter, opInput, filterDate1, null);
                    }
                } catch (Exception ex) {
                    System.out.println("Wrong input specified! Please, next time enter only month of the film you want to filter!");
                }
            } else if (opInput.equals("specificday")) {
                System.out.println("Please, enter the day according to which you want to filter");
                filterInput1 = sc.nextLine().replaceAll(" ", "");
                try {
                    datecheck = Integer.valueOf(filterInput1);
                    if (datecheck < 1 || datecheck > 31) {
                        System.out.println("Wrong input specified! Please, next time enter only day of the film you want to filter in range 1 - 31!");
                    }
                    else {
                        filterInput1 = "01/" + filterInput1 + "/1000";
                        filterDate1 = df.parse(filterInput1);
                        filtered = filterByDate(user, filterParameter, opInput, filterDate1, null);
                    }
                } catch (Exception ex) {
                    System.out.println("Wrong input specified! Please, next time enter only day of the film you want to filter");
                }
            } else {
                System.out.println("Please, enter the value according to which you want to filter in the format of MM/DD/YYYY");
                filterInput1 = sc.nextLine().replaceAll(" ", "");
                try {
                    filterDate1 = df.parse(filterInput1);
                    filtered = filterByDate(user, filterParameter, opInput, filterDate1, null);
                } catch (Exception ex) {
                    System.out.println("Wrong input specified! Please, next time enter only numeric values in the format of MM/DD/YYYY!");
                }
            }
        } else if (filterParameter.equals("TITLE") ||
                filterParameter.equals("OVERVIEW") ||
                filterParameter.equals("ORIGINALLANGUAGE") ||
                filterParameter.equals("POSTERURL")) {
            System.out.println("Please, enter the parameter by which you want to filter");
            System.out.println("\t> Starts With");
            System.out.println("\t> Ends With");
            System.out.println("\t> Contains");
            System.out.println("\t> Null (For Missed Values)");
            opInput = sc.nextLine().replaceAll(" ", "").toLowerCase();
            if (opInput.equals("null")) {
                filtered = filterByString(user, filterParameter, opInput, "");
            } else {
                System.out.println("Please, enter the value according to which you want to filter");
                filterInput1 = sc.nextLine();
                filtered = filterByString(user, filterParameter, opInput, filterInput1);
            }
        } else if (filterParameter.toLowerCase().contains("genre")) {
            System.out.println("Please, enter the parameter you by which you want to filter");
            System.out.println("\t> Starts With");
            System.out.println("\t> Ends With");
            System.out.println("\t> Contains");
            System.out.println("\t> Null (For Missed Values)");
            opInput = sc.nextLine().replaceAll(" ", "").toLowerCase();
            if (opInput.equals("null")) {
                filtered = filterByString(user, "Genre", opInput, "");
            } else if (opInput.equals("contains")) {
                System.out.println(
                        "Please, enter the Genre(s) of the film you want to filter on one line seperated by SPACE");
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
                filterInput = sc.nextLine().split(" ");
                if (filterInput.length > 0) {
                    filtered = filterByString(user, filterParameter, opInput, filterInput[0]);
                    for (int i = 1; i < filterInput.length; i++) {
                        filtered = filterByString(filtered, filterParameter, opInput, filterInput[i]);
                    }
                }
            } else {
                System.out.println("Please, enter the value according to which you want to filter");
                filterInput1 = sc.nextLine();
                filtered = filterByString(user, filterParameter, opInput, filterInput1);
            }
        } else {
            System.out.println(
                    "Wrong field specified! Please, next time enter only the given names or the number corresponding to them!");
        }

        return filtered;

    }

    public static ArrayList<Movie> filterByDouble(ArrayList<Movie> movie, String type, String oper, Double input,
            Double input1) {
        if (oper.equals("equal")) {
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
        } else if (oper.equals("greater")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Double) field.get(s)).compareTo(input) > 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("less")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Double) field.get(s)).compareTo(input) < 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("greaterequal")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Double) field.get(s)).compareTo(input) >= 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("lessequal")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Double) field.get(s)).compareTo(input) <= 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("between")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Double) field.get(s)).compareTo(input) > 0
                            && ((Double) field.get(s)).compareTo(input1) < 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("null")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return field.get(s) == null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else {
            System.out.println("Wrong parameter specified! Please, next time enter only given parameters!");
            return movie;
        }
    }

    public static ArrayList<Movie> filterByString(ArrayList<Movie> movie, String type, String oper, String input) {
        if (oper.equals("startswith")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((String) field.get(s)).startsWith(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("endswith")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((String) field.get(s)).endsWith(input);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("contains")) {
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
        } else if (oper.equals("null")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((String) field.get(s)).equals("");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else {
            System.out.println("Wrong parameter specified! Please, next time enter only given parameters!");
            return movie;
        }
    }

    public static ArrayList<Movie> filterByDate(ArrayList<Movie> movie, String type, String oper, Date input,
            Date input1) {
        if (oper.equals("equal")) {
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
        } else if (oper.equals("greater")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Date) field.get(s)).compareTo(input) > 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("less")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Date) field.get(s)).compareTo(input) < 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        }
        else if (oper.equals("greaterequal")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Date) field.get(s)).compareTo(input) >= 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        }
        else if (oper.equals("lessequal")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Date) field.get(s)).compareTo(input) <= 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        }
        else if (oper.equals("between")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return ((Date) field.get(s)).compareTo(input) > 0 && ((Date) field.get(s)).compareTo(input1) < 0;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        }
        else if (oper.equals("specificyear")) {
            System.out.println("Operation succesfully done!");
            SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return getYearFormat.format((Date) field.get(s)).equals(getYearFormat.format(input));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("specificmonth")) {
            System.out.println("Operation succesfully done!");
            SimpleDateFormat getMonthFormat = new SimpleDateFormat("MM");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return getMonthFormat.format((Date) field.get(s)).equals(getMonthFormat.format(input));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        } else if (oper.equals("specificday")) {
            System.out.println("Operation succesfully done!");
            SimpleDateFormat getDayFormat = new SimpleDateFormat("dd");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return getDayFormat.format((Date) field.get(s)).equals(getDayFormat.format(input));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());

        } else if (oper.equals("null")) {
            System.out.println("Operation succesfully done!");
            return (ArrayList<Movie>) movie.stream().filter(s -> {
                try {
                    Field field = Movie.class.getDeclaredField(type);
                    field.setAccessible(true);
                    return field.get(s) == null;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return false;
            }).collect(Collectors.toList());
        }
        else {
            System.out.println("Wrong parameter specified! Please, next time enter only given parameters!");
            return movie;
        }
    }

}
