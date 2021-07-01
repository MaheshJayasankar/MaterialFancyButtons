package com.rilixtech.community_material_typeface;

import com.rilixtech.community_material_typeface.CommunityMaterial.*;
import ohos.agp.text.Font;
import ohos.app.Context;
import ohos.global.resource.Resource;
import ohos.global.resource.RawFileDescriptor;
import org.junit.Test;
import org.junit.runner.manipulation.Ordering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;

import static java.security.AccessController.getContext;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;


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
