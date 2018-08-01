package log_reader.core;

import java.io.File;
import java.util.List;

public class FileHolder {
    private File file;
    private List<Integer> indexes;

    public FileHolder(File file, List<Integer> indexes) {
        this.file = file;
        this.indexes = indexes;
    }

    public FileHolder() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Integer> indexes) {
        this.indexes = indexes;
    }
}
