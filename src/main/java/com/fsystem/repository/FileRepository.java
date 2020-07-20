package com.fsystem.repository;

import com.fsystem.domain.entity.File;
import com.fsystem.hibernate.helper.HibernateRepository;

import java.util.List;

public interface FileRepository extends HibernateRepository<File, Integer> {
    List<File> findFolderByFolderId(Integer folderId);
}