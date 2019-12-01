package org.king2.test;


import java.util.*;

public class SearchTreeDemo {

    public static void main(String[] args) {

        /**
         * 业务场景是这样的：从数据库中查询一颗树，我现在用代码模拟，
         * 我们要做的是遍历该树，如果该树中的节点id等于我们传入的id，终止遍历该树。
         */

        // 从数据库中查询数据信息
        List<Obj> objs = TreeUtil.selectByQuery();

        // 将数据构造成一颗平衡二叉树 为什么使用平衡二叉树 因为查询方便。
        TreeNode treeNode = TreeUtil.builderTree(objs);
        Integer id = 9874;

        // 查询id
        Obj objById = treeNode.getObjById(id);
        System.out.println(objById);
    }

}


class TreeUtil {

    public static TreeNode root;
    public static TreeNode addNode;

    /**
     * 假造数据
     *
     * @return
     */
    public static List<Obj> selectByQuery() {

        // 保证Id不重复， 原因是数据库的主键id是不能重复的
        Map<Integer, Obj> objMap = new HashMap<>();
        List<Obj> objs = new ArrayList<>();
        // Id随机生产工具
        Random random = new Random();
        while (objMap.size() < 10) {
            int i = random.nextInt(10000);
            Obj obj = new Obj();
            obj.id = i;
            obj.name = "name:" + i;
            objMap.put(i, obj);
        }

        // 数据假造完成
        objMap.forEach((k, v) -> {
            objs.add(v);
        });

        return objs;
    }

    /**
     * 通过查询数据库的值，构建一颗平衡二叉树
     *
     * @param objs
     * @return
     */
    public static TreeNode builderTree(List<Obj> objs) {

        for (Obj obj : objs) {
            add(obj);
        }
        return root;
    }

    private static void add(Obj obj) {

        /**
         * 一些简单的校验我这里不做
         */
        TreeNode<Obj> newNode = new TreeNode<>(obj);

        // 这里就不想写注释了
        if (root == null) {
            root = newNode;

        } else {
            addNode = root;
            while (true) {
                Obj t = (Obj) addNode.t;
                if (obj.id < t.id && addNode.leftNode != null) {
                    addNode = addNode.leftNode;
                } else if (obj.id < t.id && addNode.leftNode == null) {
                    addNode.leftNode = newNode;
                    break;
                } else if (obj.id > t.id && addNode.rightNode != null) {
                    addNode = addNode.rightNode;
                } else if (obj.id > t.id && addNode.rightNode == null) {
                    addNode.rightNode = newNode;
                    break;
                }
            }
        }

        // 添加完成判断是否需要旋转。
        root.balance();
    }


}

/**
 * 描述着一棵树
 */
class TreeNode<T> {

    /**
     * 为了方便直接使用默认修饰
     */
    T t;
    TreeNode<T> leftNode;
    TreeNode<T> rightNode;

    public TreeNode(T t) {
        this.t = t;
    }

    /**
     * 通过id查询数据
     */
    public Obj getObjById(Integer id) {

        Obj t = (Obj) this.t;

        if ((t.id + "").equals(id + "")) {
            return t;
        } else if (id < t.id) {
            if (this.leftNode == null) return null;
            return this.leftNode.getObjById(id);
        } else if (id > t.id) {
            if (this.rightNode == null) return null;
            return this.rightNode.getObjById(id);
        }
        return null;
    }

    public int getHeight() {

        return Math.max(this.leftNode != null ? this.leftNode.getHeight() : 0,
                this.rightNode != null ? this.rightNode.getHeight() : 0) + 1;
    }

    public int leftHeight() {
        return this.leftNode == null ? 0 : this.leftNode.getHeight();
    }

    public int rightHeight() {
        return this.rightNode == null ? 0 : this.rightNode.getHeight();
    }

    public void balance() {

        // 判断是否需要左旋
        if ((this.rightHeight() - this.leftHeight()) > 1) {
            if (this.rightNode != null && this.rightNode.leftHeight() > this.rightNode.rightHeight()) {
                this.rightNode.rightBalance();
            }
            this.leftBalance();
        } else if ((this.leftHeight() - this.rightHeight()) > 1) {

            if (this.leftNode != null && this.leftNode.rightHeight() > this.leftNode.leftHeight()) {
                this.leftNode.leftBalance();
            }

            this.rightBalance();
        }

    }

    public void leftBalance() {

        TreeNode newNode = new TreeNode(this.t);
        newNode.leftNode = this.leftNode;
        newNode.rightNode = this.rightNode.leftNode;
        this.t = this.rightNode.t;
        this.rightNode = this.rightNode.rightNode;
        this.leftNode = newNode;
    }

    public void rightBalance() {

        TreeNode newNode = new TreeNode(this.t);
        newNode.rightNode = this.rightNode;
        newNode.leftNode = this.leftNode.rightNode;
        this.t = this.leftNode.t;
        this.leftNode = this.leftNode.leftNode;
        this.rightNode = newNode;
    }
}

/**
 * 描述着伪造的数据
 */
class Obj {
    Integer id;
    String name;

    @Override
    public String toString() {
        return "Obj{id=" + id + ",name=" + name + "}";
    }
}