**Janji**

Saya Armelia Zahrah Mumtaz dengan NIM 2300801 berjanji mengerjakan TP6 DPBO dengan keberkahan-Nya, maka saya tidak akan melakukan kecurangan sesuai yang telah di spesifikasikan, Aamiin


**Desain Program**


Program Flappy Bird ini diimplementasikan menggunakan Java Swing dengan struktur berorientasi objek yang terdiri dari 5 kelas utama:

App: Kelas utama yang berisi method main() dan berfungsi sebagai entry point aplikasi

Menu: Kelas yang menampilkan menu utama game

FlappyBird: Kelas yang mengimplementasikan logika permainan utama

Player: Kelas yang merepresentasikan karakter pemain (burung)

Pipe: Kelas yang merepresentasikan rintangan (pipa)

**Penjelasan Alur Program**

1. **Alur Eksekusi Program**

Program dimulai dari method main() di kelas App
App membuat instance Menu dan menampilkannya
Pemain menekan tombol "Start Game" pada menu
Method startGame() di kelas App dipanggil
App membuat JFrame dan instance FlappyBird
Game dimulai dan menjalankan loop game utama

2. **Alur Gameplay**

a. Inisialisasi Game:

Memuat gambar (background, burung, pipa)
Membuat instance Player di posisi awal
Menginisialisasi ArrayList untuk menyimpan pipa
Membuat label skor
Menyiapkan timer untuk game loop dan pembuatan pipa
Mengatur listener keyboard


b. Game Loop:

Timer gameTimer memanggil method actionPerformed() setiap 20ms
Method move() memperbarui posisi player dan pipa
Method repaint() menggambar ulang semua elemen game
Memeriksa tabrakan antara player dan pipa atau batas bawah layar


c. Mekanisme Pembuatan Pipa:

Timer pipeTimer memanggil method placePipe() setiap 2000ms
Pipa dibuat dengan ketinggian acak
Setiap pemanggilan membuat satu pasang pipa (atas dan bawah)


d. Interaksi Pengguna:

Pemain menekan spasi untuk melompat (mengubah velocityY player)
Gravitasi secara konstan diterapkan pada player (menambah velocityY)
Setelah game over, pemain dapat menekan 'R' untuk restart


e. Sistem Skor:

Skor bertambah setiap kali player berhasil melewati sepasang pipa
Hanya pipa atas yang dihitung untuk menghindari penghitungan ganda


f. Game Over:

Terjadi ketika player menabrak pipa atau mencapai batas bawah layar
Semua pergerakan dihentikan
Pesan game over dan instruksi restart ditampilkan
