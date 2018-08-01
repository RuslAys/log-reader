package log_reader;

import log_reader.core.DirectoryExplorer;

public class Main {
    public static void main(String[] args) {
        DirectoryExplorer explorer = new DirectoryExplorer();
        explorer.explore("/home/ruslan/Desktop/test", ".log");
    }
}
