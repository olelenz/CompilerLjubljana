<div align="center">

  <h3 align="center">A compiler for a made-up high-level language built using Java.</h3>

  <p align="center">
    [SS 23] CompilerLjubljana
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
As a part of a course held in SS23 at Univerza v Ljubljani, I developed a compiler for a made-up high-level language built using Java. 

It produces machine code for mmix processors.



<!-- GETTING STARTED -->
## Getting Started

Download the project and compile it (inside the /prev23 directory):
```sh
make
```

To compile a filename.p23 source file, execute the following command inside the /prev23 directory:
```sh
java -cp ./bin:./src:./lib/antlr-4.13.1-complete.jar prev23.Compiler --src-file-name=<path-to-input-file> --dst-file-name=<output-name> --num-regs=<number-of-registers-to-be-used>
```


Creating a MMIX object file:
```sh
mmixal <path-to-.mms-file>
```

Executing a MMIX object file:
```sh
mmix <path-to-.mmo-file>
```

There are two example programs provided inside the /prg directory.
You can write your own programs using the provided language definition.

### Prerequisites
Java-version
  * This project uses Java version 21

Antlr
  * A compatible antlr .jar is included in the /lib directory

MMIX (included in /lib for Unix)
  * mmixal for creating an MMIX object file
  * mmix to execute the MMIX object file


<!-- CONTACT -->
## Contact

Ole Lenz - ole.lenz@gmx.net and ole.lenz@campus.tu-berlin.de

Project Link: [https://github.com/olelenz/connect_four](https://github.com/olelenz/CompilerLjubljana)


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

The code skeleton was provided by Boštjan Slivnik.
* [Website](https://www.fri.uni-lj.si/sl/o-fakulteti/osebje/bostjan-slivnik)
