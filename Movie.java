import java.text.*;
import java.util.*;
import java.lang.reflect.*;

public class Movie {

    public static TreeMap<String, String> moviefields = new TreeMap<>();

    static {

        moviefields.put("1", "ID");
        moviefields.put("2", "RELEASEDATE");
        moviefields.put("3", "TITLE");
        moviefields.put("4", "OVERVIEW");
        moviefields.put("5", "POPULARITY");
        moviefields.put("6", "VOTECOUNT");
        moviefields.put("7", "VOTEAVERAGE");
        moviefields.put("8", "ORIGINALLANGUAGE");
        moviefields.put("9", "GENRE");
        moviefields.put("10", "POSTERURL");

    }


    private Double ID;
    private Date RELEASEDATE;
    private String TITLE;
    private String OVERVIEW;
    private Double POPULARITY;
    private Double VOTECOUNT;
    private Double VOTEAVERAGE;
    private String ORIGINALLANGUAGE;
    private String GENRE;
    private String POSTERURL;

    
    public static TreeMap<String, String> headers = new TreeMap<>();

    static {

        headers.put( "ID", "Id");
        headers.put("RELEASEDATE", "Release_Date");
        headers.put("TITLE", "Title");
        headers.put( "OVERVIEW", "Overview");
        headers.put( "POPULARITY", "Popularity");
        headers.put( "VOTECOUNT", "Vote_Count");
        headers.put( "VOTEAVERAGE", "Vote_Average");
        headers.put("ORIGINALLANGUAGE", "Original_Language");
        headers.put("GENRE", "Genre");
        headers.put( "POSTERURL", "Poster_Url");

    }

    public Movie(Double ID, Date RELEASEDATE, String TITLE, String OVERVIEW, double POPULARITY, Double VOTECOUNT,
            double VOTEAVERAGE, String ORIGINALLANGUAGE, String GENRE, String POSTERURL) {
        this.ID = ID;
        this.RELEASEDATE = RELEASEDATE;
        this.TITLE = TITLE;
        this.OVERVIEW = OVERVIEW;
        this.POPULARITY = POPULARITY == -1.0 ? null : POPULARITY;
        this.VOTECOUNT = VOTECOUNT == -1.0 ? null : VOTECOUNT;
        this.VOTEAVERAGE = VOTEAVERAGE == -1.0 ? null : VOTEAVERAGE;
        this.ORIGINALLANGUAGE = ORIGINALLANGUAGE;
        this.GENRE = GENRE;
        this.POSTERURL = POSTERURL;
    }

    public String toString() {

        Field[] fields = Movie.class.getDeclaredFields();
        SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy");
        String rls;
        String movieString = "";

        try {
            for (int i = 2; i <= 10; i++) {
                if (i == 2) {
                    rls = st.format(RELEASEDATE);
                    movieString += rls + ",";
                } else {
                    movieString += fields[i].get(this) + ",";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return movieString;

    }

    public String toString(TreeSet<Integer> ts) {

        Field[] fields = Movie.class.getDeclaredFields();
        Field fl;
        SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy");
        String rls;
        String movieString = "";

        try {
            for (int i = 1; i <= 10; i++) {
                if (ts.contains(i)) {
                    fl = fields[i];
                    if (i == 2) {
                        rls = st.format(RELEASEDATE);
                        movieString = movieString + headers.get(fl.getName()) + ": " + rls + "\n";
                    } else {
                        movieString = movieString + headers.get(fl.getName()) + ": " + fl.get(this) + "\n";
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return movieString;
    }

}