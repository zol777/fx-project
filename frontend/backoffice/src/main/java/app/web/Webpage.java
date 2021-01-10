package app.web;

import core.framework.inject.Inject;
import core.framework.util.Files;
import core.framework.web.site.WebDirectory;

/**
 * @author ericchung
 */
public class Webpage { // wrapper for mock in unit test
    @Inject
    WebDirectory directory;

    public byte[] get(String path) {
        return Files.bytes(directory.path(path));
    }
}
