document.addEventListener('DOMContentLoaded', function() {
	const targetString = 'cryptocurrency';
	let currentIndex = 0;
	const alphabet = 'cryptouen';
	const target = document.getElementById('randomStr');
	function animateString(target) {
		let timer = setInterval(function() {
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