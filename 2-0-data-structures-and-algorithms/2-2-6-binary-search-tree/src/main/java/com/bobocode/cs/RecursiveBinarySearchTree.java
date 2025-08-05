package com.bobocode.cs;

import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    int size = 0;
    Node<T> node;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> recursiveBinarySearchTree = new RecursiveBinarySearchTree<>();
        for (T elem:elements) {
            recursiveBinarySearchTree.insert(elem);
        }
        return recursiveBinarySearchTree;
    }

    @Override
    public boolean insert(T element) {
        if (element == null) {
            throw new NullPointerException();
        }


        if (size==0) {
            node = new Node<T>();
            node.value = element;
            size=1;
            return true;
        }
        boolean b = node.add(element);
        if (b == true) {
            size++;
        }
        return b;
    }

    @Override
    public boolean contains(T element) {
        Node<T> n = node;
        while (true) {
            if (n.value.compareTo(element)==0) {
                return true;
            }
            if (n.value.compareTo(element)>0) {
                if (n.left == null) {
                    return false;
                }
                n = n.left;
            }
            else {
                if (n.right == null) {
                    return false;
                }
                n = n.right;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (node == null) {
            return 0;
        }
        return node.depth();
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        node.traversal(consumer);
    }

    private static class Node<T extends Comparable<T>> {
        Node left;
        Node right;
        T value;

        public int depth() {
            if (left!=null && right!=null) {
                return Math.max(left.depth()+1, right.depth()+1);
            }
            if (left!=null) {
                return left.depth()+1;
            }
            if (right!=null) {
                return right.depth()+1;
            }
            return 0;
        }

        public boolean add(T element) {
            if (value == null) {
                value = element;
                return true;
            }
            if (value.compareTo(element)>0) {
                if (left == null) {
                    left = new Node();
                    return left.add(element);
                }
                return left.add(element);
            }
            if (value.compareTo(element)<0) {
                if (right == null) {
                    right = new Node();
                    return right.add(element);
                }
                return right.add(element);
            }
            return false;
        }

        public boolean traversal(Consumer<T> consumer) {
            boolean first = false;
            if (left==null && right==null) {
                consumer.accept(value);
                return true;
            }
            if (left!=null && left.traversal(consumer)) {
                consumer.accept(value);
                first = true;
            }
            if (right!=null ) {
                if (!first) {
                    consumer.accept(value);
                }
                right.traversal(consumer);
            }
            return true;
        }
    }
}
