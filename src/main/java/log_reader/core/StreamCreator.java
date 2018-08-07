package log_reader.core;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class StreamCreator {
    public static InputStream getStream(Object o){
        try {
            if(o instanceof File) return new FileInputStream((File) o);
            else if(o instanceof SmbFile) return new SmbFileInputStream((SmbFile) o);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
