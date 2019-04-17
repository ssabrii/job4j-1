package io.manager.properties;

/**
 * Properties.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public interface Properties {
    /**
     * Method get port from property file.
     *
     * @return port
     */
    String port();

    /**
     * Method get ip from property file.
     *
     * @return ip
     */
    String ip();
    /**
     * Method gets the current root path of the server.
     *
     * @return server root path
     */
    String getRootCatalog();
}
