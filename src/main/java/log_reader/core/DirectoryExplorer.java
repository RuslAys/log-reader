package log_reader.core;

import java.io.File;

public class DirectoryExplorer {
    FileExplorer fileExplorer = new FileExplorer();
    public void explore(String path, String ext){
        if(ext.charAt(0) != '.') ext = "." + ext;

        File file = new File(path);
        String[] dirList = file.list();

        for(String dir: dirList){
            File f = new File(path + File.separator + dir);

            if(f.isFile()){
                if(checkExtension(f, ext)) {
                    System.out.println(f.getPath());
                    System.out.println(fileExplorer.findText(f, "text test"));
                }
            }else {
                explore(path + File.separator + dir, ext);
            }
        }
    }

    private boolean checkExtension(File f, String ext){
        return f.getName().endsWith(ext);
    }
}
