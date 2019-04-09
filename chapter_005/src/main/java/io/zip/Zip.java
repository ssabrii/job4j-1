package io.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/7/2019
 */
public class Zip {
    /**
     * field args.
     */
    private final String[] args;

    /**
     * Constructor.
     *
     * @param aArgs args
     */
    public Zip(final String[] aArgs) {
        this.args = aArgs;
    }

    /**
     * Method check args.
     *
     * @return result
     */
    private boolean checkArgs() {
        return this.args.length <= 0;
    }

    /**
     * Method get path parent directory.
     *
     * @return parent directory
     */
    private File directory() {
        if (this.checkArgs()) {
            throw new InvalidParameterException();
        }
        String directory = getParameter("-d");
        return new File(directory);
    }

    /**
     * Method get path output file.
     *
     * @return output file
     */
    private File output() {
        if (this.checkArgs()) {
            throw new InvalidParameterException();
        }
        String output = getParameter("-o");
        return new File(output);
    }

    /**
     * Method get the extension of file for exclude.
     *
     * @return the extension of file
     */
    private String exclude() {
        if (this.checkArgs()) {
            throw new InvalidParameterException();
        }
        String parameter = getParameter("-e");
        String[] split = parameter.split("\\.");
        return split[1];
    }

    /**
     * Method find and argument from key.
     *
     * @param key key
     * @return parameter
     */
    private String getParameter(final String key) {
        String parameter = "";
        for (int i = 0; i < this.args.length; i++) {
            if (this.args[i].equals(key)) {
                parameter = this.args[i + 1];
                break;
            }
        }
        return parameter;
    }

    /**
     * Method check file for exclude.
     *
     * @param fileToZip file to zip
     * @param regex     regex for exclude
     * @return result
     */
    private boolean checkFile(final File fileToZip, final String regex) {
        return fileToZip.getName().endsWith(regex);
    }

    /**
     * Method zip file from exclude.
     *
     * @param fileToZip directory to zip
     * @param fileName  string name directory to zip
     * @param zipOut    the way of output
     * @param regex     regex for exclude
     * @throws IOException exception
     */
    private void zipFile(final File fileToZip,
                         final String fileName,
                         final ZipOutputStream zipOut,
                         final String regex) throws IOException {
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            }
            File[] children = Objects.requireNonNull(fileToZip.listFiles());
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(),
                        zipOut, regex);
            }
            return;
        }
        if (!(this.checkFile(fileToZip, regex))) {
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            final var vol = 1024;
            byte[] bytes = new byte[vol];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
    }

    /**
     * Method zip file.
     *
     * @throws IOException exception
     */
    public final void archive() throws IOException {
        final File parent = this.directory();
        final File zipFile = this.output();
        final String exclude = this.exclude();
        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        this.zipFile(parent, parent.getName(), zipOut, exclude);
        zipOut.close();
        fos.close();
    }

    /**
     * Method main.
     *
     * @param args args
     * @throws IOException exception
     */
    public static void main(final String[] args) throws IOException {
        new Zip(args).archive();
    }
}



