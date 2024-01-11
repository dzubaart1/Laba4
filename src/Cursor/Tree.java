package Cursor;

public class Tree
{
    private static final int LENGTH = 10; // длина массива
    private static Item[] _array; // массив
    private static int SPACE; // список свободных ячееек
    private int _root; // корень

    /***
     * Метод инициализации
     */
    public static void Initialize() {
        SPACE = 0;
        _array = new Item[LENGTH];

        for (int i = 0; i < _array.length - 1; i++)
        {
            _array[i] = new Item(-1,i + 1, "");
        }

        _array[_array.length - 1] = new Item(-1, -1, "");
    }

    /***
     * Метод вывода на экран
     */
    public static void Print()
    {
        for (int i = 0; i < LENGTH; i++)
        {
            System.out.println(i + " " + _array[i].LeftChild + " " + _array[i].RightSibling + " " + _array[i].Label);
        }
    }

    public Tree()
    {
        _root = -1;
    }

    /***
     * Возвращает родителя узла
     * @param n - узел
     * @return Родитель
     */
    public int Parent(int n) {
        return FindParent(_root, n);
    }

    /***
     * Возвращает левого сына узла
     * @param n - узел
     * @return Левый сын
     */
    public int LeftMostChild(int n)
    {
        if (_root == -1)
        {
            return -1;
        }
        if (n == _root)
        {
            return _array[_root].LeftChild;
        }

        int item = FindParent(_root, n);
        if (item == -1)
        {
            return -1;
        }

        return _array[n].LeftChild;
    }

    /***
     * Возвращает правого брата узла
     * @param n - узел
     * @return Правый брат
     */
    public int RightSibling(int n)
    {
        int item = FindParent(_root, n);

        if (item == -1)
        {
            return -1;
        }

        item = _array[item].LeftChild;
        while (item != n)
        {
            item = _array[item].RightSibling;
        }

        return _array[item].RightSibling;
    }

    /***
     * Возвращает метку узла
     * @param n - узел
     * @return Метка узла
     */
    public String Label(int n)
    {
        if (n == _root)
        {
            return _array[n].Label;
        }

        int temp = FindParent(n, _root);
        if (temp != -1)
        {
            return _array[n].Label;
        }

        throw new RuntimeException("Нет такого элемента в дереве");
    }

    /***
     * Метод создает дерево
     * @param label - метка нового дерева
     * @return Созданный объект Tree
     */
    public Tree Create(String label)
    {
        if (SPACE == -1)
        {
            return null;
        }

        int newRoot = SPACE;
        SPACE = _array[SPACE].LeftChild;

        _array[newRoot].Label = label;
        if (_root != -1)
        {
            _array[newRoot].LeftChild = _root;
        }
        else
        {
            _array[newRoot].LeftChild = -1;
        }

        _root = newRoot;
        return this;
    }

    /***
     * Метод создает дерево
     * @param tree - узел нового дерева
     * @param label - метка нового дерева
     * @return Созданный объект Tree
     */
    public Tree Create(String label, Tree tree)
    {
        if (SPACE == -1)
        {
            return null;
        }

        if (_root == -1)
        {
            return tree.Create(label);
        }

        if (tree._root == -1)
        {
            return Create(label);
        }

        int newRoot = SPACE;
        SPACE = _array[SPACE].LeftChild;

        _array[newRoot].Label = label;
        _array[newRoot].LeftChild = _root;
        _array[_root].RightSibling = tree._root;

        _root = newRoot;
        tree._root = -1;

        return this;
    }

    /***
     * Возвращает корень дерева
     */
    public int Root()
    {
        return _root;
    }

    /***
     * Метод чистит дерево
     */
    public void MakeNull() {
        if (_root == -1)
        {
            return;
        }

        ClearBranch(_root);
        _root = -1;
    }

    /***
     * Метод удяляет элементы из ветки
     * @param root - родитель ветки
     */
    private void ClearBranch(int root)
    {
        int child = _array[root].LeftChild;

        while (child != -1)
        {
            ClearBranch(child);
            child = _array[child].RightSibling;
        }

        _array[root].LeftChild = SPACE;
        _array[root].Label = "";
        SPACE = root;
    }

    /***
     * Метод возвращает родителя узла
     * @param root дерево в котором ищем
     * @param n узел который ищем
     * @return Родитель узла
     */
    private int FindParent(int root, int n)
    {
        int child = _array[root].LeftChild;

        while (child != -1)
        {
            if (child == n)
            {
                return root;
            }

            int res = FindParent(child, n);
            if (res != -1)
            {
                return res;
            }
            child = _array[child].RightSibling;
        }
        return -1;
    }
}
