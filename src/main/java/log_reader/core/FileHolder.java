package log_reader.core;

import jcifs.smb.SmbFile;

import java.io.File;
import java.util.List;

public class FileHolder {
    private List<Integer> indexes;
    private String name;
    private SmbFile smbFile;
    private File file;

    public FileHolder(List<Integer> indexes, String name, SmbFile smbFile) {
        this.indexes = indexes;
        this.name = name;
        this.smbFile = smbFile;
    }

    public FileHolder(List<Integer> indexes, String name, File file) {
        this.indexes = indexes;
        this.name = name;
        this.file = file;
    }

    public FileHolder() {
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Integer> indexes) {
        this.indexes = indexes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SmbFile getSmbFile() {
        return smbFile;
    }

    public void setSmbFile(SmbFile smbFile) {
        this.smbFile = smbFile;
        name = smbFile.getName();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        name = file.getName();
    }

    @Override
    public String toString() {
        return name;
    }
}
