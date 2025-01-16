// Sélection des éléments
const navbar = document.querySelector('.navbar');
const themeToggle = document.getElementById('themeToggle');
let darkModeActive = false;

// Gère le changement de la navbar au scroll
window.addEventListener('scroll', () => {
    if(window.scrollY > 50) {
        navbar.classList.add('scrolled');
    } else {
        navbar.classList.remove('scrolled');
    }
});

// Toggle du mode sombre
themeToggle.addEventListener('click', () => {
    document.body.classList.toggle('dark-mode');
    darkModeActive = !darkModeActive;
    themeToggle.innerHTML = darkModeActive ? 'Light Mode' : 'Dark Mode';
});