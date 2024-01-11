package Cursor;

public class Item
{
    public int LeftChild;
    public int RightSibling;
    public String Label;

    public Item(int rightSibling, int leftChild, String label)
    {
        LeftChild = leftChild;
        RightSibling = rightSibling;
        Label = label;
    }
}
