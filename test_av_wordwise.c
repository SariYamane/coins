int f(int a, int b, int p)
{
    int x;
    int y = 0;

    x = a + b;

    if (p)
    {
        y = a + b;
    }

    return x + y;
}

int main(void)
{
    return f(1, 2, 1);
}
