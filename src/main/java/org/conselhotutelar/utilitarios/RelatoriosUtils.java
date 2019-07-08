package org.conselhotutelar.utilitarios;

import org.conselhotutelar.enums.RelType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelatoriosUtils {

    private static Logger log = Logger.getLogger(String.valueOf(RelatoriosUtils.class));

    public static File geraPDFEmDisco(String fileName, RelType relType, byte[] data) {
        if (data == null) {
            return null;
        }
        OutputStream out = null;
        try {
            File file = File.createTempFile(fileName, "." + relType.getValue());
            out = new FileOutputStream(file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.write(data);
                    out.close();
                }
            } catch (IOException e) {
                log.log(Level.WARNING, e.getMessage());
            }
        }
        return null;
    }

}
