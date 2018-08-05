package log_reader.core;

import java.io.File;
import java.util.List;

public class FileHolder {
    private File file;
    private List<Position> indexes;

    public FileHolder(File file, List<Position> indexes) {
        this.file = file;
        this.indexes = indexes;
    }

    public FileHolder() {
    }

    public FileHolder(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Position> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Position> indexes) {
        this.indexes = indexes;
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
