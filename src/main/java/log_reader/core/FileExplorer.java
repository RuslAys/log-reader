package log_reader.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileExplorer {
    public FileHolder findTextInFile(File file, String text){
        FileHolder fileHolder = new FileHolder();
        List<Position> indexes = new LinkedList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(file.getAbsolutePath()))){
            String line;
            int row = 0;
            while ((line = bf.readLine()) != null){
                int diff = line.length() - text.length();
                int index = 0;
                while (index <= diff){
                    int column = line.indexOf(text, index);
                    if(column == -1){
                        index++;
                    }else{
                        indexes.add(new Position(row, column));
                        index = column + text.length();
                    }
                }
                row++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        fileHolder.setFile(file);
        fileHolder.setIndexes(indexes);
        return fileHolder;
    }
}
