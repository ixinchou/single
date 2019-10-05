package com.ixchou.util.http.request;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/05 18:37<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 重写的RequestWrapper以获取 body 数据<br/>
 * <b>Description</b>:
 */
public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private static final int BUFFER_SIZE = 1024 * 8;
    private final String body;

    public CustomRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = read(request);
    }

    private static String read(HttpServletRequest request) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        StringWriter writer = new StringWriter();
        long l = write(bufferedReader, writer);
        return writer.getBuffer().toString();
    }

    private static long write(Reader reader, Writer writer) throws IOException {
        int read;
        long total = 0;
        char[] buf = new char[BUFFER_SIZE];
        while ((read = reader.read(buf)) != -1) {
            writer.write(buf, 0, read);
            total += read;
        }
        return total;
    }

    public String getBody() {
        return body;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
