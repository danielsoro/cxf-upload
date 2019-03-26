package org.apache.tomee.sample;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.activation.DataHandler;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@ApplicationScoped
public class UploadService {

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String testUpload(Attachment anexo) {
        DataHandler handler = anexo.getDataHandler();
        try {
            InputStream stream = handler.getInputStream();
            MultivaluedMap<String, String> map = anexo.getHeaders();
            System.out.println("fileName Here" + getFileName(map));
            System.out.println("fileDir Here: " + System.getProperty("java.io.tmpdir") + getFileName(map));
            OutputStream out = new FileOutputStream(new File(System.getProperty("java.io.tmpdir") + getFileName(map)));

            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = stream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            stream.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "UPLOAD OK. :)";
    }

    private String getFileName(MultivaluedMap<String, String> header) {
        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {
                String[] name = filename.split("=");
                String exactFileName = name[1].trim().replaceAll("\"", "");
                return exactFileName;
            }
        }
        return "unknown";
    }

}
