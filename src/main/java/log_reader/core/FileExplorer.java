package log_reader.core;

import javafx.scene.control.ListView;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class FileExplorer {
    public FileHolder findTextInFile(InputStreamReader isr, String text, Object file){
        FileHolder fileHolder = new FileHolder();
        List<Integer> indexes = new LinkedList<>();
        try(BufferedReader bf = new BufferedReader(isr)){
            String line;
            int index = 0;
            while ((line = bf.readLine()) != null){
                if(line.contains(text)) indexes.add(index);
                index++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        if(file instanceof File){
            fileHolder.setFile((File) file);
        }else{
            fileHolder.setSmbFile((SmbFile) file);
        }
        fileHolder.setIndexes(indexes);
        return fileHolder;
    }

    public void addTextToListView(InputStreamReader isr, ListView<String> listView){
        try(BufferedReader br = new BufferedReader(isr)){
            String s;
            while ((s = br.readLine()) != null){
                listView.getItems().add(s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public SmbFile readSharedFolder(String ipAddrees,
                                 String folderName,
                                 String domain,
                                 String username,
                                 String password){
        folderName = checkFolderName(folderName);

        try{
            NtlmPasswordAuthentication authenticator =
                    new NtlmPasswordAuthentication(domain, username, password);
            String path = "smb://" + ipAddrees + folderName;

            SmbFile baseDir = new SmbFile(path, authenticator);
            return baseDir;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String checkFolderName(String folderName){
        if(folderName.charAt(0) != '/') folderName = "/" + folderName;
        if(!folderName.endsWith("/")) folderName = folderName + "/";
        folderName = folderName.replace("\\", "/");
        return folderName;
    }
}
