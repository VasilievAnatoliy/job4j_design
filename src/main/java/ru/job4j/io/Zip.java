package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * -d - directory - которую мы хотим архивировать.
     * -e - exclude - исключить файлы с расширением class.
     * -o - output - во что мы архивируем.
     * пример работы из консоли:
     * java -jar pack.jar -d=c:\project\job4j\ -e=class -o=project.zip
     */
    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
         List<File> files = new ArrayList<>();
        for (Path path : Search.search(
                Path.of(arguments.get("d")),
                x -> !x.toFile().getName().endsWith(arguments.get("e")))) {
            files.add(path.toFile());
        }
        packFiles(files, new File(arguments.get("o")));

    }
}
