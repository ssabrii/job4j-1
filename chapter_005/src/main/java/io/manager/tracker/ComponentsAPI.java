package io.manager.tracker;

import com.google.common.collect.Lists;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Components.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/26/2019
 */
public class ComponentsAPI {
    /**
     * field keeper current server paths by parts.
     */
    private List<String> components = new ArrayList<>();

    /**
     * Inner method collect the current server path.
     *
     * @return current path by string
     */
    private String getPath() {
        StringBuilder path = new StringBuilder();
        IntStream.range(0, this.components.size())
                .forEach(i -> path.append(this.components.get(i)).append("/"));
        return path.deleteCharAt(path.length() - 1).toString();
    }

    /**
     * Method collect and get server path by enter name directory.
     *
     * @param fileName filename
     * @return current server path
     */
    public final String collectByName(final String fileName) {
        var warn = "";
        this.components.add(fileName);
        var path = getPath();
        if (!Files.exists(Paths.get(path))) {
            warn = "Refuse.Illegal path.@";
            this.components.remove(this.components.get(
                    this.components.size() - 1));
            path = getPath();
        }
        return warn + path;
    }

    /**
     * Method collect and get server path by offset.
     *
     * @param offset offset
     * @return current server path
     */
    public final String collectByOffset(final int offset) {
        var path = "";
        var warn = "";
        final var rootSize = 9;
        final var compSize = components.size() - offset;
        if (compSize < rootSize) {
            warn = "Refuse.Root.@";
            path = getPath();
        } else {
            IntStream.range(compSize, components.size())
                    .forEach(i -> components.remove(i));
            path = getPath();
        }
        return warn + path;
    }

    /**
     * Method set root path to keeper server path.
     *
     * @param path split server root path
     */
    public final void setPathComponents(final String[] path) {
        components = Lists.newArrayList(path);

    }
}
