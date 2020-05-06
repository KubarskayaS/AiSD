using laba_9.heap;
using System;

namespace laba_9
{
    class Program
    {
        static void Main(string[] args)
        {
            var heap = DHeap.Create();

            while (true)
            {
                Console.Write("\nPress any key to continue...\n");
                Console.Write("1)Добавить узел\n");
                Console.Write("2)Удалить корень\n");
                Console.Write("3)Отрисовка кучи\n");
                int choise = Int32.Parse(Console.ReadLine());

                switch (choise)
                {
                    case 1:
                        {
                            Console.Write("Введите узел: ");
                            var n = Int32.Parse(Console.ReadLine());
                            heap.Insert(n);
                            break;
                        }
                    case 2:
                        {
                            heap.RemoveMin();
                            break;
                        }
                    case 3:
                        {
                            heap.Print();
                            break;
                        }
                    default:
                        Console.Write("Плохой выбор\n");
                        break;
                }
            }

                }
            }
}
