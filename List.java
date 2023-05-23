import java.util.*;

public class List {

    public static TreeSet<Integer> ts = new TreeSet<>();

    public static void listInterface(ArrayList<Movie> user) {
        System.out.println("Do you want to list all fields or specific field? Enter \"all\" or \"specific\"");
        Scanner sc = new Scanner(System.in);
        String listParameter = sc.nextLine().toLowerCase();
        if (listParameter.equals("all")) {
            ts.add(1);
            ts.add(2);
            ts.add(3);
            ts.add(4);
            ts.add(5);
            ts.add(6);
            ts.add(7);
            ts.add(8);
            ts.add(9);
            ts.add(10);
        } else if (listParameter.equals("specific")) {
            System.out.println("Please, specify fields that you want to print");
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
            System.out.println("Please, enter only integer value corresponding to the field you would like to choose in one line seperated by SPACE");
            String nline[] = sc.nextLine().split(" ");
            for (String nl : nline) {
                try {
                    int n = Integer.parseInt(nl);
                    if (n>=1 && n<=10)
                        ts.add(n);
                } catch (Exception ex) {
                   continue;
                }
            }
        }
        else {
            System.out.println("Wrong input specified! Please, next time enter only \"all\" or \"specific\"");
            return;
        }

        if (ts.isEmpty()) {
            System.out.println("Wrong fields entered! Please, next time enter only integer value corresponding to the field you would like to choose in one line seperated by SPACE!");
            return;
        }

        System.out.println("In which interval would you like to print?");
        System.out.println("\tIf you want to print by default just press ENTER. Otherwise enter the interval that you would like to get");
        String interval = sc.nextLine();
        String inter[], strhigh, strlow;
        int high = 0, low = Math.min(user.size() - 1, 99);
        if(!interval.isBlank()) {
            inter = interval.split(" ");
            strlow = inter[0];
            if(inter.length == 2)
                strhigh = inter[1];
            else
                strhigh = sc.nextLine().replaceAll(" ", "");
            try {
                low = Integer.parseInt(strlow);
                high = Integer.parseInt(strhigh);
                low--;
                high--;
            }
            catch (Exception ex) {
                System.out.println("Wrong input specified! Please, next time enter only numeric values!");
                return;
            }
        }

        if (user == null || user.size() == 0) {
            System.out.println("No movies found!");
            return;
        }

        print(user, Math.min(low, high), Math.max(low, high));
        ts.clear();
        
    }

    public static void print(ArrayList<Movie> movies, int low, int high) {
        
        int cnt = 0;

        if(low >= movies.size() || high < 0) {
            System.out.println("No movie found in the given interval!");
            return;
        }

        if(low < 0)
            low = 0;

        if(high >= movies.size())
            high = movies.size()-1;
        
        for(int i = low; i <= high; i++) { 
            System.out.println(movies.get(i).toString(ts));
            cnt++;
        }

        System.out.println("Total of " + movies.size() + " movies found, " + cnt + " number of movies printed");
    
    }
}
