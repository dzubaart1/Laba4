import List.*;

public class Main
{
    public static void main(String[] args)
    {
        Tree tree = new Tree();
        Tree tree2 = new Tree();
        tree = tree.Create("label");
        tree2 = tree2.Create("label2");

        tree = tree.Create("label3", tree2);
        tree = tree.Create("label4");

        System.out.println(tree);
        Tree.Print();
        SymmetricBypass(tree, tree.Root());
    }

    public static void SymmetricBypass(Tree tree, int root)
    {
        if (root < 0)
        {
            return;
        }

        int child = tree.LeftMostChild(root);
        SymmetricBypass(tree, child);
        System.out.println(tree.Label(root));

        while (child >= 0)
        {
            child = tree.RightSibling(child);
            SymmetricBypass(tree, child);
        }
    }
}