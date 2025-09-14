<a id="readme-top"></a>

<div align="center">
  <h2 align="center">Floyd Algorithm</h2>

A script that implements an algorithm for finding the lengths of the shortest paths between all pairs of vertices in a weighted directed graph with a [JavaFX][JavaFX] UI.

  <img align="center" src=".sourceReadme/preview.gif" alt="Preview" width="600"/>
</div>
<br>
<!-- TABLE OF CONTENTS -->
<details>
  <summary id="contents">Table of Contents</summary>
  <ol>
    <li>
      <a href="#🧠-about-the-project">About The Project</a>
      <ul>
        <li><a href="#💭-inspiration">Inspiration</a></li>
        <li><a href="#🔧-built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#✨-getting-started">Getting Started</a>
      <ul>
        <li><a href="#💾-install">Install</a></li>
        <li><a href="#⚙️-how-to-use">How to use</a></li>
      </ul>
    </li>
    <li><a href="#📓-contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## 🧠 About The Project

This pet project implements Floyd's algorithm with a JavaFX UI. In addition to finding the shortest path, the script calculates an ordinal matrix and a weight matrix. The number of points/ matrix size can be from 3 to 10. All points are interactive and can be moved. To create edges, you need to specify the link weight in the matrix.

### 💭 Inspiration

This project was written as a study paper. Such a stack was chosen for learning JavaFX and working with it.

### 🔧 Built With

<a href="https://www.java.com/">
  <img src="https://blog.nashtechglobal.com/wp-content/uploads/2024/06/image-19.png" alt="Java17" width="250" height="106"/>
</a>
<a href="https://openjfx.io/">
  <img src="https://upload.wikimedia.org/wikipedia/commons/3/30/JavaFX_text_logo.png" alt="JavaFX" width="250"/>
</a>
<a href="https://maven.apache.org/">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZaiwwUTVbO_KlkVRlqN-pfte12y3Ht-wddA&s" alt="Maven" width="350" height="106"/>
</a>

<p align="right">( <a href="#readme-top">to top</a> | <a href="#contents">to contents</a> )</p>

<!-- GETTING STARTED -->

## ✨ Getting Started

### 💾 Install

1. Make sure you have Java 17 or higher installed and Maven. You can download its from the official website<br>
   👉 https://www.oracle.com/java/technologies/downloads/
   👉 https://maven.apache.org/download.cgi

2. Clone the repo

    ```
    git clone https://github.com/h0usss/Floyd_Algorithm
    ```

3. Go to the application folder

    ```
    cd Floyd_Algorithm
    ```

4. Launch the app

    ```
    mvn javafx:run
    ```

### ⚙️ How to use

| Picture 1                                             | Picture 2                                               | 
| ----------------------------------------------------- | ------------------------------------------------------- | 
| <img src=".sourceReadme/matrix.png" width="400"/> | <img src=".sourceReadme/size.png" width="400"/> |
|View different types of matrix|Change the size of the matrix|

|Picture 3                                                | Picture 4                                               |
|----------------------------------------------------- | ----------------------------------------------------- | 
| <img src=".sourceReadme/lineCreate.png" width="200"/> | <img src=".sourceReadme/path.png" width="400"/> |
|Setting connections between nodes|Selecting points for the path, calculate paths, reset settings|


<p align="right">( <a href="#readme-top">to top</a> | <a href="#contents">to contents</a> )</p>

<!-- CONTACT -->

## 📓 Contact

hnoproblems@gmail.com

Project Link: [https://github.com/h0usss/Floyd_Algorithm](https://github.com/h0usss/Floyd_Algorithm)

<p align="right">( <a href="#readme-top">to top</a> | <a href="#contents">to contents</a> )</p>

[JavaFX]: https://openjfx.io/
