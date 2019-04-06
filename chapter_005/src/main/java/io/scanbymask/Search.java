package io.scanbymask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.scanbymask.Extension.checkExtension;

/**
 * Search.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/6/2019
 */
class Search {
    /**
     * field list.
     */
    private final List<File> list = new ArrayList<>();

    /**
     * Method files, find file extension.
     *
     * @param parent source folder
     * @param ext    source extension
     * @return required files
     */
    public final List<File> files(final File parent, final List<String> ext) {
        Objects.requireNonNull(parent, "must not be null");
        Objects.requireNonNull(ext, "must not be null");
        File[] files = parent.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (checkExtension(file, ext)) {
                        this.list.add(file);
                    }
                } else if (file.isDirectory()) {
                    this.files(file, ext);
                }
            }
        }
        return list;
    }


}
