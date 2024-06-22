<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/5d961484b0.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/DogeJSP/css/index.css">
    <script src="/DogeJSP/js/index.js"></script>
</head>

<body>
    <header>
        <nav>
            <a href="#">
                <img src="https://dogecoin.com/assets/images/dogecoin-logo.png" alt="">
            </a>
            <ul>
                <li>
                    <a href="#">So Home</a>
                </li>
                <li>
                    <a href="#">What is Dogecoin?</a>
                </li>
                <li>
                    <a href="#">Much Wallets</a>
                </li>
                <li>
                    <a href="#">Very Community</a>
                </li>
                <li id="dogepedia" onmouseover="displayInMenu(this.id)">
                    <a href="#">
                        So Dogepedia
                        <i class="fa-solid fa-caret-down"></i>
                    </a>
                    <div id="dogepediaInMenu" style="display:none;" onmouseout="hideInMenu(this.id)">
                        <a href="#">Document</a>
                        <a href="#">FAQ</a>
                        <a href="#">How Tos</a>
                        <a href="#">Resources</a>
                    </div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa-solid fa-shirt"></i>
                    </a>
                </li>
                <li>
                    <a>
                    	<i class="fa-solid fa-user"></i>
                    </a>
                </li>
                <li id="toggle">
                    <i class="fa-solid fa-circle-half-stroke"></i>
                </li>
                <li id="language" onmouseover="displayInMenu(this.id)">
                    <a href="#">
                        <i class="fa-solid fa-square"></i>
                        <span class="contry">EN</span>
                        <i class="fa-solid fa-caret-down"></i>
                    </a>
                    <div id="languageInMenu" style="display:none;" onmouseout="hideInMenu(this.id)">
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">FR</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">DE</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">ZH-CN</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">ZH-TW</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">PT-PT</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">NO</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">TH</span>
                        </a>
                        <a href="#">
                            <i class="fa-solid fa-square"></i>
                            <span class="contry">ES</span>
                        </a>
                    </div>
                </li>
            </ul>
        </nav>
    </header>
    <div class="video-container">
        <video type="video/mp4" autoplay loop muted class>
            <source src="https://dogecoin.com/assets/images/Header_Video.mp4">
        </video>
        <div class="content">
            <img src="https://dogecoin.com/assets/images/doge.svg" alt="">
            <h1>DOGECOIN</h1>
            <span>the people’s</span>
            <span id="randomStr"></span>
            <div class="dogesay">
                <a href="">
                    Such Core Wallet 1.14.7 released! Much upgrade, plz!
                </a>
                <div class="dogesay-triangle"></div>
            </div>
        </div>
    </div>
    <footer>
        <div class="footer-first">
            <div>
                <div>
                    <img src="https://dogecoin.com/doge-logo.png" alt="">
                    <span>DOGECOIN</span>
                </div>
                <p>The Shiba Inu is a Japanese breed of dog that was popularized as an online meme and represents
                    Dogecoin. Dogecoin was created by Jackson Palmer & Shibetoshi Nakamoto.</p>
            </div>
            <div>
                <span class="title">Legal</span>
                <ul>
                    <li><a href="#">Trademarks</a></li>
                </ul>
            </div>
            <div>
                <span class="title">Sitemap</span>
                <ul>
                    <li><a href="#">So Home</a></li>
                    <li><a href="#">What is Dogecoin?</a></li>
                    <li><a href="#">Much Wallets</a></li>
                    <li><a href="#">Very Community</a></li>
                    <li><a href="#">So Dogepedia</a></li>
                </ul>
            </div>
            <div>
                <span class="title">Foundation</span>
                <ul>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Manifesto</a></li>
                    <li><a href="#">Advisors</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">Trailmap</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-last">
            <a href="">Contributors</a>
            <p>© 2013-2024 | The Dogecoin Foundation & Dogecoin Project. All rights reserved.</p>
        </div>
    </footer>
</body>

</html>