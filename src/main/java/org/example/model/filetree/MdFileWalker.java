package org.example.model.filetree;


import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;



/**
 * a class that traverses a directory and compiles all the md files into a list
 */
public class MdFileWalker implements FileVisitor<Path> {

    private ArrayList<File> fileList;

    public MdFileWalker(ArrayList<File> contents) {
        this.fileList = contents;
    }

    /**
     * @return an ArrayList of sorted .md files
     */
    public ArrayList<File> getFileList() {
        System.out.println("\n\n");
        for (File s : fileList) {
            System.out.println(s);
        }
        return this.fileList;
    }

    /**
     * @param dir   a reference to the directory
     * @param attrs the directory's basic attributes
     * @return CONTINUE traversing the directory
     * @throws IOException if the file is not found
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.format("Starting Directory: %s%n", dir);

        ArrayList<String> filesInDir = new ArrayList<>();
        for (String s : dir.toFile().list()) {
            filesInDir.add(s);
        }
        return CONTINUE;
    }

    /**
     * @param file  a reference to the file
     * @param attrs the file's basic attributes
     * @return CONTINUE traversing the directory + add to the list of .md files
     * @throws IOException throws an IO exception
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isRegularFile()) {
            String fileName = file.getFileName().toString();
            if (fileName.endsWith(".md")) {

                fileList.add(file.toFile());
                System.out.println(fileName);
            }
        }

        return CONTINUE;
    }

    /**
     * @param file a reference to the file
     * @param exc  the I/O exception that prevented the file from being visited
     * @return CONTINUE traversing the directory
     * @throws IOException if file is not found
     */

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        exc.printStackTrace();
        return CONTINUE;
    }

    /**
     * @param dir a reference to the directory
     * @param exc {@code null} if the iteration of the directory completes without
     *            an error; otherwise the I/O exception that caused the iteration
     *            of the directory to complete prematurely
     * @return resets the count for the next directory to visit;
     * @throws IOException if file is not found
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}