package log_reader;

import log_reader.core.DirectoryExplorer;
import log_reader.core.FileExplorer;
import log_reader.core.FileHolder;
import log_reader.core.Position;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DirectoryExplorer explorer = new DirectoryExplorer();
        List<FileHolder> holder =
                explorer.getAllFilesWithText(
                        "/home/ruslan/Desktop/test",
                        ".log",
                        "te");

        System.out.println(holder);
    }
}
