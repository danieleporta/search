package nl.xs4all.banaan.tst8.web.upload;

import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.value.ValueMap;

/**
 * Panel to test the file upload field.
 * @author konijn
 *
 */
public class UploadPanel extends Panel {
    private static final long serialVersionUID = -2070660918144252605L;
    private FileUploadField uploadField;

    public UploadPanel(String id, IModel model) {
        super(id, model);
        Form form = new Form("form", model) {
            private static final long serialVersionUID = -2959490619312979077L;

            @Override
            protected void onSubmit() {
                ValueMap map = (ValueMap) getModelObject();
                map.put("submitSeen", "true");
                FileUpload upload = uploadField.getFileUpload();
                map.put("haveUpload", upload != null);
                if (upload != null) {
                    map.put("fileName", upload.getClientFileName());
                    map.put("fileSize", upload.getSize());
                    // probably based on meta-info in http request
                    map.put("fileType", upload.getContentType());
                    map.put("fileContents", load(upload));
                }
            }

            // pretend byte[] is string,
            // good enough to test just text files in ASCII subset.
            private String load(FileUpload upload) {
                try {
                    InputStream inputStream = upload.getInputStream();
                    int count = inputStream.available();
                    byte[] buf = new byte[count];
                    int result = inputStream.read(buf);
                    if (result != count) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : buf) {
                       sb.appendCodePoint(b);
                    }
                    return sb.toString();
                } catch (IOException e) {
                    return null;
                }
            }
        };
        
        add(form);
        uploadField = new FileUploadField("file");
        form.add(uploadField);
        form.setMultiPart(true);
        add(new Label("submitSeen"));
        add(new Label("haveUpload"));
        add(new Label("fileName"));
        add(new Label("fileSize"));
        add(new Label("fileType"));
    }

}
