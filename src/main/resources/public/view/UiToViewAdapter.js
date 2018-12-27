const queryGamesWithSelectedMechanics = function () {
    var allInputs = document.querySelectorAll('input');
    var selectedMechanics = [].filter.call(allInputs, function (input) {
        return input.checked;
    }).map(function (checkedInput) {
        // pull the mechanic text value from the label
        return document.querySelector(`label[for="${checkedInput.id}"]`)
            .textContent;
    });

    updateGamesView(selectedMechanics);
};

const queryForTop10Games = () => {
    updateGamesView('ALL');
}

const queryGamesForInputName = function () {
    updateGamesView(document.querySelector('input#name-search').value);
}

const saveGameLocally = function (gameName) {
    saveGame(gameName);
    updateSavedGamesView();
}

const clearGamesSavedLocally = function () {
    clearSaves();
    updateSavedGamesView();
}

const clearMechanicsView = () => {
    updateMechanicsView([])
}