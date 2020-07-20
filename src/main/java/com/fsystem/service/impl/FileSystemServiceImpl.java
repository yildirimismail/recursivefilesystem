package com.fsystem.service.impl;

import com.fsystem.domain.entity.File;
import com.fsystem.domain.entity.Folder;
import com.fsystem.domain.tree.Node;
import com.fsystem.repository.FileRepository;
import com.fsystem.repository.FolderRepository;
import com.fsystem.service.FileSystemService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemService.class);
    private final transient FolderRepository folderRepository;
    private final transient FileRepository fileRepository;

    public FileSystemServiceImpl(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public Node initiliazeTree() {
        try {
            Folder rootFolder = folderRepository.findRootFolder();
            Node root = new Node(rootFolder.getName(), rootFolder.getId(), 1);
            List<Node> nodeFolderList = new ArrayList<>();
            nodeFolderList.add(root);
            drawTree(nodeFolderList);
            return root;
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    @Override
    public Set<Node> search(String value, Node root, Set<Node> resultSet) {
        //BFS algorithm first root afterthat neighbors
        try {
            if (root.getValue().contains(value)){
                resultSet.add(root);
            }
            for (Node node : root.getNeighbors()) {
                if (node.getValue().contains(value)) {
                    resultSet.add(node);
                }
                search(value, node, resultSet);
            }
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }

        return resultSet;
    }

    @Override
    public List<String> completeFullPathOfFolder(Node root, List<String> pathVariables) {
        if (root != null) {
            pathVariables.add(root.getValue());
            completeFullPathOfFolder(root.getParentNode(), pathVariables);
        }
        return pathVariables;
    }

    @Override
    public TreeNode fillTreeComponent(Node root, TreeNode treeNode) {
        if (root.getNeighbors() != null && root.getNeighbors().size() > 0) {
            for (Node node : root.getNeighbors()) {
                TreeNode newRoot = new DefaultTreeNode(node.getValue(), treeNode);
                fillTreeComponent(node, newRoot);
            }
        }
        return treeNode;
    }

    public void drawTree(List<Node> folderList) {
        if (folderList.size() > 0) {
            try {
                for (Node rootFolder : folderList) {
                    connectFilesToTheParentFolder(rootFolder);
                    List<Folder> subFolders = folderRepository.findFoldersByFolderId(rootFolder.getFolderId());
                    List<Node> nodes = new ArrayList<>();
                    if (!subFolders.isEmpty()) {
                        for (Folder f : subFolders) {
                            Node node = new Node(f.getName(), f.getId(), 1);
                            node.setParentNode(rootFolder);
                            nodes.add(node);
                            rootFolder.addNeighbor(rootFolder, node);
                            drawTree(nodes);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void connectFilesToTheParentFolder(Node rootFolder) {
        List<File> fileList = fileRepository.findFilesByFolderId(rootFolder.getFolderId());
        for (File file : fileList) {
            Node node = new Node(file.getName(), file.getId(), 2);
            rootFolder.addNeighbor(rootFolder, node);
            node.setParentNode(rootFolder);
        }
    }

}
