import java.nio.ByteBuffer;

public class Main
{
    public static void main(String[] args)
    {
        byte[] key = new byte[16];



        SEED seed = new SEED();

        int[] CipherText = new int[4];
        int[] PlainText = new int[4];

        /*
        String keyS = "00000000000000000000000000000000";

        PlainText[0] = 0x00010203;
        PlainText[1] = 0x04050607;
        PlainText[2] = 0x08090a0b;
        PlainText[3] = 0x0c0d0e0f;
         */

        String keyS = "00102030405060708090a0b0c0d0e0f";

        PlainText[0] = 0x0;
        PlainText[1] = 0x0;
        PlainText[2] = 0x0;
        PlainText[3] = 0x0;




        keyAsHex(key, keyS);
        seed.init(key);
        CipherText = seed.encrypt(PlainText);

        for (int i = 0; i < 4; i++){
            System.out.println(Integer.toString(PlainText[i],16) + " : " + Integer.toString(CipherText[i],16));
        }

        int[] decoded = seed.decrypt(CipherText);

        for (int i = 0; i < 4; i++){
            System.out.println(Integer.toString(CipherText[i],16) + " : " + Integer.toString(decoded[i],16));
        }
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