package com.fsystem.repository;

import com.fsystem.domain.entity.Folder;
import com.fsystem.domain.tree.Node;
import com.fsystem.hibernate.helper.HibernateRepository;

import java.util.List;

public interface FolderRepository extends HibernateRepository<Folder, Integer> {
    List<Folder> findFoldersByFolderId(Integer folderId);

    Folder findRootFolder();
}