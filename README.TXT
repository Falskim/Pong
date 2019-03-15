------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Pong
PURPOSE OF PROJECT: Tidak ada lol
VERSION or DATE: 1.0
HOW TO START THIS PROJECT: Buka dengan aplikasi greenfoot (java)
AUTHORS: Fawwaz Syiham Muyassar
USER INSTRUCTIONS: Berhubung belum ditambahkan splash screen untuk menu, 
maka untuk mengatur melawan ai atau player 2 edit dulu subclass World yaitu 'Background'
pada method setGame() :

> addObject(new Player(SPEED, 2), WIDTH - 10, HEIGHT/2);
> addObject(new Bot(SPEED, ball), WIDTH - 10, HEIGHT/2);

Tambahkan // (untuk mengjadikan comment) pada salah satu line yang tidak dibutuhkan 
