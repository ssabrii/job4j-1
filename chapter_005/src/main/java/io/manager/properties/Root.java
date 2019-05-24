package io.manager.properties;

/**
 * Root.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 5/10/2019
 */
public class Root {
    /**
     * field path root.
     */
    private final String root;

    /**
     * Constructor.
     */
    public Root() {
        this.root = getUserDir() + "src/main/java/io/manager";
    }

    /**
     * Method gets the current root path of the server.
     *
     * @return server root path
     */
    public final String getRootCatalog() {
        return this.root;
    }

    /**
     * Method gets the path user dir for any OS.
     *
     * @return path
     */
    public final String getUserDir() {
        final var slash = System.getProperty("file.separator");
        var dir = System.getProperty("user.dir");
        if (!dir.endsWith(slash)) {
            dir += slash;
        }
        if (dir.contains("\\")) {
            dir = dir.replace('\\', '/');
        }
        if (!dir.contains("chapter_005")) {
            dir += "chapter_005/";
        }
        return dir;
    }

}

