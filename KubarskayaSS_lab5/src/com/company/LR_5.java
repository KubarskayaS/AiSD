package com.company;

import java.util.Random;
import java.lang.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;


class Tape{   // Класс, хранящий метаданные о файле.
    // Оболочка для исключения из логики
    // соортировки низкоуровневых операций
    // чтения/записи/открытия/закрытия файла

    String path;  // Путь к файлу
    int elems;    // Количество элементов в файле (необходимо для)
    // определения того, что из файла были считаны все данные
    BufferedWriter writer; // Поток записи данных в файл
    Scanner reader;		   // Поток чтения данных из файла

    // Логические флаги состояния файла
    // и текущей операции над ним
    boolean isWrite;
    boolean isRead;
    boolean isEmpty;
    boolean isFull;

    Tape(String _path) throws IOException{ //Инициализация файла
        path=_path;
        isEmpty=true; // По умолчанию - пустой, открыт на запись
        isFull=false;
        isWrite=true;
        writeMode();
        isRead=false;
        elems=0;
    }


    void writeMode() throws IOException{ // Переключение в режим
        // записи данных
        isWrite=true;
        FileWriter os = new FileWriter(path);
        writer = new BufferedWriter(os);
    }


    void endWrite() throws IOException{ // Выход из режима записи
        // сохранение состояния файла
        isWrite=false;
        writer.close();
    }


    void readMode() throws IOException{ //Режим чтения данных
        isRead=true;
        File is = new File(path);
        reader = new Scanner(is);
    }


    void endRead() throws IOException{ // Выход из режима чтения данных
        isRead=false;
        if(reader!=null)
            reader.close();
    }

    void addNum(int num) throws IOException{ // Запись числа в файл
        isEmpty=false;
        if(!isWrite) writeMode();                // Если файл не был открыт
        // для записи, то вызывается метод
        // перехода в режим записи
        writer.write(Integer.toString(num) + " ");
        elems++;
    }


    void clearFile() throws IOException{    // Очистить файл
        // Вызывается после считывания всех данных
        isEmpty=true;
        File fl=new File(path);
        fl.delete();
        File fn=new File(path);
        fn.createNewFile();
    }

    int readNum() throws IOException{     // Считывание данных из файла
        if(!isRead) readMode();
        elems--;
        int num = reader.nextInt();
        if(elems==0) clearFile();        // Если были считаны все данные
        // Файл очищается
        return num;
    }

}

class LR_5{

    static int[] arr10_F,    arr10_B,    arr10_R,    // Массивы для заполнения файлов
            arr500_F,   arr500_B,   arr500_R,   // числами, которые будет сортироваться
            arr10000_F, arr10000_B, arr10000_R;
    // F - forward, отсортированная по возрастанию последовательность
    // B - backward - по убыванию
    // R - random

    static String Sarr10_F,    Sarr10_B,    Sarr10_R,  // Пути к файлам, обозначения аналогичные
            Sarr500_F,   Sarr500_B,   Sarr500_R,
            Sarr10000_F, Sarr10000_B, Sarr10000_R;

    static int compares =0;
    static int swaps    =0;
    static long start_time;
    static long end_time;


    static int[] genF(int n){				// Генерация последовательного массива чисел
        int[] arr = new int[n];				// для инициализации файла
        for(int i=0; i<n; i++) arr[i]=i;
        return arr;
    }

    static int[] genB(int n){					// Аналогично для убывания
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i]=n-i-1;
        return arr;
    }


    static int[] genR(int n){					// Аналогично произвольный порядок
        int[] arr = new int[n];
        Random r = new Random();
        arr = r.ints(n, 0, n).toArray();
        return arr;
    }

    static void printArr(int[] arr){									// Техническая функция
        for(int i=0; i<arr.length; i++) System.out.print(arr[i] + " "); // вывода массива на экран
        System.out.println();
    }



    static void writeToFile(int[] arr, String file) throws IOException{    // Запись в файл массива чисел
        FileWriter os = new FileWriter(file);								   // для первичной инициализации
        BufferedWriter bos = new BufferedWriter(os);						   // исходных для сортировки файлов
        // нужными данными
        for(int i=0; i<arr.length-1; i++) bos.write(Integer.toString(arr[i]) + " ");
        bos.write(Integer.toString(arr[arr.length-1]));
        bos.close();
    }

