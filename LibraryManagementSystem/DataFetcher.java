package LibraryManagementSystem;

import java.io.*;
import java.util.Map;

public class DataFetcher
{
    public DataFetcher(String userType, Map<String, String> map)
    {
        String fileName = switch (userType)
        {
            case "lb" -> "data/LibrarianData.txt";
            case "st" -> "data/StudentData.txt";
            case "th" -> "data/TeacherData.txt";
            default -> null;
        };
        if (fileName != null) fetchData(map, fileName);
    }
    private void fetchData(Map<String, String> map, String fileName)
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            System.out.println("No previous data found!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] kv = line.split(",");
                if (kv.length == 2) map.put(kv[0], kv[1]);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error in loading data: " + e.getMessage());
        }
    }
}
