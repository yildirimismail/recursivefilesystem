package com.fsystem.domain.tree;


import java.util.HashSet;
import java.util.Set;

public class Node {

    private String value;
    private Set<Node> neighbors;
    private Integer folderId;
    private Node parentNode;
    private int type;
    private String path;

    public Node(String value, Integer folderId, int type){
        this.value = value;
        this.folderId = folderId;
        this.type = type;
        neighbors = new HashSet<>();
    }

    public void addNeighbor(Node parentNode, Node willConnectNode) {

        for (Node node : parentNode.getNeighbors()){
            if (node.value.equals(willConnectNode.getValue())){
                return;
            }
        }
        parentNode.getNeighbors().add(willConnectNode);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
