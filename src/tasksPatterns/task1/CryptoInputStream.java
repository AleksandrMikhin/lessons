package tasksPatterns.task1;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CryptoInputStream extends FilterInputStream {

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     * this instance is to be created without an underlying stream.
     */
    private byte[] passArray;
    private long readByte = 0;

    public CryptoInputStream(InputStream in, String password) {
        super(in);
        this.passArray = password.getBytes();
    }

    @Override
    public int read() throws IOException {
        int res = super.read();
        if (res >= 0) readByte++;
        return res ^ passArray[(int) (readByte % passArray.length)];
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException{
        int res = super.read(b, off, len);

        if (res >= 0) {
            for (int i = 0; i < res; i++) {
                readByte++;
                b[i - off] ^= passArray[(int) (readByte % passArray.length)];
            }
        }
        return res;

    }
}