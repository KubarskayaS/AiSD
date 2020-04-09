#include <iostream>

using namespace std;
void vvod(int* sizeofA, int* sizeofB, int* x) {
	cout << "Введите объём первого сосуда - " << endl;
	cin >> *sizeofA;
	cout << "Введите объём второго сосуда - " << endl;
	cin >> *sizeofB;
	cout << "Введите нужный объём - " << endl;
	cin >> *x;
	if (*sizeofA < *sizeofB)
		swap(*sizeofA, *sizeofB); // sizeofA - x; sizeofB - y
}
int prover(int sizeofA, int sizeofB, int x) {
	if (sizeofA % 2 == 0 && sizeofB % 2 == 0 && x % 2 != 0)
		return 1;
	if (sizeofA == sizeofB)
		return 2;
	if (x > sizeofA && x > sizeofB)
		return 3;
	if (sizeofA <= 0 || sizeofB <= 0)
		return 4;
	return 0;

}
struct point {
	int x, y;
};
int algoritm(int sizeofA, int sizeofB, int valume, int k, point z, int* A1, int* B1, int f, int i) {
	i++;

	if (z.x != valume && z.y != valume) {

		switch (k)
		{
		case 1: 
		{
			if (z.y == 0 && z.x == 0)
				z.x = 0;
			else z.x = z.x;
			z.y = sizeofB;
			A1[i] = z.x;
			B1[i] = z.y;
			if (z.x >= sizeofA - sizeofB)
			{
				k = 4;
				f = sizeofA - z.x;
			}
			else k = 2;
			if (z.x == valume || z.y == valume)
				return i;
			else
				if (i < 999)
					return algoritm(sizeofA, sizeofB, valume, k, z, A1, B1, f, i);
				else
					return i;
		}
		case 2: 
		{
			if (z.y <= sizeofB && z.x == 0)
				z.x = z.y;
			else
				z.x += sizeofB;
			z.y = 0;

			A1[i] = z.x;
			B1[i] = z.y;
			k = 1;
			if (z.x == valume || z.y == valume)
				return i;
			else {

				if (i < 999)
					return algoritm(sizeofA, sizeofB, valume, k, z, A1, B1, f, i);
				else
					return i;
			}
		}
		case 3: 
		{
			z.y = z.y;
			z.x = 0;
			A1[i] = z.x;
			B1[i] = z.y;
			k = 2;
			if (z.x == valume || z.y == valume)
				return i;
			else
				if (i < 999)
					return algoritm(sizeofA, sizeofB, valume, k, z, A1, B1, f, i);
				else
					return i;
		}
		case 4: 
		{
			z.y = sizeofB - f;
			z.x = sizeofA;
			k = 3;
			A1[i] = z.x;
			B1[i] = z.y;
			if (z.x == valume || z.y == valume)
				return i;
			else
				if (i < 999)
					return algoritm(sizeofA, sizeofB, valume, k, z, A1, B1, f, i);
				else
					return i;
		}

		}


	}
	else {
		return i;
	}
}