//DATA GENERATION

    static void initialize(String[] args) throws IOException{ // Инициализация входных для сортировки данных
        Sarr10_R=args[0]; // Считывание из аргументов командной строки
        Sarr10_B=args[1]; // путей к файлам на диске, к которым будет применяться сортировка
        Sarr10_F=args[2]; // путь имеет вид размер_файла/тип_файла (F/B/R)

        Sarr500_R=args[3];
        Sarr500_B=args[4];
        Sarr500_F=args[5];

        Sarr10000_R=args[6];
        Sarr10000_B=args[7];
        Sarr10000_F=args[8];


        arr10_R = genR(10);					// генерация необходимых последовательностей чисел
        writeToFile(arr10_R, Sarr10_R);		// и запись их в исходные для сортировки файлы
        arr10_B = genB(10);
        writeToFile(arr10_B, Sarr10_B);
        arr10_F = genF(10);
        writeToFile(arr10_F, Sarr10_F);

        arr500_R = genR(500);
        writeToFile(arr500_R, Sarr500_R);
        arr500_B = genB(500);
        writeToFile(arr500_B, Sarr500_B);
        arr500_F = genF(500);
        writeToFile(arr500_F, Sarr500_F);


        arr10000_R = genR(10000);
        writeToFile(arr10000_R, Sarr10000_R);
        arr10000_B = genB(10000);
        writeToFile(arr10000_B, Sarr10000_B);
        arr10000_F = genF(10000);
        writeToFile(arr10000_F, Sarr10000_F);
    }

//DATA GENERATION

//FILE METADATA PROCESSING

    static String ctlg(String id){  // функция выделения пути к каталогу, в котором находится файл
        String[] data = id.split("/");	// из полного (относительного) пути файла
        return data[0];
    }

    static String dirct(String id){ // Функция выделения имени файла из полного (относительного) пути файла
        String[] data = id.split("/");
        return data[1];
    }

    static String parseTapePath(String id, int num){  // Создание имен вспомогательных файлов
        String tapeName = "";						  // для сортировки
        tapeName+=dirct(id)+"_T"+Integer.toString(num);
        return ctlg(id)+"/"+tapeName;
    }

    static String setTape(String id, int num) throws IOException{ // Создание класса-оболочки для вспомогательного
        // файла сортировки, в которых будет проходить
        // слияние

        String tapeName = parseTapePath(id, num); // Для вспомогательного файла создается имя в формате
        // тип_исходных данных(F/B/R)_номер_вспомогательного файла

        File file = new File(tapeName);  // Файл создается на диске, возвращается его имя
        file.createNewFile();
        return tapeName;
    }

    static Tape[] initializeTapes(String file) throws IOException{ // Создание всех вспомогательных файлов
        Tape[] tapes = new Tape[5]; // Создается 4 файла, в которых будет происходить слияние
        // и пустой пятый, в который данные будут сливаться на первой итерации
        for(int i=0; i<5; i++){
            Tape tp = new Tape(setTape(file, i));
            tapes[i]=tp;
        }
        return tapes;			// Возвращается список вспомогательных файлов (в оболочке Tape)
    }

