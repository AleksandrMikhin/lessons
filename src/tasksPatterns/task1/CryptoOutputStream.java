package tasksPatterns.task1;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CryptoOutputStream extends FilterOutputStream {
    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */

    private byte[] passArray;
    private long writeByte = 0;

    public CryptoOutputStream(OutputStream out, String password) {
        super(out);
        this.passArray = password.getBytes();
    }

    @Override
    public void write(int b) throws IOException {
        writeByte++;
        super.write(b ^ passArray[(int) (writeByte % passArray.length)]);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {

        for (int i = 0; i < len - off; i++) {
            writeByte++;
            b[i - off] ^= passArray[(int) (writeByte % passArray.length)];
        }
        super.write(b, off, len);
    }
}

