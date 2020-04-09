#include "Header.h"
int main() {
	setlocale(LC_ALL, "Russian");
	int A, B, valume, k = 1, f = 0, i = -1;
	vvod(&A, &B, &valume);
	//cout << A << " " << B << " " << valume;
	if ((prover(A, B, valume)) == 1) {
		cout << "Объем стаканов четный,нужный нечетный.Ошибка" << endl;
		system("pause");
		return 0;
	}
	if ((prover(A, B, valume)) == 2) {
		cout << "Объемы стаканов равны.Ошибка" << endl;
		system("pause");
		return 0;
	}

	if ((prover(A, B, valume)) == 3) {
		cout << "Объемы стаканов меньше искомого.Ошибка" << endl;
		system("pause");
		return 0;
	}
	if ((prover(A, B, valume)) == 4) {
		cout << "Объемы стаканов <=0.Ошибка" << endl;
		system("pause");
		return 0;
	}
	int* A1 = new int[1000];
	int* B1 = new int[1000];
	A1[0] = 0;
	B1[0] = 0;
	point z;
	z.x = 0;
	z.y = 0;
	int z1 = algoritm(A, B, valume, k, z, A1, B1, f, i);
	i = z1;

	cout << endl;
	if (A1[i] != valume && B1[i] != valume)
	{
		cout << "Больше 100 итераций" << endl;

	}
	else {
		cout << " A " << 0 << " ";
		for (int i1 = 0; i1 <= i; i1++) {
			cout << A1[i1] << " ";
		}
		cout << endl;
		cout << " B " << 0 << " ";
		for (int i1 = 0; i1 <= i; i1++) {
			cout << B1[i1] << " ";
		}
		cout << endl;
	}
	delete A1;
	delete B1;
	system("pause");
	return 0;
}