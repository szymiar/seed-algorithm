
// Napisanie w sposób profesjonalny (żeby ktoś mógł uzyć)
//Przetestowanie i udokumentowanie że działa
//Do sprawdzenia działania należy użyć wektorów testowych(ze specyfikacji)
//Do sprawozdania załączyć dokumentację algorytmu
//Sprawozdanie + prezentacja + kod programu
//Prezka : wykonane zadania plus konstrukcja algorytmu seed


//Feistel structure, 16 rund, silny przeciw kryptoanalizie roznicowek, ktyproanalizie liniowej, atakach z powiązanym kluczem

import java.math.BigInteger;

public class Seed {

	static BigInteger L = new BigInteger("0001020304050607",16);
	static BigInteger R = new BigInteger("08090A0B0C0D0E",16);

	static final int mod_2 = (int)Math.pow(2,32); //wartosc 2^32

	static final int KC1 = 0x9E3779B9;
	static final int KC3 = 0x78DDE6E6;
	static final int KC5 = 0xE3779B99;
	static final int KC7 = 0x8DDE6E67;
	static final int KC9 = 0x3779B99E;
	static final int KC11 = 0xDDE6E678;
	static final int KC13 = 0x779B99E3;
	static final int KC15 = 0xDE6E678D;
	static final int KC2 = 0x3C6EF373;
	static final int KC4 = 0xF1BBCDCC;
	static final int KC6 = 0xC6EF3733;
	static final int KC8 = 0x1BBCDCCF;
	static final int KC10 = 0x6EF3733C;
	static final int KC12 = 0xBBCDCCF1;
	static final int KC14 = 0xEF3733C6;
	static final int KC16 = 0xBCDCCF1B;
	static final int[] KC = {KC1,KC2,KC3,KC4,KC5, KC6,KC7,KC8,KC9,KC10,KC11,KC12,KC13,KC14,KC15,KC16};
	static int[] K;


	static final BigInteger Key = new BigInteger("00000000000000000000000000000000",16);   //Mam nadzieje ze to dziala
	static final BigInteger PlainText = new BigInteger("000102030405060708090A0B0C0D0E",16) ;
	static final BigInteger CipherText = new BigInteger("5EBAC6E0054E166819AFF1CC6D346CDB",16);


	static int Key0 = 0x00000000;
	static int Key1 = 0x00000000;
	static int Key2 = 0x00000000;
	static int Key3 = 0x00000000;


	/**
	 * Przeprowadzenie enkrypcji
	 * @param l
	 * @param r
	 */
	public static void Encryption(BigInteger l , BigInteger r){
		int Ki=0;
		for (int i = 1; i<=15; i++){
			BigInteger temp = r;
			r = l.xor(F(keySchedule(Key0, Key1, Key2, Key3 , i), r));   //K[i] będzie generowany z key schedule
			l = temp;
		}
		L = l.xor(F(keySchedule(Key0, Key1, Key2, Key3 , 16), r)); //Zmiana globalnych wartości
		R = r;
	}

	public static int keySchedule(int Key0, int Key1, int Key2, int Key3, int i){
		int Ki0;
		int Ki1;
		int t1 = Key0 + Key2 - KC[i];
		int t2 = Key1 - Key3 + KC[i];
		Ki0 = G(t1);
		Ki1 = G(t2);
		if(i%2 == 1){
			//Key0 | Key1 = (Key | Key1) >> 8   //Tego jeszcze nie czaje jak to ma sie zmieniać
		}
		else{
			//Key0 | Key1 = (Key | Key1) << 8   // I tu też
		}
		int res = Integer.parseInt(Integer.toBinaryString(Ki0) + Integer.toBinaryString(Ki1));
		return res;  //Zwraca 64 bitowy podklucz zawierający dwa 32 bitowe podklucze
		}





	public static BigInteger F(int Ki, BigInteger R){
		int R0 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt(R.toString())).substring(0,15));    //Tworzymy 32 bitowe bloki
		int R1 = Integer.parseInt(Integer.toBinaryString(Integer.parseInt(R.toString())).substring(16));
		int Ki0 = Integer.parseInt(Integer.toBinaryString(Ki).substring(0,15));
		int Ki1 = Integer.parseInt(Integer.toBinaryString(Ki).substring(16));
		int temp1 = R0^Ki0;
		int temp2 = R1^Ki1;

		int R0_res = (G((G(G(((temp1)^(temp2))+(temp1))%mod_2)+G((temp1)^(temp2)))%mod_2) +
				G((G(temp1^temp2)+(temp1)) % mod_2))%mod_2;                                       	//To jest R0'
		int R1_res = G((G(G(((temp1)^(temp2))+(temp1))%mod_2)+G((temp1)^(temp2)))%mod_2);    //To jest R1'


		R = new BigInteger(Integer.toBinaryString(R0_res) + Integer.toBinaryString(R1_res), 16 );  //Ponowne połączenie R0' i R1' w R
		return R;
	}


	public static int G(int x){
		return 0x00000000;
	}

    public static void main(String[] args) {


		Encryption(L,R);
		System.out.println(L);  //Na razie to to nic nie zrobi
		System.out.println(R);

    }

}