import java.math.BigInteger;
import java.nio.ByteBuffer;

public class Main2
{
    public static void main(String[] args)
    {
        byte[] key = new byte[16];



        SEED2 seed = new SEED2();

        BigInteger[] CipherText = new BigInteger[4];
        BigInteger[] PlainText = new BigInteger[4];



        PlainText[0] = new BigInteger("00010203",16);
        PlainText[1] = new BigInteger("04050607",16);
        PlainText[2] = new BigInteger("08090a0b",16);
        PlainText[3] = new BigInteger("0c0d0e0f",16);



        int[]PlainText2 = new int[4];
        PlainText2[0] = 0x83a2f8a2;
        PlainText2[1] = 0x88641fb9;
        PlainText2[2] = 0xa4e9a5cc;
        PlainText2[3] = 0x2f131c7d;
        String key2 = "4706480851e61be85d74bfb3fd956185";


        keyAsHex(key, "00000000000000000000000000000000");
        seed.init(key);
        CipherText = seed.encrypt(PlainText);

        for (int i = 0; i < 4; i++){
            System.out.println(getUnsignedInt(PlainText[i].intValue()) + " : " + getUnsignedInt(CipherText[i].intValue()));
        }

        BigInteger[] decoded = seed.decrypt(CipherText);

        for (int i = 0; i < 4; i++){
            System.out.println(getUnsignedInt(CipherText[i].intValue()) + " : " + getUnsignedInt(decoded[i].intValue()));
        }
/*
        System.out.println("");
        System.out.println("");
        System.out.println("");
        keyAsHex(key, key2);
        seed.init(key);
        CipherText = seed.encrypt(PlainText2);

        for (int i = 0; i < 4; i++){
            System.out.println(Long.toString(getUnsignedInt(PlainText2[i]),16) + " : " + Long.toString(getUnsignedInt(CipherText[i]),16));
        }

       // int[] decoded2 = seed.decrypt(CipherText);

        System.out.println("");
        for (int i = 0; i < 4; i++){
            System.out.println(Long.toString(getUnsignedInt(CipherText[i]),16) + " : " + Long.toString(getUnsignedInt(decoded2[i]),16));
        }

*/
    }

   /* private static void convertByte(byte[] value, String key){

        for(int i =0; i< key.length()-1 ; i++){
            value[i] = (byte)key.substring(i-1,i).;
        }

    }*/

    public static final int BITS_PER_BYTE = 8;
    public static long getUnsignedInt(int x) {
        ByteBuffer buf = ByteBuffer.allocate(Long.SIZE / BITS_PER_BYTE);
        buf.putInt(Integer.SIZE / BITS_PER_BYTE, x);
        return buf.getLong(0);
    }


    /**
     * Pozwala otrzymać poprawnie utworzony klucz wartości hexadecymalnych
     * @param res
     * @param key
     */
    private static void keyAsHex(byte[] res, String key)
    {
        byte temp;
        byte hex = 0;

        for (int i = 0; i < key.length(); i++)
        {
            temp = 0x00;
            hex = (byte)key.charAt(i);

            if ((hex >= 0x30) && (hex <= 0x39))
                temp = (byte)(hex - 0x30);
            else if ((hex >= 0x41) && (hex <= 0x5A))
                temp = (byte)(hex - 0x41 + 10);
            else if ((hex >= 0x61) && (hex <= 0x7A))
                temp = (byte)(hex - 0x61 + 10);
            else
                temp = 0x00;

            if ((i & 1) == 1)
                res[i >> 1] ^= temp & 0x0F;
            else
                res[i >> 1] = (byte)(temp << 4);
        }
    }
}
