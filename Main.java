public class Main
{
    public static void main(String[] args)
    {
        byte[] key = new byte[160];
        


        SEED seed = new SEED();

        int[] pOut = new int[4];
        int[] pIn = new int[4];

        

        pIn[0] = 0x00010203;
        pIn[1] = 0x04050607;
        pIn[2]= 0x08090a0b;
        pIn[3] = 0x0c0d0e0f;



        keyAsHex(key, "00000000000000000000000000000000");


        seed.init(key);
        pOut = seed.encrypt(pIn);

        for (int i = 0; i < 4; i++){
            System.out.println(pIn[i] + " : " + pOut[i]);
        }

        int[] decoded = seed.decrypt(pOut);

        for (int i = 0; i < 4; i++){
            System.out.println(pOut[i] + " : " + decoded[i]);
        }



    }

    private static void keyAsHex(byte[] dst, String src)
    {
        byte temp;
        byte hex = 0;

        for (int i = 0; i < src.length(); i++)
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
    }
}