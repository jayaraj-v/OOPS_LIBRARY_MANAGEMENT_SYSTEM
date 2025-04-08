package LibraryManagementSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class DataFetcher
{
    private static final String lib_fileName="LibrarianData.txt";
    private static final String stud_fileName="StudentData.txt";
    private static final String teach_fileName="teacherData.txt";
    public DataFetcher(String loginer, Map<String,String> map)
    {
        switch(loginer)
        {
            case "lb":
                fetchData(map,lib_fileName);
                break;
            case "st":
                fetchData(map,stud_fileName);
                break;
            case "th":
                fetchData(map,teach_fileName);
                break;
        }
    }
    private static void fetchData(Map<String,String> map,String fileName)
    {
        File file=new File(fileName);
        if(!file.exists())
        {
            System.out.println("No previous datas found!");
            return;
        }
        try(BufferedReader br=new BufferedReader(new FileReader(lib_fileName)))
        {
            String line;
            while((line=br.readLine())!=null)
            {
                String[] kv=line.split(",");
                if(kv.length==2) map.put(kv[0],kv[1]);
            }
        }
        catch(IOException e)
        {
            System.out.println("Error in Loading datas!");
            return;
        }
    }
}