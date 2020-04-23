#include <iostream>

using namespace std;

//сортировка выбором
void swap(int* xp, int* yp)
{
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}

void selectionSort(int arr[], int n)
{
    int i, j, min_idx;

    // передвигаем границу неотсортированного подмассива 
    for (i = 0; i < n - 1; i++)
    { 
        min_idx = i;
        for (j = i + 1; j < n; j++)
            if (arr[j] < arr[min_idx])
                min_idx = j; // поиск минимального элемента внутри неосортированного массива

        // меняем местами найденный минимальный элемент с первым элементом 
        swap(&arr[min_idx], &arr[i]);
    }
}


//сортировка вставками
void insertionSort(int arr[], int n)
{
    int i, key, j;
    for (i = 1; i < n; i++)
    {
        key = arr[i];
        j = i - 1;

        // Перемещаем элементы arr [0..i-1], которые больше, чем key, на одну позицию вперед

        while (j >= 0 && arr[j] > key)
        {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

//сортировка обменом
void bubbleSort(int arr[], int n)
{
    int i, j;
    for (i = 0; i < n - 1; i++)

        // Последний i-тый элемент уже находтся на нужном месте  
        for (j = 0; j < n - i - 1; j++)
            if (arr[j] > arr[j + 1]) //если предыдущий элемент больше последующего, то меняем их местами
                swap(&arr[j], &arr[j + 1]);
}

/* Быстрая сортировка - алгоритм разделения и завоевания. в этом методе выбирается элемент в качестве оси и массив разбивается вокруг выбранной оси.
Ключевым процессом в этой сортировке является partition (). Учитывая массив и элемент Х массива в качестве оси, помещаем х в его правильное 
положение в отсортированном массиве и помещаем все элементы которые меньше х перед самим х. все большие элементы располагаем после х */

int partition(int arr[], int low, int high) //где, arr[] - массив для сортировки, low - начальный индекс, high - конечный индекс
{
    int pivot = arr[high]; // ось 
    int i = (low - 1); // индекс наименьшего элемента

    for (int j = low; j <= high - 1; j++)
    {
        // если текущий элемент меньше, чем ось
        if (arr[j] < pivot)
        {
            i++; // увеличиваем индекс наименьшего элемента
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]); //меняем элементы местами
    return (i + 1);
}

void quickSort(int arr[], int low, int high)
{
    if (low < high)
    {
        int pi = partition(arr, low, high); // pi- индекс разбиения

        // Отдельная сортировка элементов перед разделом и после раздела
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}


// Куча поддерева с корнем из узла i, который является индексом в arr []. n -размер кучи

void heapify(int arr[], int n, int i)
{
    int largest = i; // инициализация largest как корня кучи
    int l = 2 * i + 1; // left = 2*i + 1 
    int r = 2 * i + 2; // right = 2*i + 2 

    // левый ребёнок больше корня
    if (l < n && arr[l] > arr[largest])
        largest = l;

    // правый ребенок больше, чем самый большой элемент на данный момент
    if (r < n && arr[r] > arr[largest])
        largest = r;

    // больший элемент не корень
    if (largest != i)
    {
        swap(arr[i], arr[largest]);

        // рекурсивно накапливается уязвимое поддерево
        heapify(arr, n, largest);
    }
}

// сортировка кучей
void heapSort(int arr[], int n)
{
    // сборка кучи
    for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);

    // извлекаем элементы из кучи один за одним
    for (int i = n - 1; i >= 0; i--)
    {
        // перемещаем текущий корень в конец
        swap(arr[0], arr[i]);

        // вызываем максимальный heapify на уменьшенной куче
        heapify(arr, i, 0);
    }
}


//вывод массива
void printArray(int arr[], int size)
{
    int i;
    for (i = 0; i < size; i++)
        cout << arr[i] << " ";
    cout << endl;
}


int main()
{
    int arr[] = { 89, 36, 7, 24, 75, 21, 49, 62, 51 };
    int n = sizeof(arr) / sizeof(arr[0]);
    selectionSort(arr, n);
    cout << "Sorted array(selectionSort): \n";
    printArray(arr, n);
    insertionSort(arr, n);
    cout << "Sorted array(insertionSort): \n";
    printArray(arr, n);
    bubbleSort(arr, n);
    cout << "Sorted array(bubbleSort): \n";
    printArray(arr, n);
    quickSort(arr, n, n);
    cout << "Sorted array( quickSort): \n";
    printArray(arr, n);
    heapSort(arr, n);
    cout << "Sorted array(heapSort): \n";
    printArray(arr, n);
    system("pause");
    return 0;
}