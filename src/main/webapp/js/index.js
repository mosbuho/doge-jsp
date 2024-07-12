document.addEventListener('DOMContentLoaded', function() {
	let theme = localStorage.getItem('theme');
	if (!theme) {
		if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches) {
			theme = 'dark';
		} else {
			theme = 'light';
		}
	}
	document.documentElement.setAttribute('theme', theme);

	document.getElementById('toggle').addEventListener('click', () => {
		theme = localStorage.getItem('theme');
		if (theme === 'dark') {
			localStorage.setItem('theme', 'light');
		} else {
			localStorage.setItem('theme', 'dark');
		}
		document.documentElement.setAttribute('theme', localStorage.getItem('theme'));
	}, false);
});

function displayInMenu(id) {
	document.getElementById(id + 'InMenu').style.display = 'flex';
}

function hideInMenu(id) {
	document.getElementById(id).style.display = 'none';
}