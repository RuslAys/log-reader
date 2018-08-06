package log_reader.core;

import javafx.scene.control.ListView;
import jcifs.smb.NtlmAuthenticator;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.*;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

public class FileExplorer {
    public FileHolder findTextInFile(InputStreamReader isr, String text){
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
//        fileHolder.setFile(file);
//        fileHolder.setIndexes(indexes);
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

    public InputStreamReader readSharedFolder(String ipaddrees,
                                 String folderName,
                                 String domain,
                                 String username,
                                 String password){
        folderName = checkFolderName(folderName);

        try{
            NtlmPasswordAuthentication authenticator =
                    new NtlmPasswordAuthentication(domain, username, password);
            String path = folderName;

            SmbFile baseDir = new SmbFile(path, authenticator);
            SmbFileInputStream sfis = new SmbFileInputStream(baseDir);
            return new InputStreamReader(sfis);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String checkFolderName(String folderName){
        if(folderName.charAt(0) != '/') return "/" + folderName;
        return folderName;
    }
}
