package List;

public class Item
{
    public String Label;
    public Son Son;
    public int NextSpace;
    public Item(int nextSpace, String label)
    {
        NextSpace = nextSpace;
        Label = label;
    }
}
