package org.example.model.filetree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class MdFileWriter {

    private String content;
    private File output;
    public MdFileWriter(StringBuilder toWrite, Path writeTo) {
        this.content = toWrite.toString();
        this.output = convertPath(writeTo);
    }

    public File convertPath(Path p) {
        if (p.isAbsolute()) {
            if (!p.toFile().getName().endsWith(".md")) {
                p = Path.of(p + "/studyGuide.md");
            }
        } else {
            if (!p.toFile().getName().endsWith(".md")) {
                p = Path.of(p + "/studyGuide.md");
            }
        }
        return p.toFile();
    }


    /**
     *
     * @return A fully written .md file of
     * @throws IOException if file is not found
     */
    public File write() {
        try {
            FileWriter writer = new FileWriter(output);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
