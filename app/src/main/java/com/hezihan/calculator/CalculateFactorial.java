package com.hezihan.calculator;

public class CalculateFactorial
{
    public static final int MAX=1000;

    private int res_size;
    private final int[] res =new int[MAX];

    //good job, you have a good design, but you can use a constructor to initialize the res_size
    CalculateFactorial()
    {
        res_size = 1;
    }

    public int getRes()
    {
        return res_size;
    }

    //计算n的阶乘，返回一个int数组
    public int[] factorial(int n)
    {
        //初始化结果数组
        res[0] = 1;

        //计算n的阶乘
        for (int x=2; x<=n; x++)
            res_size = multiply(x, res_size);

        return res;
    }

    //计算x与res[]数组中的每个数字相乘，结果存入res[]数组中，返回res[]数组的长度
    private int multiply(int x, int r)
    {
        int carry = 0;

        //一个个数字与x相乘
        for (int i=0; i<r; i++)
        {
            int prod = res[i] * x + carry;
            res[i] = prod % 10;
            carry  = prod/10;
        }

        //将进位存入res[]数组中
        while (carry!=0)
        {
            res[r] = carry%10;
            carry = carry/10;
            r++;
        }
        return r;
    }
}
