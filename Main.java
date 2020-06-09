public class Main
{
    public static void main(String[] args)
    {
        byte[] key = new byte[160];
        byte[] in = new byte[200];
        byte[] mac = new byte[160];


        KISA_SEED seed = new KISA_SEED();

        int[] pOut = new int[4];
        int[] pIn = new int[4];

        int keyLen = 0, inLen = 0, macLen = 16;

        pIn[0] = 0x00010203;
        pIn[1] = 0x04050607;
        pIn[2]= 0x08090a0b;
        pIn[3] = 0x0c0d0e0f;



        System.out.println("looool");
        keyLen = asc2hex(key, "00000000000000000000000000000000");


        System.out.println(pIn[0]);
        seed.init(key);
        pOut = seed.encrypt(pIn);


        System.out.print(pOut[0]);
        System.out.println("");

        System.out.print(pOut[3]);
        System.out.println("");
        System.out.print(pOut[2]);
        System.out.println("");
        System.out.print(pOut[1]);



    }

    private static int asc2hex(byte[] dst, String src)
    {
        byte temp = 0x00, hex = 0;
        int i = 0;

        for (i = 0; i < src.length(); i++)
        {
            temp = 0x00;
            hex = (byte)src.charAt(i);

            if ((hex >= 0x30) && (hex <= 0x39))
                temp = (byte)(hex - 0x30);
            else if ((hex >= 0x41) && (hex <= 0x5A))
                temp = (byte)(hex - 0x41 + 10);
            else if ((hex >= 0x61) && (hex <= 0x7A))
                temp = (byte)(hex - 0x61 + 10);
            else
                temp = 0x00;

            if ((i & 1) == 1)
                dst[i >> 1] ^= temp & 0x0F;
            else
                dst[i >> 1] = (byte)(temp << 4);
        }

        return ((i + 1) / 2);
    }

    private static void print_hex(String valName, byte[] data, int dataLen)
    {
        int i = 0;

        System.out.printf("%s [%dbyte] :", valName, dataLen);
        for (i = 0; i < dataLen; i++)
        {
            if ((i & 0x0F) == 0)
                System.out.println("");

            System.out.printf(" %02X", data[i]);
        }
        System.out.println("");
    }

    private static void print_title(String title)
    {
        System.out.println("================================================");
        System.out.println("  " + title);
        System.out.println("================================================");
    }

    private static void print_result(String func, int ret)
    {
        if (ret == 1)
        {
            System.out.println("================================================");
            System.out.println("  " + func + " Failure!");
            System.out.println("================================================");

            System.exit(0);
        }
        else
        {
            System.out.println("================================================");
            System.out.println("  " + func + " Success!");
            System.out.println("================================================");
        }
    }
}