//FILE METADATA PROCESSING


    static void  firstDistribution(String file, Tape[] tapes, int[] distrib, int elems) throws IOException{

        // Метод осуществления первичного распределения исходных
        // данных по вспомогательным файлам
        // аргументы - файл с исходныи данными
        // вспомогательные файлы
        // таблица первичного распределения (список)
        // количество элементов

        File is = new File(file);			// Исходный файл открывается
        Scanner sc = new Scanner(is);


        int del = arrSumm(distrib)-elems;   // Находится количество пустых серий
        // (разность между числом элементов в
        // распраделении и общим числом элементов)


        int div=0;	// Переменная для равномерного распределения общего числа
        // пустых серий среди всех файлов
        // нужна для того, чтобы количество пустых серий различалось не более чем
        // на 1 во вспомогательных файлах

        for(int i=0; i<distrib.length; i++){ // В цикле по всем строчкам первичного распределения
            div=distrib.length-i;			 // Переменная принимает значения 4, 3, 2, 1
            // и количество пустых серий
            // del/4
            // (del-del/4)/3
            // (del-(del-del/4)/3)/2
            // и так далее, что с учетом особенностей округления в java
            // дает равномерное распределение пустых серий

            for(int j=0; j<(int)Math.round((float)del/(float)div); j++){ // Записывается нужное количество пустых серий
                swaps++; //FIO OPERATON
                tapes[i].addNum(-2);      // -2 это обозначение пустой серии
                swaps++; //FIO OPERATON
                tapes[i].addNum(-5);	  // -5 это обозначение границы двух серий
                distrib[i]-=1;			  // при записи серии в файл из соответствующей
                // записи таблицы первичного распределения серий
                // вычитается 1
            }
            while(distrib[i]>0){			// после записи пустых серий
                // пока не были записаны все числа по
                // таблице распределения серий
                // в файл записываются реальные числа
                // из входного файла
                swaps+=2; //FIO OPERATON
                tapes[i].addNum(sc.nextInt());
                swaps++; //FIO OPERATON
                tapes[i].addNum(-5);
                distrib[i]-=1;
            }
            del-=(int)Math.round((float)del/(float)div);
        }


        for(int i=0; i<4; i++){			// Заканчивается запись во вспомогательные файлы
            tapes[i].endWrite();
        }

        sc.close();

    }



    static int findEmpty(Tape[] tapes){		// Функция, которая находит пустой файл
        // в списке рабочих файлов (в него будет проходить слияние)
        for(int i=0; i<5; i++){
            if(tapes[i].isEmpty) return i;
        }
        return -1;
    }

    static int minind(int[] arr){          // Определение файла из сливаемых, в котором в очереди
        // в очереди на считывание находится минимальное
        // число для сливания
        // -3 метка, обозначающая, что из файла была считана
        // серия, и считывать следующую из него пока не нужно
        // -5 граница серии. Если во всех файлах была
        // достингута граница серии, то вовращается -3
        // как сигнал о том, что надо сливать по следующей
        // серии из файлов
        int min=Integer.MAX_VALUE;
        int minInd=0;
        for(int i=0; i<arr.length; i++){
            compares++;
            if(arr[i]<=min && arr[i]!=-3 && arr[i]!=-5){min=arr[i]; minInd=i;}
        }
        if(min==Integer.MAX_VALUE) return -3;
        return minInd;
    }


    static int[] nextSeries(int[] arr){ // Функция выставляет метку завершения считывания серии
        // во всех файлах. Вызывается после того, как во всех файлах
        // была достигнута граница серии
        for(int i=0; i<arr.length; i++)
            arr[i]=-3;
        return arr;
    }

    static boolean isSeriesEnded(int[] arr){							// Функция определяет
        for(int i=0; i<arr.length; i++) if(arr[i]!=-5) return false;	// была ли во всех файлах
        return true;                                                // достингнута граница серии
    }


    static boolean endOfMerge=false;  // Логическая переменная, сигнализирующая о завершении
    // всего каскадного слияния

    static boolean isFinished(Tape[] tapes){ // Функция, которая проверяет не было ли
        // завершено каскадное слияние.
        // Если все файлы. кроме одного
        // пустые, то слияние завершилось
        // возвращается true и выставлется логическая
        // переменная завершения слияния
        int counter=0;
        for(int i=0; i<5; i++)
            if(tapes[i].isEmpty) counter++;
        if(counter==4){endOfMerge=true; return true;}
        return false;
    }

    static Tape[] doCascade(Tape[] tapes)throws IOException{ // Функция одного прохода каскадного слияния

        int empty;    // Индекс пустого файла в списке файлов
        int[] readed; // Список индексов файлов, из которых сливаются серии в пустой файл
        for(int i=4; i>0; i--){  // Файла 4 + 1 пустой и на одном проходе алгоритма 4 каскадных слияния
            // Цикл идет от 4 до 1, так слияние происходит из
            // 4-х файлов в 1
            // 3-х в 1
            // 2-х в 1
            // 1 в 1
            // в цикле каскадного слияния

            if(isFinished(tapes)) break; // Перед каждым каскадным слиянием проврка не был ли достигнут результат


            empty = findEmpty(tapes); // Определяется пустой файл

            readed = new int[i]; // Слияние в пустой файл прохходит из 4, 3, 2, 1 файлов.

            for(int j=0; j<i; j++) readed[j]=-3; // Файлы из которых будут считываться данные
            // помечаются меткой -3 (необходимо считать новую серию)

            while(findEmpty(tapes)==empty || findEmpty(tapes)==-1){ // Пока какой-то из сливаемых файлов не стал пустым
                while(true){     // Сливание серий, в while true. Когда соответствующие серии будут слиты
                    // вызовется break -> затем проверка на то, не стал ли какой-то файл пустым
                    // затем следующая итерация слияния

                    int counter = 0; // Индексы файлов, по которым происходит слияние
                    // Индекс пропускаетф пустые файлы, и файлы, помеченные
                    // как те, в которые уже было произведено слияние

                    int minInd = 0; // Индекс файла, из которого должно быть слито следующее число
                    // (минимальное в рассматриваемых сериях)


                    for(int j=0; j<tapes.length; j++){ // Проходятся все вспомогательные файлы
                        if(j==empty || tapes[j].isFull) continue;
                        if(readed[counter]==-3) {readed[counter]=tapes[j].readNum(); // Из файла, который подвергается слиянию
                            // считывается следующее число из серии
                            // если выставлена метка
                            // если не выставлена - число было
                            // считано, но не слито, и число сохраняется
                            // ожидая своей очереди на слияние
                            swaps++;}
                        counter++;
                    }

                    if(!isSeriesEnded(readed)){	 // Если не был достигнут конец серий во всех сливаемых файлах
                        minInd = minind(readed);     // из считванных числе определяется минимальное (которое будет сливаться)

                        tapes[empty].addNum(readed[minInd]); swaps++; //Записывается в сливаемый (в который сливают) файл
                        readed[minInd]=-3; // Выставляется метка - число было считано, на следующей итерации необходимо будет
                        // считать следующее число из этого файла
                    }

                    else{ // Если во всех сливаемых файлах был достигнут конец серии

                        nextSeries(readed); // Во всех сливаемых файлах выставляется метка - считывать следующую серию
                        tapes[empty].addNum(-5); swaps++; // В сливаемом (в который сливают) файле записывается конец серии
                        break; // Завершение цикла слияния серии (проверка не образовался ли пустой файл, если нет,
                        // сливание следующих серий)
                    }
                }
            }



            tapes[empty].isFull=true; // После сливания всех серий, файл, в которй сливались данные
            // помечается как full (он в текущем цикле каскадного слияния больше
            // не будет рассматриваться)
            tapes[empty].endWrite();
            empty = findEmpty(tapes); // Определяется новый пустой файл
        }

        for(int i=0;i<tapes.length; i++){tapes[i].isFull=false; tapes[i].endWrite();}
        // Поосле завершения одного прохода каскадного слияния все файлы закрываются,
        // Метки "заполненных (слитых)" файлов снимаются

        return tapes;
    }

    static void setUpTapes(Tape[] tapes)throws IOException{ // Настройка всех файлов перед очередным
        // проходом слияния
        // Все выставляются по умолчани в режим записи
        for(int i=0; i<tapes.length; i++){
            // System.out.println(tapes[i].elems);
            tapes[i].endRead();
            tapes[i].endWrite();
        }
    }

    static void writeResult(String file, Tape[] tapes)throws IOException{ // Функция записи результата каскадного слияния
        // в выходной файл с суффиксом _RES
        int notEmpty=0;
        for(int i=0; i<5; i++) if(!tapes[i].isEmpty){notEmpty=i; break;} // В конце алгоритма не пустой только
        // один файл, он находится и данные
        // переписываются в результат из него
        String resFile = ctlg(file) + "/" + dirct(file)+"_RES";


        FileWriter os = new FileWriter(resFile);
        BufferedWriter bos = new BufferedWriter(os);

        int num=0;
        while((num=tapes[notEmpty].readNum())!=-5){
            if(num==-2) continue;
            bos.write(Integer.toString(num) + " ");
        }

        bos.close();


    }



    static void cascadeSort(String file, int elems) throws IOException{ // Функция каскадного слияния
        // пока слияние не завершится,
        // будет применять каскадное слияние
        // к вспомогательным файлам

        Tape[] tapes = initializeTapes(file);								// Инициализация вспомогательных файлов
        firstDistribution(file, tapes, primaryDivision(elems, 4), elems);	// Проведение первичного распределения данных

        endOfMerge=false;	// Выставление логической переменой завершения слияния на false
        // когда останется только один непустой файл, алгоритм каскадного слияния
        // выставит ее в true и цикл завершится

        while(endOfMerge!=true){ // Провести итерацию каскадного слияния - если не завершилось, обнулить
            // флаги файлов и провести еще одну итерацию
            tapes=doCascade(tapes);
            setUpTapes(tapes);}

        writeResult(file, tapes);	// 	В итоге записать результат


    }


    public static int arrSumm(int[] arr){ // Сумма элементов массива
        // нужна для определения количества пустых серий
        // применяется к таблице первичного распределения
        int summ=0;
        for(int i: arr) summ+=i;
        return summ;
    }

    public static int[] copy(int[] arr){				// Копирование массива, техническая функция
        int[] narr = new int[arr.length];
        for(int i=0; i<arr.length; i++) narr[i]=arr[i];
        return narr;
    }

    public static int[] primaryDivision(int elems, int files){	//высчитываем сколько должно быть элемнтов
        // Первичное распределение
        // Curr stage
        // заполняется на основе prev stage
        // по алгоритму (суммы элементов)
        // Когда в curr stage сумма элементов станет
        // больше либо равной количеству элементов для слиния
        // она возвращается, как необходимая схема распределения
        // (меньше брать нельзя, так как все элементы не поместятся)
        int[] prevStage = new int[files];
        prevStage[0]=1;
        for(int i=1; i<prevStage.length; i++) prevStage[i]=0;

        int[] currStage = new int[files];
        for(int i=0; i<prevStage.length; i++) currStage[i]=0;

        int summ=0;
        while(arrSumm(currStage)<elems){
            summ=0;
            for(int i=0; i<prevStage.length; i++){
                summ+=prevStage[i];
                currStage[currStage.length-1-i]=summ;
            }
            prevStage=copy(currStage);
        }
        return prevStage;
    }



    public static void cascadeMetrix(String file, int elems) throws IOException{ // Проведение слияния на конкретном
        // файле, с замером времени выполнения
        // и количества операций обращения к файлам
        // и количества сравнений
        swaps=0;
        compares=0;
        start_time = System.nanoTime();
        cascadeSort(file, elems);
        end_time = System.nanoTime();
        long time = end_time-start_time;
        System.out.println(file + "\ncompares: " + compares + "\nFILE I/O operations: " + swaps + "\ntime: " + time +"\n");
    }


    public static void main(String[] args) throws IOException{
        initialize(args); // Инициализация входных данных




        cascadeMetrix(Sarr10_F,10);
        cascadeMetrix(Sarr10_B,10);
        cascadeMetrix(Sarr10_R,10);

        cascadeMetrix(Sarr500_F,500);
        cascadeMetrix(Sarr500_B,500);
        cascadeMetrix(Sarr500_R,500);

        cascadeMetrix(Sarr10000_F,10000);
        cascadeMetrix(Sarr10000_B,10000);
        cascadeMetrix(Sarr10000_R,10000);


    }}