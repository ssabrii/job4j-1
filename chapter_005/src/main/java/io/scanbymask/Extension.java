package io.scanbymask;

import java.io.File;
import java.util.List;

/**
 * Extension.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/7/2019
 */
public final class Extension {
    /**
     * Constructor.
     */
    private Extension() {
    }

    /**
     * Method check extension file.
     *
     * @param file source file
     * @param ext  source extension files
     * @return result
     */
    public static boolean checkExtension(final File file,
                                         final List<String> ext) {
        String[] split = file.getName().split("\\.");
        for (String in : ext) {
            if (split[1].equals(in)) {
                return true;
            }
        }
        return false;
    }
}
