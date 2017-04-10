package com.wangtao.blog.common.util.base64;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName:BASE64Encoder
 * @author: KarlWang
 * @Description: TODO(字符串加密工具类) 
 * @date:2017年4月10日 下午2:47:01
 * @see com.wangtao.blog.common.util.base64.BASE64Encoder
 */
public class BASE64Encoder extends CharacterEncoder {

    /** This array maps the characters to their 6 bit values */
    private static final char PEM_ARRAY[] = {
        //       0   1   2   3   4   5   6   7
                'A','B','C','D','E','F','G','H', // 0
                'I','J','K','L','M','N','O','P', // 1
                'Q','R','S','T','U','V','W','X', // 2
                'Y','Z','a','b','c','d','e','f', // 3
                'g','h','i','j','k','l','m','n', // 4
                'o','p','q','r','s','t','u','v', // 5
                'w','x','y','z','0','1','2','3', // 6
                '4','5','6','7','8','9','+','/'  // 7
        };

    /** this class encodes three bytes per atom. */
    protected int bytesPerAtom() {
        return (3);
    }

    /**
     * this class encodes 57 bytes per line. This results in a maximum
     * of 57/3 * 4 or 76 characters per output line. Not counting the
     * line termination.
     */
    protected int bytesPerLine() {
        return (57);
    }

    /**
     * encodeAtom - Take three bytes of input and encode it as 4
     * printable characters. Note that if the length in len is less
     * than three is encodes either one or two '=' signs to indicate
     * padding characters.
     */
    protected void encodeAtom(OutputStream outStream, byte data[], int offset, int len)
        throws IOException {
        byte a, b, c;

        if (len == 1) {
            a = data[offset];
            b = 0;
            c = 0;
            outStream.write(PEM_ARRAY[(a >>> 2) & 0x3F]);
            outStream.write(PEM_ARRAY[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStream.write('=');
            outStream.write('=');
        } else if (len == 2) {
            a = data[offset];
            b = data[offset+1];
            c = 0;
            outStream.write(PEM_ARRAY[(a >>> 2) & 0x3F]);
            outStream.write(PEM_ARRAY[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStream.write(PEM_ARRAY[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            outStream.write('=');
        } else {
            a = data[offset];
            b = data[offset+1];
            c = data[offset+2];
            outStream.write(PEM_ARRAY[(a >>> 2) & 0x3F]);
            outStream.write(PEM_ARRAY[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
            outStream.write(PEM_ARRAY[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
            outStream.write(PEM_ARRAY[c & 0x3F]);
        }
    }
    
}
