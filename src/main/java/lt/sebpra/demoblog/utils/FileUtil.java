package lt.sebpra.demoblog.utils;

import org.apache.commons.io.FilenameUtils;

public class FileUtil {
    private final String COMMA_SEPARATOR = ".";
    public String renameToUUID(String fullFileName) {
        return new Generator().getUUID() + COMMA_SEPARATOR + FilenameUtils.getExtension(fullFileName);
    }
}
