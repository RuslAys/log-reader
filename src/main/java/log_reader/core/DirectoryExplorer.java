package log_reader.core;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DirectoryExplorer {
    private List<FileHolder> holder = new LinkedList<>();

    FileExplorer fileExplorer = new FileExplorer();
    public List<FileHolder> getAllFilesWithText(String path, String ext, String text){
        if(ext.charAt(0) != '.') ext = "." + ext;

        File file = new File(path);
        String[] dirList = file.list();

        for(String dir: dirList){
            File f = new File(path + File.separator + dir);
            if(f.isFile()){
                if(checkExtension(f, ext)) {
                    List<Position> indexes = fileExplorer.findTextInFile(f, text);
                    if(indexes.size() != 0){
                        holder.add(new FileHolder(f, indexes));
                    }
                }
            }else {
                getAllFilesWithText(path + File.separator + dir, ext, text);
            }
        }
        return holder;
    }

    private boolean checkExtension(File f, String ext){
        return f.getName().endsWith(ext);
    }
}
