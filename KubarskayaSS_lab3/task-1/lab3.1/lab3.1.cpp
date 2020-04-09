#include <iostream>
using namespace std;
#include <ctime>

struct res {
	int res1;
	int res2;
	int res3;
};

res results;

void maxsubarray(int* arr, int l, int m, int r)
{
	int leftsum = -99999, sum = 0, maxleft;
	for (int i = m; i >= l; i--)
	{
		sum += arr[i];
		if (sum > leftsum)
		{
			leftsum = sum;
			maxleft = i;
		}
	}
	int rightsum = -99999, maxright;
	sum = 0;
	for (int i = m + 1; i <= r; i++)
	{
		sum += arr[i];
		if (sum > rightsum)
		{
			rightsum = sum;
			maxright = i;
		}
	}
	results.res1 = maxleft;
	results.res2 = maxright;
	results.res3 = leftsum + rightsum;
}

void findmaxarray(int* arr, int l, int r)
{
	if (r == l)
	{
		results.res1 = l;
		results.res2 = r;
		results.res3 = arr[l];
		return;
	}
	else
	{
		int m = (l + r) / 2;
		findmaxarray(arr, l, m);
		int leftlow = results.res1;
		int lefthigh = results.res2;
		int leftsum = results.res3;
		findmaxarray(arr, m + 1, r);
		int rightlow = results.res1;
		int righthigh = results.res2;
		int rightsum = results.res3;
		maxsubarray(arr, l, m, r);
		int crosslow = results.res1;
		int crosshigh = results.res2;
		int crosssum = results.res3;

		if (leftsum >= rightsum && leftsum >= crosssum)
		{
			results.res1 = leftlow;
			results.res2 = lefthigh;
			results.res3 = leftsum;
			return;
		}
		else
		{
			if (rightsum >= leftsum && rightsum >= crosssum)
			{
				results.res1 = rightlow;
				results.res2 = righthigh;
				results.res3 = rightsum;
				return;
			}
			else
			{
				results.res1 = crosslow;
				results.res2 = crosshigh;
				results.res3 = crosssum;
				return;
			}
		}
	}
}

int main()
{
	setlocale(0, "");
	srand(time(0));
	int N;
	cout << "Введите количество дней: ";
	cin >> N;
	int* array = new int[N];
	cout << "День:\t";
	for (int i = 0; i < N; i++)
	{
		cout << i << "\t";
	}
	cout << endl << "Цена:\t";
	for (int i = 0; i < N; i++)
	{
		array[i] = rand() % 50;
		cout << array[i] << "\t";
	}
	cout << endl << "Изменение:\t";
	int* arr = new int[N - 1];
	for (int i = 0; i < N - 1; i++)
	{
		arr[i] = array[i + 1] - array[i];
		cout << arr[i] << "\t";
	}
	cout << endl;
	int l = 0, r = N - 2, m = (l + r) / 2;
	findmaxarray(arr, l, r);
	cout << "День покупки: " << results.res1 << endl << "День продажи: " << results.res2 + 1 << endl << "Прибыль: " << results.res3 << endl;
	system("pause");
}