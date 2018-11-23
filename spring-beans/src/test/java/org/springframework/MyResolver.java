package org.springframework;

import org.jetbrains.annotations.NotNull;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;

public class MyResolver implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) {
        if (systemId.equals("http://www.myhost.com/today")) {
            Reader reader = new MyReader();
            return new InputSource(reader);
        } else {
            // use the default behaviour
            return null;
        }
    }

	private class MyReader extends Reader {
		@Override
		public int read(@NotNull char[] cbuf, int off, int len) throws IOException {
			return 0;
		}

		@Override
		public void close() throws IOException {

		}
	}
}