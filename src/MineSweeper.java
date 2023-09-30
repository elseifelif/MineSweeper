import java.util.Scanner;
import java.util.Random;

// Değerlendirme Formu #5: Proje MineSweeper sınıfı içerisinde tasarlanmıştır.

public class MineSweeper {
    Scanner scan = new Scanner(System.in);
    int oyunAlaniSatirSayisi;
    int oyunAlaniSutunSayisi;
    String[][] mayinliOyunAlani;
    String[][] oyunAlaniKullaniciGorunumu;
    int satirTercihi;
    int sutunTercihi;
    int guncellenmisSatirTercihi;
    int guncellenmisSutunTercihi;


    // calistir() fonksiyonunun main metodu ile (Main sınıfındaki) çağırılmasıyla program çalıştırılmaktadır.

    public void calistir() {

        oyunAlaniOlustur();
        mayinsizAlanOlustur();
        mayinYerlesimi();
        mayinliDiziYazdir();
        mayinsizOyunGorunumu();
        oyunuBaslat();
    }

    // Değerlendirme Formu #7: Satır ve sütun sayılarını kullanıcı belirliyor.
    // 0 veya 0'dan küçük bir sayı yazılırsa tekrar sayı girişi isteniyor.

    public void oyunAlaniOlustur() {
        System.out.println("Oyundaki satır sayısını yazınız.");
        oyunAlaniSatirSayisi = scan.nextInt();
        System.out.println("Oyundaki sütun sayısını yazınız.");
        oyunAlaniSutunSayisi = scan.nextInt();
        if (oyunAlaniSutunSayisi <= 0 || oyunAlaniSatirSayisi <= 0) {
            System.out.println("Hatalı sayı girişi yaptınız, lütfen satır ve sütun bilgisini tekrar yazınız.");
            oyunAlaniOlustur();
        } else {

            mayinliOyunAlani = new String[oyunAlaniSatirSayisi][oyunAlaniSutunSayisi];
            oyunAlaniKullaniciGorunumu = new String[oyunAlaniSatirSayisi][oyunAlaniSutunSayisi];
        }
    }

    // İlk olarak tüm indisler "_" değerine getirilmektedir, mayınlar daha sonra eklenecektir.

    public void mayinsizAlanOlustur() {

        for (int i = 0; i < oyunAlaniSatirSayisi; i++) {
            for (int j = 0; j < oyunAlaniSutunSayisi; j++) {
                mayinliOyunAlani[i][j] = "_";
            }
        }

    }
    // Kullanıcı görünümü: mayınlar gizlenmiş şekilde oyun alanı kullanıcıya gösterilecektir.

