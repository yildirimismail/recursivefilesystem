package com.fsystem.service;

import com.fsystem.domain.tree.Node;
import org.primefaces.model.TreeNode;

import java.util.List;
import java.util.Set;

public interface FileSystemService {
    Node initiliazeTree();

    Set<Node> search(String value, Node root, Set<Node> resultSet);

    List<String> completeFullPathOfFolder(Node root, List<String> pathVariables);

    TreeNode fillTreeComponent(Node root, TreeNode treeNode);
}
