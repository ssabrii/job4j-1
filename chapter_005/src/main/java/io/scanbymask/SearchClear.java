package io.scanbymask;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

import static io.scanbymask.Extension.checkExtension;

/**
 * SearchClear.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/6/2019
 */
class SearchClear {
    /**
     * field list.
     */
    private final List<File> list = new ArrayList<>();

    /**
     * Method files, find file extension without recursion.
     *
     * @param parent source folder
     * @param ext    source extension
     * @return required files
     */
    public final List<File> files(final File parent, final List<String> ext) {
        Queue<File> fileTree = new PriorityQueue<>();
        Collections.addAll(fileTree,
                Objects.requireNonNull(parent.listFiles()));
        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree,
                        Objects.requireNonNull(currentFile.listFiles()));
            } else {
                if (checkExtension(currentFile, ext)) {
                    this.list.add(currentFile);
                }
            }
        }
        return list;
    }

}
