package com.rilixtech.font_test_module.slice;

import com.rilixtech.font_test_module.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TextField;
import ohos.agp.text.Font;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.global.resource.RawFileEntry;
import ohos.global.resource.Resource;

import java.io.*;

public class FontTestAbilitySlice extends AbilitySlice {
    private static final String TTF_FILE = "communitymaterial-font-v1.9.32.ttf";
    private static final String RAW_FILE_PATH = "";
    private static TextField tf1;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_font_test);

        tf1 = (TextField) findComponentById(ResourceTable.Id_textField1);
        tf1.setText(getFontFromName(TTF_FILE).toString());

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    public Font getFontFromName(String name){ RawFileEntry rawFileEntry = getResourceManager()
                .getRawFileEntry("resources/rawfile/"+name);

        try{ File file = getFileFromRawFile(rawFileEntry,"file_"+name);
            Font.Builder typeface = new Font.Builder(file);
            return typeface.build();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public File getFileFromRawFile(RawFileEntry rawFileEntry, String filename) {
        byte[] buf = null;
        try{ File file = new File(getCacheDir(), filename);
            Resource resource = rawFileEntry.openRawFile();
            buf = new byte[(int) rawFileEntry.openRawFileDescriptor().getFileSize()];
            int bytesRead = resource.read(buf);
            if (bytesRead != buf.length) {
                throw new IOException("Asset read failed");
            }
            FileOutputStream output = new FileOutputStream(file);
            output.write(buf, 0, bytesRead);
            output.close();
            return file;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
