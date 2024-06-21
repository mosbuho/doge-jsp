if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
    document.documentElement.setAttribute("theme", "dark");
    localStorage.setItem("theme", "dark");
} else {
    document.documentElement.setAttribute("theme", "light");
    localStorage.setItem("theme", "light");
}
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('toggle').addEventListener("click", () => {
        let theme = localStorage.getItem("theme");
        if (theme == 'dark') {
            localStorage.setItem("theme", "light");
            document.documentElement.setAttribute("theme", "light");
        } else {
            localStorage.setItem("theme", "dark");
            document.documentElement.setAttribute("theme", "dark");
        }
    }, false);
    // --------------------------------------------------------------- //
    const targetString = 'cryptocurrency';
    let currentIndex = 0;
    const alphabet = 'cryptouen';
    const target = document.getElementById('randomStr');
    function animateString(target) {
        let timer = setInterval(function () {
            let randomString = '';
            for (let i = 0; i < targetString.length; i++) {
                if (i < currentIndex) {
                    randomString += targetString[i];
                } else {
                    randomString += alphabet[Math.floor(Math.random() * alphabet.length)];
                }
            }
            target.textContent = randomString;
            if (randomString[currentIndex] === targetString[currentIndex]) {
                currentIndex++;
            }
            if (randomString === targetString) {
                clearInterval(timer);
            }
        }, 60);
    }
    animateString(target);
});
// --------------------------------------------------------------- //

function displayInMenu(id) {
    document.getElementById(id + "InMenu").style.display = "flex";
}

function hideInMenu(id) {
    document.getElementById(id).style.display = "none";
}