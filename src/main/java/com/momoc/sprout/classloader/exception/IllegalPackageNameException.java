package com.momoc.sprout.classloader.exception;

public class IllegalPackageNameException extends Exception {

    public IllegalPackageNameException(String msg){
        super("Illegal package name: " + msg);
    }
}
