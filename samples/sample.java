import java.io.*;

public class FileManager {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter a file or directory path:");
        String path = reader.readLine();

        File file = new File(path);

        if (file.exists()) {
            System.out.println("Name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());

            if (file.isDirectory()) {
                System.out.println("Type: Directory");

                File[] files = file.listFiles();

                if (files != null) {
                    for (File f : files) {
                        System.out.println(f.getName());
                    }
                }
            } else {
                System.out.println("Type: File");
                System.out.println("Size: " + file.length() + " bytes");
            }

            System.out.println("Do you want to copy, move, or delete this file/directory? (c/m/d)");
            String choice = reader.readLine();

            if (choice.equals("c")) {
                System.out.println("Enter the destination directory:");
                String destPath = reader.readLine();
                copyFile(file, new File(destPath, file.getName()));
                System.out.println("File copied successfully.");
            } else if (choice.equals("m")) {
                System.out.println("Enter the destination directory:");
                String destPath = reader.readLine();
                moveFile(file, new File(destPath, file.getName()));
                System.out.println("File moved successfully.");
            } else if (choice.equals("d")) {
                deleteFile(file);
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("File/directory does not exist.");
        }
    }

    public static void copyFile(File source, File dest) throws IOException {
        if (source.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }

            String[] children = source.list();
            for (String child : children) {
                copyFile(new File(source, child), new File(dest, child));
            }
        } else {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

    public static void moveFile(File source, File dest) throws IOException {
        copyFile(source, dest);
        deleteFile(source);
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
                deleteFile(child);
            }
        }
        file.delete();
    }
}
