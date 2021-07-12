package com.rilixtech.community_material_typeface;

import ohos.agp.text.Font;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;


public class ExampleTest {
    private static Font.Builder typeface;
    private static final String TTF_FILE = "communitymaterial-font-v1.9.32.ttf";
    private static final String RAW_FILE_PATH = "";

    public ExampleTest() {
        typeface = null;
    }


    @Test
    public void check_getTypeface(){
//        RawFileDescriptor rawFileDescriptor = getResourceManager()
//                .getRawFileEntry("entry/resources/rawfile/anim_flag_iceland.gif").openRawFileDescriptor();
        File file = new File(RAW_FILE_PATH+ TTF_FILE);
        assertNotNull(file);

//        try {
//            BufferedReader bfreader = new BufferedReader(new FileReader(file));
//            System.out.println(bfreader.readLine());
//            System.out.println(bfreader.readLine());
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        try{
            typeface = new Font.Builder(file);
//            assertNotNull(font);
//            assertEquals("Font",font.getClass().getSimpleName());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
