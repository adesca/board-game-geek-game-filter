
const initSelf = () => {
    updateSavedGamesView();
}

const saveGame = (gameName) => {
    localStorage.setItem(gameName, JSON.stringify(getInfoForGameInView(gameName)));

};

const clearSaves = () => {
    localStorage.clear();
};

const updateSavedGamesView = () => {
    const containerDiv = document.querySelector('#saved-games ul');
    removeChildrenElements(containerDiv);

    Object.keys(localStorage).forEach(key => {
        try {
            const potentialGame = JSON.parse(localStorage.getItem(key));
            if (potentialGame.name) {
                createGameElement(potentialGame).then(gameEl => {
                    containerDiv.appendChild(gameEl);
                });
            }
        } catch (e) {
            console.error("Error attempting to parse ", localStorage.getItem(key),
                "\nRetrieved from local storage with key ", key);
            console.error(e);
        }

    })
};

initSelf();