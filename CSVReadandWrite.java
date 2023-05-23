import java.io.*;
import java.text.*;
import java.util.*;

public class CSVReadandWrite {

    public static ArrayList<String> Columns(String line) {

        ArrayList<String> entry = new ArrayList<String>();
        int j = 0;
        boolean flag = false;

        for (int i = 0; i < line.length(); i++) {

            if (line.charAt(i) == ',' && !flag) {
                entry.add(line.substring(j, i));
                j = i + 1;
            }
            else if (line.charAt(i) == '\"') {
                flag = !flag;
            }

        }

        if (line.substring(j).equals(",")) {
            entry.add("");
        } else {
            entry.add(line.substring(j));
        }

        return entry;

    }

    public static ArrayList<Movie> CSVReader(String source) {
        ArrayList<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source));) {
            String line;
            Double Id = -1.0;

            while ((line = br.readLine()) != null) {

                ArrayList<String> entry = Columns(line);

                while (entry.size() != 9) {
                    line += "\n" + br.readLine();
                    entry = Columns(line);
                }

                Id++;
                if (Id >= 1) {
                    int x = -1;
                    String date = entry.get(++x);
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date Release_Date = null;
                    try {
                        Release_Date = date == "" ? null : df.parse(date);
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    String Title = entry.get(++x);
                    String Overview = entry.get(++x);
                    Double Popularity = entry.get(++x) == "" ? -1.0 : Double.parseDouble(entry.get(x));
                    Double Vote_Count = entry.get(++x) == "" ? -1.0 : Double.parseDouble(entry.get(x));
                    Double Vote_Average = entry.get(++x) == "" ? -1.0 : Double.parseDouble(entry.get(x));
                    String Original_Language = entry.get(++x);
                    String Genre = entry.get(++x);
                    String Poster_Url = entry.get(++x);
                    Movie mv = new Movie(Id, Release_Date, Title, Overview, Popularity, Vote_Count, Vote_Average,
                            Original_Language, Genre, Poster_Url);
                    movies.add(mv);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return movies;

    }

    public static void CSVWriter(ArrayList<Movie> movies, String location) {
        String source = location;
        if (!source.endsWith(".csv"))
            source += ".csv";
        File file = new File(source);
        if (file.getParentFile() != null)
            file.getParentFile().mkdirs();
        String header = "Release_Date,Title,Overview,Popularity,Vote_Count,Vote_Average,Original_Language,Genre,Poster_Url";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(header);
            bw.newLine();
            for (Movie mv : movies) {
                bw.write(mv.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(String.format("Error occurred while exporting CSV. %s", e.getMessage()));
        }
    }

}