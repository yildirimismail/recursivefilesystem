package com.fsystem.bean;

import com.fsystem.domain.tree.Node;
import com.fsystem.repository.FolderRepository;
import com.fsystem.service.FileSystemService;
import com.sun.glass.events.mac.NpapiEvent;
import jdk.nashorn.internal.ir.IfNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@ManagedBean
@Scope("view")
public class FileSystemBean {
    private String value;
    private Node root;
    private Set<Node> nodes;
    private TreeNode treeNode;
    private List<Node> nodeList;

    private final transient FileSystemService fileSystemService;

    public FileSystemBean(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @PostConstruct
    public void init() {
        root = fileSystemService.initiliazeTree();
        nodes = new HashSet<>();
        treeNode = fileSystemService.fillTreeComponent(root, new DefaultTreeNode(root.getValue(), null));
    }

    public void search() {
        nodes = new HashSet<>();
        nodes = fileSystemService.search(value, root, nodes);
        for (Node node : nodes) {
            List<String> pathVariables = new ArrayList<>();
            node.setPath("");
            fileSystemService.completeFullPathOfFolder(node, pathVariables);
            for (String path : pathVariables) {
                if (path != null) {
                    node.setPath("/" + path + node.getPath());
                }
            }
        }
        nodeList = convertToArrayList();
    }

    public List<Node> convertToArrayList() {
        return new ArrayList<>(nodes);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
