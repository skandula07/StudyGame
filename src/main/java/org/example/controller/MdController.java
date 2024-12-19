package org.example.controller;

import org.example.model.filetree.MdFileReader;
import org.example.model.filetree.MdFileWalker;
import org.example.model.filetree.MdFileWriter;
import org.example.model.sort.OrderingFlag;
import org.example.model.sort.SortByDateAdded;
import org.example.model.sort.SortByLastModified;
import org.example.model.sort.SortByName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * class that helps control the creation and writing of a .md study guide file
 */
public class MdController {

    private Path directory;
    private Path destination;
    private ArrayList<File> contents;
    private OrderingFlag flag;

    private MdFileWalker fileWalker;
    private MdFileReader fileReader;
    private MdFileWriter fileWriter;

    /**
     *
     * @param path The path of the chosen directory
     * @param order the order of which the study guide's contents will be formatted
     * @param dest the ending destination the study guide will be saved to
     */
    public MdController(String path, String order, String dest) {
        this.directory = Path.of(path);
        this.flag = flag.getFlag(order);
        this.destination = Path.of(dest);
        this.contents = new ArrayList<>();
        this.fileWalker = new MdFileWalker(contents);
    }

    /**
     * begins the writing process of an .md study guide
     */
    public void start() {
        try {
            Files.walkFileTree(directory, fileWalker);
            this.contents = sortList(fileWalker.getFileList());
            this.fileReader = new MdFileReader(contents);
            StringBuilder studyGuideContents = fileReader.read();
            fileWriter = new MdFileWriter(studyGuideContents, destination);
            fileWriter.write();
        } catch (IOException e) {
        }
    }


    public void studySession() {
        try {
            Files.walkFileTree(directory, fileWalker);
            this.contents = sortList(fileWalker.getFileList());
            this.fileReader = new MdFileReader(contents);
            StringBuilder studyGuideContents = fileReader.read();
            fileWriter = new MdFileWriter(studyGuideContents, destination);
            fileWriter.write();
        } catch (IOException e) {
        }
    }


    /**
     * @return a list of all the .md files in the directory
     */
    private ArrayList<File> sortList(ArrayList<File> fileList) {
        System.out.println(flag);

        if (flag.equals(OrderingFlag.filename)) {
            SortByName byName = new SortByName();
            Collections.sort(fileList, byName);
             System.out.println(fileList);

            return fileList;

        } else if (flag.equals(OrderingFlag.creation)) {
            SortByDateAdded byDate = new SortByDateAdded();
            Collections.sort(fileList, byDate);
            System.out.println(fileList);

            return fileList;

        } else if (flag.equals(OrderingFlag.modified)) {
            SortByLastModified byModified = new SortByLastModified();
            Collections.sort(fileList, byModified);
            System.out.println(fileList);

            return fileList;
        } else {
            throw new RuntimeException();
        }
    }
}
