package nl.xs4all.banaan.tst8.web.upload;

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
                    String fileName = upload.getClientFileName();
                    map.put("fileName", fileName);
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
    }

}
