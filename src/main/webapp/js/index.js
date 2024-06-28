if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
	document.documentElement.setAttribute("theme", "dark");
	localStorage.setItem("theme", "dark");
} else {
	document.documentElement.setAttribute("theme", "light");
	localStorage.setItem("theme", "light");
}

document.addEventListener('DOMContentLoaded', function() {
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
});

function displayInMenu(id) {
	document.getElementById(id + "InMenu").style.display = "flex";
}

function hideInMenu(id) {
	document.getElementById(id).style.display = "none";
}