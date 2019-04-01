package io.checkeven;

import java.io.IOException;
import java.io.InputStream;

/**
 * CheckEven.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 3/10/2019
 */
public class CheckEven {
    /**
     * isNumber.
     *
     * @param in stream
     * @return boolean
     */
    public final boolean isNumber(final InputStream in) {
        final var up = 57;
        final var down = 48;
        final var fall = false;
        final var size = 32768;
        byte[] buffer = new byte[size];
        int countBytes;
        try {
            countBytes = in.read(buffer);
            if (countBytes == -1) {
                return fall;
            }
            if (buffer[countBytes - 1] % 2 == 0) {
                return fall;
            }
            //matches("-?[\\d]+");
            for (int i = 0; i < countBytes; i++) {
                if ((buffer[i] < down || buffer[i] > up)) {
                    return fall;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

