package com.fsystem.bean;

import com.fsystem.repository.FolderRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class FileSystemBean {
    private String value = "deneme";

    private final transient FolderRepository folderRepository;

    public FileSystemBean(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public void find(){
        try {
            folderRepository.findAll();
            value = "calisti";
            System.out.println("ok");
        }catch (Exception e){
            value = "calismadi";
            System.out.println("nok");
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
