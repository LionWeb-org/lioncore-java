package org.lionweb.lioncore.java.api;

import org.lionweb.lioncore.java.model.Node;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This NodeResolver consult a given list of nodes to find the one with the requested ID.
 */
public class LocalNodeResolver implements NodeResolver {
    private Map<String, Node> nodes = new HashMap<>();

    public LocalNodeResolver() {

    }

    public LocalNodeResolver(List<Node> nodes) {
        nodes.forEach(n -> add(n));
    }

    public void add(@Nonnull Node node) {
        nodes.put(node.getID(), node);
    }

    @Nullable
    @Override
    public Node resolve(String nodeID) {
        return nodes.get(nodeID);
    }

    public void addAll(@Nonnull List<Node> nodes) {
        nodes.forEach(n -> add(n));
    }
}