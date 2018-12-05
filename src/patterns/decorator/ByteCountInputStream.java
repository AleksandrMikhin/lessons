package patterns.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteCountInputStream extends FilterInputStream {

    private long readByte;

//    protected ByteCountInputStream(InputStream in, String password) { для домашнего
    protected ByteCountInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int res = super.read();
        if (res >= 0) readByte++;
        return res;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int res = super.read(b, off, len);
        if (res >= 0) readByte += res;
        return res;

    }
}
