package List;

public class Tree
{
    private static final int LENGTH = 10; // длина массива
    private static Item[] _array; // массив
    private static int SPACE; // список свободных ячееек
    private int _root; // корень

    /***
     * Метод инициализации
     */
    public static void Initialize()
    {
        SPACE = 0;
        _array = new Item[LENGTH];

        for (int i = 0; i < _array.length - 1; i++)
        {
            _array[i] = new Item(i + 1, "");
        }

        _array[_array.length - 1] = new Item(-1, "");
    }

    /***
     * Метод вывода на экран
     */
    public static void Print()
    {
        for (int i = 0; i < LENGTH; i++)
        {
            System.out.print(i + " " +  _array[i].NextSpace + " " + _array[i].Label);

            Son son = _array[i].Son;
            System.out.print(" | ");
            while (son != null)
            {
                System.out.print(son.Label + " ");
                son = son.NextSon;
            }

            System.out.println();
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
    public int Parent(int n)
    {
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

        if (_root == n)
        {
            return _array[_root].Son.Label;
        }

        int item = FindParent(_root, n);
        if (item == -1)
        {
            return -1;
        }

        return _array[n].Son.Label;
    }

    /***
     * Возвращает правого брата узла
     * @param n - узел
     * @return Правый брат
     */
    public int RightSibling(int n)
    {
        int item = FindParent(_root, n);

        if (item != -1 && _array[item].Son.NextSon != null)
        {
            return _array[item].Son.NextSon.Label;
        }

        return -1;
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

        int item = FindParent(_root, n);
        if (item != -1)
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

        if (_root != -1)
        {
            _array[SPACE].Son = new Son(_root, null);
        }

        _array[SPACE].Label = label;
        _root = SPACE;
        SPACE = _array[SPACE].NextSpace;
        return this;
    }

    /***
     * Метод создает дерево
     * @param tree - узел нового дерева
     * @param label - метка нового дерева
     * @return Созданный объект Tree
     */
    public Tree Create(String label, Tree tree) {
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

        _array[SPACE].Label = label;
        _array[SPACE].Son = new Son(_root, new Son(tree._root, null));

        _root = SPACE;
        SPACE = _array[SPACE].NextSpace;
        return this;
    }

    /***
     * Метод чистит дерево
     * @return Корень дерева
     */
    public int Root()
    {
        return _root;
    }

    /***
     * Метод чистит дерево
     */
    public void MakeNull()
    {
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
    private int FindParent(int root, int n)
    {
        Son son = _array[root].Son;

        while (son != null)
        {
            if (son.Label == n)
            {
                return root;
            }

            int res = FindParent(son.Label, root);
            if (res != -1)
            {
                return res;
            }

            son = son.NextSon;
        }
        return -1;
    }

    /***
     * Метод удяляет элементы из ветки
     * @param root - родитель ветки
     */
    private void ClearBranch(int root)
    {
        Son child = _array[root].Son;
        while (child != null)
        {
            ClearBranch(child.Label);
            child = child.NextSon;
        }

        _array[root].NextSpace = SPACE;
        _array[root].Label = "";
        SPACE = root;
    }
}
