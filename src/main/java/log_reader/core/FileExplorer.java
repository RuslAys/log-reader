package log_reader.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileExplorer {
    public List<Integer> findText(File file, String text){
        List<Integer> indexes = new LinkedList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(file.getName()))){
            String line;
            int index = 0;
            while ((line = bf.readLine()) != null){
                index++;
                if(line.contains(text)){
                    indexes.add(index);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return indexes;
    }
}
