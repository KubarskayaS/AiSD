using System;
using System.Collections.Generic;

namespace laba_9.heap
{
    class DHeap : IHeap
    {
        private int[] heap;
        private int num = 0;

        private const int d = 3;

        private DHeap()
        {
            heap = new int[1000000];
        }

        public static IHeap Create()
        {
            return new DHeap();
        }

        public void Insert(int value)
        {
            heap[num] = value;
            ShiftUp(num);
            num++;
        }

        public int RemoveMin()
        {
            if (num == 0)
            {
                throw new NullReferenceException("Heap is empty.");
            }

            var result = heap[0];
            num--;
            Swap(0, num);
            ShiftDown(0);
            return result;
        }

        private int ParentIndex(int i)
        {
            if (i == 0) return -1;

            return (i - 1) / d;
        }

        private int[] ChildsIndexes(int i)
        {
            int t = 1;
            var indexes = new List<int>();

            while (i * d + t < num && t <= d)
            {
                indexes.Add(i * d + t);
                t++;
            }

            return indexes.ToArray();
        }

        private void Swap(int i, int j)
        {
            var buf = heap[i];
            heap[i] = heap[j];
            heap[j] = buf;
        }

        private void ShiftUp(int i)
        {
            var parentIndex = ParentIndex(i);

            while (i != 0 && heap[i] < heap[parentIndex])
            {
                Swap(i, parentIndex);
                i = parentIndex;
                parentIndex = ParentIndex(i);
            }
        }

        private void ShiftDown(int i)
        {
            var minChildIndex = MinChildIndex(i);

            while (minChildIndex != -1 && heap[i] > heap[minChildIndex])
            {
                Swap(i, minChildIndex);
                i = minChildIndex;
                minChildIndex = MinChildIndex(i);
            }
        }

        private int MinChildIndex(int i)
        {
            var minIndex = -1;

            var childs = ChildsIndexes(i);

            if (childs.Length == 0) return minIndex;

            var min = heap[childs[0]];
            minIndex = childs[0];
            for (int k = 1; k < childs.Length; k++)
            {
                if (min > heap[childs[k]])
                {
                    min = heap[childs[k]];
                    minIndex = childs[k];
                }
            }
            return minIndex;
        }

        public void Print()
        {
            if (num == 0)
            {
                Console.WriteLine("Empty.");
                return;
            }

            Console.ForegroundColor = ConsoleColor.DarkCyan;
            Console.Write("└─");
            Console.ResetColor();
            Console.Write("{0}\n", heap[0]);

            var childs = ChildsIndexes(0);
            if (childs.Length == 0) return;

            for (int i = 0; i < childs.Length; i++)
            {
                Print(childs[i], 1, i == childs.Length - 1);
            }
        }

        private void Print(int node, int order, bool end)
        {
            PrintNode(node, order, end);

            var childs = ChildsIndexes(node);
            if (childs.Length == 0) return;

            for (int i = 0; i < childs.Length; i++)
            {
                Print(childs[i], order + 1, i == childs.Length - 1);
            }
        }

        private void PrintNode(int node, int order, bool end)
        {
            string empty = "";
            /*for (int i = 0; i < order; i++)
            {
                empty += "  ";
            }*/
            var parent = ParentIndex(node);
            while (parent != -1)
            {
                var grandPa = ParentIndex(parent);
                if (grandPa != -1)
                {
                    if (parent == grandPa * d + d)
                    {
                        empty = "  " + empty;
                    }
                    else
                    {
                        empty = "│ " + empty;
                    }
                }
                else
                {
                    empty = "  " + empty;
                }
                parent = ParentIndex(parent);
            }

            Console.ForegroundColor = ConsoleColor.DarkCyan;
            Console.Write(empty);
            if (end) Console.Write("└─");
            else Console.Write("├─");
            Console.ForegroundColor = ConsoleColor.Green;
            Console.Write("C:");
            Console.ResetColor();
            Console.Write("{0}\n", heap[node]);
        }
    }
}
