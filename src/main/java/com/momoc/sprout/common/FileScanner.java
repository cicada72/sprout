package com.momoc.sprout.common;

import java.io.File;

public class FileScanner {



    public static File findTargetFileByName(String parentPath, String targetFileName){
        File parent = new File(parentPath);
        File target = null;
        if(parent.exists() && parent.getName().equals(targetFileName)){
            return parent;
        }
        if(parent.exists() && parent.isDirectory()){
            File [] subFileList = parent.listFiles();
            if(subFileList != null){
                for(int index = 0; index < subFileList.length; index++){
                    File currentFile = subFileList[index];
                    if(currentFile.getName().equals(targetFileName)){
                        target = currentFile;
                        break;
                    }
                    if(index == (subFileList.length -1)){
                        for(File f : subFileList){
                            target = findTargetFileByName(f.getAbsolutePath(), targetFileName);
                            if(target != null){
                                break;
                            }
                        }
                    }
                }
            }
        }
        return target;
    }

}
