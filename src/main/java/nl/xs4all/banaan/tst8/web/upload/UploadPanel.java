package nl.xs4all.banaan.tst8.web.upload;

import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
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
                // to show when model is updated
                map.put("textSeen", map.get("text"));
                map.put("submitSeen", "true");
                FileUpload upload = uploadField.getFileUpload();
                map.put("haveUpload", upload != null);
                if (upload != null) {
                    // RFC 2388 suggests filename encoding using RFC-2231;
                    // also seen as unescaped UTF-8.
                    map.put("fileName", upload.getClientFileName());
                    map.put("fileSize", upload.getSize());
                    // as sent by client, see RFC2388 4.5;
                    // text/plain also seen without charset= or encoding parameter.
                    map.put("fileType", upload.getContentType());
                    
                    // content may be interpreted by browser as html
                    // even if filename suffix or mimetype disagree;
                    // this gives rise to CSS attacks.
                    // http://www.adambarth.com/papers/2009/barth-caballero-song.pdf
                    // http://tools.ietf.org/html/draft-abarth-mime-sniff-01
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
                        try {
                       sb.appendCodePoint(b);
                        }
                        catch (IllegalArgumentException e) {
                            // not a single-byte character
                            return null;
                        }
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
        form.add(new TextField("text"));
        form.setMultiPart(true);
        add(new Label("textSeen"));
        add(new Label("submitSeen"));
        add(new Label("haveUpload"));
        add(new Label("fileName"));
        add(new Label("fileSize"));
        add(new Label("fileType"));
        add(new Label("fileContents"));
    }

}
