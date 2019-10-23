package com.springboot.hwzl.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtils {

    /**
     * InputStream轉String
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static String inputStreamToString(InputStream inStream) throws Exception {
      /*  BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        StringBuffer buffer = new StringBuffer();
        String line ;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();*/
        return new String(inputStreamToByte(inStream), "UTF-8");
    }

    /**
     * InputStream轉byte[]
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] inputStreamToByte(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }


}