    void mayinsizOyunGorunumu() {
        for (int i = 0; i < oyunAlaniSatirSayisi; i++) {
            for (int j = 0; j < oyunAlaniSutunSayisi; j++) {
                oyunAlaniKullaniciGorunumu[i][j] = "_";
                System.out.print(oyunAlaniKullaniciGorunumu[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /*Değerlendirme formu #8: Mayın sayısı matris alanının 1/4'ü olacak şekilde hesaplanıyor.
    Hesaplama ondalıklı olur ise virgülden sonraki değerler ihmal edilecektir.*/

    public int mayinSayisiHesapla() {
        return (oyunAlaniSutunSayisi * oyunAlaniSatirSayisi) / 4;
    }

    // Değerlendirme Formu #8: hesaplanan mayın sayısına göre matris içerisinde rastgele mayın yerleşimi yapılmaktadır.
    public void mayinYerlesimi() {

        for (int i = 0; i < mayinSayisiHesapla(); i++) {
            int mayininBulunduguSatir;
            int mayininBulunduguSutun;

            Random randomObject = new Random();

            int rastgeleSayi = randomObject.nextInt(oyunAlaniSatirSayisi * oyunAlaniSutunSayisi);

            /* index = (0,0) değerinden rastgeleSayi değerine kadar sayma işlemi yapılırsa
            aşağıdaki satır ve sütun değerlerine ulaşılacaktır.*/

            mayininBulunduguSatir = rastgeleSayi / oyunAlaniSutunSayisi;
            mayininBulunduguSutun = rastgeleSayi % oyunAlaniSutunSayisi;

            /* Mayın ataması yapılacak indise daha önceden mayın yerleştirildiyse,
            yeni mayın eklenemediğinden döngüyü 1 kez daha tekrarlamak için i değerini 1 azaltıyoruz.
            Böylece doğru sayıda mayın yerleştirilecektir. */

            if (mayinliOyunAlani[mayininBulunduguSatir][mayininBulunduguSutun].equals("*")) {
                i--;
            }
            mayinliOyunAlani[mayininBulunduguSatir][mayininBulunduguSutun] = "*";

        }

    }

    // Mayın yerleşimi yapıldıktan sonra çağırılan mayınlı diziyi yazdırma metodu
    public void mayinliDiziYazdir() {
        for (int i = 0; i < oyunAlaniSatirSayisi; i++) {
            for (int j = 0; j < oyunAlaniSutunSayisi; j++) {

                System.out.print((mayinliOyunAlani[i][j] + "  "));
            }
            System.out.println();
        }
        System.out.println();

    }

    // Ekstra özellik: Kullanıcıya her turda henüz seçilmemiş indis sayısı ve tablodaki mayın sayısı bildirilmektedir.
    public int isaretlemeYapilmamisIndisSayisi() {
        int sayac2 = 0;

        for (int i = 0; i < oyunAlaniSatirSayisi; i++) {
            for (int j = 0; j < oyunAlaniSutunSayisi; j++) {
                if (oyunAlaniKullaniciGorunumu[i][j].equals("_")) {
                    sayac2++;
                }
            }
        }
        return sayac2;
    }

    // Ekstra özellik: Bilgi mesajı
    public void bilgiMesaji() {
        System.out.println();
        System.out.println("İşaretlenmemiş hücre sayısı: " + isaretlemeYapilmamisIndisSayisi());
        System.out.println("Toplam mayın sayısı: " + mayinSayisiHesapla());

    }

    /* Değerlendirme Formu #9: Kullanıcının seçmek istediği satır ve sütun değerlerinin alınması

    Programda array indisleri 1 yerine 0'dan başladığı için kullanıcının seçtiği satır ve sütunların
    doğru indise denk gelmesi için sayılar 1'er adet azaltıldı ve
    guncellenmisSatirTercihi, guncellenmisSutunTercihi değişkenlerine atandı.*/

    public void satirVeSutunSecimi() {

        System.out.println("Seçmek istediğiniz satırı yazınız.");
        satirTercihi = scan.nextInt();
        guncellenmisSatirTercihi = satirTercihi - 1;

        System.out.println("Seçmek istediğiniz sütunu yazınız.");
        sutunTercihi = scan.nextInt();
        guncellenmisSutunTercihi = sutunTercihi - 1;

    }

    // Oyundaki turları ayırmak için ayraç metodu
    public void ayrac() {
        System.out.println("_______________________");
        System.out.println();

    }

    /* Değerlendirme Formu #6: oyunu başlatma, oyunu kaybetme, oyunu kazanma vb. fonksiyonların kontrolü
    Programda oyunun 1. turundan itibaren oyunu kazanma veya kaybetme durumuna kadar
    oyunuBaslat() metodu çalıştırılmaktadır.*/

    public void oyunuBaslat() {

        satirVeSutunSecimi();

        if (hataliIndexKontrolu()) { //Değerlendirme Formu #10
            tekrarHucreSecimiHataliGiris(); //Değerlendirme Formu #10
            ayrac();
            oyunuBaslat();
        } else if (tekrarEdenIndexKontrolu()) {
            tekrarHucreSecimi_TekrarEdenIndis();
            ayrac();
            oyunuBaslat();
        } else if (oyunuKaybetmeKontrolu()) { //Değerlendirme Formu #13
            ayrac();
            oyunKaybetmeMesaji(); //Değerlendirme Formu #15
        } else {
            cevredekiMayinSayisi(); //Değerlendirme Formu #12
            isaretlemeYapilmamisIndisSayisi();
            bilgiMesaji();
            guncelOyunGorunumu(); //Değerlendirme Formu #11
            if (oyunuKazanmaKontrolu()) { //Değerlendirme Formu #14
                ayrac();
                oyunuKazanmaMesaji(); //Değerlendirme Formu #15
                oyunuKazanmaGorunumu();
            } else {
                ayrac();
                oyunuBaslat();
            }
        }
    }

    /* Değerlendirme Formu #12: Kullanıcının seçtiği indisin sağ, sol, üst, alt ve çapraz komşu indislerindeki
    mayın sayısı bir sayaçta toplanarak hesaplanır. Kullanıcının seçtiği hücreyi (x,y) noktası kabul edersek,
    x+1,y+1,x-1,y-1 satır ve sütunlarında bulunan komşu hücrelerin mayın durumu kontrol edilmektedir.

    Hesaplanan değer oyunAlaniKullaniciGorunumu isimli array'de
    ilgili indise yazdırılır. Komşu hücrelerde hiç mayın yok ise 0 değeri yazdırılır.

    Oyun sınırlarını aşmamak için, kontrol edilen indisin 0 ile oyundaki en büyük satır/sütun değerleri arasında
    olması gerekir, hücrelerdeki mayın sayıları bu koşula göre toplanır. */

    void cevredekiMayinSayisi() {
        int sayac = 0;
        // x-1, y+1 koordinatı
        if ((guncellenmisSatirTercihi - 1 >= 0) && (guncellenmisSutunTercihi + 1 <= oyunAlaniSutunSayisi - 1)) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi - 1][guncellenmisSutunTercihi + 1].equals("*")) sayac++;
        }
        // x=0, y+1 koordinatı
        if (guncellenmisSutunTercihi + 1 <= oyunAlaniSutunSayisi - 1) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi][guncellenmisSutunTercihi + 1].equals("*")) sayac++;
        }
        // x+1, y+1 koordinatı
        if ((guncellenmisSutunTercihi + 1 <= oyunAlaniSutunSayisi - 1) &&
                (guncellenmisSatirTercihi + 1 <= oyunAlaniSatirSayisi - 1)) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi + 1][guncellenmisSutunTercihi + 1].equals("*")) sayac++;
        }
        // x-1, y=0 koordinatı
        if (guncellenmisSatirTercihi - 1 >= 0) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi - 1][guncellenmisSutunTercihi].equals("*")) sayac++;
        }
        // x-1, y-1 koordinatı
        if ((guncellenmisSatirTercihi - 1 >= 0) && (guncellenmisSutunTercihi - 1 >= 0)) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi - 1][guncellenmisSutunTercihi - 1].equals("*")) sayac++;
        }
        // x=0, y-1 koordinatı
        if ((guncellenmisSutunTercihi - 1 >= 0)) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi][guncellenmisSutunTercihi - 1].equals("*")) sayac++;
        }
        // x+1, y-1 koordinatı
        if ((guncellenmisSatirTercihi + 1 <= oyunAlaniSatirSayisi - 1) && (guncellenmisSutunTercihi - 1 >= 0)) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi + 1][guncellenmisSutunTercihi - 1].equals("*")) sayac++;
        }
        // x+1, y=0 koordinatı
        if ((guncellenmisSatirTercihi + 1 <= oyunAlaniSatirSayisi - 1)) {
            if (mayinliOyunAlani[guncellenmisSatirTercihi + 1][guncellenmisSutunTercihi].equals("*")) sayac++;
        }

        oyunAlaniKullaniciGorunumu[guncellenmisSatirTercihi][guncellenmisSutunTercihi] = String.valueOf(sayac);
    }

    /*Değerlendirme Formu #10: Kullanıcının seçtiği indis değerleri oyun alanı içerisinde mi kontrol edilmektedir.
    Tercih edilen değerler oyun sınırlarını aşıyorsa tekrar satır ve sütun bilgisi istenir.*/
    public boolean hataliIndexKontrolu() {
        return (guncellenmisSatirTercihi >= oyunAlaniSatirSayisi)
                || (guncellenmisSutunTercihi >= oyunAlaniSutunSayisi)
                || (guncellenmisSatirTercihi < 0) || (guncellenmisSutunTercihi < 0);
    }

    public void tekrarHucreSecimiHataliGiris() {
        System.out.println("Hatalı seçim yapıldığı için satır ve sütun değerini yeniden yazınız.");

    }

    //Ekstra özellik: Kullanıcı daha önce seçtiği indisi tekrar tercih etti mi kontrol edilmektedir.
    public boolean tekrarEdenIndexKontrolu() {

        char karakter = oyunAlaniKullaniciGorunumu[guncellenmisSatirTercihi][guncellenmisSutunTercihi].charAt(0);
        return (Character.isDigit(karakter));
    }

    // Ekstra özellik: Kullanıcı daha önce seçtiği hücreyi tekrar seçerse bilgi mesajı yayınlanarak
    // farklı bir indis tercih etmesi istenir.

    public void tekrarHucreSecimi_TekrarEdenIndis() {
        System.out.println("Daha önce aynı hücreyi seçmiştiniz. Farklı bir satır ve sütun değeri yazınız.");
        System.out.println();

    }

    // Değerlendirme Formu #11: Kullanıcı seçim yaptıktan sonra güncel oyun görünümü ekrana yazdırılır.

    void guncelOyunGorunumu() {
        for (int i = 0; i < oyunAlaniSatirSayisi; i++) {
            for (int j = 0; j < oyunAlaniSutunSayisi; j++) {
                System.out.print(oyunAlaniKullaniciGorunumu[i][j] + "  ");
            }
            System.out.println();

        }
        System.out.println();
    }
    // Değerlendirme Formu #13: Kullanıcının seçtiği indise göre oyunu kaybetme kontrolü yapılmaktadır.
    // Seçilen indiste mayın varsa kullanıcı oyunu kaybedecektir.

    public boolean oyunuKaybetmeKontrolu() {
        return (mayinliOyunAlani[guncellenmisSatirTercihi][guncellenmisSutunTercihi].equals("*"));
    }

    // Değerlendirme Formu #15: Oyunu kaybetme mesajı
    public void oyunKaybetmeMesaji() {

        System.out.println("Mayına denk geldiniz, oyunu kaybettiniz.");

    }

    // Değerlendirme Formu #14: Henüz seçilmemiş indis sayısı mayın sayısına eşit olduğunda oyun kazanılmaktadır.
    public boolean oyunuKazanmaKontrolu() {

        return isaretlemeYapilmamisIndisSayisi() == mayinSayisiHesapla();

    }
    // Değerlendirme Formu #15: Oyunu kazanma mesajı

    void oyunuKazanmaMesaji() {

        System.out.println("Tebrikler, oyunu kazandınız.");

    }
    // Oyun kazanıldığında mayın yerleşimi yapılmış haliyle tablo ekrana yazdırılır.

    void oyunuKazanmaGorunumu() {
        for (int i = 0; i < oyunAlaniSatirSayisi; i++) {
            for (int j = 0; j < oyunAlaniSutunSayisi; j++) {
                if (oyunAlaniKullaniciGorunumu[i][j].equals("_")) {
                    oyunAlaniKullaniciGorunumu[i][j] = "*";
                }
                System.out.print(oyunAlaniKullaniciGorunumu[i][j] + "  ");

            }
            System.out.println();
        }
        System.out.println();
    }
}