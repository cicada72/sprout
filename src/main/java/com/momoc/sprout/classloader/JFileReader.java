package com.momoc.sprout.classloader;

import com.momoc.sprout.classloader.entity.JFile;
import com.momoc.sprout.classloader.entity.JFileType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JFileReader {

    private List<JFile> jFiles = new ArrayList<>();

    public void read(File baseFile){
        if(baseFile != null && baseFile.exists()){
            if(baseFile.isDirectory()){
                File[] subFile = baseFile.listFiles();
                if(subFile != null){
                    for (File f : subFile) {
                        if (f.isFile()) {
                            this.classifyFile(f);
                        }
                        if (f.isDirectory()) {
                            this.read(f);
                        }
                    }
                }
            }
            if(baseFile.isFile()){
                this.classifyFile(baseFile);
            }
        }
    }

    public List<JFile> getJFiles(){
        return this.jFiles;
    }

    private void classifyFile(File file){
        if(file != null && file.exists() && file.isFile()){
            String fileName = file.getName();
            if(fileName.toLowerCase().trim().endsWith(".class")){
                this.addJFileToList(file);
            }
            if(fileName.toLowerCase().trim().endsWith(".jar")){
                this.addJFileToList(file);
            }
        }
    }

    private void addJFileToList(File file){
        JFile jFile = new JFile();
        jFile.setFile(file);
        this.jFiles.add(jFile);
    }
}
