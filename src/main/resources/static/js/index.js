function activateTab(tab) {
    // Retirer la classe "active" de tous les onglets
    var allTabs = document.querySelectorAll('.tab');
    allTabs.forEach(function (t) {
        t.classList.remove('active');
    });

    // Ajouter la classe "active" à l'onglet actuel
    tab.classList.add('active');
}